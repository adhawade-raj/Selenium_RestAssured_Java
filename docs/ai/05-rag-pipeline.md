# RAG Pipeline - Production Implementation

## Overview

A **RAG Pipeline** is a complete system that takes documents, processes them, indexes them, retrieves relevant ones, and generates answers. This document covers building production-ready RAG systems.

## RAG Pipeline Architecture

```
┌─────────────────────────────────────────────────────────────────┐
│                    RAG PIPELINE                                  │
├─────────────────────────────────────────────────────────────────┤
│                                                                   │
│  ┌─────────────────┐       ┌──────────────────┐                 │
│  │  1. INGESTION   │──────>│  2. PROCESSING   │                 │
│  │  - Upload docs  │       │  - Split text    │                 │
│  │  - Extract text │       │  - Clean content │                 │
│  └─────────────────┘       │  - Extract meta  │                 │
│                            └────────┬─────────┘                  │
│                                     │                            │
│                                     ↓                            │
│                            ┌──────────────────┐                  │
│                            │  3. EMBEDDING    │                  │
│                            │  - Tokenize      │                  │
│                            │  - Vector encode │                  │
│                            └────────┬─────────┘                  │
│                                     │                            │
│                                     ↓                            │
│                            ┌──────────────────┐                  │
│                            │  4. STORAGE      │                  │
│                            │  - Vector DB     │                  │
│                            │  - Metadata      │                  │
│                            │  - Full text     │                  │
│                            └────────┬─────────┘                  │
│                                     │                            │
│  ┌─────────────────┐       ┌────────▼─────────┐                 │
│  │  8. RESPONSE    │<──────│  5. RETRIEVAL    │                 │
│  │  - Format       │       │  - Semantic      │                 │
│  │  - Citations    │       │  - Reranking     │                 │
│  │  - Metadata     │       │  - Filtering     │                 │
│  └──────┬──────────┘       └────────┬─────────┘                 │
│         ▲                           │                            │
│         │                           ↓                            │
│         │                  ┌──────────────────┐                  │
│         │                  │  6. RANKING      │                  │
│         │                  │  - Cross-encoder │                  │
│         │                  │  - BM25 score    │                  │
│         │                  └────────┬─────────┘                  │
│         │                           │                            │
│         │                           ↓                            │
│         │                  ┌──────────────────┐                  │
│         └──────────────────│  7. GENERATION   │                  │
│                            │  - LLM process   │                  │
│                            │  - Add context   │                  │
│                            └──────────────────┘                  │
│                                                                   │
└─────────────────────────────────────────────────────────────────┘
```

## 1. Document Ingestion

### Multiple Source Support

```python
from typing import List
from dataclasses import dataclass
from abc import ABC, abstractmethod

@dataclass
class Document:
    content: str
    metadata: dict
    source: str

class DocumentLoader(ABC):
    @abstractmethod
    def load(self) -> List[Document]:
        pass

class PDFLoader(DocumentLoader):
    def __init__(self, filepath: str):
        self.filepath = filepath
    
    def load(self) -> List[Document]:
        """Load PDF documents"""
        from PyPDF2 import PdfReader
        
        documents = []
        reader = PdfReader(self.filepath)
        
        for page_num, page in enumerate(reader.pages):
            text = page.extract_text()
            documents.append(Document(
                content=text,
                metadata={'page': page_num + 1},
                source=self.filepath
            ))
        
        return documents

class WebLoader(DocumentLoader):
    def __init__(self, urls: List[str]):
        self.urls = urls
    
    def load(self) -> List[Document]:
        """Load from web URLs"""
        import requests
        from bs4 import BeautifulSoup
        
        documents = []
        for url in self.urls:
            response = requests.get(url)
            soup = BeautifulSoup(response.content, 'html.parser')
            text = soup.get_text()
            
            documents.append(Document(
                content=text,
                metadata={'url': url},
                source=url
            ))
        
        return documents

class DatabaseLoader(DocumentLoader):
    def __init__(self, query: str, connection_string: str):
        self.query = query
        self.connection_string = connection_string
    
    def load(self) -> List[Document]:
        """Load from database"""
        import sqlite3
        
        documents = []
        conn = sqlite3.connect(self.connection_string)
        cursor = conn.cursor()
        cursor.execute(self.query)
        
        for row in cursor.fetchall():
            documents.append(Document(
                content=str(row),
                metadata={'row_id': row[0]},
                source='database'
            ))
        
        return documents

# Usage
loaders = [
    PDFLoader("guide.pdf"),
    WebLoader(["https://example.com"]),
    DatabaseLoader("SELECT * FROM articles", "db.sqlite")
]

all_documents = []
for loader in loaders:
    all_documents.extend(loader.load())
```

## 2. Document Processing

### Text Chunking Strategies

```python
from typing import List

class TextChunker:
    @staticmethod
    def fixed_size_chunking(
        text: str,
        chunk_size: int = 1000,
        overlap: int = 200
    ) -> List[str]:
        """Split text into fixed-size chunks with overlap"""
        chunks = []
        for i in range(0, len(text), chunk_size - overlap):
            chunk = text[i:i + chunk_size]
            chunks.append(chunk)
        return chunks
    
    @staticmethod
    def sentence_based_chunking(text: str, max_chunk_size: int = 500) -> List[str]:
        """Split on sentence boundaries"""
        import re
        
        sentences = re.split(r'[.!?]+', text)
        chunks = []
        current_chunk = ""
        
        for sentence in sentences:
            if len(current_chunk) + len(sentence) < max_chunk_size:
                current_chunk += sentence + ". "
            else:
                if current_chunk:
                    chunks.append(current_chunk)
                current_chunk = sentence + ". "
        
        if current_chunk:
            chunks.append(current_chunk)
        
        return chunks
    
    @staticmethod
    def hierarchical_chunking(text: str, preserve_structure: bool = True):
        """Preserve document structure (sections, paragraphs)"""
        chunks = []
        sections = text.split('\n\n')  # Split on paragraph
        
        for section in sections:
            if len(section) > 1000:
                # Recursively chunk large sections
                sub_chunks = TextChunker.sentence_based_chunking(section)
                chunks.extend(sub_chunks)
            else:
                chunks.append(section)
        
        return chunks

# Usage
text = "Long document text..."
chunks = TextChunker.sentence_based_chunking(text, max_chunk_size=500)
print(f"Created {len(chunks)} chunks")
```

## 3. Embedding & Storage

### Complete Embedding Pipeline

```python
from typing import List, Dict
import numpy as np

class EmbeddingPipeline:
    def __init__(self, embedding_model_name: str = "all-MiniLM-L6-v2"):
        from sentence_transformers import SentenceTransformer
        self.model = SentenceTransformer(embedding_model_name)
    
    def embed_texts(self, texts: List[str]) -> np.ndarray:
        """Generate embeddings for texts"""
        embeddings = self.model.encode(texts, show_progress_bar=True)
        return embeddings
    
    def embed_text_with_metadata(self, docs: List[Document]):
        """Embed documents while preserving metadata"""
        embeddings = self.embed_texts([doc.content for doc in docs])
        
        return [
            {
                'id': f"doc_{i}",
                'text': doc.content,
                'embedding': embeddings[i],
                'metadata': doc.metadata,
                'source': doc.source
            }
            for i, doc in enumerate(docs)
        ]

class VectorStore:
    def __init__(self, embedding_dim: int = 384):
        self.embeddings: Dict[str, np.ndarray] = {}
        self.metadata: Dict[str, Dict] = {}
        self.texts: Dict[str, str] = {}
    
    def add_documents(self, docs_with_embeddings: List[Dict]):
        """Store embeddings and metadata"""
        for doc in docs_with_embeddings:
            self.embeddings[doc['id']] = doc['embedding']
            self.metadata[doc['id']] = doc['metadata']
            self.texts[doc['id']] = doc['text']
    
    def search(self, query_embedding: np.ndarray, top_k: int = 5) -> List[Dict]:
        """Retrieve top K similar documents"""
        from sklearn.metrics.pairwise import cosine_similarity
        
        scores = {}
        for doc_id, embedding in self.embeddings.items():
            similarity = cosine_similarity(
                [query_embedding],
                [embedding]
            )[0][0]
            scores[doc_id] = similarity
        
        # Sort and return top K
        top_docs = sorted(
            scores.items(),
            key=lambda x: x[1],
            reverse=True
        )[:top_k]
        
        return [
            {
                'id': doc_id,
                'score': score,
                'text': self.texts[doc_id],
                'metadata': self.metadata[doc_id]
            }
            for doc_id, score in top_docs
        ]

# Usage
pipeline = EmbeddingPipeline()
embeddings = pipeline.embed_text_with_metadata(documents)

store = VectorStore()
store.add_documents(embeddings)
```

## 4. Retrieval & Ranking

### Advanced Retrieval with Reranking

```python
class AdvancedRetriever:
    def __init__(self, vector_store: VectorStore):
        self.vector_store = vector_store
        self.reranker = None
    
    def retrieve_with_reranking(
        self,
        query_embedding: np.ndarray,
        query_text: str,
        initial_k: int = 10,
        final_k: int = 3
    ) -> List[Dict]:
        """
        1. Retrieve more candidates with vector search
        2. Rerank using cross-encoder
        3. Return top K
        """
        
        # Step 1: Vector retrieval (get more candidates)
        candidates = self.vector_store.search(query_embedding, top_k=initial_k)
        
        # Step 2: Reranking with cross-encoder
        from sentence_transformers import CrossEncoder
        
        reranker = CrossEncoder('cross-encoder/ms-marco-MiniLM-L-12-v2')
        
        # Prepare pairs for reranking
        pairs = [
            (query_text, doc['text'])
            for doc in candidates
        ]
        
        # Get reranking scores
        reranking_scores = reranker.predict(pairs)
        
        # Step 3: Sort by reranking scores
        ranked = sorted(
            zip(candidates, reranking_scores),
            key=lambda x: x[1],
            reverse=True
        )[:final_k]
        
        return [doc for doc, score in ranked]

# Filtering strategies
class FilteredRetriever:
    def __init__(self, vector_store: VectorStore):
        self.vector_store = vector_store
    
    def retrieve_with_filters(
        self,
        query_embedding: np.ndarray,
        filters: Dict,
        top_k: int = 5
    ) -> List[Dict]:
        """Retrieve with metadata filtering"""
        
        # Get all results
        all_results = self.vector_store.search(query_embedding, top_k=1000)
        
        # Apply filters
        filtered = []
        for doc in all_results:
            # Check if metadata matches filters
            if self._matches_filters(doc['metadata'], filters):
                filtered.append(doc)
                if len(filtered) >= top_k:
                    break
        
        return filtered
    
    @staticmethod
    def _matches_filters(metadata: Dict, filters: Dict) -> bool:
        """Check if metadata matches all filters"""
        for key, value in filters.items():
            if key not in metadata or metadata[key] != value:
                return False
        return True

# Usage
retriever = AdvancedRetriever(store)
results = retriever.retrieve_with_reranking(
    query_embedding,
    query_text,
    initial_k=10,
    final_k=3
)
```

## 5. Generation with Context

### LLM Integration with Retrieved Context

```python
from openai import OpenAI

class RAGGenerator:
    def __init__(self, retriever):
        self.retriever = retriever
        self.llm = OpenAI()
    
    def generate_with_context(
        self,
        query: str,
        query_embedding: np.ndarray,
        top_k: int = 3,
        include_citations: bool = True
    ) -> Dict:
        """Generate response using retrieved context"""
        
        # Step 1: Retrieve relevant documents
        retrieved_docs = self.retriever.retrieve_with_reranking(
            query_embedding,
            query,
            final_k=top_k
        )
        
        # Step 2: Build context string
        context = self._build_context_string(retrieved_docs)
        
        # Step 3: Create prompt with context
        prompt = self._create_prompt(query, context)
        
        # Step 4: Get LLM response
        response = self.llm.chat.completions.create(
            model="gpt-4",
            messages=[
                {
                    "role": "system",
                    "content": "You are a helpful assistant. "
                              "Use the provided context to answer questions. "
                              "If you use information from the context, cite it."
                },
                {"role": "user", "content": prompt}
            ],
            temperature=0.7
        )
        
        answer = response.choices[0].message.content
        
        # Step 5: Return with metadata
        return {
            'answer': answer,
            'sources': [doc['metadata'] for doc in retrieved_docs],
            'documents': [doc['text'] for doc in retrieved_docs] if include_citations else [],
            'usage': {
                'prompt_tokens': response.usage.prompt_tokens,
                'completion_tokens': response.usage.completion_tokens
            }
        }
    
    @staticmethod
    def _build_context_string(documents: List[Dict]) -> str:
        """Build formatted context from retrieved documents"""
        context_parts = []
        for i, doc in enumerate(documents, 1):
            context_parts.append(
                f"[Source {i}] ({doc['metadata'].get('source', 'Unknown')})\n"
                f"{doc['text'][:500]}..."
            )
        return "\n\n".join(context_parts)
    
    @staticmethod
    def _create_prompt(query: str, context: str) -> str:
        """Create prompt with context and query"""
        return f"""
Answer the following question based on the provided context.
If the answer is not in the context, say "I don't have this information."

CONTEXT:
{context}

QUESTION:
{query}

ANSWER:
"""
```

## Complete RAG Pipeline Example

```python
class CompleteRAGPipeline:
    def __init__(self):
        self.loaders = []
        self.chunker = TextChunker()
        self.embedding_pipeline = EmbeddingPipeline()
        self.vector_store = VectorStore()
        self.retriever = AdvancedRetriever(self.vector_store)
        self.generator = RAGGenerator(self.retriever)
    
    def ingest_documents(self, loaders: List[DocumentLoader]):
        """Ingest and process documents"""
        print("1. Ingesting documents...")
        all_docs = []
        for loader in loaders:
            all_docs.extend(loader.load())
        
        print(f"2. Processing {len(all_docs)} documents...")
        chunks = []
        for doc in all_docs:
            doc_chunks = self.chunker.sentence_based_chunking(doc.content)
            for chunk in doc_chunks:
                chunks.append(Document(
                    content=chunk,
                    metadata=doc.metadata,
                    source=doc.source
                ))
        
        print(f"3. Creating embeddings for {len(chunks)} chunks...")
        embeddings = self.embedding_pipeline.embed_text_with_metadata(chunks)
        
        print("4. Storing in vector database...")
        self.vector_store.add_documents(embeddings)
        
        print("✓ Ingestion complete!")
    
    def query(self, query: str, top_k: int = 3) -> Dict:
        """Query the RAG pipeline"""
        print(f"Processing query: {query}")
        
        # Embed query
        query_embedding = self.embedding_pipeline.model.encode([query])[0]
        
        # Generate response
        result = self.generator.generate_with_context(
            query,
            query_embedding,
            top_k=top_k
        )
        
        return result

# Usage
rag_pipeline = CompleteRAGPipeline()

# Ingest documents
loaders = [
    PDFLoader("company_handbook.pdf"),
    WebLoader(["https://docs.example.com"])
]
rag_pipeline.ingest_documents(loaders)

# Query
result = rag_pipeline.query("What is our company's vacation policy?")
print(f"Answer: {result['answer']}")
print(f"Sources: {result['sources']}")
```

## Monitoring & Evaluation

### Pipeline Health Checks

```python
class RAGPipelineMonitor:
    def __init__(self, pipeline: CompleteRAGPipeline):
        self.pipeline = pipeline
        self.metrics = {
            'retrieval_quality': [],
            'response_time': [],
            'token_usage': []
        }
    
    def evaluate_retrieval(self, test_queries: List[Dict]):
        """Evaluate retrieval quality"""
        from sklearn.metrics import precision_score, recall_score
        
        for test_case in test_queries:
            query = test_case['query']
            expected_sources = set(test_case['expected_sources'])
            
            result = self.pipeline.query(query)
            retrieved_sources = set(
                [doc['source'] for doc in result['sources']]
            )
            
            precision = len(expected_sources & retrieved_sources) / len(retrieved_sources)
            recall = len(expected_sources & retrieved_sources) / len(expected_sources)
            
            self.metrics['retrieval_quality'].append({
                'query': query,
                'precision': precision,
                'recall': recall
            })
        
        return self.metrics['retrieval_quality']
```

## Next Steps

- Learn about [MCP Protocol](./06-mcp.md) for integration
- Explore [LangChain](./07-langchain.md) for production frameworks
- Study [LangGraph](./08-langgraph.md) for complex workflows

---

*Reference: Production RAG Pipeline Implementation*
