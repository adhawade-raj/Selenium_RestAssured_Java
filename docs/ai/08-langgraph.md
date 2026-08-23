# LangGraph - Building Stateful AI Workflows

## What is LangGraph?

**LangGraph** is a library for building stateful, multi-actor applications with LLMs. It extends LangChain by adding:

- 📊 State management between steps
- 🔀 Complex control flow (loops, branches)
- 🔄 Cyclical processes (agent loops)
- 🧠 Memory and context management
- 🎯 Goal-oriented workflows

## Why LangGraph?

### LangChain vs LangGraph

```
LangChain (Linear):
Input → Prompt → LLM → Output
        └─────────────┘ (One-way)

LangGraph (Cyclic):
Input → [State] → Logic → Decision → Actions
          ↑                     │
          └─────────────────────┘ (Loop back)
```

### Use Cases

- ✅ **Agents** with decision loops
- ✅ **Multi-step workflows** with branching
- ✅ **Iterative refinement** (revision loops)
- ✅ **Multi-turn conversations** with state
- ✅ **Complex orchestration** of multiple LLMs

## Installation

```bash
pip install langgraph langchain openai
```

## Core Concepts

### 1. State

State represents the current context/data flowing through the graph.

```python
from typing import TypedDict, List

class AgentState(TypedDict):
    """State for an agent workflow"""
    task: str
    plan: str
    actions: List[str]
    result: str
    iteration: int

# State flows through the graph
state = AgentState(
    task="Write a blog post about AI",
    plan="",
    actions=[],
    result="",
    iteration=0
)
```

### 2. Nodes

Nodes are functions that process the state.

```python
def planning_node(state: AgentState) -> AgentState:
    """Node that creates a plan"""
    plan = llm.invoke(f"Create a plan for: {state['task']}")
    state['plan'] = plan
    return state

def execution_node(state: AgentState) -> AgentState:
    """Node that executes the plan"""
    actions = plan_to_actions(state['plan'])
    state['actions'] = actions
    return state

def reflection_node(state: AgentState) -> AgentState:
    """Node that reflects on results"""
    result = llm.invoke(f"Reflect on: {state['actions']}")
    state['result'] = result
    return state
```

### 3. Edges

Edges connect nodes and define control flow.

```python
# Simple edge (always go to next node)
graph.add_edge("planning", "execution")

# Conditional edge (branch based on state)
def should_revise(state: AgentState) -> str:
    """Decide whether to revise or finish"""
    if "revise" in state['result'].lower():
        return "revision"  # Go back to planning
    else:
        return "finish"    # Done

graph.add_conditional_edges(
    "reflection",
    should_revise,
    {
        "revision": "planning",
        "finish": "end"
    }
)
```

## Building Your First Graph

### Simple Sequential Workflow

```python
from langgraph.graph import Graph
from langchain_openai import ChatOpenAI

# Initialize LLM
llm = ChatOpenAI(model="gpt-4")

# Define state
class WorkflowState(TypedDict):
    topic: str
    outline: str
    content: str

# Define nodes
def create_outline(state: WorkflowState) -> WorkflowState:
    """Create article outline"""
    outline = llm.invoke(
        f"Create an outline for: {state['topic']}"
    )
    state['outline'] = outline.content
    return state

def write_content(state: WorkflowState) -> WorkflowState:
    """Write article content"""
    content = llm.invoke(
        f"Write article based on outline:\n{state['outline']}"
    )
    state['content'] = content.content
    return state

# Build graph
graph = Graph()

# Add nodes
graph.add_node("outline", create_outline)
graph.add_node("content", write_content)

# Add edges
graph.add_edge("outline", "content")
graph.add_edge("content", "__end__")

# Set entry point
graph.set_entry_point("outline")

# Compile graph
workflow = graph.compile()

# Execute
result = workflow.invoke({
    "topic": "The Future of AI",
    "outline": "",
    "content": ""
})

print(result['content'])
```

## Agent with LangGraph

### Complete Agent Implementation

```python
from langgraph.graph import Graph, END
from langgraph.prebuilt import ToolNode
from typing import Optional

class WorkerAgent:
    def __init__(self):
        self.llm = ChatOpenAI(model="gpt-4")
        self.tools = self._init_tools()
    
    def _init_tools(self):
        """Initialize available tools"""
        from langchain.tools import tool
        
        @tool
        def calculator(expression: str) -> str:
            """Calculate expression"""
            return str(eval(expression))
        
        @tool
        def search(query: str) -> str:
            """Search for information"""
            return f"Search results for '{query}'"
        
        return {"calculator": calculator, "search": search}
    
    def build_graph(self):
        """Build the agent graph"""
        
        # Define state
        class AgentState(TypedDict):
            messages: List[dict]
            iteration: int
            max_iterations: int
        
        # Create graph
        graph = Graph()
        
        # Node 1: LLM decision making
        def llm_node(state: AgentState) -> AgentState:
            """LLM decides what to do"""
            
            # Build prompt
            prompt = f"""
            You are a helpful assistant with access to tools.
            
            Available tools:
            - calculator: Calculate math expressions
            - search: Search for information
            
            User messages:
            {state['messages']}
            
            Decide: Should you use a tool? If yes, call it.
            If no tool needed, provide the final answer.
            """
            
            # Get LLM response
            response = self.llm.invoke(prompt)
            
            # Update messages
            state['messages'].append({
                "role": "assistant",
                "content": response.content
            })
            
            state['iteration'] += 1
            
            return state
        
        # Node 2: Tool execution
        def tool_node(state: AgentState) -> AgentState:
            """Execute tools if LLM requested them"""
            
            last_message = state['messages'][-1]['content']
            
            # Extract tool call from LLM response
            if "TOOL:" in last_message:
                tool_name, tool_input = parse_tool_call(last_message)
                
                # Execute tool
                if tool_name in self.tools:
                    result = self.tools[tool_name].run(tool_input)
                    
                    # Add tool result
                    state['messages'].append({
                        "role": "tool",
                        "content": result
                    })
            
            return state
        
        # Decision function
        def should_continue(state: AgentState) -> str:
            """Decide whether to continue or end"""
            
            # Check if we've reached max iterations
            if state['iteration'] >= state['max_iterations']:
                return "end"
            
            # Check if last message is final answer
            last_message = state['messages'][-1]['content']
            if "FINAL ANSWER:" in last_message:
                return "end"
            
            # Continue if tool was used
            if "TOOL:" in last_message:
                return "tools"
            
            return "llm"
        
        # Add nodes
        graph.add_node("llm", llm_node)
        graph.add_node("tools", tool_node)
        
        # Add edges
        graph.add_edge("start", "llm")
        graph.add_conditional_edges(
            "llm",
            should_continue,
            {
                "llm": "llm",
                "tools": "tools",
                "end": END
            }
        )
        graph.add_edge("tools", "llm")
        
        return graph.compile()
    
    def run(self, user_input: str) -> str:
        """Run the agent"""
        
        # Build graph
        workflow = self.build_graph()
        
        # Initial state
        state = {
            "messages": [
                {"role": "user", "content": user_input}
            ],
            "iteration": 0,
            "max_iterations": 10
        }
        
        # Execute
        result = workflow.invoke(state)
        
        # Extract final answer
        for message in reversed(result['messages']):
            if message['role'] == 'assistant':
                return message['content']

# Usage
agent = WorkerAgent()
response = agent.run("What is 10 * 5 plus the number of planets?")
print(response)
```

## Conditional Workflows

### Routing Based on State

```python
from langgraph.graph import Graph

def router_example():
    """Route to different nodes based on state"""
    
    class ProcessState(TypedDict):
        user_input: str
        category: str
        result: str
    
    graph = Graph()
    
    # Categorize input
    def categorize(state: ProcessState) -> ProcessState:
        """Determine input category"""
        text = state['user_input'].lower()
        
        if 'math' in text or 'calculate' in text:
            state['category'] = 'math'
        elif 'weather' in text:
            state['category'] = 'weather'
        else:
            state['category'] = 'general'
        
        return state
    
    # Process based on category
    def process_math(state: ProcessState) -> ProcessState:
        result = llm.invoke(f"Solve this math problem: {state['user_input']}")
        state['result'] = result.content
        return state
    
    def process_weather(state: ProcessState) -> ProcessState:
        result = llm.invoke(f"Answer this weather question: {state['user_input']}")
        state['result'] = result.content
        return state
    
    def process_general(state: ProcessState) -> ProcessState:
        result = llm.invoke(state['user_input'])
        state['result'] = result.content
        return state
    
    # Build graph
    graph.add_node("categorize", categorize)
    graph.add_node("math", process_math)
    graph.add_node("weather", process_weather)
    graph.add_node("general", process_general)
    
    # Routing logic
    def route_based_on_category(state: ProcessState) -> str:
        return state['category']
    
    graph.add_edge("start", "categorize")
    graph.add_conditional_edges(
        "categorize",
        route_based_on_category,
        {
            "math": "math",
            "weather": "weather",
            "general": "general"
        }
    )
    
    graph.add_edge("math", END)
    graph.add_edge("weather", END)
    graph.add_edge("general", END)
    
    return graph.compile()

# Usage
workflow = router_example()
result = workflow.invoke({
    "user_input": "What is 10 + 5?",
    "category": "",
    "result": ""
})
print(result['result'])
```

## Iterative Refinement

### Loop Until Quality Threshold

```python
from langgraph.graph import Graph

class QualityRefinement:
    def __init__(self):
        self.llm = ChatOpenAI(model="gpt-4")
    
    def build_refinement_graph(self):
        """Build iterative refinement workflow"""
        
        class RefinementState(TypedDict):
            task: str
            attempt: str
            quality_score: int
            iteration: int
            final_output: str
        
        graph = Graph()
        
        # Generate content
        def generate(state: RefinementState) -> RefinementState:
            """Generate initial content"""
            prompt = f"Create: {state['task']}"
            response = self.llm.invoke(prompt)
            state['attempt'] = response.content
            return state
        
        # Evaluate quality
        def evaluate(state: RefinementState) -> RefinementState:
            """Score the attempt"""
            prompt = f"""
            Rate this on 1-10: {state['attempt']}
            Respond with just the number.
            """
            response = self.llm.invoke(prompt)
            
            try:
                score = int(response.content.strip())
                state['quality_score'] = score
            except:
                state['quality_score'] = 5
            
            return state
        
        # Refine if needed
        def refine(state: RefinementState) -> RefinementState:
            """Improve the attempt"""
            prompt = f"""
            Improve this (make it better):
            {state['attempt']}
            """
            response = self.llm.invoke(prompt)
            state['attempt'] = response.content
            state['iteration'] += 1
            return state
        
        # Add nodes
        graph.add_node("generate", generate)
        graph.add_node("evaluate", evaluate)
        graph.add_node("refine", refine)
        
        # Routing function
        def should_refine(state: RefinementState) -> str:
            """Decide: refine or finish?"""
            
            if state['quality_score'] >= 8:
                return "finish"  # Good enough
            
            if state['iteration'] >= 3:
                return "finish"  # Max iterations
            
            return "refine"  # Need improvement
        
        # Add edges
        graph.add_edge("start", "generate")
        graph.add_edge("generate", "evaluate")
        graph.add_conditional_edges(
            "evaluate",
            should_refine,
            {
                "refine": "refine",
                "finish": "end"
            }
        )
        graph.add_edge("refine", "evaluate")
        
        return graph.compile()
    
    def run(self, task: str) -> str:
        """Run refinement workflow"""
        workflow = self.build_refinement_graph()
        
        result = workflow.invoke({
            "task": task,
            "attempt": "",
            "quality_score": 0,
            "iteration": 0,
            "final_output": ""
        })
        
        return result['attempt']

# Usage
refinement = QualityRefinement()
final_content = refinement.run("Write a haiku about programming")
print(final_content)
```

## Best Practices

✅ **Do's**
- Keep nodes simple and focused
- Use conditional edges for complex logic
- Add iteration limits to prevent infinite loops
- Log state transitions for debugging
- Use type hints for state

❌ **Don'ts**
- Don't create nodes with side effects
- Don't make state too large
- Don't forget to set entry/exit points
- Don't skip error handling
- Don't create circular references without conditions

## Debugging

```python
# Enable verbose mode
workflow = graph.compile()
result = workflow.invoke(state, {"verbose": True})

# Add logging
import logging
logging.basicConfig(level=logging.DEBUG)

# Visualize graph
from langchain_core.runnables.graph import MermaidDrawer

graph_viz = workflow.get_graph().draw_mermaid()
print(graph_viz)
```

## LangGraph vs LangChain

| Feature | LangChain | LangGraph |
|---------|-----------|-----------|
| **Linear chains** | ✓ | ✓ |
| **State mgmt** | Limited | ✓ Excellent |
| **Loops** | Complex | ✓ Simple |
| **Agents** | ✓ | ✓ Better |
| **Routing** | ✓ | ✓ Better |
| **Cycles** | ✗ | ✓ |

## Real-World Example: Research Assistant

```python
class ResearchAssistant:
    """Multi-step research workflow"""
    
    def __init__(self):
        self.llm = ChatOpenAI(model="gpt-4")
    
    def build_graph(self):
        class ResearchState(TypedDict):
            topic: str
            initial_research: str
            contradictions_found: List[str]
            final_report: str
        
        graph = Graph()
        
        # Step 1: Initial research
        def research(state):
            response = self.llm.invoke(f"Research: {state['topic']}")
            state['initial_research'] = response.content
            return state
        
        # Step 2: Find contradictions
        def find_contradictions(state):
            prompt = f"""
            Review this research for contradictions:
            {state['initial_research']}
            """
            response = self.llm.invoke(prompt)
            contradictions = response.content.split('\n')
            state['contradictions_found'] = contradictions
            return state
        
        # Step 3: Verify and finalize
        def finalize(state):
            response = self.llm.invoke(
                f"Resolve and finalize: {state['initial_research']}"
            )
            state['final_report'] = response.content
            return state
        
        graph.add_node("research", research)
        graph.add_node("contradictions", find_contradictions)
        graph.add_node("finalize", finalize)
        
        graph.add_edge("start", "research")
        graph.add_edge("research", "contradictions")
        graph.add_edge("contradictions", "finalize")
        graph.add_edge("finalize", END)
        
        return graph.compile()
```

## Next Steps

- Master [LangChain](./07-langchain.md) basics first
- Build complex [AI Agents](./02-ai-agents.md)
- Integrate [RAG](./04-rag.md) for knowledge
- Study official [LangGraph docs](https://langchain-ai.github.io/langgraph/)

---

*Reference: LangGraph for Stateful AI Workflows*
