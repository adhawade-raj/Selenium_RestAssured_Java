# Performance Testing Overview

Performance testing measures system behavior under expected load and identifies bottlenecks.

## What is Performance Testing?

Performance testing evaluates application responsiveness, stability, and scalability under various load conditions. It ensures the system meets performance requirements and identifies optimization opportunities.

**Key Metrics:**
- Response time
- Throughput (requests/sec)
- CPU and memory usage
- Error rate

---

## Performance Testing Types

### 1. Load Testing
Tests system behavior under normal and peak loads.
- Simulates realistic user traffic
- Measures response times and resource utilization
- Identifies max capacity
- **Example:** Testing a website with 1000 concurrent users

### 2. Stress Testing
Pushes system beyond normal operating capacity to find breaking point.
- Increases load gradually until system fails
- Identifies maximum limits
- Reveals failure recovery behavior
- **Example:** Increasing users until the server crashes

### 3. Spike Testing
Tests system response to sudden, significant load spikes.
- Simulates unexpected traffic bursts
- Checks recovery after spike
- Measures stability
- **Example:** Flash sale or viral content spike

### 4. Soak Testing (Endurance Testing)
Tests system stability over extended periods with normal load.
- Runs for hours/days
- Identifies memory leaks
- Detects resource degradation
- **Example:** Running 100 concurrent users for 24 hours

### 5. Volume Testing
Tests system performance with large data volumes.
- Focuses on database impact
- Measures processing time
- Checks storage constraints
- **Example:** Processing millions of database records

### 6. Scalability Testing
Tests system's ability to scale up/down resources.
- Evaluates horizontal scaling (more servers)
- Evaluates vertical scaling (more capacity per server)
- Measures performance improvement
- **Example:** Adding servers and checking load distribution

### 7. Endurance Testing
Verifies system stability during extended operation.
- Similar to soak testing
- Tests resource management
- Identifies memory leaks and degradation
- **Example:** Running continuous operations for extended time

---

## Performance Testing Process

1. **Define Requirements** → Establish performance goals and thresholds
2. **Design Test Scenarios** → Create realistic load patterns
3. **Set Up Environment** → Configure test infrastructure
4. **Execute Tests** → Run performance test scenarios
5. **Collect Metrics** → Gather performance data
6. **Analyze Results** → Identify bottlenecks and issues
7. **Optimize** → Fix performance issues
8. **Validate** → Retest after improvements

---

## Common Tools

- **JMeter** - Load testing tool
- **LoadRunner** - Enterprise performance testing
- **Gatling** - Scala-based load testing
- **Locust** - Python-based load testing
- **WebLoad** - Web application performance testing

---

## When to Perform Performance Testing

✓ Before production release
✓ After major changes
✓ Infrastructure upgrades
✓ Capacity planning
✓ SLA compliance verification

---

## Performance Test Metrics & Parameters

### CPU Usage
- Processor utilization percentage (0-100%)
- High CPU indicates processing bottleneck
- **Threshold:** Should remain below 80% under normal load
- **Example:** API response slows at 95% CPU utilization

### Memory Usage
- RAM consumption (MB/GB)
- Peak and average memory during test
- **Threshold:** Should not exceed 85% of available RAM
- **Example:** Application uses 4GB out of 6GB available

### Memory Leaks
Memory leaks occur when an application allocates memory but fails to release it after use, causing available memory to decrease over time.
- **Symptom:** Increasing memory usage without corresponding data increase
- **Impact:** System slowdown, eventual crash, increased CPU (swapping)
- **Detection:** Monitor memory in long-running tests (Soak/Endurance testing)
- **Example:** REST API running for 24 hours with memory growing from 1GB → 5GB despite same load

### Storage/Disk I/O
- Read/write speed (throughput: MB/sec)
- Disk space utilization
- I/O operations per second (IOPS)
- **Threshold:** Disk I/O latency < 20ms
- **Example:** Database logging slowing API response to 5 seconds

### Response Time
- Time from request to response
- Average, Min, Max, Percentile (P50, P95, P99)
- **Threshold:** API response < 200ms, Website < 500ms
- **Example:** Average API response 150ms, but P99 at 2 seconds

### Throughput
- Requests processed per second
- **Threshold:** Must meet SLA requirements
- **Example:** API handles 1000 requests/sec under load

### Error Rate
- Percentage of failed requests
- Server errors (5xx), timeouts
- **Threshold:** Should be < 0.1%
- **Example:** At 5000 concurrent users, error rate jumps to 2%

### Network Bandwidth
- Data transfer rate (Mbps)
- Network latency
- Packet loss
- **Example:** API consuming 500 Mbps bandwidth at peak

---

## What We Test in Performance Tests

| Parameter | What to Monitor | Why It Matters |
|-----------|-----------------|----------------|
| **CPU** | Utilization %, peak usage | Identifies processing bottlenecks |
| **Memory** | RAM usage, peaks, leaks | Prevents crashes, ensures stability |
| **Disk I/O** | Read/write speed, IOPS | Database/file operations efficiency |
| **Response Time** | Avg, Min, Max, P95, P99 | User experience, SLA compliance |
| **Throughput** | Requests/sec | System capacity |
| **Error Rate** | % failed requests | System reliability |
| **Network** | Bandwidth, latency | Connection quality |
| **Thread Count** | Active threads | Resource consumption |
| **Database Connections** | Connection pool usage | DB query performance |
| **Garbage Collection** | GC pause time, frequency | Java/.NET app performance |

---

## Real-World Project Example

### Project: REST API Performance Testing

**Scenario:** E-commerce API serving mobile and web applications

**Testing Performed:**
1. **Load Testing** - 5000 concurrent users, 60 min duration
   - Measured response times: Avg 180ms, P99 500ms ✅
   - CPU usage peaked at 75% ✅
   - Memory stable at 3.2GB throughout ✅

2. **Spike Testing** - Sudden spike from 1000 → 10000 users
   - API recovered within 2 seconds ✅
   - No memory leaks observed ✅

3. **Capacity Testing** - Identified breaking point at 15000 concurrent users
   - Response time degraded to 3+ seconds
   - Error rate jumped to 5%

4. **Mobile App Performance Testing** (Signed off by Perf QA)
   - Handled by Dev team
   - Memory usage on mobile: Avg 120MB, Peak 180MB ✅
   - Network optimization verified
   - Approved by Performance QA team

**Results:**
- API approved for production
- Added caching to reduce DB load
- Load balancer configuration optimized

**Findings:**
- Slow database query causing CPU spike at 12000+ users
- Memory leak in background job (fixed by dev team)
- Storage growth: +500MB per day in logs (archived old logs)

---

## Website Performance Testing Example

**Scenario:** E-commerce Website Load Testing

**Testing:**
1. **Load Test** - 2000 concurrent users
   - Page load time: Avg 2.1s, P99 4.5s ✅
   - CPU at 68%, Memory at 6.5GB
   - Throughput: 450 pages/sec

2. **Soak Test** - 500 users for 48 hours
   - Detected memory leak in caching layer
   - Memory grew from 5GB → 8.2GB over 48 hours
   - Fixed: Cache eviction policy implemented

3. **Volume Test** - 50M product records
   - Search query time: 800ms (unacceptable)
   - Database indexing optimized
   - Search time reduced to 120ms

**Results:**
- CPU limit: 80% at 2000 concurrent users
- Disk space needed: 50GB (products + logs)
- Network bandwidth: 250 Mbps peak
