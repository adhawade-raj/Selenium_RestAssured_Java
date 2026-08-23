# Model Context Protocol (MCP)

## What is MCP?

**Model Context Protocol (MCP)** is a standardized protocol that enables secure communication between AI models and external data sources, tools, and services. It defines a common interface for:

- 🔌 Connecting models to tools
- 📊 Sharing context and data
- 🔐 Managing permissions and security
- 🔄 Standardizing interactions

## Why MCP?

### The Problem

Before MCP, each LLM framework implemented tool calling differently:

```
Without MCP:
┌────────┐     Custom format      ┌──────────┐
│ OpenAI │ ─────────────────────> │ Tool A   │
└────────┘     Different format   └──────────┘

┌────────┐     Another format     ┌──────────┐
│Anthropic│ ─────────────────────> │ Tool B   │
└────────┘                        └──────────┘

┌────────┐     Yet another format ┌──────────┐
│ Google │ ─────────────────────> │ Tool C   │
└────────┘                        └──────────┘

Result: Incompatible, hard to maintain, fragmented
```

### The Solution

```
With MCP:
┌────────┐     MCP Standard       ┌──────────┐
│ OpenAI │ ─────────────────────> │ Tool A   │
├────────┤     Format             ├──────────┤
│Anthropic│ ───────────────────> │ Tool B   │
├────────┤                        ├──────────┤
│ Google │ ─────────────────────> │ Tool C   │
└────────┘                        └──────────┘

Result: Unified, interoperable, maintainable
```

## MCP Architecture

### Core Components

```
┌─────────────────────────────────────────────┐
│            MCP Client (e.g., LLM)            │
│  (Requests tools, data, sends commands)    │
└────────────┬────────────────────────────────┘
             │
    MCP Protocol (JSON-RPC 2.0)
    Over stdio, HTTP, SSE, WebSocket
             │
┌────────────▼────────────────────────────────┐
│         MCP Server (Resource Provider)       │
│  - Tools                                     │
│  - Resources (databases, files)              │
│  - Prompts (templates)                       │
│  - Notifications                             │
└─────────────────────────────────────────────┘
```

## MCP Protocol Basics

### Message Format

MCP uses JSON-RPC 2.0 for communication:

```json
{
  "jsonrpc": "2.0",
  "id": 1,
  "method": "tools/list",
  "params": {}
}
```

### Request/Response Flow

```
1. Client → Server (Request)
   {
     "jsonrpc": "2.0",
     "id": 1,
     "method": "tools/call",
     "params": {
       "name": "get_weather",
       "arguments": {"location": "NYC"}
     }
   }

2. Server → Client (Response)
   {
     "jsonrpc": "2.0",
     "id": 1,
     "result": {
       "content": [
         {
           "type": "text",
           "text": "Weather in NYC: Sunny, 72°F"
         }
       ]
     }
   }
```

## Capabilities

### 1. Tools (Function Calling)

```python
from typing import Any

class MCPTool:
    """Define a tool accessible via MCP"""
    
    def __init__(self, name: str, description: str):
        self.name = name
        self.description = description
        self.input_schema = {}
    
    def execute(self, arguments: dict) -> str:
        """Execute the tool"""
        raise NotImplementedError

class GetWeatherTool(MCPTool):
    def __init__(self):
        super().__init__(
            name="get_weather",
            description="Get current weather for a location"
        )
        self.input_schema = {
            "type": "object",
            "properties": {
                "location": {
                    "type": "string",
                    "description": "City name"
                }
            },
            "required": ["location"]
        }
    
    def execute(self, arguments: dict) -> str:
        location = arguments.get("location")
        # Call weather API
        return f"Weather in {location}: Sunny, 72°F"

class SearchDBTool(MCPTool):
    def __init__(self):
        super().__init__(
            name="search_database",
            description="Search company database"
        )
        self.input_schema = {
            "type": "object",
            "properties": {
                "query": {
                    "type": "string",
                    "description": "Search query"
                }
            },
            "required": ["query"]
        }
    
    def execute(self, arguments: dict) -> str:
        query = arguments.get("query")
        # Execute database query
        return f"Search results for '{query}': ..."
```

### 2. Resources (Data Access)

```python
from dataclasses import dataclass
from typing import List

@dataclass
class MCPResource:
    """Resource accessible via MCP"""
    uri: str  # Unique identifier (e.g., "file://docs/api.md")
    name: str
    description: str
    mime_type: str  # e.g., "text/plain", "application/json"

class KnowledgeBaseResource(MCPResource):
    def __init__(self, doc_id: str):
        self.doc_id = doc_id
        super().__init__(
            uri=f"kb://doc/{doc_id}",
            name=f"Document {doc_id}",
            description="Company knowledge base document",
            mime_type="text/plain"
        )
    
    def read(self) -> str:
        """Read resource content"""
        # Load document from knowledge base
        return "Document content..."

class APIMockupResource(MCPResource):
    def __init__(self):
        super().__init__(
            uri="api://customers",
            name="Customer Database",
            description="Access customer records",
            mime_type="application/json"
        )
    
    def read(self) -> str:
        """Read resource content"""
        return """{
  "customers": [
    {"id": 1, "name": "Alice", "email": "alice@example.com"},
    {"id": 2, "name": "Bob", "email": "bob@example.com"}
  ]
}"""
```

### 3. Prompts (Prompt Templates)

```python
from typing import Dict

class MCPPrompt:
    """Reusable prompt template"""
    
    def __init__(self, name: str, template: str, arguments: List[str]):
        self.name = name
        self.template = template
        self.arguments = arguments
    
    def format(self, **kwargs) -> str:
        """Format prompt with arguments"""
        return self.template.format(**kwargs)

# Example prompts
code_review_prompt = MCPPrompt(
    name="code_review",
    template="Review this code for bugs and improvements:\n{code}",
    arguments=["code"]
)

customer_support_prompt = MCPPrompt(
    name="customer_support",
    template="Customer Question: {question}\nCustomer Context: {context}\nRespond professionally.",
    arguments=["question", "context"]
)
```

## MCP Server Implementation Example

```python
import json
from typing import Dict, List, Any
from abc import ABC, abstractmethod

class MCPServer:
    """Simple MCP Server implementation"""
    
    def __init__(self):
        self.tools: Dict[str, MCPTool] = {}
        self.resources: Dict[str, MCPResource] = {}
        self.prompts: Dict[str, MCPPrompt] = {}
    
    def register_tool(self, tool: MCPTool):
        """Register a tool"""
        self.tools[tool.name] = tool
    
    def register_resource(self, resource: MCPResource):
        """Register a resource"""
        self.resources[resource.uri] = resource
    
    def register_prompt(self, prompt: MCPPrompt):
        """Register a prompt template"""
        self.prompts[prompt.name] = prompt
    
    def handle_request(self, request: Dict[str, Any]) -> Dict[str, Any]:
        """Handle MCP request"""
        
        method = request.get("method")
        params = request.get("params", {})
        request_id = request.get("id")
        
        try:
            if method == "tools/list":
                result = self._list_tools()
            
            elif method == "tools/call":
                tool_name = params.get("name")
                arguments = params.get("arguments", {})
                result = self._call_tool(tool_name, arguments)
            
            elif method == "resources/list":
                result = self._list_resources()
            
            elif method == "resources/read":
                uri = params.get("uri")
                result = self._read_resource(uri)
            
            elif method == "prompts/list":
                result = self._list_prompts()
            
            elif method == "prompts/get":
                prompt_name = params.get("name")
                arguments = params.get("arguments", {})
                result = self._get_prompt(prompt_name, arguments)
            
            else:
                raise ValueError(f"Unknown method: {method}")
            
            return {
                "jsonrpc": "2.0",
                "id": request_id,
                "result": result
            }
        
        except Exception as e:
            return {
                "jsonrpc": "2.0",
                "id": request_id,
                "error": {
                    "code": -1,
                    "message": str(e)
                }
            }
    
    def _list_tools(self) -> Dict:
        """List available tools"""
        return {
            "tools": [
                {
                    "name": tool.name,
                    "description": tool.description,
                    "inputSchema": tool.input_schema
                }
                for tool in self.tools.values()
            ]
        }
    
    def _call_tool(self, tool_name: str, arguments: Dict) -> Dict:
        """Execute tool"""
        if tool_name not in self.tools:
            raise ValueError(f"Tool not found: {tool_name}")
        
        tool = self.tools[tool_name]
        result = tool.execute(arguments)
        
        return {
            "content": [
                {
                    "type": "text",
                    "text": result
                }
            ]
        }
    
    def _list_resources(self) -> Dict:
        """List available resources"""
        return {
            "resources": [
                {
                    "uri": resource.uri,
                    "name": resource.name,
                    "description": resource.description,
                    "mimeType": resource.mime_type
                }
                for resource in self.resources.values()
            ]
        }
    
    def _read_resource(self, uri: str) -> Dict:
        """Read resource"""
        if uri not in self.resources:
            raise ValueError(f"Resource not found: {uri}")
        
        resource = self.resources[uri]
        content = resource.read()
        
        return {
            "contents": [
                {
                    "uri": uri,
                    "mimeType": resource.mime_type,
                    "text": content
                }
            ]
        }
    
    def _list_prompts(self) -> Dict:
        """List available prompts"""
        return {
            "prompts": [
                {
                    "name": prompt.name,
                    "description": prompt.description,
                    "arguments": prompt.arguments
                }
                for prompt in self.prompts.values()
            ]
        }
    
    def _get_prompt(self, name: str, arguments: Dict) -> Dict:
        """Get formatted prompt"""
        if name not in self.prompts:
            raise ValueError(f"Prompt not found: {name}")
        
        prompt = self.prompts[name]
        formatted = prompt.format(**arguments)
        
        return {
            "messages": [
                {
                    "role": "user",
                    "content": formatted
                }
            ]
        }

# Usage
server = MCPServer()

# Register tools
server.register_tool(GetWeatherTool())
server.register_tool(SearchDBTool())

# Register resources
server.register_resource(APICustomersResource())

# Register prompts
server.register_prompt(code_review_prompt)
server.register_prompt(customer_support_prompt)

# Handle incoming requests
request = {
    "jsonrpc": "2.0",
    "id": 1,
    "method": "tools/call",
    "params": {
        "name": "get_weather",
        "arguments": {"location": "NYC"}
    }
}

response = server.handle_request(request)
print(json.dumps(response, indent=2))
```

## MCP Client Implementation

```python
import json
from typing import Dict, Any

class MCPClient:
    """Simple MCP Client"""
    
    def __init__(self, server: MCPServer):
        self.server = server
    
    def list_tools(self) -> List[Dict]:
        """List available tools"""
        request = {
            "jsonrpc": "2.0",
            "id": 1,
            "method": "tools/list",
            "params": {}
        }
        response = self.server.handle_request(request)
        return response["result"]["tools"]
    
    def call_tool(self, tool_name: str, arguments: Dict) -> str:
        """Call a tool"""
        request = {
            "jsonrpc": "2.0",
            "id": 2,
            "method": "tools/call",
            "params": {
                "name": tool_name,
                "arguments": arguments
            }
        }
        response = self.server.handle_request(request)
        
        if "error" in response:
            raise Exception(response["error"]["message"])
        
        content = response["result"]["content"]
        return content[0]["text"] if content else ""
    
    def read_resource(self, uri: str) -> str:
        """Read a resource"""
        request = {
            "jsonrpc": "2.0",
            "id": 3,
            "method": "resources/read",
            "params": {"uri": uri}
        }
        response = self.server.handle_request(request)
        
        if "error" in response:
            raise Exception(response["error"]["message"])
        
        contents = response["result"]["contents"]
        return contents[0]["text"] if contents else ""

# Usage
client = MCPClient(server)

# List tools
tools = client.list_tools()
print("Available tools:")
for tool in tools:
    print(f"  - {tool['name']}: {tool['description']}")

# Call tool
weather = client.call_tool("get_weather", {"location": "NYC"})
print(f"Weather: {weather}")

# Read resource
customer_data = client.read_resource("api://customers")
print(f"Customers: {customer_data}")
```

## MCP with LLMs

### Integration with Claude

```python
# Using MCP with Anthropic Claude
from anthropic import Anthropic

client = Anthropic()

# Define tools in Claude's format
tools = [
    {
        "name": "get_weather",
        "description": "Get current weather",
        "input_schema": {
            "type": "object",
            "properties": {
                "location": {"type": "string"}
            },
            "required": ["location"]
        }
    }
]

# Conversation with Claude
messages = [
    {
        "role": "user",
        "content": "What's the weather in NYC?"
    }
]

response = client.messages.create(
    model="claude-3-opus-20240229",
    max_tokens=1024,
    tools=tools,
    messages=messages
)

# Process tool calls
for block in response.content:
    if hasattr(block, 'type') and block.type == 'tool_use':
        tool_name = block.name
        arguments = block.input
        
        # Execute tool via MCP
        result = client_mcp.call_tool(tool_name, arguments)
        
        # Return to Claude
        messages.append({
            "role": "assistant",
            "content": response.content
        })
        messages.append({
            "role": "user",
            "content": [
                {
                    "type": "tool_result",
                    "tool_use_id": block.id,
                    "content": result
                }
            ]
        })
```

## MCP Benefits

✅ **Standardization** - Unified protocol for all models
✅ **Interoperability** - Mix tools from different providers
✅ **Security** - Defined permission model
✅ **Scalability** - Can handle multiple clients/servers
✅ **Maintainability** - Single implementation for all models
✅ **Extensibility** - Easy to add new tools

## Real-World Applications

1. **Tool Marketplace** - Shared tools across organizations
2. **Enterprise Integration** - Connect models to internal systems
3. **Microservices** - Model-driven microservice orchestration
4. **Plugin Systems** - Extensible AI applications

## Next Steps

- Learn [LangChain](./07-langchain.md) for higher-level abstractions
- Explore [LangGraph](./08-langgraph.md) for complex workflows
- Review [RAG Pipeline](./05-rag-pipeline.md) with MCP integration

---

*Reference: Model Context Protocol (MCP) Specification*
