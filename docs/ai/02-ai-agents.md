# AI Agents

## What is an AI Agent?

An **AI Agent** is an autonomous system that perceives its environment, makes decisions, and takes actions to achieve specific goals. Unlike static LLMs that respond to individual prompts, agents can:

- 🔄 Run continuously or in loops
- 🧠 Make decisions based on observations
- 🛠️ Use external tools and APIs
- 💾 Maintain state and memory
- 🎯 Pursue long-term objectives

## Agent Architecture

### Core Components

```
┌─────────────────────────────────────────┐
│          PERCEPTION                      │
│  (Sensors, Input, Environment)          │
└────────────────┬────────────────────────┘
                 │
                 ↓
        ┌────────────────┐
        │  LLM/Reasoning │  ← Makes decisions
        │     Engine     │
        └────────────────┘
                 │
                 ↓
         ┌───────────────┐
         │  ACTION       │
         │  SELECTION    │  ← Choose tool/action
         └───────────────┘
                 │
                 ↓
         ┌───────────────┐
         │  TOOLS/APIs   │  ← Execute actions
         │  Execution    │
         └───────────────┘
                 │
                 ↓
┌─────────────────────────────────────────┐
│          ENVIRONMENT                     │
│  (Results, Feedback, State Change)      │
└─────────────────────────────────────────┘
```

## Agent Types

### 1. **Reactive Agents**
Responds immediately to inputs without planning.

```python
# Example: Customer service chatbot
User: "What's my order status?"
Agent:
  1. Extract order ID from message
  2. Query database
  3. Return status
# Direct input → action (no planning)
```

### 2. **Planning Agents**
Breaks down goals into steps and executes them.

```python
# Example: Travel booking agent
Goal: "Book a flight from NYC to LA"
Plan:
  1. Find available flights
  2. Check prices
  3. Verify passenger information
  4. Process payment
  5. Send confirmation
# Creates plan → executes steps
```

### 3. **Learning Agents**
Improves performance over time through feedback.

```python
# Example: Recommendation system
Initial: Random recommendations
Feedback: User clicks/preferences
Learning: Adjust recommendations
Result: Better personalized suggestions
```

## Agent Framework Pattern

### ReAct (Reasoning + Acting)

The most popular agent pattern combining reasoning and actions.

```
Step 1: THOUGHT
  "I need to find the current date to calculate age"

Step 2: ACTION
  Use calendar tool to get current date

Step 3: OBSERVATION
  "Today's date is 2026-08-23"

Step 4: THOUGHT
  "Now I can calculate: 2026 - 1990 = 36 years old"

Step 5: FINAL ANSWER
  "You are 36 years old"
```

## Tools and Functions

Agents interact with the world through tools (function calls).

### Common Agent Tools

```python
# Tool Example 1: Web Search
{
    "name": "web_search",
    "description": "Search the internet for information",
    "parameters": {
        "query": "string - search query"
    }
}

# Tool Example 2: Calculator
{
    "name": "calculator",
    "description": "Perform mathematical operations",
    "parameters": {
        "expression": "string - math expression"
    }
}

# Tool Example 3: Database Query
{
    "name": "query_database",
    "description": "Execute SQL queries",
    "parameters": {
        "sql": "string - SQL query"
    }
}
```

### Defining Tools in Python

```python
from typing import Any

def get_weather(location: str) -> str:
    """Get current weather for a location"""
    # API call to weather service
    return f"Weather in {location}: Sunny, 72°F"

def send_email(recipient: str, subject: str, body: str) -> str:
    """Send an email"""
    # Email sending logic
    return f"Email sent to {recipient}"

# Tools accessible to agent
TOOLS = {
    "get_weather": get_weather,
    "send_email": send_email
}
```

## Example: Customer Support Agent

### Scenario
Handle customer support tickets automatically.

### Agent Flow

```python
class CustomerSupportAgent:
    def __init__(self):
        self.llm = get_llm()
        self.tools = {
            "search_kb": self.search_knowledge_base,
            "query_db": self.query_customer_db,
            "escalate": self.escalate_to_human
        }
    
    def handle_ticket(self, ticket: str):
        """Process customer support ticket"""
        
        messages = [
            {
                "role": "user",
                "content": ticket
            }
        ]
        
        while True:
            # Step 1: LLM decides what to do
            response = self.llm.chat(messages)
            
            # Step 2: Check if action needed
            if "TOOL_CALL:" in response:
                tool_name, tool_input = parse_tool_call(response)
                
                # Step 3: Execute tool
                result = self.tools[tool_name](tool_input)
                
                # Step 4: Add observation to conversation
                messages.append({
                    "role": "assistant",
                    "content": response
                })
                messages.append({
                    "role": "user",
                    "content": f"Tool result: {result}"
                })
            else:
                # Step 5: Agent generated response
                return response
    
    def search_knowledge_base(self, query: str) -> str:
        """Search knowledge base for similar issues"""
        # Search implementation
        return "Found 3 articles: ..."
    
    def query_customer_db(self, customer_id: str) -> str:
        """Get customer information"""
        # Database query
        return "Customer: John, Plan: Premium, ..."
    
    def escalate_to_human(self, reason: str) -> str:
        """Escalate to human support"""
        return "Ticket escalated to support team"
```

### Execution Example

```
Ticket: "My subscription isn't working and I was charged twice"

Agent Thought: "This requires multiple pieces of information"
Agent Action: Use search_kb with "subscription issues"
Result: Found troubleshooting guide and billing info

Agent Thought: "Need to check customer's account"
Agent Action: Use query_db with "customer_id=12345"
Result: Customer: Premium, Last charge: $99.99, $99.99

Agent Thought: "Duplicate charge confirmed, refund needed"
Agent Action: Use escalate with "Duplicate charge on account"
Result: Escalated to billing team

Agent Response: "We've identified a duplicate charge and escalated 
to our billing team. You'll receive a refund within 2-3 business days."
```

## Agent Loop (Agentic Loop)

```python
def agent_loop(agent, user_input):
    """Main agent execution loop"""
    
    state = {
        "messages": [{"role": "user", "content": user_input}],
        "iterations": 0,
        "max_iterations": 10
    }
    
    while state["iterations"] < state["max_iterations"]:
        state["iterations"] += 1
        
        # 1. Get LLM response
        llm_response = agent.llm.chat(state["messages"])
        
        # 2. Parse for tool calls
        tool_calls = agent.parse_tool_calls(llm_response)
        
        # 3. If no tool calls, return response
        if not tool_calls:
            return llm_response
        
        # 4. Execute tools
        state["messages"].append({
            "role": "assistant",
            "content": llm_response
        })
        
        for tool_call in tool_calls:
            result = agent.execute_tool(tool_call)
            state["messages"].append({
                "role": "user",
                "content": f"Tool {tool_call.name} result: {result}"
            })
    
    return "Max iterations reached"
```

## Multi-Agent Systems

Multiple agents working together on complex tasks.

### Example: Content Creation Team

```
User Request: "Write and publish a blog post about AI"

┌─────────────────────┐
│  Researcher Agent   │  ← Finds latest AI information
└──────────┬──────────┘
           │
           ↓
┌─────────────────────┐
│   Writer Agent      │  ← Drafts the article
└──────────┬──────────┘
           │
           ↓
┌─────────────────────┐
│   Editor Agent      │  ← Reviews and improves
└──────────┬──────────┘
           │
           ↓
┌─────────────────────┐
│ Publisher Agent     │  ← Publishes to platform
└─────────────────────┘
```

## Challenges and Solutions

| Challenge | Solution |
|-----------|----------|
| **Hallucination** | Use RAG with verified data sources |
| **Tool Errors** | Implement error handling and retries |
| **Context Limit** | Summarize conversation history |
| **Cost** | Use function calling efficiently |
| **Speed** | Parallelize tool execution |

## Real-World Applications

1. **Customer Service** - Handling support tickets
2. **Data Analysis** - Analyzing datasets and generating reports
3. **Research** - Gathering and synthesizing information
4. **Automation** - Executing business workflows
5. **Coding** - Writing and debugging code
6. **Sales** - Lead qualification and outreach
7. **Healthcare** - Appointment scheduling and diagnosis assistance

## Best Practices

✅ **Do's**
- Define clear tool interfaces
- Implement proper error handling
- Monitor agent performance
- Use iterative development
- Test with edge cases
- Implement timeouts and max iterations

❌ **Don'ts**
- Overuse tools (simpler is better)
- Allow infinite loops (set iteration limits)
- Ignore security (validate tool inputs)
- Skip logging and monitoring
- Use untested external APIs
- Ignore rate limits

## Next Steps

- Explore [Python for AI Automation](./03-python-ai-automation.md)
- Learn about [LangChain](./07-langchain.md) for building agents
- Understand [LangGraph](./08-langgraph.md) for complex agent workflows

---

*Reference: AI Agents Architecture & Design Patterns*
