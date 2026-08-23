# SQL Essential Queries - Q&A Format

Complete collection of essential SQL queries with questions and answers. Total queries to practice: **60+**

---

## Table of Contents
1. [Basic Joins](#basic-joins) - 4 questions
2. [Finding Extremes](#finding-extremes) - 7 questions
3. [Duplicates & Unique Records](#duplicates--unique-records) - 5 questions
4. [Aggregation Patterns](#aggregation-patterns) - 6 questions
5. [Ranking & Ordering](#ranking--ordering) - 5 questions
6. [String Operations](#string-operations) - 5 questions
7. [Date Operations](#date-operations) - 5 questions
8. [Complex Filtering](#complex-filtering) - 4 questions
9. [Self Joins](#self-joins) - 2 questions
10. [Window Functions](#window-functions) - 4 questions
11. [DELETE Operations](#delete-operations) - 6 questions
12. [Comparison Topics](#comparison-topics) - 3 questions

---

## Basic Joins

### Q1: Write a simple join to connect two tables
**Question:** Connect two tables (TableA and TableB) using their ID columns and display names from both.

**Answer:**
```sql
-- Most basic syntax (implicit join)
SELECT a.Name, b.Name
FROM TableA a, TableB b
WHERE a.ID = b.ID;
```

**Explanation:** This is the simplest form of joining. The WHERE clause acts as the join condition. Modern syntax uses INNER JOIN keyword.

---

### Q2: Write an INNER JOIN query with explicit syntax
**Question:** Display customer names and their order IDs using explicit INNER JOIN syntax.

**Answer:**
```sql
SELECT c.CustomerName, o.OrderID
FROM Customers c
INNER JOIN Orders o ON c.CustomerID = o.CustomerID;
```

**Explanation:** INNER JOIN explicitly shows the join condition in the ON clause. Only matching records from both tables are returned.

---

### Q3: Join three tables
**Question:** Display customer names, order IDs, and product names from three related tables.

**Answer:**
```sql
SELECT 
  c.CustomerName,
  o.OrderID,
  p.ProductName
FROM Customers c
INNER JOIN Orders o ON c.CustomerID = o.CustomerID
INNER JOIN OrderDetails od ON o.OrderID = od.OrderID
INNER JOIN Products p ON od.ProductID = p.ProductID;
```

**Explanation:** Chain multiple INNER JOINs to connect multiple tables. Each join adds another condition to filter the results.

---

### Q4: Write a LEFT JOIN to find unmatched records
**Question:** Get all customers and their orders. Include customers with no orders.

**Answer:**
```sql
SELECT c.CustomerName, o.OrderID
FROM Customers c
LEFT JOIN Orders o ON c.CustomerID = o.CustomerID;
```

**Explanation:** LEFT JOIN returns ALL records from the left table (Customers) and matching records from the right table (Orders). Non-matching orders will have NULL values.

---

## Finding Extremes

### Q5: Find the highest salary
**Question:** Get the maximum salary from the Employees table.

**Answer:**
```sql
SELECT MAX(Salary) as HighestSalary
FROM Employees;
```

**Explanation:** MAX() is an aggregate function that returns the highest value. Use MIN() for lowest.

---

### Q6: Find the employee with the highest salary
**Question:** Display the name and salary of the employee earning the most.

**Answer:**
```sql
SELECT Name, Salary
FROM Employees
WHERE Salary = (SELECT MAX(Salary) FROM Employees);
```

**Explanation:** Use a subquery to find the MAX salary, then filter WHERE clause to get the matching employee.

---

### Q7: Find the second highest salary
**Question:** Get the second highest salary amount from the Employees table.

**Answer:**
```sql
-- Method 1: Using OFFSET
SELECT DISTINCT Salary
FROM Employees
ORDER BY Salary DESC
LIMIT 1 OFFSET 1;

-- Method 2: Using Subquery
SELECT MAX(Salary) as SecondHighestSalary
FROM Employees
WHERE Salary < (SELECT MAX(Salary) FROM Employees);

-- Method 3: Using NOT IN
SELECT MAX(Salary) as SecondHighestSalary
FROM Employees
WHERE Salary NOT IN (SELECT MAX(Salary) FROM Employees);
```

**Explanation:** 
- Method 1: OFFSET 1 skips the first row (highest), then LIMIT 1 gets the next highest.
- Method 2: Find all salaries less than MAX, then get the MAX of those.
- Method 3: Exclude the highest salary, then get MAX of remaining.

---

### Q8: Find the third highest salary
**Question:** Get the third highest distinct salary.

**Answer:**
```sql
SELECT DISTINCT Salary
FROM Employees
ORDER BY Salary DESC
LIMIT 1 OFFSET 2;
```

**Explanation:** OFFSET 2 skips the first two rows (1st and 2nd highest), LIMIT 1 gets the third.

---

### Q9: Find the Nth highest salary (general solution)
**Question:** Create a query to find any Nth highest salary. Example: 5th highest.

**Answer:**
```sql
-- General formula for Nth highest salary
SELECT MAX(Salary)
FROM Employees
WHERE Salary NOT IN (
  SELECT DISTINCT Salary
  FROM Employees
  ORDER BY Salary DESC
  LIMIT N-1
);

-- Example: 5th highest salary
SELECT MAX(Salary) as 5thHighestSalary
FROM Employees
WHERE Salary NOT IN (
  SELECT DISTINCT Salary
  FROM Employees
  ORDER BY Salary DESC
  LIMIT 4
);
```

**Explanation:** Exclude the top (N-1) salaries using NOT IN, then get the MAX of remaining salaries.

---

### Q10: Find the lowest salary
**Question:** Get the minimum salary and the employee name.

**Answer:**
```sql
SELECT Name, Salary
FROM Employees
WHERE Salary = (SELECT MIN(Salary) FROM Employees);
```

**Explanation:** MIN() returns the lowest value. Use a subquery to get the minimum salary value.

---

### Q11: Find top 5 salaries
**Question:** Display the top 5 highest salary amounts.

**Answer:**
```sql
-- MySQL/PostgreSQL
SELECT DISTINCT Salary
FROM Employees
ORDER BY Salary DESC
LIMIT 5;

-- SQL Server
SELECT TOP 5 DISTINCT Salary
FROM Employees
ORDER BY Salary DESC;
```

**Explanation:** Different databases use different syntax. LIMIT (MySQL) vs TOP (SQL Server).

---

## Duplicates & Unique Records

### Q12: Find duplicate records
**Question:** Find all email addresses that appear more than once in the Users table.

**Answer:**
```sql
SELECT Email, COUNT(*) as Occurrence
FROM Users
GROUP BY Email
HAVING COUNT(*) > 1;
```

**Explanation:** GROUP BY groups records by Email, HAVING filters groups where count > 1 (duplicates).

---

### Q13: Get all rows with duplicate emails
**Question:** Display all records that have duplicate email addresses.

**Answer:**
```sql
SELECT *
FROM Users
WHERE Email IN (
  SELECT Email
  FROM Users
  GROUP BY Email
  HAVING COUNT(*) > 1
);
```

**Explanation:** Subquery finds duplicate emails, main query returns all rows with those emails.

---

### Q14: Remove duplicate records (keep one)
**Question:** Delete all but one record for each duplicate email.

**Answer:**
```sql
DELETE FROM Users
WHERE ID NOT IN (
  SELECT MIN(ID)
  FROM Users
  GROUP BY Email
);
```

**Explanation:** GROUP BY finds minimum ID for each email (keep this), DELETE all others.

---

### Q15: Count unique values
**Question:** Count how many distinct countries are in the Customers table.

**Answer:**
```sql
SELECT COUNT(DISTINCT Country) as UniqueCountries
FROM Customers;
```

**Explanation:** COUNT(DISTINCT column) returns the number of unique values. Use SELECT DISTINCT to list them.

---

### Q16: Count occurrences by group
**Question:** Display each country and how many customers are from that country.

**Answer:**
```sql
SELECT Country, COUNT(*) as CustomerCount
FROM Customers
GROUP BY Country
ORDER BY CustomerCount DESC;
```

**Explanation:** GROUP BY groups records, COUNT(*) counts rows in each group.

---

## Aggregation Patterns

### Q17: Sum by department
**Question:** Calculate the total salary for each department.

**Answer:**
```sql
SELECT Department, SUM(Salary) as TotalSalary
FROM Employees
GROUP BY Department
ORDER BY TotalSalary DESC;
```

**Explanation:** SUM() adds up values. GROUP BY creates separate groups for each department.

---

### Q18: Average by group
**Question:** Find the average salary for each department.

**Answer:**
```sql
SELECT Department, AVG(Salary) as AverageSalary
FROM Employees
GROUP BY Department;
```

**Explanation:** AVG() calculates the average. GROUP BY ensures one result per department.

---

### Q19: Multiple aggregations
**Question:** Get count, average, max, min, and total salary for each department.

**Answer:**
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

**Explanation:** Use multiple aggregate functions in one query. All get applied to grouped data.

---

### Q20: Aggregation with filtering (HAVING)
**Question:** Find departments where the average salary is greater than 50,000.

**Answer:**
```sql
SELECT Department, AVG(Salary) as AvgSalary
FROM Employees
GROUP BY Department
HAVING AVG(Salary) > 50000;
```

**Explanation:** WHERE filters rows before grouping. HAVING filters groups after aggregation.

---

### Q21: Multiple conditions with WHERE
**Question:** Find orders from Germany with status 'Completed' and amount > 100.

**Answer:**
```sql
SELECT *
FROM Orders
WHERE Country = 'Germany'
  AND Status = 'Completed'
  AND Amount > 100;
```

**Explanation:** Multiple WHERE conditions using AND. All must be true for a row to be included.

---

### Q22: Count with WHERE clause
**Question:** Count active customers from the USA.

**Answer:**
```sql
SELECT COUNT(*) as ActiveUSACustomers
FROM Customers
WHERE Country = 'USA'
  AND Status = 'Active';
```

**Explanation:** WHERE clause filters rows before COUNT applies. Only matching rows are counted.

---

## Ranking & Ordering

### Q23: Rank employees by salary
**Question:** Display employees with their salary rank.

**Answer:**
```sql
SELECT 
  Name,
  Salary,
  RANK() OVER (ORDER BY Salary DESC) as SalaryRank
FROM Employees;
```

**Explanation:** RANK() assigns a rank to each row. ORDER BY specifies the ranking order (DESC = highest first).

---

### Q24: Rank within department
**Question:** Rank employees within their own department by salary.

**Answer:**
```sql
SELECT 
  Name,
  Department,
  Salary,
  RANK() OVER (PARTITION BY Department ORDER BY Salary DESC) as DeptRank
FROM Employees;
```

**Explanation:** PARTITION BY divides data into groups. Ranking starts fresh for each partition.

---

### Q25: Dense rank (no gaps)
**Question:** Display employees with dense ranking (no gaps if same salary).

**Answer:**
```sql
SELECT 
  Name,
  Salary,
  DENSE_RANK() OVER (ORDER BY Salary DESC) as DenseRank
FROM Employees;
```

**Explanation:** DENSE_RANK() doesn't skip numbers after ties. RANK() would skip numbers.

---

### Q26: Sequential row numbers
**Question:** Assign sequential numbers to employees ordered by salary.

**Answer:**
```sql
SELECT 
  ROW_NUMBER() OVER (ORDER BY Salary DESC) as RowNum,
  Name,
  Salary
FROM Employees;
```

**Explanation:** ROW_NUMBER() gives sequential numbers starting from 1.

---

### Q27: Order by multiple columns
**Question:** Order employees by department (A-Z) then by salary (highest first).

**Answer:**
```sql
SELECT Name, Department, Salary
FROM Employees
ORDER BY Department ASC, Salary DESC;
```

**Explanation:** Multiple ORDER BY columns. ASC (default) for ascending, DESC for descending.

---

## String Operations

### Q28: Concatenate columns
**Question:** Create a full name by combining first and last names.

**Answer:**
```sql
-- Method 1
SELECT CONCAT(FirstName, ' ', LastName) as FullName
FROM Employees;

-- Method 2 (SQL Standard)
SELECT FirstName || ' ' || LastName as FullName
FROM Employees;
```

**Explanation:** CONCAT() joins strings. || operator is used in some databases.

---

### Q29: String length
**Question:** Find employees whose name is longer than 10 characters.

**Answer:**
```sql
SELECT Name, LENGTH(Name) as NameLength
FROM Employees
WHERE LENGTH(Name) > 10;
```

**Explanation:** LENGTH() returns the number of characters. Can be used in WHERE to filter.

---

### Q30: Substring
**Question:** Extract the first 5 characters of each employee name.

**Answer:**
```sql
SELECT SUBSTRING(Name, 1, 5) as FirstFive
FROM Employees;
```

**Explanation:** SUBSTRING(column, start_position, length). Position starts at 1, not 0.

---

### Q31: UPPER and LOWER
**Question:** Convert names to uppercase and lowercase.

**Answer:**
```sql
SELECT 
  Name,
  UPPER(Name) as NameUpper,
  LOWER(Name) as NameLower
FROM Employees;
```

**Explanation:** UPPER() converts to uppercase. LOWER() converts to lowercase.

---

### Q32: Pattern matching with LIKE
**Question:** Find all employees whose name starts with 'J'.

**Answer:**
```sql
SELECT *
FROM Employees
WHERE Name LIKE 'J%';
```

**Explanation:** % is wildcard for any characters. 'J%' = starts with J. '%J%' = contains J.

---

## Date Operations

### Q33: Current date and time
**Question:** Get today's date.

**Answer:**
```sql
-- SQL Server
SELECT GETDATE() as Today;

-- MySQL
SELECT CURDATE() as Today;

-- PostgreSQL
SELECT NOW() as Today;
```

**Explanation:** Different databases use different functions for current date.

---

### Q34: Date difference
**Question:** Calculate the number of days between start and end dates.

**Answer:**
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

**Explanation:** Database syntax differs for date calculations.

---

### Q35: Add days to a date
**Question:** Add 30 days to the order date.

**Answer:**
```sql
-- SQL Server
SELECT DATEADD(DAY, 30, OrderDate) as DeliveryDate FROM Orders;

-- MySQL
SELECT DATE_ADD(OrderDate, INTERVAL 30 DAY) as DeliveryDate FROM Orders;

-- PostgreSQL
SELECT OrderDate + INTERVAL '30 days' as DeliveryDate FROM Orders;
```

**Explanation:** Each database has different syntax for date arithmetic.

---

### Q36: Extract year, month, day
**Question:** Extract the year, month, and day from join dates.

**Answer:**
```sql
SELECT 
  YEAR(JoinDate) as JoinYear,
  MONTH(JoinDate) as JoinMonth,
  DAY(JoinDate) as JoinDay
FROM Employees;
```

**Explanation:** YEAR(), MONTH(), DAY() extract date components.

---

### Q37: Records from last 30 days
**Question:** Get all orders from the last 30 days.

**Answer:**
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

**Explanation:** Use WHERE to filter dates. Syntax varies by database.

---

## Complex Filtering

### Q38: NOT EXISTS (find unmatched records)
**Question:** Find all customers who haven't placed any orders.

**Answer:**
```sql
SELECT *
FROM Customers c
WHERE NOT EXISTS (
  SELECT 1 FROM Orders o WHERE o.CustomerID = c.CustomerID
);
```

**Explanation:** NOT EXISTS returns true if subquery has no results. Efficient for finding unmatched records.

---

### Q39: IN vs EXISTS comparison
**Question:** Find customers who have placed orders using two methods.

**Answer:**
```sql
-- Method 1: IN
SELECT *
FROM Customers
WHERE CustomerID IN (SELECT CustomerID FROM Orders);

-- Method 2: EXISTS (usually more efficient)
SELECT *
FROM Customers c
WHERE EXISTS (SELECT 1 FROM Orders o WHERE o.CustomerID = c.CustomerID);
```

**Explanation:** EXISTS checks existence (returns true/false). IN checks membership in a list. EXISTS is often faster.

---

### Q40: Multiple conditions with AND/OR
**Question:** Find orders with status 'Completed' or 'Pending' AND amount > 100.

**Answer:**
```sql
SELECT *
FROM Orders
WHERE (Status = 'Completed' OR Status = 'Pending')
  AND Amount > 100;
```

**Explanation:** Use parentheses to group OR conditions. AND has higher precedence than OR.

---

### Q41: CASE statement
**Question:** Categorize employees as 'High', 'Medium', or 'Low' earners based on salary.

**Answer:**
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

**Explanation:** CASE evaluates conditions in order, returns first match. ELSE is the default.

---

## Self Joins

### Q42: Find manager-employee relationships
**Question:** Display each employee and their manager's name.

**Answer:**
```sql
SELECT 
  e.Name as Employee,
  m.Name as Manager
FROM Employees e
LEFT JOIN Employees m ON e.ManagerID = m.EmployeeID;
```

**Explanation:** Join a table to itself using aliases. LEFT JOIN includes employees with no manager (NULL).

---

### Q43: Find employees at same salary level
**Question:** Find pairs of employees earning the same salary.

**Answer:**
```sql
SELECT 
  e1.Name as Employee1,
  e2.Name as Employee2,
  e1.Salary
FROM Employees e1
INNER JOIN Employees e2 ON e1.Salary = e2.Salary
  AND e1.EmployeeID < e2.EmployeeID;
```

**Explanation:** Self join on Salary. The extra condition (e1.EmployeeID < e2.EmployeeID) prevents duplicate pairs.

---

## Window Functions

### Q44: Running total
**Question:** Show cumulative sum of salaries as you go down the list.

**Answer:**
```sql
SELECT 
  Name,
  Salary,
  SUM(Salary) OVER (ORDER BY EmployeeID) as RunningTotal
FROM Employees;
```

**Explanation:** Window functions compute values across a "window" of rows. ORDER BY defines the window frame.

---

### Q45: Cumulative sum by department
**Question:** Show running total of salaries within each department.

**Answer:**
```sql
SELECT 
  Name,
  Department,
  Salary,
  SUM(Salary) OVER (PARTITION BY Department ORDER BY EmployeeID) as DeptRunningTotal
FROM Employees;
```

**Explanation:** PARTITION BY resets the window for each department.

---

### Q46: Lead/Lag functions
**Question:** Show current salary, next higher salary, and next lower salary for each employee.

**Answer:**
```sql
SELECT 
  Name,
  Salary,
  LEAD(Salary) OVER (ORDER BY Salary DESC) as NextHigherSalary,
  LAG(Salary) OVER (ORDER BY Salary DESC) as NextLowerSalary
FROM Employees;
```

**Explanation:** LEAD() gets next row value. LAG() gets previous row value.

---

### Q47: Percentage of total
**Question:** Calculate each employee's salary as a percentage of total salary.

**Answer:**
```sql
SELECT 
  Name,
  Salary,
  ROUND(Salary * 100.0 / SUM(Salary) OVER (), 2) as PercentageOfTotal
FROM Employees;
```

**Explanation:** SUM(Salary) OVER () sums ALL rows. Divide individual by total for percentage.

---

## DELETE Operations

### Q48: Delete with WHERE clause
**Question:** Delete all customers from 'USA' who are inactive.

**Answer:**
```sql
DELETE FROM Customers
WHERE Country = 'USA'
  AND Status = 'Inactive';
```

**Explanation:** WHERE clause specifies which rows to delete. Without WHERE, all rows are deleted!

---

### Q49: Delete based on another table
**Question:** Delete orders from customers that don't exist in the Customers table.

**Answer:**
```sql
DELETE FROM Orders
WHERE CustomerID NOT IN (SELECT CustomerID FROM Customers);
```

**Explanation:** Use subquery to identify rows to delete. Prevents orphaned records.

---

### Q50: Delete duplicate records
**Question:** Keep only one record for each email, delete duplicates.

**Answer:**
```sql
DELETE FROM Users
WHERE ID NOT IN (
  SELECT MIN(ID)
  FROM Users
  GROUP BY Email
);
```

**Explanation:** Keep minimum ID per email group, delete all others.

---

### Q51: Delete specific records
**Question:** Delete the employee with the highest salary.

**Answer:**
```sql
DELETE FROM Employees
WHERE Salary = (SELECT MAX(Salary) FROM Employees);
```

**Explanation:** Subquery finds the max salary, DELETE removes matching row.

---

### Q52: Delete with JOIN
**Question:** Delete orders where the associated customer has been inactive for over a year.

**Answer:**
```sql
DELETE o FROM Orders o
INNER JOIN Customers c ON o.CustomerID = c.CustomerID
WHERE c.LastActivityDate < DATEADD(YEAR, -1, GETDATE());
```

**Explanation:** JOIN identifies which rows to delete. Complex deletion with multiple table conditions.

---

### Q53: Delete all records from a table
**Question:** Clear all records from a temporary table.

**Answer:**
```sql
DELETE FROM TempTable;
```

**Explanation:** DELETE without WHERE removes all rows. ⚠️ Be careful! Always backup first.

---

## Comparison Topics

### Q54: TRUNCATE vs DELETE vs DROP
**Question:** Explain the differences between TRUNCATE, DELETE, and DROP.

**Answer:**

| Feature | DELETE | TRUNCATE | DROP |
|---------|--------|----------|------|
| **Type** | DML (Data) | DDL (Structure) | DDL (Structure) |
| **Speed** | Slow | Fast | Very Fast |
| **Space** | Frees gradually | Frees immediately | Removes entirely |
| **WHERE** | ✓ Supported | ✗ NO WHERE | ✗ Removes table |
| **Undo** | Logged (can rollback) | Not logged | Can't undo |
| **Identity** | Continues from last | Resets to seed | N/A |
| **Syntax** | `DELETE FROM table WHERE...` | `TRUNCATE TABLE table` | `DROP TABLE table` |

**Explanation:**
- **DELETE**: Removes rows, logged, slower, can use WHERE
- **TRUNCATE**: Removes all rows at once, not logged, faster, resets identity
- **DROP**: Removes entire table structure

---

### Q55: When to use JOIN vs WHERE
**Question:** When should you use INNER JOIN vs WHERE clause for joining?

**Answer:**
```sql
-- Method 1: Using WHERE (implicit join)
SELECT a.Name, b.Name
FROM TableA a, TableB b
WHERE a.ID = b.ID;

-- Method 2: Using INNER JOIN (explicit join)
SELECT a.Name, b.Name
FROM TableA a
INNER JOIN TableB b ON a.ID = b.ID;
```

**Explanation:**
- **WHERE approach**: Works but mixes join logic with filtering
- **INNER JOIN approach**: Clearer intent, easier to maintain, better for complex queries
- **Best practice**: Use explicit JOIN syntax (INNER/LEFT/RIGHT/FULL)

---

### Q56: IN vs EXISTS performance
**Question:** Which is faster: IN with subquery or EXISTS?

**Answer:**
```sql
-- Slower (usually): IN with subquery
SELECT * FROM Customers
WHERE CustomerID IN (SELECT CustomerID FROM Orders);

-- Faster (usually): EXISTS
SELECT * FROM Customers c
WHERE EXISTS (SELECT 1 FROM Orders o WHERE o.CustomerID = c.CustomerID);
```

**Explanation:**
- **IN**: Evaluates the entire subquery list before checking
- **EXISTS**: Stops as soon as it finds one matching record
- **Best practice**: Use EXISTS for better performance with large datasets
- **Note**: Modern query optimizers may optimize these similarly

---

### Q57: GROUP BY vs PARTITION BY
**Question:** What's the difference between GROUP BY and PARTITION BY (window functions)?

**Answer:**
```sql
-- GROUP BY: Reduces rows (aggregation)
SELECT Department, COUNT(*) as EmployeeCount
FROM Employees
GROUP BY Department;  -- Result: One row per department

-- PARTITION BY: Keeps all rows (window function)
SELECT 
  Name,
  Department,
  COUNT(*) OVER (PARTITION BY Department) as DeptCount
FROM Employees;  -- Result: All employee rows with count
```

**Explanation:**
- **GROUP BY**: Combines rows, reduces result set
- **PARTITION BY**: Divides rows into groups but returns all rows
- **Use GROUP BY**: When you want aggregated summary
- **Use PARTITION BY**: When you want original rows with group calculations

---

### Q58: LEFT JOIN vs NOT IN for exclusion
**Question:** Find customers with no orders using two methods.

**Answer:**
```sql
-- Method 1: LEFT JOIN with IS NULL
SELECT c.CustomerID, c.CustomerName
FROM Customers c
LEFT JOIN Orders o ON c.CustomerID = o.CustomerID
WHERE o.OrderID IS NULL;

-- Method 2: NOT IN
SELECT * FROM Customers
WHERE CustomerID NOT IN (SELECT CustomerID FROM Orders);

-- Method 3: NOT EXISTS (most efficient)
SELECT * FROM Customers c
WHERE NOT EXISTS (SELECT 1 FROM Orders WHERE CustomerID = c.CustomerID);
```

**Explanation:**
- **Method 1 (LEFT JOIN)**: Clear and intuitive, easy to understand
- **Method 2 (NOT IN)**: Simpler syntax but can be slow with NULLs
- **Method 3 (NOT EXISTS)**: Most efficient, recommended for performance
- **Best practice**: Use NOT EXISTS for better performance

---

### Q59: HAVING vs WHERE
**Question:** When do you use HAVING instead of WHERE?

**Answer:**
```sql
-- WRONG: WHERE can't use aggregates
-- SELECT Department, AVG(Salary)
-- FROM Employees
-- WHERE AVG(Salary) > 50000  ❌ ERROR!

-- CORRECT: HAVING for aggregate conditions
SELECT Department, AVG(Salary) as AvgSalary
FROM Employees
GROUP BY Department
HAVING AVG(Salary) > 50000;

-- WHERE filters rows BEFORE grouping
SELECT Department, AVG(Salary) as AvgSalary
FROM Employees
WHERE Salary > 40000  -- Filter individual salaries
GROUP BY Department
HAVING COUNT(*) > 5;   -- Filter groups by count
```

**Explanation:**
- **WHERE**: Filters rows BEFORE grouping/aggregation
- **HAVING**: Filters groups AFTER aggregation
- **Key difference**: WHERE can't use aggregate functions, HAVING can only

---

### Q60: UNION vs UNION ALL
**Question:** Explain the difference between UNION and UNION ALL.

**Answer:**
```sql
-- UNION: Removes duplicates (slower)
SELECT City FROM Customers
UNION
SELECT City FROM Suppliers
ORDER BY City;

-- UNION ALL: Keeps duplicates (faster)
SELECT City FROM Customers
UNION ALL
SELECT City FROM Suppliers
ORDER BY City;
```

**Result Example with data:
- Customers cities: New York, London, Paris, London
- Suppliers cities: London, Paris, Tokyo

**UNION result:** New York, London, Paris, Tokyo (4 rows - duplicates removed)

**UNION ALL result:** New York, London, Paris, London, London, Paris, Tokyo (7 rows - all kept)

**Explanation:**
- **UNION**: Automatically removes duplicate rows, requires sorting (slower)
- **UNION ALL**: Keeps all rows including duplicates (faster)
- **Best practice**: Use UNION ALL unless you specifically need to remove duplicates

---

## Summary

Total queries to practice: **60 questions**

### By Category:
- Basic Joins: 4
- Finding Extremes: 7  
- Duplicates & Unique: 5
- Aggregation: 6
- Ranking: 5
- String Operations: 5
- Date Operations: 5
- Complex Filtering: 4
- Self Joins: 2
- Window Functions: 4
- DELETE Operations: 6
- Comparison Topics: 7

### Key Points to Remember:
✓ Always use explicit JOIN syntax (INNER/LEFT/RIGHT)  
✓ Use WHERE to filter rows, HAVING to filter groups  
✓ EXISTS is faster than IN for large datasets  
✓ Use TRUNCATE for fast table clear (no WHERE)  
✓ DELETE for selective row removal with WHERE  
✓ Window functions keep all rows, GROUP BY reduces  
✓ Test all queries with LIMIT before running on large tables  
✓ Always backup before DELETE or TRUNCATE  

