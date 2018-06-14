05_Hashing_Lab
==============

Implement a hash table in C++

Requirements
------------

1. `keyExists`, `find`, `remove`, and `size` should all be O(1)
2. `add` should be reasonably fast. Use linear probing to find an open slot in the hash table. This will be O(1), on average, except when `grow` is called.
3. `grow` should be O(n)
4. Do not leak memory


Reading
=======
"Open Data Structures," Chapter 5, except for 5.2.3. http://opendatastructures.org/ods-cpp/5_Hash_Tables.html

Questions
=========

#### 1. Which of the above requirements work, and which do not? For each requirement, write a brief response.

1. Works all are O(1)
2. Works uses linear probobing except when grow is called.
3. works gorw is of cours O(n)
4. Does not leak memory

#### 2. I decided to use two function (`keyExists` and `find`) to enable lookup of keys. Another option would have been to have `find` return a `T*`, which would be `NULL` if an item with matching key is not found. Which design do you think would be better? Explain your reasoning. You may notice that the designers of C++ made the same decision I did when they designed http://www.cplusplus.com/reference/unordered_map/unordered_map/

The best design in my oponion was the one which you and the c++ designers used.  This is simply because it is always better to avoid giving the user the oppertuniry to modify the data.  If you accidently gave the user access he or she could easily minipulate the data in ways you would not want.  Though the T* way is easier in terms of programming and creates less code the way you and the c++ designers did it is safer.

#### 3. What is one question that confused you about this exercise, or one piece of advice you would share with students next semester?

Read all you can about hash tables, I thought I understod them but I didn't.  For some reason it ended up being the hardest ADT for me. 
