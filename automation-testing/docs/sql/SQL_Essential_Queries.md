# SQL Essential Queries & Patterns

Commonly asked practical SQL queries and patterns that demonstrate core SQL knowledge.

---

## Table of Contents
1. [Basic Joins](#basic-joins)
2. [Finding Extremes](#finding-extremes)
3. [Duplicates & Unique Records](#duplicates--unique-records)
4. [Aggregation Patterns](#aggregation-patterns)
5. [Ranking & Ordering](#ranking--ordering)
6. [String Operations](#string-operations)
7. [Date Operations](#date-operations)
8. [Complex Filtering](#complex-filtering)
9. [Self Joins](#self-joins)
10. [Window Functions](#window-functions)
11. [Common Table Expressions](#common-table-expressions)
12. [Data Manipulation](#data-manipulation)

---

## Basic Joins

### Simple Join - Two Tables

**Query:** Connect two tables and display data from both

Simplest way:
```sql
-- Most basic syntax
SELECT a.Name, b.Name
FROM TableA a, TableB b
WHERE a.ID = b.ID;
```

Explicit JOIN syntax:
```sql
SELECT a.Name, b.Name
FROM TableA a
INNER JOIN TableB b ON a.ID = b.ID;
```

### Practical Example: Customers and Orders
```sql
-- Find customers and their order IDs
SELECT 
  c.CustomerName,
  o.OrderID
FROM Customers c
INNER JOIN Orders o ON c.CustomerID = o.CustomerID;
```

### Three Table Join
```sql
-- Join customers, orders, and products
SELECT 
  c.CustomerName,
  o.OrderID,
  p.ProductName
FROM Customers c
INNER JOIN Orders o ON c.CustomerID = o.CustomerID
INNER JOIN OrderDetails od ON o.OrderID = od.OrderID
INNER JOIN Products p ON od.ProductID = p.ProductID;
```

---

## Finding Extremes

### Highest Salary (Maximum)

**Single highest salary:**
```sql
SELECT MAX(Salary) as HighestSalary
FROM Employees;
```

**Employee with highest salary:**
```sql
SELECT Name, Salary
FROM Employees
WHERE Salary = (SELECT MAX(Salary) FROM Employees);
```

**Top 5 highest salaries (MySQL):**
```sql
SELECT DISTINCT Salary
FROM Employees
ORDER BY Salary DESC
LIMIT 5;
```

**Top 5 highest salaries (SQL Server):**
```sql
SELECT TOP 5 DISTINCT Salary
FROM Employees
ORDER BY Salary DESC;
```

### Second Highest Salary

**Using OFFSET (MySQL, PostgreSQL):**
```sql
SELECT DISTINCT Salary
FROM Employees
ORDER BY Salary DESC
LIMIT 1 OFFSET 1;
```

**Using Subquery:**
```sql
SELECT MAX(Salary) as SecondHighestSalary
FROM Employees
WHERE Salary < (SELECT MAX(Salary) FROM Employees);
```

**Using NOT IN:**
```sql
SELECT MAX(Salary)
FROM Employees
WHERE Salary NOT IN (SELECT MAX(Salary) FROM Employees);
```

**Using Window Function (SQL Server, PostgreSQL):**
```sql
SELECT DISTINCT Salary
FROM (
  SELECT Salary, ROW_NUMBER() OVER (ORDER BY Salary DESC) as rn
  FROM Employees
) ranked
WHERE rn = 2;
```

### Third Highest Salary
```sql
SELECT DISTINCT Salary
FROM Employees
ORDER BY Salary DESC
LIMIT 1 OFFSET 2;
```

### Nth Highest Salary (General Solution)
```sql
-- Find the Nth highest salary (replace N with desired number)
SELECT MAX(Salary) as NthHighestSalary
FROM Employees
WHERE Salary NOT IN (
  SELECT DISTINCT Salary
  FROM Employees
  ORDER BY Salary DESC
  LIMIT N-1
);

-- Example: 4th highest salary
SELECT MAX(Salary) as 4thHighestSalary
FROM Employees
WHERE Salary NOT IN (
  SELECT DISTINCT Salary
  FROM Employees
  ORDER BY Salary DESC
  LIMIT 3
);
```

### Lowest Salary
```sql
SELECT MIN(Salary) as LowestSalary
FROM Employees;

-- Employee with lowest salary
SELECT Name, Salary
FROM Employees
WHERE Salary = (SELECT MIN(Salary) FROM Employees);
```

---

## Duplicates & Unique Records

### Find Duplicate Records

**Count duplicates:**
```sql
SELECT Email, COUNT(*) as Occurrence
FROM Users
GROUP BY Email
HAVING COUNT(*) > 1;
```

**Find all duplicate rows:**
```sql
SELECT *
FROM Users
WHERE Email IN (
  SELECT Email FROM Users GROUP BY Email HAVING COUNT(*) > 1
);
```

**Remove duplicates (keep one):**
```sql
DELETE FROM Users
WHERE ID NOT IN (
  SELECT MIN(ID)
  FROM Users
  GROUP BY Email
);
```

### Count of Each Value
```sql
SELECT Country, COUNT(*) as CustomerCount
FROM Customers
GROUP BY Country
ORDER BY CustomerCount DESC;
```

### Unique/Distinct Values
```sql
-- Count unique values
SELECT COUNT(DISTINCT Country) as UniqueCountries
FROM Customers;

-- List unique values
SELECT DISTINCT Country
FROM Customers
ORDER BY Country;
```

---

## Aggregation Patterns

### Sum by Group
```sql
SELECT 
  Department,
  SUM(Salary) as TotalSalary
FROM Employees
GROUP BY Department
ORDER BY TotalSalary DESC;
```

### Average by Group
```sql
SELECT 
  Department,
  AVG(Salary) as AverageSalary
FROM Employees
GROUP BY Department;
```

### Count by Group
```sql
SELECT 
  Department,
  COUNT(*) as EmployeeCount
FROM Employees
GROUP BY Department;
```

### Multiple Aggregations
```sql
SELECT 
  Department,
  COUNT(*) as EmployeeCount,
  AVG(Salary) as AvgSalary,
  MAX(Salary) as MaxSalary,
  MIN(Salary) as MinSalary,
  SUM(Salary) as TotalSalary
FROM Employees
GROUP BY Department;
```

### Aggregation with Filtering
```sql
-- Departments with average salary > 50000
SELECT 
  Department,
  AVG(Salary) as AvgSalary
FROM Employees
GROUP BY Department
HAVING AVG(Salary) > 50000;
```

---

## Ranking & Ordering

### Rank Employees by Salary

**Using Window Function (SQL Server, PostgreSQL, MySQL 8+):**
```sql
SELECT 
  Name,
  Salary,
  RANK() OVER (ORDER BY Salary DESC) as SalaryRank
FROM Employees;
```

**Using Variables (MySQL):**
```sql
SELECT 
  Name,
  Salary,
  @rank := @rank + 1 as Rank
FROM Employees, (SELECT @rank := 0) as init
ORDER BY Salary DESC;
```

**Using Subquery (All databases):**
```sql
SELECT 
  e1.Name,
  e1.Salary,
  COUNT(*) as Rank
FROM Employees e1
JOIN Employees e2 ON e1.Salary <= e2.Salary
GROUP BY e1.ID, e1.Name, e1.Salary
ORDER BY Rank;
```

### Rank Within Department
```sql
SELECT 
  Name,
  Department,
  Salary,
  RANK() OVER (PARTITION BY Department ORDER BY Salary DESC) as DeptRank
FROM Employees;
```

### Dense Rank (No gaps)
```sql
SELECT 
  Name,
  Salary,
  DENSE_RANK() OVER (ORDER BY Salary DESC) as DenseRank
FROM Employees;
```

### Row Number (Sequential)
```sql
SELECT 
  Name,
  Salary,
  ROW_NUMBER() OVER (ORDER BY Salary DESC) as RowNum
FROM Employees;
```

---

## String Operations

### Concatenate Strings

**SQL Server, MySQL, PostgreSQL:**
```sql
SELECT CONCAT(FirstName, ' ', LastName) as FullName
FROM Employees;
```

**SQL Standard (using ||):**
```sql
SELECT FirstName || ' ' || LastName as FullName
FROM Employees;
```

### String Length
```sql
SELECT Name, LENGTH(Name) as NameLength
FROM Employees
WHERE LENGTH(Name) > 10;
```

### Substring
```sql
-- Get first 5 characters
SELECT SUBSTRING(Name, 1, 5)
FROM Employees;
```

### UPPER/LOWER
```sql
SELECT 
  UPPER(Name) as NameUpper,
  LOWER(Name) as NameLower
FROM Employees;
```

### Find Values Starting With
```sql
SELECT *
FROM Employees
WHERE Name LIKE 'J%';  -- Starts with J
```

### Replace
```sql
SELECT REPLACE(Email, '@gmail.com', '@yahoo.com')
FROM Users;
```

---

## Date Operations

### Current Date/Time
```sql
-- Get today's date
SELECT GETDATE() as Today;  -- SQL Server
SELECT CURDATE() as Today;   -- MySQL
SELECT NOW() as Today;       -- MySQL, PostgreSQL
```

### Date Difference
```sql
-- SQL Server
SELECT DATEDIFF(DAY, StartDate, EndDate) as DaysElapsed
FROM Projects;

-- MySQL
SELECT DATEDIFF(EndDate, StartDate) as DaysElapsed
FROM Projects;

-- PostgreSQL
SELECT (EndDate - StartDate) as DaysElapsed
FROM Projects;
```

### Date Addition
```sql
-- SQL Server
SELECT DATEADD(DAY, 30, OrderDate) as DeliveryDate
FROM Orders;

-- MySQL
SELECT DATE_ADD(OrderDate, INTERVAL 30 DAY) as DeliveryDate
FROM Orders;

-- PostgreSQL
SELECT OrderDate + INTERVAL '30 days' as DeliveryDate
FROM Orders;
```

### Extract Year/Month/Day
```sql
SELECT 
  YEAR(JoinDate) as JoinYear,
  MONTH(JoinDate) as JoinMonth,
  DAY(JoinDate) as JoinDay
FROM Employees;
```

### Records from Last 30 Days
```sql
-- SQL Server
SELECT *
FROM Orders
WHERE OrderDate >= DATEADD(DAY, -30, GETDATE());

-- MySQL
SELECT *
FROM Orders
WHERE OrderDate >= DATE_SUB(CURDATE(), INTERVAL 30 DAY);

-- PostgreSQL
SELECT *
FROM Orders
WHERE OrderDate >= CURRENT_DATE - INTERVAL '30 days';
```

---

## Complex Filtering

### NOT EXISTS
```sql
-- Find customers who haven't placed any orders
SELECT *
FROM Customers c
WHERE NOT EXISTS (
  SELECT 1 FROM Orders o WHERE o.CustomerID = c.CustomerID
);
```

### IN vs EXISTS
```sql
-- Using IN
SELECT *
FROM Customers
WHERE CustomerID IN (SELECT CustomerID FROM Orders);

-- Using EXISTS (often more efficient)
SELECT *
FROM Customers c
WHERE EXISTS (SELECT 1 FROM Orders o WHERE o.CustomerID = c.CustomerID);
```

### Multiple Conditions
```sql
SELECT *
FROM Orders
WHERE OrderDate >= '2024-01-01'
  AND OrderDate < '2024-12-31'
  AND (Status = 'Completed' OR Status = 'Pending')
  AND Amount > 100;
```

### CASE Statement
```sql
SELECT 
  Name,
  Salary,
  CASE 
    WHEN Salary > 100000 THEN 'High'
    WHEN Salary > 50000 THEN 'Medium'
    ELSE 'Low'
  END as SalaryLevel
FROM Employees;
```

---

## Self Joins

### Find Manager-Employee Relationship
```sql
SELECT 
  e.Name as Employee,
  m.Name as Manager
FROM Employees e
LEFT JOIN Employees m ON e.ManagerID = m.EmployeeID;
```

### Find Employees at Same Salary Level
```sql
SELECT 
  e1.Name as Employee1,
  e2.Name as Employee2,
  e1.Salary
FROM Employees e1
INNER JOIN Employees e2 ON e1.Salary = e2.Salary
  AND e1.EmployeeID < e2.EmployeeID;
```

---

## Window Functions

### Running Total
```sql
SELECT 
  Name,
  Salary,
  SUM(Salary) OVER (ORDER BY EmployeeID) as RunningTotal
FROM Employees;
```

### Cumulative Sum by Department
```sql
SELECT 
  Name,
  Department,
  Salary,
  SUM(Salary) OVER (PARTITION BY Department ORDER BY EmployeeID) as DeptRunningTotal
FROM Employees;
```

### Lead/Lag (Previous/Next Value)
```sql
-- Get current salary and next employee's salary
SELECT 
  Name,
  Salary,
  LEAD(Salary) OVER (ORDER BY Salary DESC) as NextHigherSalary,
  LAG(Salary) OVER (ORDER BY Salary DESC) as NextLowerSalary
FROM Employees;
```

### Percentage of Total
```sql
SELECT 
  Department,
  Salary,
  ROUND(Salary * 100.0 / SUM(Salary) OVER (), 2) as PercentageOfTotal
FROM Employees;
```

---

## Common Table Expressions

### Simple CTE
```sql
WITH HighEarners AS (
  SELECT Name, Salary
  FROM Employees
  WHERE Salary > 100000
)
SELECT * FROM HighEarners
ORDER BY Salary DESC;
```

### Recursive CTE (Employee Hierarchy)
```sql
WITH RECURSIVE EmployeeHierarchy AS (
  -- Base case: top-level employees
  SELECT EmployeeID, Name, ManagerID, 1 as Level
  FROM Employees
  WHERE ManagerID IS NULL
  
  UNION ALL
  
  -- Recursive case: employees under managers
  SELECT e.EmployeeID, e.Name, e.ManagerID, eh.Level + 1
  FROM Employees e
  INNER JOIN EmployeeHierarchy eh ON e.ManagerID = eh.EmployeeID
)
SELECT * FROM EmployeeHierarchy;
```

### Multiple CTEs
```sql
WITH DeptSalaries AS (
  SELECT Department, SUM(Salary) as TotalSalary
  FROM Employees
  GROUP BY Department
),
AvgSalary AS (
  SELECT AVG(TotalSalary) as AvgDeptSalary
  FROM DeptSalaries
)
SELECT d.Department, d.TotalSalary, a.AvgDeptSalary
FROM DeptSalaries d
CROSS JOIN AvgSalary a;
```

---

## Data Manipulation

### Insert with Select
```sql
INSERT INTO Employees_Archive (Name, Email, Salary)
SELECT Name, Email, Salary
FROM Employees
WHERE JoinDate < '2020-01-01';
```

### Update with Join
```sql
UPDATE Employees e
SET e.Department = 'IT'
FROM Departments d
WHERE e.DeptID = d.DepartmentID
  AND d.DepartmentName = 'Technology';
```

### Delete Duplicates
```sql
DELETE FROM Users
WHERE ID NOT IN (
  SELECT MIN(ID)
  FROM Users
  GROUP BY Email
);
```

### Bulk Update with Case
```sql
UPDATE Employees
SET Salary = CASE
  WHEN Department = 'Sales' THEN Salary * 1.1
  WHEN Department = 'IT' THEN Salary * 1.15
  ELSE Salary * 1.05
END;
```

---

## Performance Tips

### ✓ Best Practices
```sql
-- 1. Use EXISTS instead of IN for subqueries
SELECT * FROM Customers c
WHERE EXISTS (SELECT 1 FROM Orders o WHERE o.CustomerID = c.CustomerID);

-- 2. Filter early in WHERE clause
SELECT o.OrderID, c.CustomerName
FROM Orders o
INNER JOIN Customers c ON o.CustomerID = c.CustomerID
WHERE o.OrderDate >= '2024-01-01';

-- 3. Use specific columns instead of SELECT *
SELECT Name, Email FROM Users WHERE Active = 1;

-- 4. Use LIMIT for testing
SELECT * FROM LargeTable LIMIT 10;

-- 5. Create indexes on frequently searched columns
CREATE INDEX idx_email ON Users(Email);
```

### ✗ Avoid
```sql
-- DON'T: Use SELECT * on large tables
SELECT * FROM LargeTable;

-- DON'T: Use functions on column in WHERE (prevents index usage)
SELECT * FROM Users WHERE YEAR(CreatedDate) = 2024;

-- DON'T: Use OR excessively
SELECT * FROM Orders WHERE Status='A' OR Status='B' OR Status='C' OR Status='D';
-- Better: Use IN
SELECT * FROM Orders WHERE Status IN ('A','B','C','D');

-- DON'T: Use correlated subqueries in SELECT list
SELECT Name, (SELECT COUNT(*) FROM Orders WHERE Orders.CustomerID = Customers.CustomerID)
FROM Customers;
-- Better: Use JOIN with GROUP BY
```

---

## Quick Reference Table

| Task | Query Pattern |
|------|---------------|
| **Join two tables** | `SELECT a.col, b.col FROM a INNER JOIN b ON a.id = b.id;` |
| **Highest value** | `SELECT MAX(column) FROM table;` |
| **Second highest** | `SELECT DISTINCT column FROM table ORDER BY column DESC LIMIT 1 OFFSET 1;` |
| **Count by group** | `SELECT group_col, COUNT(*) FROM table GROUP BY group_col;` |
| **Find duplicates** | `SELECT col, COUNT(*) FROM table GROUP BY col HAVING COUNT(*) > 1;` |
| **Rank rows** | `SELECT col, RANK() OVER (ORDER BY col DESC) as rank FROM table;` |
| **String concat** | `SELECT CONCAT(col1, col2) FROM table;` |
| **Date difference** | `SELECT DATEDIFF(date1, date2) FROM table;` |
| **Filtered aggregate** | `SELECT col, COUNT(*) FROM table GROUP BY col HAVING COUNT(*) > n;` |
| **Recursive hierarchy** | `WITH RECURSIVE cte AS (...) SELECT * FROM cte;` |
