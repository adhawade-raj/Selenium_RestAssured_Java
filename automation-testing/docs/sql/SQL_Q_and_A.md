# SQL Queries & Concepts

A practical collection of commonly used SQL queries and concepts.

## Table of Contents

1. [SELECT and Filtering](#1-select-and-filtering)
2. [DISTINCT and ORDER BY](#2-distinct-and-order-by)
3. [Aggregate Functions](#3-aggregate-functions)
4. [GROUP BY](#4-group-by)
5. [HAVING](#5-having)
6. [WHERE vs HAVING](#6-where-vs-having)
7. [INNER JOIN](#7-inner-join)
8. [Simple Join (Implicit Join)](#8-simple-join-implicit-join)
9. [LEFT JOIN and Unmatched Records](#9-left-join-and-unmatched-records)
10. [Multiple Table JOIN](#10-multiple-table-join)
11. [ON vs WHERE](#11-on-vs-where)
12. [Highest and Lowest Salary](#12-highest-and-lowest-salary)
13. [Second Highest and Second Lowest Salary](#13-second-highest-and-second-lowest-salary)
14. [Nth Highest and Nth Lowest Salary](#14-nth-highest-and-nth-lowest-salary)
15. [Top N and Bottom N Records](#15-top-n-and-bottom-n-records)
16. [Highest Salary per Department](#16-highest-salary-per-department)
17. [Second Highest Salary per Department](#17-second-highest-salary-per-department)
18. [Employees Above Average Salary](#18-employees-above-average-salary)
19. [Duplicate Values](#19-duplicate-values)
20. [Duplicate Records](#20-duplicate-records)
21. [Identify Duplicate Rows](#21-identify-duplicate-rows)
22. [NULL Handling](#22-null-handling)
23. [IN, NOT IN and EXISTS](#23-in-not-in-and-exists)
24. [NOT EXISTS / Missing Records](#24-not-exists--missing-records)
25. [Subquery](#25-subquery)
26. [Correlated Subquery](#26-correlated-subquery)
27. [CASE Statement](#27-case-statement)
28. [String Queries](#28-string-queries)
29. [Date Queries](#29-date-queries)
30. [ROW_NUMBER, RANK and DENSE_RANK](#30-row_number-rank-and-dense_rank)
31. [Latest Record per Group](#31-latest-record-per-group)
32. [Previous and Next Record](#32-previous-and-next-record)
33. [Running Total](#33-running-total)
34. [UNION vs UNION ALL](#34-union-vs-union-all)
35. [DELETE, TRUNCATE and DROP](#35-delete-truncate-and-drop)
36. [Employees Without Department](#36-employees-without-department)
37. [Customers Without Orders](#37-customers-without-orders)
38. [Orders Without Valid Customers](#38-orders-without-valid-customers)
39. [Employees Earning More Than Manager](#39-employees-earning-more-than-manager)
40. [Same Salary Records](#40-same-salary-records)
41. [Practical Data Validation Query](#41-practical-data-validation-query)

---

## 1. SELECT and Filtering

**Q1. How do you fetch records using multiple conditions?**

```sql
SELECT Name, Department, Salary
FROM Employees
WHERE Department = 'IT'
AND Salary > 50000;
```

Using IN:

```sql
SELECT *
FROM Employees
WHERE Department IN ('IT', 'HR');
```

Using BETWEEN:

```sql
SELECT *
FROM Employees
WHERE Salary BETWEEN 50000 AND 80000;
```

---

## 2. DISTINCT and ORDER BY

**Q2. How do you find unique values and sort records?**

```sql
SELECT DISTINCT Department
FROM Employees
ORDER BY Department;
```

Highest salary first:

```sql
SELECT Name, Salary
FROM Employees
ORDER BY Salary DESC;
```

---

## 3. Aggregate Functions

**Q3. How do you use COUNT, SUM, AVG, MIN and MAX?**

```sql
SELECT COUNT(*) AS EmployeeCount,
       SUM(Salary) AS TotalSalary,
       AVG(Salary) AS AverageSalary,
       MIN(Salary) AS LowestSalary,
       MAX(Salary) AS HighestSalary
FROM Employees;
```

COUNT variations:

```sql
SELECT COUNT(*) AS TotalRows,
       COUNT(Email) AS NonNullEmails,
       COUNT(DISTINCT Email) AS UniqueEmails
FROM Employees;
```

---

## 4. GROUP BY

**Q4. How do you find employee count, total salary and average salary department-wise?**

```sql
SELECT Department,
       COUNT(*) AS EmployeeCount,
       SUM(Salary) AS TotalSalary,
       AVG(Salary) AS AverageSalary
FROM Employees
GROUP BY Department;
```

---

## 5. HAVING

**Q5. How do you find departments having more than 5 employees and average salary greater than 50000?**

```sql
SELECT Department,
       COUNT(*) AS EmployeeCount,
       AVG(Salary) AS AverageSalary
FROM Employees
GROUP BY Department
HAVING COUNT(*) > 5
AND AVG(Salary) > 50000;
```

---

## 6. WHERE vs HAVING

**Q6. What is the difference between WHERE and HAVING?**

- **WHERE** filters individual rows.
- **HAVING** filters grouped results.

```sql
SELECT Department,
       AVG(Salary) AS AverageSalary
FROM Employees
WHERE Salary >= 30000
GROUP BY Department
HAVING AVG(Salary) > 50000;
```

Conceptually:

```
WHERE → GROUP BY → HAVING
```

---

## 7. INNER JOIN

**Q7. How do you join Employees and Departments?**

```sql
SELECT e.Name,
       e.Salary,
       d.DepartmentName
FROM Employees e
INNER JOIN Departments d
ON e.DepartmentID = d.DepartmentID;
```

INNER JOIN returns only matching records.

---

## 8. Simple Join (Implicit Join)

**Q8. How do you perform a simple join using comma-separated syntax?**

Simple join using comma-separated tables (Cartesian product with WHERE condition):

```sql
SELECT e.Name,
       e.Salary,
       d.DepartmentName
FROM Employees e, Departments d
WHERE e.DepartmentID = d.DepartmentID;
```

Multiple table join:

```sql
SELECT e.Name,
       d.DepartmentName,
       l.LocationName
FROM Employees e, Departments d, Locations l
WHERE e.DepartmentID = d.DepartmentID
AND d.LocationID = l.LocationID;
```

**Note:** This syntax produces a Cartesian product of all rows first, then filters with WHERE. It's functionally equivalent to INNER JOIN but less efficient. Prefer explicit JOIN syntax for clarity and performance.

---

## 9. LEFT JOIN and Unmatched Records

**Q8. How do you find employees who don't have a department?**

```sql
SELECT e.*
FROM Employees e
LEFT JOIN Departments d
ON e.DepartmentID = d.DepartmentID
WHERE d.DepartmentID IS NULL;
```

All employees including unmatched ones:

```sql
SELECT e.Name,
       d.DepartmentName
FROM Employees e
LEFT JOIN Departments d
ON e.DepartmentID = d.DepartmentID;
```

---

## 10. Multiple Table JOIN

**Q9. How do you join three tables?**

```sql
SELECT e.Name,
       d.DepartmentName,
       l.LocationName
FROM Employees e
INNER JOIN Departments d
ON e.DepartmentID = d.DepartmentID
INNER JOIN Locations l
ON d.LocationID = l.LocationID;
```

---

## 11. ON vs WHERE

**Q10. What is the difference between ON and WHERE in a JOIN?**

Condition in ON:

```sql
SELECT c.CustomerName,
       o.OrderID
FROM Customers c
LEFT JOIN Orders o
ON c.CustomerID = o.CustomerID
AND o.Amount > 100;
```

Condition in WHERE:

```sql
SELECT c.CustomerName,
       o.OrderID
FROM Customers c
LEFT JOIN Orders o
ON c.CustomerID = o.CustomerID
WHERE o.Amount > 100;
```

With an outer JOIN, these can produce different results.

---

## 12. Highest and Lowest Salary

**Q11. How do you find the highest and lowest salary and the corresponding employees?**

Highest:

```sql
SELECT MAX(Salary) AS HighestSalary
FROM Employees;
```

Lowest:

```sql
SELECT MIN(Salary) AS LowestSalary
FROM Employees;
```

Highest salary employee(s):

```sql
SELECT *
FROM Employees
WHERE Salary = (
    SELECT MAX(Salary)
    FROM Employees
);
```

Lowest salary employee(s):

```sql
SELECT *
FROM Employees
WHERE Salary = (
    SELECT MIN(Salary)
    FROM Employees
);
```

---

## 13. Second Highest and Second Lowest Salary

**Q12. How do you find the second highest and second lowest salary?**

Second highest:

```sql
SELECT MAX(Salary) AS SecondHighestSalary
FROM Employees
WHERE Salary < (
    SELECT MAX(Salary)
    FROM Employees
);
```

Second lowest:

```sql
SELECT MIN(Salary) AS SecondLowestSalary
FROM Employees
WHERE Salary > (
    SELECT MIN(Salary)
    FROM Employees
);
```

Employee(s) with second highest:

```sql
SELECT *
FROM Employees
WHERE Salary = (
    SELECT MAX(Salary)
    FROM Employees
    WHERE Salary < (
        SELECT MAX(Salary)
        FROM Employees
    )
);
```

Employee(s) with second lowest:

```sql
SELECT *
FROM Employees
WHERE Salary = (
    SELECT MIN(Salary)
    FROM Employees
    WHERE Salary > (
        SELECT MIN(Salary)
        FROM Employees
    )
);
```

---

## 14. Nth Highest and Nth Lowest Salary

**Q13. How do you find the Nth highest and Nth lowest salary?**

Nth highest:

```sql
SELECT Salary
FROM (
    SELECT Salary,
           DENSE_RANK() OVER (
               ORDER BY Salary DESC
           ) AS SalaryRank
    FROM Employees
) t
WHERE SalaryRank = N;
```

Nth lowest:

```sql
SELECT Salary
FROM (
    SELECT Salary,
           DENSE_RANK() OVER (
               ORDER BY Salary ASC
           ) AS SalaryRank
    FROM Employees
) t
WHERE SalaryRank = N;
```

---

## 14. Top N and Bottom N Records

**Q14. How do you find the top 3 and bottom 3 distinct salaries?**

Top 3:

```sql
SELECT DISTINCT Salary
FROM Employees
ORDER BY Salary DESC
LIMIT 3;
```

Bottom 3:

```sql
SELECT DISTINCT Salary
FROM Employees
ORDER BY Salary ASC
LIMIT 3;
```

---

## 15. Highest Salary per Department

**Q15. How do you find the highest salary and employee(s) with the highest salary in each department?**

Highest salary amount:

```sql
SELECT Department,
       MAX(Salary) AS HighestSalary
FROM Employees
GROUP BY Department;
```

Employee(s):

```sql
SELECT *
FROM (
    SELECT e.*,
           RANK() OVER (
               PARTITION BY Department
               ORDER BY Salary DESC
           ) AS SalaryRank
    FROM Employees e
) t
WHERE SalaryRank = 1;
```

---

## 16. Second Highest Salary per Department

**Q16. How do you find the second highest salary in each department?**

```sql
SELECT *
FROM (
    SELECT e.*,
           DENSE_RANK() OVER (
               PARTITION BY Department
               ORDER BY Salary DESC
           ) AS SalaryRank
    FROM Employees e
) t
WHERE SalaryRank = 2;
```

---

## 17. Employees Above Average Salary

**Q17. How do you find employees earning more than the overall average salary?**

```sql
SELECT *
FROM Employees
WHERE Salary > (
    SELECT AVG(Salary)
    FROM Employees
);
```

---

## 18. Duplicate Values

**Q18. How do you find duplicate values in a column?**

```sql
SELECT Email,
       COUNT(*) AS Occurrence
FROM Users
GROUP BY Email
HAVING COUNT(*) > 1;
```

---

## 19. Duplicate Records

**Q19. How do you find all records containing duplicate values?**

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

---

## 20. Identify Duplicate Rows

**Q20. How do you identify duplicate rows using ROW_NUMBER()?**

```sql
SELECT *
FROM (
    SELECT u.*,
           ROW_NUMBER() OVER (
               PARTITION BY Email
               ORDER BY ID
           ) AS RowNum
    FROM Users u
) t
WHERE RowNum > 1;
```

- `RowNum = 1` is the first occurrence.
- `RowNum > 1` identifies duplicate occurrences.

---

## 21. NULL Handling

**Q21. How do you find and handle NULL values?**

Find NULL:

```sql
SELECT *
FROM Employees
WHERE Department IS NULL;
```

Find non-NULL:

```sql
SELECT *
FROM Employees
WHERE Department IS NOT NULL;
```

Replace NULL:

```sql
SELECT Name,
       COALESCE(Department, 'Not Assigned') AS Department
FROM Employees;
```

Do not use:

```sql
WHERE Department = NULL;
```

---

## 22. IN, NOT IN and EXISTS

**Q22. What is the difference between IN, NOT IN and EXISTS?**

IN:

```sql
SELECT *
FROM Customers
WHERE CustomerID IN (
    SELECT CustomerID
    FROM Orders
);
```

EXISTS:

```sql
SELECT *
FROM Customers c
WHERE EXISTS (
    SELECT 1
    FROM Orders o
    WHERE o.CustomerID = c.CustomerID
);
```

NOT IN:

```sql
SELECT *
FROM Customers
WHERE CustomerID NOT IN (
    SELECT CustomerID
    FROM Orders
);
```

Be careful with NOT IN when the subquery can return NULL.

---

## 23. NOT EXISTS / Missing Records

**Q23. How do you find customers who have never placed an order?**

Using NOT EXISTS:

```sql
SELECT *
FROM Customers c
WHERE NOT EXISTS (
    SELECT 1
    FROM Orders o
    WHERE o.CustomerID = c.CustomerID
);
```

Using LEFT JOIN:

```sql
SELECT c.*
FROM Customers c
LEFT JOIN Orders o
ON c.CustomerID = o.CustomerID
WHERE o.OrderID IS NULL;
```

---

## 24. Subquery

**Q24. What is a subquery? Give common examples.**

A subquery is a query inside another query.

Employees above average:

```sql
SELECT *
FROM Employees
WHERE Salary > (
    SELECT AVG(Salary)
    FROM Employees
);
```

Highest salary:

```sql
SELECT *
FROM Employees
WHERE Salary = (
    SELECT MAX(Salary)
    FROM Employees
);
```

Second highest salary:

```sql
SELECT MAX(Salary)
FROM Employees
WHERE Salary < (
    SELECT MAX(Salary)
    FROM Employees
);
```

---

## 25. Correlated Subquery

**Q25. How do you find employees earning more than their department average?**

```sql
SELECT e.*
FROM Employees e
WHERE e.Salary > (
    SELECT AVG(e2.Salary)
    FROM Employees e2
    WHERE e2.Department = e.Department
);
```

The inner query references a value from the outer query, so it is correlated.

---

## 26. CASE Statement

**Q26. How do you categorize employees based on salary?**

```sql
SELECT Name,
       Salary,
       CASE
           WHEN Salary >= 100000 THEN 'High'
           WHEN Salary >= 50000 THEN 'Medium'
           ELSE 'Low'
       END AS SalaryCategory
FROM Employees;
```

---

## 27. String Queries

**Q27. How do you perform common string operations?**

Starts with A:

```sql
SELECT *
FROM Employees
WHERE Name LIKE 'A%';
```

Contains "an":

```sql
SELECT *
FROM Employees
WHERE Name LIKE '%an%';
```

Uppercase:

```sql
SELECT UPPER(Name)
FROM Employees;
```

Lowercase:

```sql
SELECT LOWER(Name)
FROM Employees;
```

Concatenate:

```sql
SELECT CONCAT(FirstName, ' ', LastName) AS FullName
FROM Employees;
```

Trim spaces:

```sql
SELECT TRIM(Name)
FROM Employees;
```

---

## 28. Date Queries

**Q28. How do you find records created today, in the last 7 days, or between dates?**

Today:

```sql
SELECT *
FROM Users
WHERE CreatedDate >= CURRENT_DATE
AND CreatedDate < CURRENT_DATE + INTERVAL '1 day';
```

Last 7 days:

```sql
SELECT *
FROM Users
WHERE CreatedDate >= CURRENT_TIMESTAMP - INTERVAL '7 days';
```

Between dates:

```sql
SELECT *
FROM Orders
WHERE OrderDate >= '2026-01-01'
AND OrderDate < '2026-02-01';
```

Date syntax varies by database.

---

## 29. ROW_NUMBER, RANK and DENSE_RANK

**Q29. What is the difference between ROW_NUMBER(), RANK() and DENSE_RANK()?**

For salary values: 100000, 100000, 90000, 80000

| Salary | ROW_NUMBER | RANK | DENSE_RANK |
|--------|-----------|------|-----------|
| 100000 | 1         | 1    | 1         |
| 100000 | 2         | 1    | 1         |
| 90000  | 3         | 3    | 2         |
| 80000  | 4         | 4    | 3         |

- `ROW_NUMBER()` → unique sequence
- `RANK()` → ties share rank and gaps are created
- `DENSE_RANK()` → ties share rank and no gaps are created

---

## 30. Latest Record per Group

**Q30. How do you find the latest order for each customer?**

```sql
SELECT *
FROM (
    SELECT o.*,
           ROW_NUMBER() OVER (
               PARTITION BY CustomerID
               ORDER BY OrderDate DESC
           ) AS RowNum
    FROM Orders o
) t
WHERE RowNum = 1;
```

For the second latest:

```sql
WHERE RowNum = 2;
```

---

## 31. Previous and Next Record

**Q31. How do you get the previous and next record value?**

Previous:

```sql
SELECT Name,
       Salary,
       LAG(Salary) OVER (
           ORDER BY EmployeeID
       ) AS PreviousSalary
FROM Employees;
```

Next:

```sql
SELECT Name,
       Salary,
       LEAD(Salary) OVER (
           ORDER BY EmployeeID
       ) AS NextSalary
FROM Employees;
```

---

## 32. Running Total

**Q32. How do you calculate a running total?**

```sql
SELECT EmployeeID,
       Name,
       Salary,
       SUM(Salary) OVER (
           ORDER BY EmployeeID
       ) AS RunningTotal
FROM Employees;
```

Window functions retain the individual rows while calculating across related rows.

---

## 33. UNION vs UNION ALL

**Q33. What is the difference between UNION and UNION ALL?**

UNION removes duplicates:

```sql
SELECT Name FROM Employees
UNION
SELECT Name FROM Managers;
```

UNION ALL keeps duplicates:

```sql
SELECT Name FROM Employees
UNION ALL
SELECT Name FROM Managers;
```

---

## 34. DELETE, TRUNCATE and DROP

**Q34. What is the difference between DELETE, TRUNCATE and DROP?**

DELETE:

```sql
DELETE FROM Employees
WHERE EmployeeID = 101;
```

TRUNCATE:

```sql
TRUNCATE TABLE Employees;
```

DROP:

```sql
DROP TABLE Employees;
```

| Feature | DELETE | TRUNCATE | DROP |
|---------|--------|----------|------|
| Selected rows | Yes | No | No |
| All rows | Yes | Yes | Yes |
| Table structure remains | Yes | Yes | No |
| WHERE supported | Yes | No | No |

Exact transaction, logging, identity-reset and trigger behavior can vary by database.

---

## 35. Employees Without Department

**Q35. How do you find employees whose department does not exist?**

```sql
SELECT e.*
FROM Employees e
LEFT JOIN Departments d
ON e.DepartmentID = d.DepartmentID
WHERE d.DepartmentID IS NULL;
```

This identifies orphan employee records.

---

## 36. Customers Without Orders

**Q36. How do you find customers who have never placed an order?**

Using LEFT JOIN:

```sql
SELECT c.*
FROM Customers c
LEFT JOIN Orders o
ON c.CustomerID = o.CustomerID
WHERE o.OrderID IS NULL;
```

Using NOT EXISTS:

```sql
SELECT *
FROM Customers c
WHERE NOT EXISTS (
    SELECT 1
    FROM Orders o
    WHERE o.CustomerID = c.CustomerID
);
```

---

## 37. Orders Without Valid Customers

**Q37. How do you find orders whose customer does not exist?**

```sql
SELECT o.*
FROM Orders o
LEFT JOIN Customers c
ON o.CustomerID = c.CustomerID
WHERE c.CustomerID IS NULL;
```

This identifies orphan orders.

---

## 38. Employees Earning More Than Manager

**Q38. How do you find employees whose salary is greater than their manager's salary?**

Assume table structure with columns: EmployeeID, Name, ManagerID, Salary

```sql
SELECT e.Name AS Employee,
       e.Salary AS EmployeeSalary,
       m.Name AS Manager,
       m.Salary AS ManagerSalary
FROM Employees e
INNER JOIN Employees m
ON e.ManagerID = m.EmployeeID
WHERE e.Salary > m.Salary;
```

This is a self JOIN.

---

## 39. Same Salary Records

**Q39. How do you find employees having the same salary?**

All duplicate salary records:

```sql
SELECT *
FROM Employees
WHERE Salary IN (
    SELECT Salary
    FROM Employees
    GROUP BY Salary
    HAVING COUNT(*) > 1
);
```

Duplicate salary values only:

```sql
SELECT Salary,
       COUNT(*) AS EmployeeCount
FROM Employees
GROUP BY Salary
HAVING COUNT(*) > 1;
```

The same pattern can be used for Email, Phone, EmployeeCode, etc.

---

## 40. Practical Data Validation Query

**Q40. How can you validate related data across tables?**

Suppose an order was created and we need to validate the database record.

**Fetch order and customer details:**

```sql
SELECT o.OrderID,
       o.CustomerID,
       o.Amount,
       o.Status,
       c.CustomerName
FROM Orders o
LEFT JOIN Customers c
ON o.CustomerID = c.CustomerID
WHERE o.OrderID = 1001;
```

**Check whether the customer is missing:**

```sql
SELECT o.*
FROM Orders o
LEFT JOIN Customers c
ON o.CustomerID = c.CustomerID
WHERE o.OrderID = 1001
AND c.CustomerID IS NULL;
```

**Check for duplicate Order IDs:**

```sql
SELECT OrderID,
       COUNT(*) AS Occurrence
FROM Orders
GROUP BY OrderID
HAVING COUNT(*) > 1;
```

**Validate expected status and amount:**

```sql
SELECT *
FROM Orders
WHERE OrderID = 1001
AND Status = 'COMPLETED'
AND Amount = 5000;
```

These patterns are useful for validating data created or modified by an application or API.

---

## Quick Reference

| Requirement | SQL Concept |
|-------------|------------|
| Filter records | WHERE |
| Multiple values | IN |
| Range | BETWEEN |
| Pattern matching | LIKE |
| Unique values | DISTINCT |
| Sort | ORDER BY |
| Count | COUNT() |
| Total | SUM() |
| Average | AVG() |
| Highest | MAX() |
| Lowest | MIN() |
| Group data | GROUP BY |
| Filter groups | HAVING |
| Matching records | INNER JOIN |
| Keep all left records | LEFT JOIN |
| Find missing records | LEFT JOIN + IS NULL |
| Second highest | MAX() + subquery |
| Nth highest | DENSE_RANK() |
| Duplicates | GROUP BY + HAVING COUNT(*) > 1 |
| Existence | EXISTS |
| Non-existence | NOT EXISTS |
| Conditional logic | CASE |
| Previous row | LAG() |
| Next row | LEAD() |
| Latest per group | ROW_NUMBER() + PARTITION BY |
| Running total | SUM() OVER() |
| Combine results | UNION / UNION ALL |
| Delete rows | DELETE |
| Remove all rows | TRUNCATE |
| Remove table | DROP |
