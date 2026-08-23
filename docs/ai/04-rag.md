# Retrieval-Augmented Generation (RAG)

## What is RAG?

**Retrieval-Augmented Generation (RAG)** is a technique that combines information retrieval with text generation to provide LLMs with relevant, up-to-date, and domain-specific information.

### The Problem RAG Solves

```
Without RAG:
User: "What's the latest news about AI?"
LLM: "I don't have access to real-time information. 
     My knowledge was last updated in April 2024."

With RAG:
User: "What's the latest news about AI?"
System: (retrieves latest articles from news APIs)
LLM: "According to recent articles from August 2026..."
```

## Why RAG?

### LLM Limitations Addressed

| Limitation | RAG Solution |
|-----------|--------------|
| **Stale Knowledge** | Retrieves latest information |
| **Hallucination** | Grounded in actual documents |
| **Domain-Specific** | Can access proprietary data |
| **Explainability** | Shows source documents |
| **Cost** | Cheaper than fine-tuning |

## RAG Architecture

### High-Level Flow

```
┌─────────────────────┐
│   User Query        │
│  "What is machine   │
│   learning?"        │
└──────────┬──────────┘
           │
           ↓
┌──────────────────────────┐
│  Query Processing        │
│  - Normalize            │
│  - Extract keywords     │
└──────────┬───────────────┘
           │
           ↓
┌──────────────────────────┐
│  RETRIEVAL PHASE         │
│  - Search knowledge base │
│  - Find relevant docs    │
│  - Rank results          │
└──────────┬───────────────┘
           │
           ↓
┌──────────────────────────┐
│  Retrieved Context       │
│  ┌────────────────────┐  │
│  │ Doc 1: ML basics   │  │
│  │ Doc 2: ML types    │  │
│  │ Doc 3: ML apps     │  │
│  └────────────────────┘  │
└──────────┬───────────────┘
           │
           ↓
┌──────────────────────────┐
│  GENERATION PHASE        │
│  Combine query + context │
│  into LLM prompt         │
└──────────┬───────────────┘
           │
           ↓
┌──────────────────────────┐
│  LLM Response            │
│  "Machine learning is    │
│   a subset of AI that... │
│   (based on Doc 2)"       │
└──────────────────────────┘
```

## Components of RAG

### 1. **Document Collection/Knowledge Base**
Documents to retrieve from:
- PDF files
- Websites
- Database records
- Code repositories
- Internal documents

### 2. **Embedding Model**
Converts text to numerical vectors.

```python
# Text to Embedding
text = "Machine learning is a type of AI"
embedding = embedding_model.encode(text)
# Result: [0.123, -0.456, 0.789, ...]
```

### 3. **Vector Database**
Stores embeddings for efficient retrieval.

```
Document 1: "Python is a programming language"
Embedding:  [0.1, 0.2, 0.3, ...]
            ↓ (stored in)
Vector Database (e.g., Pinecone, Weaviate, Milvus)
```

### 4. **Retrieval Algorithm**
Finds similar documents.

```python
Query: "What's Python?"
Query Embedding: [0.12, 0.19, 0.31, ...]

Similarity Search:
Doc 1 similarity: 0.95 ✓ (Most similar)
Doc 2 similarity: 0.45
Doc 3 similarity: 0.32
```

### 5. **LLM**
Generates response using retrieved context.

## RAG Workflow Example

### Document Processing

```python
from langchain.document_loaders import PDFLoader
from langchain.text_splitter import RecursiveCharacterTextSplitter
from langchain.embeddings.openai import OpenAIEmbeddings
from langchain.vectorstores import FAISS

# Step 1: Load documents
loader = PDFLoader("machine_learning_guide.pdf")
documents = loader.load()

# Step 2: Split into chunks
text_splitter = RecursiveCharacterTextSplitter(
    chunk_size=1000,
    chunk_overlap=200
)
chunks = text_splitter.split_documents(documents)

# Step 3: Create embeddings
embeddings = OpenAIEmbeddings()

# Step 4: Store in vector database
vector_store = FAISS.from_documents(chunks, embeddings)
```

### Query Processing

```python
from langchain.chains import RetrievalQA
from langchain.llms import OpenAI

# Create RAG chain
llm = OpenAI()
qa_chain = RetrievalQA.from_chain_type(
    llm=llm,
    chain_type="stuff",
    retriever=vector_store.as_retriever()
)

# Process query
query = "What is machine learning?"
response = qa_chain.run(query)
print(response)
```

## RAG Implementation Example

### Complete Example: Company Knowledge Base

```python
from typing import List
from dataclasses import dataclass
import json

@dataclass
class Document:
    title: str
    content: str
    source: str

class CompanyKnowledgeRAG:
    def __init__(self, documents: List[Document]):
        """Initialize RAG system with company documents"""
        self.documents = documents
        self.knowledge_base = self._build_knowledge_base()
    
    def _build_knowledge_base(self) -> dict:
        """Build searchable knowledge base"""
        kb = {}
        for doc in self.documents:
            # Create searchable index
            keywords = self._extract_keywords(doc.content)
            kb[doc.title] = {
                'content': doc.content,
                'source': doc.source,
                'keywords': keywords
            }
        return kb
    
    def _extract_keywords(self, text: str) -> List[str]:
        """Extract key terms from text"""
        # Simplified keyword extraction
        words = text.lower().split()
        return [w for w in words if len(w) > 4]
    
    def retrieve_relevant_docs(self, query: str, top_k: int = 3) -> List[str]:
        """Retrieve top K relevant documents"""
        query_keywords = set(self._extract_keywords(query))
        
        scores = {}
        for title, doc_info in self.knowledge_base.items():
            # Calculate relevance score
            doc_keywords = set(doc_info['keywords'])
            overlap = len(query_keywords & doc_keywords)
            scores[title] = overlap
        
        # Sort and return top K
        top_docs = sorted(scores.items(), key=lambda x: x[1], reverse=True)[:top_k]
        return [title for title, score in top_docs if score > 0]
    
    def generate_answer(self, query: str, llm) -> str:
        """Generate answer using LLM + retrieved context"""
        
        # Step 1: Retrieve relevant documents
        relevant_docs = self.retrieve_relevant_docs(query)
        
        # Step 2: Build context from retrieved docs
        context = self._build_context(relevant_docs)
        
        # Step 3: Create prompt with context
        prompt = f"""
        Context from company knowledge base:
        {context}
        
        Question: {query}
        
        Answer based on the above context:
        """
        
        # Step 4: Get LLM response
        response = llm.chat.completions.create(
            model="gpt-4",
            messages=[{"role": "user", "content": prompt}]
        )
        
        return response.choices[0].message.content
    
    def _build_context(self, doc_titles: List[str]) -> str:
        """Build context string from selected documents"""
        context_parts = []
        for title in doc_titles:
            doc = self.knowledge_base[title]
            context_parts.append(f"[{title}]\n{doc['content'][:500]}...")
        
        return "\n\n".join(context_parts)

# Usage Example
documents = [
    Document(
        title="Python Guide",
        content="Python is a versatile programming language. "
                "It's used for web development, data science, AI, and more.",
        source="docs/python.md"
    ),
    Document(
        title="AI Basics",
        content="AI (Artificial Intelligence) is the simulation of human intelligence. "
                "Key areas include machine learning, deep learning, and NLP.",
        source="docs/ai.md"
    ),
    Document(
        title="Machine Learning",
        content="Machine learning enables computers to learn from data. "
                "Types include supervised, unsupervised, and reinforcement learning.",
        source="docs/ml.md"
    )
]

rag = CompanyKnowledgeRAG(documents)

# Without RAG: "I don't know about your company's Python standards"
# With RAG: Uses retrieved company documents
# query = "What are the company's guidelines for Python?"
# answer = rag.generate_answer(query, llm_client)
```

## Retrieval Techniques

### 1. **Keyword Search (BM25)**
Fast but less accurate.

```python
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity

vectorizer = TfidfVectorizer()
doc_vectors = vectorizer.fit_transform(documents)
query_vector = vectorizer.transform([query])
similarities = cosine_similarity(query_vector, doc_vectors)
```

### 2. **Vector Similarity (Semantic)**
More accurate, uses embeddings.

```python
from sentence_transformers import SentenceTransformer

model = SentenceTransformer('all-MiniLM-L6-v2')

doc_embeddings = model.encode(documents)
query_embedding = model.encode(query)

# Cosine similarity
similarities = cosine_similarity([query_embedding], doc_embeddings)[0]
top_indices = np.argsort(similarities)[-3:]  # Top 3
```

### 3. **Hybrid Search**
Combines keyword and semantic search.

```python
def hybrid_search(query: str, documents: List[str], alpha=0.5):
    """Combine BM25 and semantic search"""
    
    # BM25 scores
    bm25_scores = bm25_search(query, documents)
    
    # Semantic scores
    semantic_scores = semantic_search(query, documents)
    
    # Combine with weighted average
    combined_scores = alpha * bm25_scores + (1 - alpha) * semantic_scores
    
    return sorted(zip(documents, combined_scores), 
                  key=lambda x: x[1], reverse=True)
```

## Vector Databases

### Popular Options

| Database | Pros | Cons | Use Case |
|----------|------|------|----------|
| **Pinecone** | Fully managed, easy | Cloud only | Quick setup |
| **Weaviate** | Open source, flexible | Self-hosted complexity | Custom needs |
| **Milvus** | High performance | More configuration | Large scale |
| **FAISS** | Fast, simple | In-memory | Development |
| **Qdrant** | Modern, feature-rich | Newer | Production |

### Example: Using Pinecone

```python
import pinecone
from langchain.vectorstores import Pinecone

# Initialize Pinecone
pinecone.init(api_key="your-key", environment="us-west1-gcp")

# Create index
index_name = "rag-documents"
if index_name not in pinecone.list_indexes():
    pinecone.create_index(index_name, dimension=1536)

# Store embeddings
from langchain.embeddings.openai import OpenAIEmbeddings
embeddings = OpenAIEmbeddings()

vectorstore = Pinecone.from_documents(
    documents=documents,
    embedding=embeddings,
    index_name=index_name
)

# Query
retriever = vectorstore.as_retriever()
results = retriever.get_relevant_documents("machine learning")
```

## RAG Challenges & Solutions

| Challenge | Solution |
|-----------|----------|
| **Irrelevant Retrieval** | Use better embedding models, hybrid search |
| **Information Loss** | Use hierarchical chunking, preserve structure |
| **Hallucination** | Require citing sources, use smaller context |
| **Latency** | Cache results, use smaller models |
| **Cost** | Use cheaper models for retrieval |
| **Scalability** | Use managed vector databases |

## Best Practices

✅ **Do's**
- Use semantic embeddings (not keyword search alone)
- Test retrieval quality before LLM generation
- Maintain document freshness
- Track source documents
- Implement reranking for quality
- Monitor retrieval performance

❌ **Don'ts**
- Don't use raw documents (chunk them)
- Don't ignore context window limits
- Don't skip embedding quality
- Don't ignore relevance scores
- Don't use outdated embeddings

## Next Steps

- Learn [RAG Pipeline](./05-rag-pipeline.md) for production implementations
- Explore [LangChain](./07-langchain.md) for RAG frameworks
- Understand [MCP](./06-mcp.md) for protocol integration

---

*Reference: Retrieval-Augmented Generation Fundamentals*
