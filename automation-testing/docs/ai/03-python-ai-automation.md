# Python for AI Automation

## Why Python for AI?

Python has become the **de facto language** for AI and machine learning. Here's why:

### 1. **Rich Ecosystem**
```python
# AI/ML Libraries Available
- NumPy/Pandas: Data manipulation
- Scikit-learn: Machine learning
- TensorFlow/PyTorch: Deep learning
- OpenAI/Anthropic: LLM APIs
- LangChain: LLM application framework
- Requests: API interactions
```

### 2. **Easy to Learn & Read**
```python
# Python is readable like English
result = llm.ask("What is AI?")
for tool in available_tools:
    if tool.matches(user_query):
        tool.execute()

# Compared to Java verbosity:
# LLMResponse result = llmService.ask("What is AI?");
# for (Tool tool : availableTools) {
#     if (tool.matches(userQuery)) {
#         tool.execute();
#     }
# }
```

### 3. **Fast Prototyping**
```python
# Rapid development cycle
# Minutes to test AI concepts
import openai

response = openai.ChatCompletion.create(
    model="gpt-4",
    messages=[{"role": "user", "content": "Hello!"}]
)
print(response.choices[0].message.content)
```

### 4. **Community & Libraries**
- 100K+ AI/ML packages on PyPI
- Active communities (Stack Overflow, GitHub)
- Extensive documentation
- Pre-built frameworks and tools

### 5. **Industry Standard**
- Used at Google, Meta, OpenAI, Anthropic
- De facto language for data science
- Most LLM frameworks built in Python first

## Python for AI Automation - Key Areas

### A. API Integration

#### Calling LLM APIs

```python
# OpenAI API Integration
from openai import OpenAI
import os

client = OpenAI(api_key=os.getenv("OPENAI_API_KEY"))

def query_llm(prompt: str) -> str:
    """Query an LLM with a prompt"""
    response = client.chat.completions.create(
        model="gpt-4",
        messages=[
            {"role": "system", "content": "You are a helpful assistant"},
            {"role": "user", "content": prompt}
        ],
        temperature=0.7
    )
    return response.choices[0].message.content

# Usage
answer = query_llm("What is machine learning?")
print(answer)
```

#### Anthropic Claude Integration

```python
# Anthropic API Integration
from anthropic import Anthropic

client = Anthropic()

def query_claude(prompt: str) -> str:
    """Query Claude with a prompt"""
    message = client.messages.create(
        model="claude-3-opus-20240229",
        max_tokens=1024,
        messages=[
            {"role": "user", "content": prompt}
        ]
    )
    return message.content[0].text

# Usage
response = query_claude("Explain quantum computing")
print(response)
```

### B. Tool Creation & Execution

```python
# Define tools for agent use
from typing import Any
from dataclasses import dataclass

@dataclass
class Tool:
    name: str
    description: str
    execute: callable

# Tool 1: Calculator
def calculate(expression: str) -> str:
    """Execute mathematical expression"""
    try:
        result = eval(expression)
        return f"Result: {result}"
    except Exception as e:
        return f"Error: {e}"

calculator_tool = Tool(
    name="calculator",
    description="Perform mathematical calculations",
    execute=calculate
)

# Tool 2: Web Search (simulated)
def search_web(query: str) -> str:
    """Search for information on the web"""
    # In production: call actual search API
    return f"Search results for '{query}': ..."

search_tool = Tool(
    name="web_search",
    description="Search the internet for information",
    execute=search_web
)

# Usage
print(calculator_tool.execute("10 + 5 * 2"))  # Result: 20
```

### C. Building Agents

```python
# Simple Agent Implementation
class SimpleAgent:
    def __init__(self, llm_client, tools: list):
        self.llm = llm_client
        self.tools = {tool.name: tool for tool in tools}
        self.conversation_history = []
    
    def process(self, user_input: str) -> str:
        """Process user input and return response"""
        self.conversation_history.append({
            "role": "user",
            "content": user_input
        })
        
        # Get LLM response
        response = self.llm.chat.completions.create(
            model="gpt-4",
            messages=self.conversation_history
        )
        
        assistant_response = response.choices[0].message.content
        self.conversation_history.append({
            "role": "assistant",
            "content": assistant_response
        })
        
        return assistant_response
    
    def execute_tool(self, tool_name: str, input_data: str) -> str:
        """Execute a tool"""
        if tool_name in self.tools:
            return self.tools[tool_name].execute(input_data)
        return f"Tool '{tool_name}' not found"

# Usage
from openai import OpenAI
agent = SimpleAgent(OpenAI(), [calculator_tool, search_tool])
result = agent.process("What is 100 divided by 5?")
print(result)
```

### D. Data Processing & Analysis

```python
# Prepare data for AI analysis
import json
import pandas as pd
from typing import List

# Load and process data
def load_customer_data(filepath: str) -> pd.DataFrame:
    """Load customer data from CSV"""
    df = pd.read_csv(filepath)
    
    # Clean data
    df = df.dropna()  # Remove missing values
    df['created_at'] = pd.to_datetime(df['created_at'])
    
    return df

# Analyze data with LLM
def analyze_data_with_ai(data: pd.DataFrame, query: str) -> str:
    """Use LLM to analyze tabular data"""
    
    # Convert data to JSON for LLM context
    data_summary = {
        "total_rows": len(data),
        "columns": data.columns.tolist(),
        "sample": data.head(5).to_dict('records')
    }
    
    prompt = f"""
    Analyze this customer data:
    {json.dumps(data_summary, indent=2)}
    
    Query: {query}
    """
    
    # Query LLM
    client = OpenAI()
    response = client.chat.completions.create(
        model="gpt-4",
        messages=[{"role": "user", "content": prompt}]
    )
    
    return response.choices[0].message.content

# Usage
# df = load_customer_data('customers.csv')
# analysis = analyze_data_with_ai(df, "What's the average customer lifetime value?")
# print(analysis)
```

### E. Async & Parallel Processing

```python
# Handle multiple requests concurrently
import asyncio
from typing import List

async def query_llm_async(prompt: str) -> str:
    """Query LLM asynchronously"""
    # Simulate async API call
    await asyncio.sleep(0.1)  # Simulated network delay
    return f"Response to: {prompt[:30]}..."

async def batch_process(prompts: List[str]) -> List[str]:
    """Process multiple prompts in parallel"""
    tasks = [query_llm_async(prompt) for prompt in prompts]
    results = await asyncio.gather(*tasks)
    return results

# Usage
async def main():
    prompts = [
        "What is AI?",
        "Explain machine learning",
        "Describe neural networks"
    ]
    results = await batch_process(prompts)
    for prompt, result in zip(prompts, results):
        print(f"{prompt} -> {result}")

# asyncio.run(main())
```

### F. Error Handling & Retry Logic

```python
# Robust error handling for AI operations
import time
from typing import Optional

def retry_with_backoff(
    func,
    max_retries: int = 3,
    backoff_factor: float = 2.0,
    initial_delay: float = 1.0
):
    """Retry function with exponential backoff"""
    
    for attempt in range(max_retries):
        try:
            return func()
        except Exception as e:
            if attempt == max_retries - 1:
                raise  # Final attempt, re-raise exception
            
            wait_time = initial_delay * (backoff_factor ** attempt)
            print(f"Attempt {attempt + 1} failed. Retrying in {wait_time}s...")
            time.sleep(wait_time)

# Usage
def call_llm():
    client = OpenAI()
    return client.chat.completions.create(
        model="gpt-4",
        messages=[{"role": "user", "content": "Hello"}]
    )

try:
    response = retry_with_backoff(call_llm)
    print(response.choices[0].message.content)
except Exception as e:
    print(f"Failed after retries: {e}")
```

### G. Logging & Monitoring

```python
# Monitor AI operations
import logging
from datetime import datetime

logging.basicConfig(
    level=logging.INFO,
    format='%(asctime)s - %(name)s - %(levelname)s - %(message)s'
)
logger = logging.getLogger(__name__)

class MonitoredLLMClient:
    def __init__(self):
        self.client = OpenAI()
        self.call_count = 0
        self.total_tokens = 0
    
    def query(self, prompt: str) -> str:
        """Query LLM with monitoring"""
        self.call_count += 1
        start_time = datetime.now()
        
        try:
            logger.info(f"LLM Query #{self.call_count}: {prompt[:50]}...")
            
            response = self.client.chat.completions.create(
                model="gpt-4",
                messages=[{"role": "user", "content": prompt}]
            )
            
            result = response.choices[0].message.content
            tokens = response.usage.total_tokens
            self.total_tokens += tokens
            
            elapsed = (datetime.now() - start_time).total_seconds()
            logger.info(
                f"Response received in {elapsed}s. "
                f"Tokens used: {tokens}. Total: {self.total_tokens}"
            )
            
            return result
        
        except Exception as e:
            logger.error(f"LLM Query failed: {e}", exc_info=True)
            raise

# Usage
llm = MonitoredLLMClient()
# response = llm.query("What is Python?")
```

## Python Project Structure for AI

```
project/
├── main.py                 # Entry point
├── config.py              # Configuration & settings
├── requirements.txt       # Python dependencies
├── .env                   # Environment variables (API keys)
├── agents/
│   ├── __init__.py
│   ├── base_agent.py      # Base agent class
│   └── tools.py           # Tool definitions
├── utils/
│   ├── __init__.py
│   ├── llm_client.py      # LLM API wrappers
│   ├── data_processor.py  # Data processing
│   └── logger.py          # Logging setup
└── tests/
    ├── test_agents.py
    ├── test_tools.py
    └── test_llm.py
```

## Essential Python Libraries for AI

```python
# requirements.txt
# API & LLM Clients
openai>=1.0.0          # OpenAI API
anthropic>=0.7.0       # Anthropic Claude API
python-dotenv>=1.0.0   # Environment variables

# Frameworks
langchain>=0.1.0       # LLM application framework
langgraph>=0.0.1       # Agent workflow graphs

# Data Processing
pandas>=2.0.0          # Data manipulation
numpy>=1.24.0          # Numerical computing

# Utilities
requests>=2.31.0       # HTTP client
pydantic>=2.0.0        # Data validation
aiohttp>=3.8.0         # Async HTTP

# Development
pytest>=7.4.0          # Testing
black>=23.0.0          # Code formatting
pylint>=2.17.0         # Code linting
mypy>=1.4.0            # Type checking
```

## Installation & Setup

```bash
# Create virtual environment
python -m venv venv

# Activate environment
# Windows
venv\Scripts\activate
# macOS/Linux
source venv/bin/activate

# Install dependencies
pip install -r requirements.txt

# Set environment variables
export OPENAI_API_KEY="your-key-here"
export ANTHROPIC_API_KEY="your-key-here"
```

## Performance Optimization Tips

1. **Use Async/Await** - For concurrent API calls
2. **Cache Results** - Avoid redundant LLM calls
3. **Batch Processing** - Process multiple items together
4. **Token Optimization** - Use shorter prompts
5. **Model Selection** - Use faster models when appropriate
6. **Connection Pooling** - Reuse HTTP connections

## Python vs Other Languages for AI

| Feature | Python | Java | Go |
|---------|--------|------|-----|
| **AI Libraries** | ⭐⭐⭐⭐⭐ | ⭐⭐ | ⭐⭐ |
| **Learning Curve** | Easy | Hard | Medium |
| **Performance** | Medium | Fast | Fast |
| **Community** | Huge | Large | Small |
| **Prototyping** | Fast | Slow | Medium |

## Next Steps

- Learn [LangChain](./07-langchain.md) for building production AI apps
- Explore [LangGraph](./08-langgraph.md) for complex workflows
- Build [AI Agents](./02-ai-agents.md) with Python

---

*Reference: Python for AI & Automation Best Practices*
