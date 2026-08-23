# LangChain - Building LLM Applications

## What is LangChain?

**LangChain** is a framework for developing applications powered by large language models. It provides:

- 🔗 Chains: Sequences of LLM calls
- 💾 Memory: Context management between calls
- 🛠️ Agents: Autonomous decision-making systems
- 📚 RAG: Retrieval-augmented generation
- 🔌 Integrations: Pre-built connectors to APIs and services

## Why LangChain?

```
Without LangChain:
├─ Manage API calls manually
├─ Handle prompt templating
├─ Store conversation memory
├─ Parse LLM outputs
├─ Chain multiple calls
└─ Error handling

With LangChain:
├─ Built-in LLM interfaces ✓
├─ Prompt templates ✓
├─ Memory management ✓
├─ Output parsing ✓
├─ Chain composition ✓
└─ Error handling ✓
```

## Installation

```bash
pip install langchain openai python-dotenv

# For advanced features
pip install langchain-openai langchain-community langchain-text-splitters
```

## Core Concepts

### 1. LLMs

```python
from langchain_openai import ChatOpenAI

# Initialize LLM
llm = ChatOpenAI(
    model="gpt-4",
    temperature=0.7,
    api_key="your-key"
)

# Simple query
response = llm.invoke("What is machine learning?")
print(response.content)
```

### 2. Prompt Templates

```python
from langchain.prompts import ChatPromptTemplate

# Create template
template = """
You are an expert {expertise}.
Answer the following question in {language}:

Question: {question}
"""

prompt = ChatPromptTemplate.from_template(template)

# Format prompt
formatted_prompt = prompt.format_messages(
    expertise="Python developer",
    language="simple English",
    question="What is a list in Python?"
)

# Use with LLM
response = llm.invoke(formatted_prompt)
```

### 3. Chains

A **chain** sequences multiple operations together.

```python
from langchain.chains import LLMChain

# Create chain: Prompt → LLM
chain = prompt | llm

# Execute
input_data = {
    "expertise": "Machine Learning Engineer",
    "language": "technical English",
    "question": "Explain gradient descent"
}
result = chain.invoke(input_data)
print(result.content)
```

### 4. Memory

```python
from langchain.memory import ConversationBufferMemory

# Create memory
memory = ConversationBufferMemory()

# Add to conversation
memory.save_context(
    {"input": "What is AI?"},
    {"output": "AI is artificial intelligence..."}
)
memory.save_context(
    {"input": "What's machine learning?"},
    {"output": "ML is a subset of AI..."}
)

# Retrieve memory
memory_variables = memory.load_memory_variables({})
print(memory_variables)
```

## Building Applications

### Example 1: Question Answering Bot

```python
from langchain_openai import ChatOpenAI
from langchain.prompts import ChatPromptTemplate
from langchain.memory import ConversationBufferMemory

class QABot:
    def __init__(self):
        self.llm = ChatOpenAI(model="gpt-4")
        self.memory = ConversationBufferMemory(
            memory_key="chat_history",
            return_messages=True
        )
        
        self.prompt = ChatPromptTemplate.from_messages([
            ("system", "You are a helpful AI assistant."),
            ("history", "{chat_history}"),
            ("human", "{question}")
        ])
        
        self.chain = self.prompt | self.llm
    
    def chat(self, question: str) -> str:
        """Process user question"""
        
        # Get chat history
        chat_history = self.memory.load_memory_variables({})
        
        # Generate response
        response = self.chain.invoke({
            "question": question,
            "chat_history": chat_history.get("chat_history", [])
        })
        
        # Save to memory
        self.memory.save_context(
            {"input": question},
            {"output": response.content}
        )
        
        return response.content

# Usage
bot = QABot()
print(bot.chat("What is Python?"))
print(bot.chat("What are its main features?"))  # Remembers context
```

### Example 2: RAG with LangChain

```python
from langchain.document_loaders import PDFLoader
from langchain.text_splitter import RecursiveCharacterTextSplitter
from langchain.embeddings.openai import OpenAIEmbeddings
from langchain.vectorstores import FAISS
from langchain.chains import RetrievalQA

class RAGSystem:
    def __init__(self, pdf_path: str):
        # Load and process documents
        loader = PDFLoader(pdf_path)
        documents = loader.load()
        
        # Split into chunks
        splitter = RecursiveCharacterTextSplitter(
            chunk_size=1000,
            chunk_overlap=200
        )
        chunks = splitter.split_documents(documents)
        
        # Create embeddings and vector store
        embeddings = OpenAIEmbeddings()
        self.vector_store = FAISS.from_documents(chunks, embeddings)
        
        # Initialize LLM
        self.llm = ChatOpenAI(model="gpt-4")
    
    def query(self, question: str) -> str:
        """Query the RAG system"""
        
        # Create retrieval chain
        qa_chain = RetrievalQA.from_chain_type(
            llm=self.llm,
            chain_type="stuff",
            retriever=self.vector_store.as_retriever(search_kwargs={"k": 3})
        )
        
        # Get answer
        result = qa_chain.run(question)
        return result

# Usage
rag = RAGSystem("company_handbook.pdf")
answer = rag.query("What is our vacation policy?")
print(answer)
```

### Example 3: Multi-Step Agent

```python
from langchain.agents import initialize_agent, Tool
from langchain.agents import AgentType
from langchain.tools import tool

# Define tools
@tool
def get_weather(location: str) -> str:
    """Get weather for a location"""
    return f"Weather in {location}: Sunny, 72°F"

@tool
def search_web(query: str) -> str:
    """Search the web"""
    return f"Search results for '{query}': ..."

@tool
def calculate(expression: str) -> str:
    """Calculate expression"""
    try:
        result = eval(expression)
        return str(result)
    except:
        return "Invalid expression"

# Create tools list
tools = [get_weather, search_web, calculate]

# Initialize agent
agent = initialize_agent(
    tools=tools,
    llm=ChatOpenAI(model="gpt-4", temperature=0),
    agent=AgentType.ZERO_SHOT_REACT_DESCRIPTION,
    verbose=True
)

# Execute
response = agent.run("What's the weather in NYC? Calculate 10 + 5")
print(response)
```

## Advanced Patterns

### 1. Streaming

```python
from langchain_openai import ChatOpenAI

llm = ChatOpenAI(model="gpt-4", streaming=True)

# Stream response token by token
for chunk in llm.stream("Write a short poem about AI"):
    print(chunk.content, end="", flush=True)
```

### 2. Output Parsing

```python
from langchain.output_parsers import PydanticOutputParser
from pydantic import BaseModel, Field

class Review(BaseModel):
    rating: int = Field(1, 10, description="Rating 1-10")
    summary: str = Field(..., description="Review summary")

parser = PydanticOutputParser(pydantic_object=Review)

prompt = ChatPromptTemplate.from_template(
    "Review this product and provide JSON output.\n{format_instructions}\nProduct: {product}"
)

chain = prompt | llm | parser

result = chain.invoke({
    "product": "Smart Watch",
    "format_instructions": parser.get_format_instructions()
})

print(f"Rating: {result.rating}")
print(f"Summary: {result.summary}")
```

### 3. Sequential Chains

```python
from langchain.chains import SequentialChain

# Step 1: Generate outline
outline_template = """
Create an outline for an article about: {topic}
Outline:
"""
outline_prompt = ChatPromptTemplate.from_template(outline_template)
outline_chain = outline_prompt | llm

# Step 2: Write article
article_template = """
Write a detailed article based on this outline:
{outline}
Article:
"""
article_prompt = ChatPromptTemplate.from_template(article_template)
article_chain = article_prompt | llm

# Chain them together
combined_chain = SequentialChain(
    chains=[outline_chain, article_chain],
    input_variables=["topic"],
    verbose=True
)

result = combined_chain({"topic": "The Future of AI"})
```

### 4. Conditional Logic

```python
from langchain.chains.router import MultiPromptChain
from langchain.prompts import PromptTemplate

# Create specialized prompts
math_prompt = PromptTemplate(
    input_variables=["input"],
    template="You are a math expert. Solve: {input}"
)

science_prompt = PromptTemplate(
    input_variables=["input"],
    template="You are a science expert. Explain: {input}"
)

# Router chain
from langchain.chains.router.llm_router import LLMRouterChain, RouterOutputParser
from langchain.chains.router.multi_prompt import MultiPromptChain

destination_chains = {
    "math": math_prompt | llm,
    "science": science_prompt | llm
}

router_prompt = PromptTemplate(
    input_variables=["input"],
    template="""
    Classify as either 'math' or 'science':
    Input: {input}
    Classification:
    """
)

router_chain = LLMRouterChain.from_llm_and_prompts(
    llm=llm,
    prompt=router_prompt
)

# Execute based on classification
multi_chain = MultiPromptChain(
    router_chain=router_chain,
    destination_chains=destination_chains
)
```

## Common LangChain Patterns

### Pattern 1: LCEL (LangChain Expression Language)

Modern way to build chains:

```python
# Sequential pipeline
chain = prompt | llm | output_parser

# Branching
chain = (
    {"question": prompt}
    | llm
    | output_parser
)

# With conditions
from langchain.schema.runnable import RunnableBranch

chain = RunnableBranch(
    (lambda x: "math" in x.lower(), math_chain),
    (lambda x: "science" in x.lower(), science_chain),
    default_chain
)
```

### Pattern 2: Tool Integration

```python
from langchain_community.tools import DuckDuckGoSearchRun

# Use existing tool
search = DuckDuckGoSearchRun()

# In agent
tools = [
    Tool(
        name="web_search",
        func=search.run,
        description="Search the web"
    )
]
```

### Pattern 3: Custom Chains

```python
from langchain.schema.runnable import Runnable

class CustomChain(Runnable):
    def invoke(self, input):
        # Custom processing
        return self.process(input)
    
    def process(self, input):
        # Your logic here
        pass
```

## Debugging & Monitoring

```python
import logging

# Enable logging
logging.basicConfig(level=logging.DEBUG)

# With LangChain
from langchain.callbacks import StdOutCallbackHandler

chain = prompt | llm

# Add callbacks for debugging
response = chain.invoke(
    {"question": "What is AI?"},
    callbacks=[StdOutCallbackHandler()]
)
```

## Production Considerations

✅ **Error Handling**
```python
try:
    result = chain.invoke(input_data)
except Exception as e:
    logger.error(f"Chain execution failed: {e}")
    return default_response
```

✅ **Rate Limiting**
```python
from langchain.callbacks import get_openai_callback

with get_openai_callback() as cb:
    result = chain.invoke(input_data)
    print(f"Cost: ${cb.total_cost:.4f}")
    print(f"Tokens: {cb.total_tokens}")
```

✅ **Caching**
```python
from langchain.cache import InMemoryCache
import langchain

langchain.llm_cache = InMemoryCache()
# Identical requests return cached results
```

## LangChain vs Raw API

| Task | Raw API | LangChain |
|------|---------|-----------|
| Simple query | ✓ Easy | Simple |
| Multiple steps | ✗ Complex | ✓ Easy |
| Memory mgmt | ✗ Manual | ✓ Built-in |
| RAG | ✗ Lots of code | ✓ Few lines |
| Tool calling | ✗ Manual | ✓ Automatic |
| Testing | ✗ Hard | ✓ Easy |

## Next Steps

- Explore [LangGraph](./08-langgraph.md) for complex workflows
- Learn [RAG](./04-rag.md) techniques
- Study [AI Agents](./02-ai-agents.md) patterns

## Resources

- **Documentation**: https://python.langchain.com/
- **GitHub**: https://github.com/langchain-ai/langchain
- **Community**: LangChain Discord, GitHub Discussions

---

*Reference: LangChain Framework for LLM Applications*
