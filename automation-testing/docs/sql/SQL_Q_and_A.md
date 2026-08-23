# SQL Essential Queries - Q&A Format

Complete collection of essential SQL queries with questions and answers. Total queries to practice: **60+**

**Progression:** Basic → Intermediate → Advanced

---

## Table of Contents
1. [Basic SELECT & WHERE](#basic-select--where) - 4 questions
2. [Basic Joins](#basic-joins) - 4 questions
3. [Aggregation with GROUP BY & HAVING](#aggregation-with-group-by--having) - 6 questions
4. [DELETE Operations](#delete-operations) - 6 questions
5. [Finding Extremes](#finding-extremes) - 7 questions
6. [Duplicates & Unique Records](#duplicates--unique-records) - 5 questions
7. [Ranking & Ordering](#ranking--ordering) - 5 questions
8. [String Operations](#string-operations) - 5 questions
9. [Date Operations](#date-operations) - 5 questions
10. [Complex Filtering & Subqueries](#complex-filtering--subqueries) - 4 questions
11. [Self Joins](#self-joins) - 2 questions
12. [Window Functions](#window-functions) - 4 questions
13. [Comparison Topics](#comparison-topics) - 2 questions

---

## Basic SELECT & WHERE

### Q1: Simple SELECT statement
**Question:** Display all customer names from the Customers table.

**Answer:**
```sql
SELECT CustomerName
FROM Customers;
```

**Explanation:** SELECT specifies columns to display, FROM specifies the table. Use * to select all columns.

---

### Q2: SELECT with single WHERE condition
**Question:** Get all customers from 'USA'.

**Answer:**
```sql
SELECT *
FROM Customers
WHERE Country = 'USA';
```

**Explanation:** WHERE clause filters rows. Only rows matching the condition are returned.

---

### Q3: Multiple conditions with WHERE (AND)
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

### Q4: COUNT with WHERE clause
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

## Basic Joins

### Q5: Simple join (two tables)
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

### Q6: INNER JOIN with explicit syntax
**Question:** Display customer names and their order IDs using explicit INNER JOIN syntax.

**Answer:**
```sql
SELECT c.CustomerName, o.OrderID
FROM Customers c
INNER JOIN Orders o ON c.CustomerID = o.CustomerID;
```

**Explanation:** INNER JOIN explicitly shows the join condition in the ON clause. Only matching records from both tables are returned.

---

### Q7: Three table join
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

### Q8: LEFT JOIN to find unmatched records
**Question:** Get all customers and their orders. Include customers with no orders.

**Answer:**
```sql
SELECT c.CustomerName, o.OrderID
FROM Customers c
LEFT JOIN Orders o ON c.CustomerID = o.CustomerID;
```

**Explanation:** LEFT JOIN returns ALL records from the left table (Customers) and matching records from the right table (Orders). Non-matching will have NULL values.

---

## Aggregation with GROUP BY & HAVING

### Q9: COUNT by group
**Question:** Display each country and how many customers are from that country.

**Answer:**
```sql
SELECT Country, COUNT(*) as CustomerCount
FROM Customers
GROUP BY Country
ORDER BY CustomerCount DESC;
```

**Explanation:** GROUP BY groups records, COUNT(*) counts rows in each group. Always include grouped columns in SELECT.

---

### Q10: SUM by group
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

### Q11: AVG by group
**Question:** Find the average salary for each department.

**Answer:**
```sql
SELECT Department, AVG(Salary) as AverageSalary
FROM Employees
GROUP BY Department;
```

**Explanation:** AVG() calculates the average. GROUP BY ensures one result per department.

---

### Q12: Multiple aggregations
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

### Q13: HAVING to filter groups
**Question:** Find departments where the average salary is greater than 50,000.

**Answer:**
```sql
SELECT Department, AVG(Salary) as AvgSalary
FROM Employees
GROUP BY Department
HAVING AVG(Salary) > 50000;
```

**Explanation:** WHERE filters rows BEFORE grouping. HAVING filters groups AFTER aggregation. Aggregate functions can only be in HAVING, not WHERE.

---

### Q14: WHERE and HAVING together
**Question:** Find departments with average salary > 40,000, excluding employees with salary < 30,000, and only departments with more than 5 employees.

**Answer:**
```sql
SELECT Department, AVG(Salary) as AvgSalary, COUNT(*) as EmployeeCount
FROM Employees
WHERE Salary >= 30000
GROUP BY Department
HAVING AVG(Salary) > 40000 AND COUNT(*) > 5;
```

**Explanation:** WHERE filters rows first, then GROUP BY, then HAVING filters the grouped results.

---

## DELETE Operations

### Q15: Simple DELETE with WHERE
**Question:** Delete all customers from the 'Inactive' status.

**Answer:**
```sql
DELETE FROM Customers
WHERE Status = 'Inactive';
```

**Explanation:** WHERE clause specifies which rows to delete. Without WHERE, ALL rows are deleted! Always test with SELECT first.

---

### Q16: DELETE with multiple conditions
**Question:** Delete all customers from 'USA' who are inactive.

**Answer:**
```sql
DELETE FROM Customers
WHERE Country = 'USA'
  AND Status = 'Inactive';
```

**Explanation:** Use AND/OR in WHERE clause to specify complex deletion criteria.

---

### Q17: DELETE based on another table (subquery)
**Question:** Delete orders from customers that don't exist in the Customers table.

**Answer:**
```sql
DELETE FROM Orders
WHERE CustomerID NOT IN (SELECT CustomerID FROM Customers);
```

**Explanation:** Use subquery to identify rows to delete. Prevents orphaned records.

---

### Q18: DELETE duplicate records
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

### Q19: DELETE specific records with aggregate
**Question:** Delete the employee with the highest salary.

**Answer:**
```sql
DELETE FROM Employees
WHERE Salary = (SELECT MAX(Salary) FROM Employees);
```

**Explanation:** Subquery finds the max salary, DELETE removes matching row.

---

### Q20: Delete all records from a table
**Question:** Clear all records from a temporary table.

**Answer:**
```sql
DELETE FROM TempTable;
```

**Explanation:** DELETE without WHERE removes all rows. ⚠️ Be careful! Always backup first. For speed, use TRUNCATE TABLE instead.

---

## Finding Extremes

### Q21: Maximum value
**Question:** Get the highest salary from the Employees table.

**Answer:**
```sql
SELECT MAX(Salary) as HighestSalary
FROM Employees;
```

**Explanation:** MAX() is an aggregate function that returns the highest value. Use MIN() for lowest.

---

### Q22: Find row with maximum value
**Question:** Display the name and salary of the employee earning the most.

**Answer:**
```sql
SELECT Name, Salary
FROM Employees
WHERE Salary = (SELECT MAX(Salary) FROM Employees);
```

**Explanation:** Use a subquery to find the MAX salary, then filter WHERE clause to get the matching employee.

---

### Q23: Second highest value
**Question:** Get the second highest salary amount from the Employees table.

**Answer:**
```sql
-- Method 1: Using OFFSET (MySQL, PostgreSQL)
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

### Q24: Third highest value
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

### Q25: Nth highest value (general)
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

### Q26: Minimum value
**Question:** Get the minimum salary and the employee name.

**Answer:**
```sql
SELECT Name, Salary
FROM Employees
WHERE Salary = (SELECT MIN(Salary) FROM Employees);
```

**Explanation:** MIN() returns the lowest value. Use a subquery to get the minimum salary value.

---

### Q27: Top N values
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

### Q28: Find duplicate records
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

### Q29: Get all rows with duplicates
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

### Q30: Count unique values
**Question:** Count how many distinct countries are in the Customers table.

**Answer:**
```sql
SELECT COUNT(DISTINCT Country) as UniqueCountries
FROM Customers;
```

**Explanation:** COUNT(DISTINCT column) returns the number of unique values. Use SELECT DISTINCT to list them.

---

### Q31: List distinct values
**Question:** Display all unique countries from customers.

**Answer:**
```sql
SELECT DISTINCT Country
FROM Customers
ORDER BY Country;
```

**Explanation:** DISTINCT removes duplicate values. ORDER BY sorts the results.

---

### Q32: Count occurrences of each value
**Question:** Show how many customers are in each country, sorted by count.

**Answer:**
```sql
SELECT Country, COUNT(*) as CustomerCount
FROM Customers
GROUP BY Country
ORDER BY CustomerCount DESC;
```

**Explanation:** GROUP BY with COUNT shows frequency of each value.

---

## Ranking & Ordering

### Q33: ORDER BY single column
**Question:** List customers ordered by country alphabetically.

**Answer:**
```sql
SELECT * FROM Customers
ORDER BY Country;
```

**Explanation:** ORDER BY sorts results. Default is ASC (ascending). Use DESC for descending.

---

### Q34: ORDER BY multiple columns
**Question:** Order employees by department (A-Z) then by salary (highest first).

**Answer:**
```sql
SELECT Name, Department, Salary
FROM Employees
ORDER BY Department ASC, Salary DESC;
```

**Explanation:** Multiple ORDER BY columns. ASC (default) for ascending, DESC for descending.

---

### Q35: Rank rows by value
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

### Q36: Rank within groups
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

### Q37: Dense rank vs RANK
**Question:** Show the difference between RANK and DENSE_RANK.

**Answer:**
```sql
SELECT 
  Name,
  Salary,
  RANK() OVER (ORDER BY Salary DESC) as RegularRank,
  DENSE_RANK() OVER (ORDER BY Salary DESC) as DenseRank,
  ROW_NUMBER() OVER (ORDER BY Salary DESC) as RowNum
FROM Employees;
```

**Explanation:** 
- RANK() skips numbers after ties (1, 1, 3)
- DENSE_RANK() doesn't skip (1, 1, 2)
- ROW_NUMBER() always sequential (1, 2, 3)

---

## String Operations

### Q38: Concatenate columns
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

### Q39: String length
**Question:** Find employees whose name is longer than 10 characters.

**Answer:**
```sql
SELECT Name, LENGTH(Name) as NameLength
FROM Employees
WHERE LENGTH(Name) > 10;
```

**Explanation:** LENGTH() returns the number of characters. Can be used in WHERE to filter.

---

### Q40: Substring
**Question:** Extract the first 5 characters of each employee name.

**Answer:**
```sql
SELECT SUBSTRING(Name, 1, 5) as FirstFive
FROM Employees;
```

**Explanation:** SUBSTRING(column, start_position, length). Position starts at 1, not 0.

---

### Q41: UPPER and LOWER case
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

### Q42: Pattern matching with LIKE
**Question:** Find all employees whose name starts with 'J'.

**Answer:**
```sql
SELECT *
FROM Employees
WHERE Name LIKE 'J%';
```

**Explanation:** % is wildcard for any characters. 'J%' = starts with J. '%J%' = contains J. '_J%' = second character is J.

---

## Date Operations

### Q43: Current date
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

### Q44: Date difference
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

### Q45: Add days to a date
**Question:** Add 30 days to the order date to get delivery date.

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

### Q46: Extract date parts
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

### Q47: Recent records filter
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

## Complex Filtering & Subqueries

### Q48: IN operator
**Question:** Find customers from specific countries: Germany, France, and UK.

**Answer:**
```sql
SELECT * FROM Customers
WHERE Country IN ('Germany', 'France', 'UK');
```

**Explanation:** IN checks if value matches any item in a list. Cleaner than multiple OR conditions.

---

### Q49: NOT IN operator
**Question:** Find customers NOT from Germany, France, or UK.

**Answer:**
```sql
SELECT * FROM Customers
WHERE Country NOT IN ('Germany', 'France', 'UK');
```

**Explanation:** NOT IN excludes rows matching any value in the list.

---

### Q50: EXISTS (find matching)
**Question:** Find customers who have placed orders using EXISTS.

**Answer:**
```sql
SELECT *
FROM Customers c
WHERE EXISTS (SELECT 1 FROM Orders o WHERE o.CustomerID = c.CustomerID);
```

**Explanation:** EXISTS checks if subquery returns any rows. Returns true/false without returning data.

---

### Q51: NOT EXISTS (find unmatched)
**Question:** Find all customers who haven't placed any orders.

**Answer:**
```sql
SELECT *
FROM Customers c
WHERE NOT EXISTS (
  SELECT 1 FROM Orders o WHERE o.CustomerID = c.CustomerID
);
```

**Explanation:** NOT EXISTS returns true if subquery has no results. Efficient for finding unmatched records. Often faster than NOT IN.

---

## Self Joins

### Q52: Manager-employee relationship
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

### Q53: Find pairs with same value
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

### Q54: Running total
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

### Q55: Cumulative sum by group
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

### Q56: Lead/Lag functions
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

### Q57: Percentage of total
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

## Comparison Topics

### Q58: TRUNCATE vs DELETE
**Question:** Explain the differences between TRUNCATE and DELETE.

**Answer:**

| Feature | DELETE | TRUNCATE |
|---------|--------|----------|
| **Type** | DML (Data modification) | DDL (Data definition) |
| **Speed** | Slower (row by row) | Faster (removes all at once) |
| **WHERE clause** | ✓ Supported | ✗ NO WHERE clause |
| **Space freed** | Gradually | Immediately |
| **Logging** | Fully logged | Not logged |
| **Rollback** | Yes (can undo) | Can't undo |
| **Identity reset** | Continues from last value | Resets to seed |
| **Syntax** | `DELETE FROM table WHERE...` | `TRUNCATE TABLE table` |

**Explanation:**
- **DELETE**: Removes specific rows, logged, slower, can use WHERE
- **TRUNCATE**: Removes all rows at once, not logged, faster, resets identity
- **Best practice**: Use TRUNCATE for clearing entire table, DELETE for selective removal

---

### Q59: IN vs EXISTS performance
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
- **IN**: Evaluates the entire subquery list before checking membership
- **EXISTS**: Stops as soon as it finds one matching record (faster)
- **Best practice**: Use EXISTS for better performance with large datasets
- **Modern note**: Modern query optimizers may optimize these similarly

---

### Q60: GROUP BY vs PARTITION BY
**Question:** What's the difference between GROUP BY and PARTITION BY?

**Answer:**
```sql
-- GROUP BY: Reduces rows (aggregation)
SELECT Department, COUNT(*) as EmployeeCount
FROM Employees
GROUP BY Department;  
-- Result: One row per department

-- PARTITION BY: Keeps all rows (window function)
SELECT 
  Name,
  Department,
  COUNT(*) OVER (PARTITION BY Department) as DeptCount
FROM Employees;  
-- Result: All employee rows with count repeated
```

**Explanation:**
- **GROUP BY**: Combines rows, reduces result set to summary
- **PARTITION BY**: Divides rows into groups but returns all rows with calculations
- **Use GROUP BY**: When you want aggregated summary data
- **Use PARTITION BY**: When you want original rows with group calculations added

---

## Summary

Total queries to practice: **60 questions**

### By Category:
- Basic SELECT & WHERE: 4
- Basic Joins: 4
- Aggregation with GROUP BY & HAVING: 6
- DELETE Operations: 6
- Finding Extremes: 7  
- Duplicates & Unique: 5
- Ranking & Ordering: 5
- String Operations: 5
- Date Operations: 5
- Complex Filtering & Subqueries: 4
- Self Joins: 2
- Window Functions: 4
- Comparison Topics: 2

### Key Points to Remember:
✓ Start with simple SELECT and WHERE  
✓ Always use explicit JOIN syntax (INNER/LEFT/RIGHT)  
✓ Use WHERE to filter rows, HAVING to filter groups  
✓ EXISTS is faster than IN for large datasets  
✓ Use TRUNCATE for fast table clear (no WHERE)  
✓ DELETE for selective row removal with WHERE  
✓ Window functions keep all rows, GROUP BY reduces  
✓ Test all queries with LIMIT before running on large tables  
✓ Always backup before DELETE or TRUNCATE  

