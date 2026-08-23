# Large Language Models (LLMs)

## What is an LLM?

A **Large Language Model (LLM)** is a type of artificial intelligence model trained on massive amounts of text data to understand and generate human language. LLMs use deep learning (specifically transformer architecture) to predict the next word in a sequence, enabling them to perform various language tasks.

### Key Characteristics

| Feature | Description |
|---------|-------------|
| **Size** | Billions to hundreds of billions of parameters |
| **Training Data** | Trained on diverse internet text, books, articles |
| **Architecture** | Transformer-based neural networks |
| **Capability** | Understanding context, answering questions, generating text |
| **Speed** | Real-time inference with token-based processing |

## How LLMs Work

### 1. **Tokenization**
Text is broken into smaller units called **tokens** (words, subwords, or characters).

```
Input: "Hello world"
Tokens: ["Hello", " ", "world"]
```

### 2. **Embedding**
Each token is converted into a numerical vector (embedding) that captures semantic meaning.

```python
# Token to embedding mapping
"cat" → [0.234, -0.156, 0.789, ...]
"dog" → [0.245, -0.148, 0.812, ...]
```

### 3. **Transformer Processing**
The model processes tokens through multiple layers of attention mechanisms to understand relationships between words.

```
Input: "The cat sat on the mat"
         ↓
    [Tokenize]
         ↓
    [Embedding Layer]
         ↓
    [Multiple Attention Heads]
         ↓
    [Feed-forward Networks]
         ↓
    [Output: Next token prediction]
```

### 4. **Generation**
The model generates one token at a time, using previous tokens as context.

## Popular LLMs

### Proprietary Models
- **GPT-4** (OpenAI) - Multimodal, most capable
- **GPT-3.5** (OpenAI) - Fast, cost-effective
- **Claude 3** (Anthropic) - Strong reasoning, safety-focused
- **Gemini** (Google) - Multimodal, scalable
- **Llama** (Meta) - Open-source, efficient

### Open Source
- **Mistral** - Efficient, good performance
- **Falcon** - Fast inference
- **CodeLlama** - Specialized for code generation

## Core Capabilities

### 1. **Text Generation**
```python
# Example: Writing an email
Prompt: "Write a professional email to decline a meeting"
Output: "Dear [Name],
         Thank you for the invitation...
         Unfortunately, I have a prior commitment..."
```

### 2. **Question Answering**
```python
# Example: Knowledge retrieval
Q: "What is the capital of France?"
A: "The capital of France is Paris."
```

### 3. **Text Classification**
```python
# Example: Sentiment analysis
Input: "This product is amazing!"
Output: "Positive (confidence: 0.95)"
```

### 4. **Summarization**
```python
# Example: Content summarization
Long text → Model → "The article discusses how AI is transforming healthcare..."
```

### 5. **Translation**
```python
# Example: Language translation
Input (EN): "Hello, how are you?"
Output (ES): "Hola, ¿cómo estás?"
```

### 6. **Code Generation**
```python
# Example: Generate Python function
Prompt: "Write a function to check if a number is prime"
Output: 
def is_prime(n):
    if n < 2:
        return False
    for i in range(2, int(n**0.5) + 1):
        if n % i == 0:
            return False
    return True
```

## Prompt Engineering

Effective prompts are crucial for getting better outputs from LLMs.

### Prompt Techniques

#### 1. **Zero-shot Prompting**
```python
Prompt: "Classify this sentiment: I love this movie!"
# Model classifies without examples
```

#### 2. **Few-shot Prompting**
```python
Prompt: 
"Classify the sentiment:
Example 1: 'Great product!' - Positive
Example 2: 'Terrible experience' - Negative
Classify: 'It's okay, nothing special'"

# Model learns from examples
```

#### 3. **Chain-of-Thought Prompting**
```python
Prompt: 
"Solve this step by step:
Q: If there are 5 apples and you eat 2, how many are left?
Think step by step:
1. Starting amount: 5 apples
2. Amount eaten: 2 apples
3. Remaining: 5 - 2 = 3 apples"
```

### Best Practices

- ✅ **Be specific**: Use clear, detailed instructions
- ✅ **Provide context**: Give relevant background information
- ✅ **Use examples**: Show desired output format
- ✅ **Iterate**: Refine prompts based on results
- ✅ **Test variations**: Try different approaches

## LLM Limitations

| Limitation | Description | Example |
|------------|-------------|---------|
| **Hallucination** | Generates false information confidently | LLM claims a fact that doesn't exist |
| **Context Window** | Limited memory of previous text | Can't remember text beyond ~4K-100K tokens |
| **Stale Knowledge** | Training data has cutoff date | May not know recent events |
| **Reasoning** | Weak on complex multi-step logic | Struggles with intricate math problems |
| **Bias** | Inherits biases from training data | May generate biased responses |

## API Usage Example

### Using OpenAI API with Python

```python
from openai import OpenAI

client = OpenAI(api_key="your-api-key")

# Simple completion
response = client.chat.completions.create(
    model="gpt-4",
    messages=[
        {
            "role": "system",
            "content": "You are a helpful assistant."
        },
        {
            "role": "user",
            "content": "What is machine learning?"
        }
    ],
    temperature=0.7,  # Creativity level (0-1)
    max_tokens=500    # Response length limit
)

print(response.choices[0].message.content)
```

### Using Anthropic Claude API

```python
from anthropic import Anthropic

client = Anthropic()

message = client.messages.create(
    model="claude-3-opus-20240229",
    max_tokens=1024,
    messages=[
        {
            "role": "user",
            "content": "Explain quantum computing in simple terms"
        }
    ]
)

print(message.content[0].text)
```

## Key Metrics

| Metric | Meaning |
|--------|---------|
| **Tokens** | Units of text (1 token ≈ 4 characters) |
| **Temperature** | Randomness of responses (0=deterministic, 1=creative) |
| **Top-p** | Cumulative probability of tokens to consider |
| **Frequency Penalty** | Discourages repetitive words |
| **Presence Penalty** | Discourages discussing same topics |

## Real-World Applications

1. **Customer Service** - AI chatbots handling inquiries
2. **Content Creation** - Automated article/email generation
3. **Code Assistance** - GitHub Copilot, code completion
4. **Data Analysis** - Processing and summarizing datasets
5. **Translation** - Multilingual communication
6. **Medical** - Assisting in diagnosis and documentation
7. **Legal** - Document review and contract analysis

## Next Steps

- Learn about [AI Agents](./02-ai-agents.md) to automate complex workflows
- Explore [LangChain](./07-langchain.md) for building LLM applications
- Understand [RAG](./04-rag.md) for knowledge-enhanced LLMs

---

*Reference: LLM Fundamentals*
