1: The implementation is a standard BMH-algorithm. By compiling a set of shift-values for all keys (in needle and not)
the program kan safely skip ahead a given amount of characters in a string.

The program uses existing data structures and methods in java, namely chararray and hashmap to easily traverse through
strings and to find values associated with characters.
2: Compile with javac *.java.
3: Main method is in file Main.
4: None in particular.
5: It seems okay!
6: Appears to be fully functional.
7: StackOverflow and http://programmering.wiki.ifi.uio.no/Boyer-Moore


Expected output:
---
Pattern found at 38
LUCKYDUCKCLUCK
---
Pattern found at 0
cog
Pattern found at 8
cag
---
Pattern found at 2
zwrca
---
Pattern found at 0
cogc
Pattern found at 3
cagc
Pattern found at 6
cigc