package RA_03_04_PostAPIs;

public class D4_JsonPath_GitHubData {

	
	
	/**
	 * 
	 * https://github.com/json-path/JsonPath
	 * 
	 * <dependency>
    * <groupId>com.jayway.jsonpath</groupId>
    *<artifactId>json-path</artifactId>
    *<version>2.9.0</version>
	*</dependency>


$.store.book[0].title

or the bracket–notation

$['store']['book'][0]['title']

Operators
Operator					Description
$	    					The root element to query. This starts all path expressions.
@	    					The current node being processed by a filter predicate.
*	    					Wildcard. Available anywhere a name or numeric are required.
..	  						Deep scan. Available anywhere a name is required.
.<name>	 					Dot-notated child
['<name>' (, '<name>')]		Bracket-notated child or children
[<number> (, <number>)]		Array index or indexes
[start:end]					Array slice operator
[?(<expression>)]			Filter expression. Expression must evaluate to a boolean value.


Function	Description														Output type
min()		Provides the min value of an array of numbers						Double
max()		Provides the max value of an array of numbers						Double
avg()		Provides the average value of an array of numbers					Double
stddev()	Provides the standard deviation value of an array of numbers		Double
length()	Provides the length of an array										Integer
sum()		Provides the sum value of an array of numbers						Double
keys()		Provides the property keys (An alternative for terminal tilde ~)	Set<E>
concat(X)	Provides a concatinated version of the path output with a new item	like input
append(X)	add an item to the json path output array	like input
first()		Provides the first item of an array	Depends on the array
last()		Provides the last item of an array	Depends on the array
index(X)	Provides the item of an array of index: X, if the X is negative, take from backwards	Depends on the array
 
 *
 *Filter Operators		Operator	Description
 *							
==						left is equal to right (note that 1 is not equal to '1')
!=						left is not equal to right
<						1left is less than right
<=						left is less or equal to right
>						left is greater than right
>=						left is greater than or equal to right
=~						left matches regular expression [?(@.name =~ /foo.*?/i)]
in						left exists in right [?(@.size in ['S', 'M'])]
nin						left does not exists in right
subsetof				left is a subset of right [?(@.sizes subsetof ['S', 'M', 'L'])]
anyof					left has an intersection with right [?(@.sizes anyof ['M', 'L'])]
noneof					left has no intersection with right [?(@.sizes noneof ['M', 'L'])]
size					size of left (array or string) should match right
empty					left (array or string) should be empty
 

Example

{
    "store": {
        "book": [
            {
                "category": "reference",
                "author": "Nigel Rees",
                "title": "Sayings of the Century",
                "price": 8.95
            },
            {
                "category": "fiction",
                "author": "Evelyn Waugh",
                "title": "Sword of Honour",
                "price": 12.99
            },
            {
                "category": "fiction",
                "author": "Herman Melville",
                "title": "Moby Dick",
                "isbn": "0-553-21311-3",
                "price": 8.99
            },
            {
                "category": "fiction",
                "author": "J. R. R. Tolkien",
                "title": "The Lord of the Rings",
                "isbn": "0-395-19395-8",
                "price": 22.99
            }
        ],
        "bicycle": {
            "color": "red",
            "price": 19.95
        }
    },
    "expensive": 10
}

examples
JsonPath								Result
$.store.book[*].author				The authors of all books
$..author							All authors
$.store.*								All things, both books and bicycles
$.store..price						The price of everything
$..book[2]							The third book
$..book[-2]							The second to last book
$..book[0,1]						The first two books
$..book[:2]							All books from index 0 (inclusive) until index 2 (exclusive)
$..book[1:2]						All books from index 1 (inclusive) until index 2 (exclusive)
$..book[-2:]						Last two books
$..book[2:]								All books from index 2 (inclusive) to last
$..book[?(@.isbn)]						All books with an ISBN number
$.store.book[?(@.price < 10)]			All books in store cheaper than 10
$..book[?(@.price <= $['expensive'])]	All books in store that are not "expensive"
$..book[?(@.author =~ /.*REES/i)]		All books matching regex (ignore case)
$..*									Give me every thing
$..book.length()						The number of books
 *
 *
 *
 *
 */
}
