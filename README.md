# scrabbler

## Solution Statement

### To compile the source code, please type:
	$ javac scrabbler.java

### To run the source code, please type:  

	$ java scrabbler abcdefg
	$ java scrabbler --prefix fi
	$ java scrabbler —suffix o

For problem #1, a HashMap<K,V> is implemented to store the letters of the set and it’s corresponding status. 
	Time Complexity = O(n)
	Space Complexity = O(n)
For solution, please see class MatchSpell.

For problem #2, my original method is check each word of the dictionary if it satisfies the prefix requirement. See class class MatchPrefix.
	Time Complexity = O(n)
	Space Complexity = O(n)

Then I have made some optimizations based on following assumptions:

	Assumption:
	1. All words in this dictionary are lower case English words.
	2. Words are sorted base on alphabet order.

	Based on Above assumptions, I used binary search method to find the first occurrence of the prefix and the last occurrence of the prefix. The return all the words within the range without checking.
	See class MatchPrefixOpt.
	Time Complexity = O(logn) (for search only exclusive of reading all lines)
	Space Complexity = O(n)

For problem #3, See class class MatchPrefix. Similar to problem #2 original method.
	Time Complexity = O(n)
	Space Complexity = O(n)
