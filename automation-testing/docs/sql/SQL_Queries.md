# SQL Query Reference Guide

## Table of Contents
1. [SELECT Queries](#select-queries)
2. [WHERE Clause](#where-clause)
3. [Logical Operators](#logical-operators)
4. [ORDER BY](#order-by)
5. [INSERT](#insert)
6. [NULL Handling](#null-handling)
7. [UPDATE & DELETE](#update--delete)
8. [LIMIT & TOP](#limit--top)
9. [Aggregate Functions](#aggregate-functions)
10. [LIKE & IN Operators](#like--in-operators)
11. [BETWEEN](#between)
12. [Aliases](#aliases)
13. [Joins](#joins)
14. [UNION](#union)
15. [GROUP BY & HAVING](#group-by--having)
16. [SELECT INTO](#select-into)
17. [Database & Table Operations](#database--table-operations)
18. [Constraints](#constraints)
19. [Indexes](#indexes)
20. [Views](#views)

---

## SELECT Queries

### Basic SELECT
Select specific columns:
```sql
SELECT column1, column2, ... FROM table_name;

SELECT CustomerName, City FROM Customers;
```

Select all columns:
```sql
SELECT * FROM table_name;

SELECT * FROM Customers;
```

### SELECT DISTINCT
Returns only distinct (unique) values:
```sql
SELECT DISTINCT column1, column2, ... FROM table_name;

SELECT DISTINCT Country FROM Customers;

SELECT COUNT(DISTINCT Country) FROM Customers;
```

---

## WHERE Clause

### Basic WHERE
```sql
SELECT column1, column2, ... FROM table_name WHERE condition;

SELECT * FROM Customers WHERE Country='Mexico';

SELECT * FROM Customers WHERE CustomerID=1;
```

### Comparison Operators
```sql
-- Equal to
SELECT * FROM Products WHERE Price = 18;

-- Greater than
SELECT * FROM Products WHERE Price > 30;

-- Less than
SELECT * FROM Products WHERE Price < 30;

-- Greater than or equal to
SELECT * FROM Products WHERE Price >= 30;

-- Less than or equal to
SELECT * FROM Products WHERE Price <= 30;

-- Not equal to
SELECT * FROM Products WHERE Price <> 18;

-- BETWEEN
SELECT * FROM Products WHERE Price BETWEEN 50 AND 60;

-- LIKE pattern matching
SELECT * FROM Customers WHERE City LIKE 's%';

-- IN list
SELECT * FROM Customers WHERE City IN ('Paris','London');
```

---

## Logical Operators

### AND Operator
```sql
SELECT column1, column2, ... FROM table_name
WHERE condition1 AND condition2 AND condition3 ...;

SELECT * FROM Customers
WHERE Country='Germany' AND City='Berlin';
```

### OR Operator
```sql
SELECT column1, column2, ... FROM table_name
WHERE condition1 OR condition2 OR condition3 ...;

SELECT * FROM Customers
WHERE City='Berlin' OR City='München';
```

### NOT Operator
```sql
SELECT column1, column2, ... FROM table_name
WHERE NOT condition;

SELECT * FROM Customers WHERE NOT Country='Germany';
```

### Combined Logic
```sql
SELECT * FROM Customers WHERE Country='Germany'
AND (City='Berlin' OR City='Munchen');

SELECT * FROM Customers WHERE NOT Country='Germany'
AND NOT Country='USA';
```

---

## ORDER BY

Sort query results in ascending or descending order:
```sql
SELECT column1, column2, ... FROM table_name
ORDER BY column1, column2, ... ASC|DESC;

-- Single column ascending (default)
SELECT * FROM Customers ORDER BY Country;

-- Single column descending
SELECT * FROM Customers ORDER BY Country DESC;

-- Multiple columns
SELECT * FROM Customers ORDER BY Country, CustomerName;

-- Mix of ASC and DESC
SELECT * FROM Customers ORDER BY Country ASC, CustomerName DESC;
```

---

## INSERT

Insert new records into a table:
```sql
INSERT INTO table_name (column1, column2, column3, ...)
VALUES (value1, value2, value3, ...);

INSERT INTO Customers (CustomerName, ContactName, Address,
City, PostalCode, Country)
VALUES ('Cardinal', 'Tom B.Erichsen', 'Skagen 21', 'Stavanger',
'4006', 'Norway');
```

Insert without specifying column names (must provide all values in order):
```sql
INSERT INTO table_name VALUES (value1, value2, value3, ...);

INSERT INTO Customers VALUES (93, 'Cardinal', 'Tom B. Erichsen',
'Skagen 21', 'Stavanger', '4006', 'Norway');
```

---

## NULL Handling

### IS NULL
Select records where a field is NULL:
```sql
SELECT column_names FROM table_name
WHERE column_name IS NULL;

SELECT CustomerName, ContactName, Address FROM Customers
WHERE Address IS NULL;
```

### IS NOT NULL
Select records where a field is NOT NULL:
```sql
SELECT column_names FROM table_name
WHERE column_name IS NOT NULL;

SELECT CustomerName, ContactName, Address FROM Customers
WHERE Address IS NOT NULL;
```

---

## UPDATE & DELETE

### UPDATE
Modify existing records:
```sql
UPDATE table_name SET column1 = value1, column2 = value2, ...
WHERE condition;

UPDATE Customers SET ContactName = 'Alfred Schmidt',
City='Frankfurt' WHERE CustomerID = 1;

-- Without WHERE clause (updates all records!)
UPDATE Customers SET ContactName='Juan';
```

### DELETE
Delete records:
```sql
DELETE FROM table_name WHERE condition;

DELETE FROM Customers WHERE CustomerName='Alfreds Futterkiste';

-- Delete all records (without WHERE clause!)
DELETE FROM table_name;

DELETE FROM Customers;
```

---

## LIMIT & TOP

### TOP (SQL Server, MS Access)
```sql
SELECT TOP number|percent column_name(s) FROM table_name
WHERE condition;

SELECT TOP 3 * FROM Customers;

SELECT TOP 50 PERCENT * FROM Customers;

SELECT TOP 3 * FROM Customers WHERE Country='Germany';
```

### LIMIT (MySQL, PostgreSQL, SQLite)
```sql
SELECT column_name(s) FROM table_name WHERE condition
LIMIT number;

SELECT * FROM Customers LIMIT 3;

SELECT * FROM Customers WHERE Country='Germany' LIMIT 3;
```

---

## Aggregate Functions

### MIN() - Find minimum value
```sql
SELECT MIN(column_name) FROM table_name WHERE condition;

SELECT MIN(Price) FROM Products;

-- Get 5 minimum values
SELECT Price FROM Products ORDER BY Price LIMIT 5;
```

### MAX() - Find maximum value
```sql
SELECT MAX(column_name) FROM table_name WHERE condition;

SELECT MAX(Price) FROM Products;

-- Get 5 maximum values
SELECT Price FROM Products ORDER BY Price DESC LIMIT 5;
```

### COUNT() - Count rows
```sql
SELECT COUNT(column_name) FROM table_name WHERE condition;

SELECT COUNT(ProductID) FROM Products;
```

### AVG() - Calculate average
```sql
SELECT AVG(column_name) FROM table_name WHERE condition;

SELECT AVG(Price) FROM Products;
```

### SUM() - Calculate sum
```sql
SELECT SUM(column_name) FROM table_name WHERE condition;

SELECT SUM(Quantity) FROM OrderDetails;
```

---

## LIKE & IN Operators

### LIKE - Pattern Matching
Wildcards:
- `%` - represents zero or more characters
- `_` - represents a single character

```sql
SELECT column_name(s) FROM table_name WHERE column_name
LIKE pattern;

-- Starts with 'a'
SELECT * FROM Customers WHERE CustomerName LIKE 'a%';

-- Ends with 'a'
SELECT * FROM Customers WHERE CustomerName LIKE '%a';

-- Contains 'or'
SELECT * FROM Customers WHERE CustomerName LIKE '%or%';

-- Second character is 'r'
SELECT * FROM Customers WHERE CustomerName LIKE '_r%';

-- Starts with 'a' and has at least 2 more characters
SELECT * FROM Customers WHERE CustomerName LIKE 'a__%';

-- Starts with 'a' and ends with 'o'
SELECT * FROM Customers WHERE ContactName LIKE 'a%o';

-- Starts with 'ber'
SELECT * FROM Customers WHERE City LIKE 'ber%';
```

### IN - Match Multiple Values
```sql
SELECT column_name(s) FROM table_name WHERE column_name
IN (value1, value2, ...);

SELECT * FROM Customers WHERE Country IN ('Germany', 'France', 'UK');

-- NOT IN
SELECT * FROM Customers WHERE Country
NOT IN ('Germany', 'France', 'UK');
```

---

## BETWEEN

Select values within a range:
```sql
SELECT column_name(s) FROM table_name
WHERE column_name BETWEEN value1 AND value2;

-- Inclusive range
SELECT * FROM Products WHERE Price BETWEEN 10 AND 20;

-- NOT BETWEEN
SELECT * FROM Products WHERE Price NOT BETWEEN 10 AND 20;

-- With additional conditions
SELECT * FROM Products WHERE Price BETWEEN 10 AND 20
AND CategoryID NOT IN (1,2,3);

-- Text range
SELECT * FROM Products WHERE ProductName BETWEEN 'Carnarvon Tigers'
AND 'Mozzarella di Giovanni' ORDER BY ProductName;

SELECT * FROM Products WHERE ProductName NOT BETWEEN
'Carnarvon Tigers' AND 'Mozzarella di Giovanni' ORDER BY ProductName;

-- Date range
SELECT * FROM Orders WHERE OrderDate BETWEEN '1996-07-01'
AND '1996-07-31';
```

---

## Aliases

### Column Alias
Give a column a temporary name:
```sql
SELECT column_name AS alias_name FROM table_name;

SELECT CustomerID AS ID, CustomerName AS Customer FROM Customers;
```

### Table Alias
Give a table a temporary name:
```sql
SELECT column_name(s) FROM table_name AS alias_name;

SELECT o.OrderID, o.OrderDate, c.CustomerName FROM
Customers AS c, Orders AS o WHERE c.CustomerName='Around the Horn'
AND c.CustomerID=o.CustomerID;
```

---

## Joins

See [SQL_Joins.md](SQL_Joins.md) for detailed join examples and diagrams.

### INNER JOIN
Returns records that have matching values in both tables:
```sql
SELECT column_name(s) FROM table1 INNER JOIN table2
ON table1.column_name = table2.column_name;

SELECT OrderID, CustomerName FROM Orders INNER JOIN Customers
ON Orders.CustomerID = Customers.CustomerID;

SELECT * FROM Orders INNER JOIN Customers
ON Orders.CustomerID = Customers.CustomerID;
```

### Multiple INNER JOINs
```sql
SELECT Orders.OrderID, Customers.CustomerName, Shippers.ShipperName
FROM ((Orders INNER JOIN Customers ON Orders.CustomerID = Customers.CustomerID)
INNER JOIN Shippers ON Orders.ShipperID = Shippers.ShipperID);
```

### LEFT JOIN
Returns all records from the left table and matching records from the right table:
```sql
SELECT column_name(s) FROM table1 LEFT JOIN table2
ON table1.column_name = table2.column_name;

SELECT Customers.CustomerName, Orders.OrderID FROM Customers
LEFT JOIN Orders ON Customers.CustomerID = Orders.CustomerID
ORDER BY Customers.CustomerName;

SELECT * FROM Customers LEFT JOIN Orders
ON Customers.CustomerID = Orders.CustomerID
ORDER BY Customers.CustomerName;
```

### RIGHT JOIN
Returns all records from the right table and matching records from the left table:
```sql
SELECT column_name(s) FROM table1 RIGHT JOIN table2
ON table1.column_name = table2.column_name;

SELECT Orders.OrderID, Employees.LastName, Employees.FirstName
FROM Orders RIGHT JOIN Employees
ON Orders.EmployeeID = Employees.EmployeeID ORDER BY Orders.OrderID;
```

### FULL OUTER JOIN
Returns all records when there is a match in either table:
```sql
SELECT column_name(s) FROM table1 FULL OUTER JOIN table2
ON table1.column_name = table2.column_name WHERE condition;

SELECT Customers.CustomerName, Orders.OrderID FROM Customers
FULL OUTER JOIN Orders ON Customers.CustomerID=Orders.CustomerID
ORDER BY Customers.CustomerName;
```

---

## UNION

Combine results from two or more SELECT queries (removes duplicates):
```sql
SELECT column_name(s) FROM table1 UNION SELECT column_name(s)
FROM table2;

SELECT City FROM Customers UNION SELECT City FROM Suppliers
ORDER BY City;
```

### UNION ALL
Allows duplicate values:
```sql
SELECT column_name(s) FROM table1 UNION ALL SELECT column_name(s)
FROM table2;

SELECT City FROM Customers UNION ALL SELECT City FROM Suppliers
ORDER BY City;
```

---

## GROUP BY & HAVING

### GROUP BY
Group rows by one or more columns:
```sql
SELECT column_name(s) FROM table_name
GROUP BY column_name(s);

SELECT COUNT(CustomerID), Country FROM Customers GROUP BY Country;
```

### HAVING
Filter groups after aggregation:
```sql
SELECT column_name(s) FROM table_name
GROUP BY column_name(s) HAVING condition;

SELECT COUNT(CustomerID), Country FROM Customers GROUP BY Country
HAVING COUNT(CustomerID) > 5;
```

---

## SELECT INTO

Copy data from one table into a new table:
```sql
SELECT * INTO newtable [IN externaldb] FROM oldtable WHERE condition;

SELECT * INTO CustomersBackup2017 FROM Customers;

SELECT * INTO CustomersGermany FROM Customers
WHERE Country = 'Germany';
```

Copy specific columns:
```sql
SELECT column1, column2, column3, … INTO newtable [IN externaldb]
FROM oldtable WHERE condition;

SELECT CustomerName, ContactName INTO CustomersBackup2017 FROM Customers;

SELECT Customers.CustomerName, Orders.OrderID
INTO CustomersOrderBackup2017 FROM Customers
LEFT JOIN Orders ON Customers.CustomerID = Orders.CustomerID;
```

---

## INSERT SELECT

Insert data from one table into another:
```sql
INSERT INTO table2 SELECT * FROM table1 WHERE condition;

INSERT INTO table2 (column1, column2, column3, ...)
SELECT column1, column2, column3, ...
FROM table1 WHERE condition;

INSERT INTO Customers (CustomerName, City, Country)
SELECT SupplierName, City, Country FROM Suppliers;

INSERT INTO Customers (CustomerName, ContactName, Address,
City, PostalCode, Country) SELECT SupplierName, ContactName,
Address, City, PostalCode, Country FROM Suppliers;

INSERT INTO Customers (CustomerName, City, Country)
SELECT SupplierName, City, Country FROM Suppliers
WHERE Country='Germany';
```

---

## Database & Table Operations

### CREATE DATABASE
```sql
CREATE DATABASE databasename;

CREATE DATABASE testDB;
```

### DROP DATABASE
```sql
DROP DATABASE databasename;

DROP DATABASE testDB;
```

### CREATE TABLE
```sql
CREATE TABLE table_name (
  column1 datatype,
  column2 datatype,
  column3 datatype,
  ...
);

CREATE TABLE Persons (
  PersonID int,
  LastName varchar(255),
  FirstName varchar(255),
  Address varchar(255),
  City varchar(255)
);
```

### DROP TABLE
```sql
DROP TABLE table_name;

DROP TABLE Shippers;
```

### TRUNCATE TABLE
Remove all data but keep the table structure:
```sql
TRUNCATE TABLE table_name;

TRUNCATE TABLE orders;
```

### ALTER TABLE - ADD Column
```sql
ALTER TABLE table_name ADD column_name datatype;

ALTER TABLE Customers ADD Email varchar(255);
```

### ALTER TABLE - DROP Column
```sql
ALTER TABLE table_name DROP COLUMN column_name;

ALTER TABLE Customers DROP COLUMN Email;
```

### ALTER TABLE - MODIFY Column
```sql
ALTER TABLE tableName MODIFY columnName data_type constraintName;

ALTER TABLE Persons MODIFY Age int NOT NULL;
```

---

## Constraints

### NOT NULL
```sql
CREATE TABLE Persons (
  ID int NOT NULL,
  LastName varchar(255) NOT NULL,
  FirstName varchar(255) NOT NULL,
  Age int
);
```

### UNIQUE
```sql
CREATE TABLE Persons (
  ID int NOT NULL,
  LastName varchar(255) NOT NULL,
  FirstName varchar(255),
  Age int,
  UNIQUE (ID)
);

CREATE TABLE Persons (
  ID int NOT NULL,
  LastName varchar(255) NOT NULL,
  FirstName varchar(255),
  Age int,
  CONSTRAINT UC_Person UNIQUE (ID, LastName)
);

-- Add UNIQUE constraint
ALTER TABLE Persons ADD UNIQUE (ID);

ALTER TABLE Persons ADD CONSTRAINT UC_Person UNIQUE (ID, LastName);

-- Drop UNIQUE constraint
ALTER TABLE Persons DROP INDEX UC_Person;
```

### PRIMARY KEY
```sql
CREATE TABLE Persons (
  ID int NOT NULL,
  LastName varchar(255) NOT NULL,
  FirstName varchar(255),
  Age int,
  PRIMARY KEY (ID)
);

CREATE TABLE Persons (
  ID int NOT NULL,
  LastName varchar(255) NOT NULL,
  FirstName varchar(255),
  Age int,
  CONSTRAINT PK_Person PRIMARY KEY (ID, LastName)
);

-- Add PRIMARY KEY
ALTER TABLE Persons ADD PRIMARY KEY (ID);

ALTER TABLE Persons ADD CONSTRAINT PK_Person PRIMARY KEY (ID, LastName);

-- Drop PRIMARY KEY
ALTER TABLE Persons DROP PRIMARY KEY;
```

### FOREIGN KEY
```sql
CREATE TABLE Orders (
  OrderID int NOT NULL,
  OrderNumber int NOT NULL,
  PersonID int,
  PRIMARY KEY (OrderID),
  FOREIGN KEY (PersonID) REFERENCES Persons(PersonID)
);

CREATE TABLE Orders (
  OrderID int NOT NULL,
  OrderNumber int NOT NULL,
  PersonID int,
  PRIMARY KEY (OrderID),
  CONSTRAINT FK_PersonOrder FOREIGN KEY (PersonID)
  REFERENCES Persons(PersonID)
);

-- Add FOREIGN KEY
ALTER TABLE Orders ADD FOREIGN KEY (PersonID) REFERENCES Persons(PersonID);

ALTER TABLE Orders ADD CONSTRAINT FK_PersonOrder
FOREIGN KEY (PersonID) REFERENCES Persons(PersonID);

-- Drop FOREIGN KEY
ALTER TABLE Orders DROP FOREIGN KEY FK_PersonOrder;
```

### CHECK
```sql
CREATE TABLE Persons (
  ID int NOT NULL,
  LastName varchar(255) NOT NULL,
  FirstName varchar(255),
  Age int,
  CHECK (Age >= 18)
);

CREATE TABLE Persons (
  ID int NOT NULL,
  LastName varchar(255) NOT NULL,
  FirstName varchar(255),
  Age int,
  City varchar(255),
  CONSTRAINT CHK_Person CHECK (Age >= 18 AND City = 'Sandnes')
);

-- Add CHECK constraint
ALTER TABLE Persons ADD CHECK (Age >= 18);

ALTER TABLE Persons ADD CONSTRAINT CHK_PersonAge
CHECK (Age >= 18 AND City = 'Sandnes');

-- Drop CHECK constraint
ALTER TABLE Persons DROP CHECK CHK_PersonAge;
```

### DEFAULT
```sql
CREATE TABLE Persons (
  ID int NOT NULL,
  LastName varchar(255) NOT NULL,
  FirstName varchar(255),
  Age int,
  City varchar(255) DEFAULT 'Sandnes'
);

CREATE TABLE Orders (
  ID int NOT NULL,
  OrderNumber int NOT NULL,
  OrderDate date DEFAULT GETDATE()
);

-- Alter DEFAULT
ALTER TABLE Persons ALTER City SET DEFAULT 'Sandnes';

ALTER TABLE Persons ALTER City DROP DEFAULT;
```

---

## Indexes

Create indexes to improve query performance:
```sql
CREATE INDEX index_name
ON table_name (column1, column2, ...);

CREATE INDEX idx_lastname ON Persons (LastName);

CREATE INDEX idx_pname ON Persons (LastName, FirstName);
```

Create unique index:
```sql
CREATE UNIQUE INDEX index_name
ON table_name (column1, column2, ...);
```

Drop index:
```sql
ALTER TABLE table_name DROP INDEX index_name;
```

---

## Auto Increment

```sql
CREATE TABLE Persons (
  Personid int NOT NULL AUTO_INCREMENT,
  LastName varchar(255) NOT NULL,
  FirstName varchar(255),
  Age int,
  PRIMARY KEY (Personid)
);
```

---

## Views

A view is a virtual table based on a SELECT query.

### CREATE VIEW
```sql
CREATE VIEW view_name AS SELECT column1, column2, ...
FROM table_name WHERE condition;

CREATE VIEW [Brazil Customers] AS SELECT CustomerName,
ContactName FROM Customers WHERE Country = 'Brazil';

-- Use the view
SELECT * FROM [Brazil Customers];
```

### CREATE VIEW with Aggregate Function
```sql
CREATE VIEW [Products Above Average Price] AS SELECT
ProductName, Price FROM Products WHERE Price >
(SELECT AVG(Price) FROM Products);

SELECT * FROM [Products Above Average Price];
```

### CREATE OR REPLACE VIEW
```sql
CREATE OR REPLACE VIEW view_name AS SELECT column1, column2, ...
FROM table_name WHERE condition;

CREATE OR REPLACE VIEW [Brazil Customers] AS
SELECT CustomerName, ContactName, City FROM Customers
WHERE Country = 'Brazil';
```

### DROP VIEW
```sql
DROP VIEW view_name;

DROP VIEW [Brazil Customers];
```

---

## Quick Tips

- Always use `WHERE` conditions to limit result sets
- Use indexes on frequently searched columns
- Use aggregate functions with `GROUP BY` for data summarization
- Use `LEFT JOIN` when you want all records from the left table
- Use `INNER JOIN` when you want matching records only
- Backup data before running `UPDATE` or `DELETE` statements
- Use transactions for multiple related operations
