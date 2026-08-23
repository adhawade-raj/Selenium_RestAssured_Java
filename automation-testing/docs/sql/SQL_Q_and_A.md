# SQL Essential Queries - Q&A Format

Complete collection of essential SQL queries with questions and answers organized from basic to advanced with theory explanations.

**Total questions: 53**

**Progression:** Basic → Intermediate → Advanced → Theory

---

## Table of Contents
1. [Basic SELECT & WHERE](#basic-select--where) - 4 questions
2. [Basic Joins](#basic-joins) - 2 questions
3. [Aggregation with GROUP BY & HAVING](#aggregation-with-group-by--having) - 5 questions
4. [DELETE Operations & Theory](#delete-operations--theory) - 6 questions
5. [Finding Extremes](#finding-extremes) - 5 questions
6. [Duplicates & Unique Records](#duplicates--unique-records) - 4 questions
7. [Ranking & Ordering](#ranking--ordering) - 4 questions
8. [String Operations](#string-operations) - 4 questions
9. [Date Operations](#date-operations) - 4 questions
10. [Complex Filtering & Subqueries](#complex-filtering--subqueries) - 4 questions
11. [Window Functions](#window-functions) - 3 questions
12. [Theory & Comparisons](#theory--comparisons) - 8 questions

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

### Q3: Multiple conditions with WHERE
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

### Q4: Count with WHERE clause
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

-- Modern explicit syntax
SELECT a.Name, b.Name
FROM TableA a
INNER JOIN TableB b ON a.ID = b.ID;
```

**Explanation:** This is the simplest form of joining. The WHERE clause acts as the join condition. Modern syntax uses INNER JOIN keyword for clarity.

---

### Q6: LEFT JOIN to find unmatched records
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

### Q7: COUNT by group
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

### Q8: SUM by group
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

### Q9: HAVING to filter groups
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

### Q10: WHERE and HAVING together
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

### Q11: Multiple aggregations
**Question:** Get count, average, max, min, and total salary for each department in one query.

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

## DELETE Operations & Theory

### Q12: Simple DELETE with WHERE
**Question:** Delete all customers from the 'Inactive' status.

**Answer:**
```sql
DELETE FROM Customers
WHERE Status = 'Inactive';
```

**Explanation:** WHERE clause specifies which rows to delete. Without WHERE, ALL rows are deleted! Always test with SELECT first.

---

### Q13: DELETE with multiple conditions
**Question:** Delete all customers from 'USA' who are inactive.

**Answer:**
```sql
DELETE FROM Customers
WHERE Country = 'USA'
  AND Status = 'Inactive';
```

**Explanation:** Use AND/OR in WHERE clause to specify complex deletion criteria.

---

### Q14: DELETE based on subquery
**Question:** Delete orders from customers that don't exist in the Customers table.

**Answer:**
```sql
DELETE FROM Orders
WHERE CustomerID NOT IN (SELECT CustomerID FROM Customers);
```

**Explanation:** Use subquery to identify rows to delete. Prevents orphaned records.

---

### Q15: DELETE duplicate records
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

### Q16: DELETE specific records by subquery
**Question:** Delete the employee with the highest salary.

**Answer:**
```sql
DELETE FROM Employees
WHERE Salary = (SELECT MAX(Salary) FROM Employees);
```

**Explanation:** Subquery finds the max salary, DELETE removes matching row.

---

### Q17 (Theory): DELETE vs TRUNCATE vs DROP
**Question (Theory):** Explain the key differences between DELETE, TRUNCATE, and DROP commands.

**Answer:**

| Feature | DELETE | TRUNCATE | DROP |
|---------|--------|----------|------|
| **SQL Type** | DML (Data Modification) | DDL (Data Definition) | DDL (Data Definition) |
| **Speed** | Slow (row by row) | Very fast | Very fast |
| **WHERE clause** | ✓ Supported | ✗ Not supported | N/A |
| **Space released** | Gradually | Immediately | Completely removed |
| **Transaction log** | Fully logged | Not logged (minimal) | Logged |
| **Rollback** | ✓ Can undo | ✗ Cannot undo | ✗ Cannot undo |
| **Identity reset** | Continues from last | Resets to seed value | Table removed |
| **Trigger** | ✓ Fires triggers | ✗ No triggers | No triggers |
| **Example** | `DELETE FROM table WHERE id=5` | `TRUNCATE TABLE table` | `DROP TABLE table` |

**Explanation:**
- **DELETE**: Remove specific rows, logged, slower, can use WHERE, can rollback
- **TRUNCATE**: Remove all rows at once, not logged, faster, resets identity, can't use WHERE
- **DROP**: Removes entire table structure and data, can't be rolled back (without transaction)
- **Best practice**: Use TRUNCATE for clearing tables, DELETE for selective removal, DROP to remove table permanently

---

## Finding Extremes

### Q18: Maximum value
**Question:** Get the highest salary from the Employees table.

**Answer:**
```sql
SELECT MAX(Salary) as HighestSalary
FROM Employees;
```

**Explanation:** MAX() is an aggregate function that returns the highest value. Use MIN() for lowest.

---

### Q19: Find row with maximum value
**Question:** Display the name and salary of the employee earning the most.

**Answer:**
```sql
SELECT Name, Salary
FROM Employees
WHERE Salary = (SELECT MAX(Salary) FROM Employees);
```

**Explanation:** Use a subquery to find the MAX salary, then filter WHERE clause to get the matching employee.

---

### Q20: Second highest value
**Question:** Get the second highest salary amount from the Employees table (show all 3 methods).

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
- Method 1: OFFSET 1 skips the first row (highest), LIMIT 1 gets the next
- Method 2: Find all salaries less than MAX, then get the MAX of those
- Method 3: Exclude the highest, then get MAX of remaining

---

### Q21: Nth highest value
**Question:** Create a query to find the 5th highest salary.

**Answer:**
```sql
SELECT MAX(Salary) as 5thHighestSalary
FROM Employees
WHERE Salary NOT IN (
  SELECT DISTINCT Salary
  FROM Employees
  ORDER BY Salary DESC
  LIMIT 4
);
```

**Explanation:** Exclude the top 4 salaries, then get the MAX of remaining. For Nth highest, exclude top (N-1) values.

---

### Q22: Top N values
**Question:** Display the top 5 highest distinct salary amounts.

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

### Q23: Find duplicate records
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

### Q24: Get all rows with duplicates
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

### Q25: Count unique values
**Question:** Count how many distinct countries are in the Customers table.

**Answer:**
```sql
SELECT COUNT(DISTINCT Country) as UniqueCountries
FROM Customers;
```

**Explanation:** COUNT(DISTINCT column) returns the number of unique values. Use SELECT DISTINCT to list them.

---

### Q26: Count occurrences of each value
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

### Q27: ORDER BY single column
**Question:** List customers ordered by country alphabetically.

**Answer:**
```sql
SELECT * FROM Customers
ORDER BY Country;
```

**Explanation:** ORDER BY sorts results. Default is ASC (ascending). Use DESC for descending.

---

### Q28: ORDER BY multiple columns
**Question:** Order employees by department (A-Z) then by salary (highest first).

**Answer:**
```sql
SELECT Name, Department, Salary
FROM Employees
ORDER BY Department ASC, Salary DESC;
```

**Explanation:** Multiple ORDER BY columns. First column is primary sort, second is secondary.

---

### Q29: Rank rows with RANK function
**Question:** Display employees with their salary rank, handling ties correctly.

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

### Q30: Rank within groups (PARTITION BY)
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

## String Operations

### Q31: Concatenate columns
**Question:** Create a full name by combining first and last names.

**Answer:**
```sql
SELECT CONCAT(FirstName, ' ', LastName) as FullName
FROM Employees;
```

**Explanation:** CONCAT() joins strings together. Different databases may use || operator instead.

---

### Q32: String length
**Question:** Find employees whose name is longer than 10 characters.

**Answer:**
```sql
SELECT Name, LENGTH(Name) as NameLength
FROM Employees
WHERE LENGTH(Name) > 10;
```

**Explanation:** LENGTH() returns the number of characters. Can be used in WHERE to filter.

---

### Q33: UPPER and LOWER case
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

### Q34: Pattern matching with LIKE
**Question:** Find all employees whose name starts with 'J' or contains 'son'.

**Answer:**
```sql
-- Starts with 'J'
SELECT * FROM Employees WHERE Name LIKE 'J%';

-- Contains 'son'
SELECT * FROM Employees WHERE Name LIKE '%son%';

-- Second character is 'r'
SELECT * FROM Employees WHERE Name LIKE '_r%';
```

**Explanation:** % matches any characters. _ matches single character. 'J%' = starts with J. '%J' = ends with J.

---

## Date Operations

### Q35: Current date
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

### Q36: Date difference
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

### Q37: Add days to a date
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

### Q38: Extract date parts
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

## Complex Filtering & Subqueries

### Q39: IN operator
**Question:** Find customers from specific countries: Germany, France, and UK.

**Answer:**
```sql
SELECT * FROM Customers
WHERE Country IN ('Germany', 'France', 'UK');
```

**Explanation:** IN checks if value matches any item in a list. Cleaner than multiple OR conditions.

---

### Q40: EXISTS (find matching)
**Question:** Find customers who have placed orders using EXISTS.

**Answer:**
```sql
SELECT *
FROM Customers c
WHERE EXISTS (SELECT 1 FROM Orders o WHERE o.CustomerID = c.CustomerID);
```

**Explanation:** EXISTS checks if subquery returns any rows. Returns true/false without returning data.

---

### Q41: NOT EXISTS (find unmatched)
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

### Q42: CASE statement
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

## Window Functions

### Q43: Running total
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

### Q44: Cumulative sum by group
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

### Q45: Lead/Lag functions
**Question:** Show current salary and next/previous employee's salary in order.

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

## Theory & Comparisons

### Q46 (Theory): What is a JOIN?
**Question (Theory):** Explain what a JOIN is and name the different types of JOINs.

**Answer:**

A **JOIN** combines rows from two or more tables based on a related column between them.

**Main Types of JOINs:**
1. **INNER JOIN** - Returns only matching records from both tables
2. **LEFT JOIN** - Returns all records from left table and matching records from right
3. **RIGHT JOIN** - Returns matching records from left and all records from right
4. **FULL OUTER JOIN** - Returns all records when match in either table
5. **CROSS JOIN** - Returns Cartesian product (all combinations)

**Example:**
```sql
-- INNER JOIN: Only customers with orders
SELECT c.Name, o.OrderID
FROM Customers c
INNER JOIN Orders o ON c.CustomerID = o.CustomerID;
```

**Explanation:** Most basic syntax is simply: `SELECT a.Name, b.Name FROM a, b WHERE a.ID = b.ID`

---

### Q48 (Theory): Difference between JOIN conditions
**Question (Theory):** What's the difference between filtering in JOIN ON clause vs WHERE clause?

**Answer:**
```sql
-- Filter in ON (affects what gets joined)
SELECT c.Name, o.OrderID
FROM Customers c
LEFT JOIN Orders o ON c.CustomerID = o.CustomerID 
  AND o.Amount > 100;
-- Result: All customers, but only orders > 100 (others are NULL)

-- Filter in WHERE (affects final result)
SELECT c.Name, o.OrderID
FROM Customers c
LEFT JOIN Orders o ON c.CustomerID = o.CustomerID
WHERE o.Amount > 100;
-- Result: Only customers WITH orders > 100 (LEFT JOIN becomes INNER)
```

**Explanation:**
- **ON clause**: Determines which rows to join (happens before)
- **WHERE clause**: Filters the joined result (happens after)
- With LEFT/RIGHT JOIN, these produce different results!

---

### Q49 (Theory): WHERE vs HAVING
**Question (Theory):** Explain the difference between WHERE and HAVING clauses.

**Answer:**
```sql
-- WHERE: Filters BEFORE grouping (row level)
SELECT Department, AVG(Salary)
FROM Employees
WHERE Salary > 30000  -- Filter individual rows first
GROUP BY Department;

-- HAVING: Filters AFTER grouping (group level)
SELECT Department, AVG(Salary)
FROM Employees
GROUP BY Department
HAVING AVG(Salary) > 50000;  -- Filter groups after aggregation

-- WRONG: Can't use aggregate in WHERE
-- SELECT Department, AVG(Salary) FROM Employees WHERE AVG(Salary) > 50000;
```

**Explanation:**
- **WHERE**: Filters rows before GROUP BY. Can't use aggregate functions.
- **HAVING**: Filters groups after GROUP BY. Can only use aggregate functions.
- **Order**: WHERE → GROUP BY → HAVING

---

### Q50 (Theory): IN vs EXISTS performance
**Question (Theory):** Which is faster for subqueries: IN or EXISTS?

**Answer:**
```sql
-- IN: Evaluates entire subquery list
SELECT * FROM Customers
WHERE CustomerID IN (SELECT CustomerID FROM Orders);

-- EXISTS: Stops on first match (faster)
SELECT * FROM Customers c
WHERE EXISTS (SELECT 1 FROM Orders WHERE CustomerID = c.CustomerID);
```

**Explanation:**
- **IN**: Evaluates all values in subquery list, then checks membership
- **EXISTS**: Stops as soon as it finds one matching record
- **Performance**: EXISTS typically faster, especially with large datasets
- **Best practice**: Use EXISTS for better performance
- **Modern note**: Query optimizers often optimize these identically now

---

### Q51 (Theory): GROUP BY vs PARTITION BY
**Question (Theory):** What's the difference between GROUP BY and PARTITION BY (window functions)?

**Answer:**
```sql
-- GROUP BY: Reduces rows to summary
SELECT Department, COUNT(*) as Count
FROM Employees
GROUP BY Department;
-- Result: One row per department

-- PARTITION BY: Keeps all rows, adds calculation
SELECT Name, Department, COUNT(*) OVER (PARTITION BY Department) as DeptCount
FROM Employees;
-- Result: All employee rows with count repeated per department
```

**Explanation:**
- **GROUP BY**: Combines rows, reduces result set to groups
- **PARTITION BY**: Divides rows into groups but returns all rows
- **Use GROUP BY**: For aggregated summary data
- **Use PARTITION BY**: For original rows with group calculations

---

### Q52 (Theory): DISTINCT vs GROUP BY
**Question (Theory):** When would you use DISTINCT vs GROUP BY?

**Answer:**
```sql
-- DISTINCT: Remove duplicates (simple)
SELECT DISTINCT Country
FROM Customers;

-- GROUP BY: Remove duplicates + can aggregate
SELECT Country, COUNT(*) as Count
FROM Customers
GROUP BY Country;

-- Performance: DISTINCT usually faster for simple cases
-- GROUP BY needed when you need aggregates
```

**Explanation:**
- **DISTINCT**: Remove duplicate rows, no aggregation
- **GROUP BY**: Remove duplicates and allow aggregation (COUNT, SUM, AVG, etc.)
- **Use DISTINCT**: For simple duplicate removal
- **Use GROUP BY**: When you need aggregate functions with groups

---

### Q53 (Theory): Aggregate Functions - When to use each
**Question (Theory):** Explain the purpose and usage of COUNT, SUM, AVG, MIN, and MAX.

**Answer:**
```sql
SELECT 
  COUNT(*) as TotalRows,           -- Number of rows
  COUNT(DISTINCT Email) as UniqueEmails,  -- Non-NULL unique values
  SUM(Salary) as TotalSalary,      -- Sum of values
  AVG(Salary) as AverageSalary,    -- Average value
  MIN(Salary) as MinimumSalary,    -- Lowest value
  MAX(Salary) as MaximumSalary     -- Highest value
FROM Employees;
```

**Explanation:**
- **COUNT(\*)**: Total number of rows
- **COUNT(column)**: Non-NULL values in column
- **COUNT(DISTINCT col)**: Unique non-NULL values
- **SUM()**: Total of all values
- **AVG()**: Average of all values
- **MIN()**: Lowest value
- **MAX()**: Highest value

---

## Quick Reference Table

| Category | Key Queries | Use Case |
|----------|------------|----------|
| **SELECT** | SELECT col FROM table WHERE condition | Retrieve specific rows and columns |
| **JOIN** | INNER/LEFT/RIGHT JOIN ON condition | Combine data from multiple tables |
| **GROUP BY** | GROUP BY col HAVING aggregate() | Summarize data by groups |
| **Aggregate** | COUNT, SUM, AVG, MIN, MAX | Calculate statistics |
| **Subquery** | SELECT FROM (SELECT) | Nested queries for complex filtering |
| **Window Functions** | SUM/RANK OVER (PARTITION BY) | Calculations across row windows |
| **DELETE** | DELETE FROM WHERE condition | Remove specific rows with rollback |
| **TRUNCATE** | TRUNCATE TABLE | Fast removal of all rows, identity resets |
| **DROP** | DROP TABLE | Remove entire table structure |
| **IN/EXISTS** | WHERE col IN (SELECT) / EXISTS | Check membership or existence |
| **String** | CONCAT, SUBSTRING, UPPER, LOWER | Text manipulation |
| **DISTINCT** | SELECT DISTINCT col | Remove duplicate values |
