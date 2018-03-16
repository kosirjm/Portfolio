04_Linked_List_Lab (Going to give shuffle a try instead seems like fun :)
==================

Implement a simple linked list using pointers and classes.

Requirements
------------

1. Add, remove, get, and set should be O(1) if `i == 0` or if `i == size()-1`
2. All methods that take index, `i`, should throw a string exception if `i < 0` or `i >= size()`
3. Do not leak memory (make sure remove and the destructor do the right thing)
4. `size()` is O(1) time (keep track of the numItems when you add or remove, so you can just return the variable)

Reading
=======
"Open Data Structures," Chapter 3, up through section 2 (DLList), http://opendatastructures.org/ods-cpp/3_Linked_Lists.html

Questions
=========

#### 1. Which of the above requirements work, and which do not? For each requirement, write a brief response.

1. Works, follows te requirement of O(1) 
2. Works and throws the exception when needed
3. Works no memory leak
4. Works ris O(1) time

#### 2. get is O(n) for LinkedList while ArrayList is O(1)
        add is O(n) for both if must grow and O(1) if not
        set is O(n) worst case for both
        Iterator.remove() is O(1) for Linked List while its O(n-index) for ArrayList
        ListIterator.add(E element) is O(1) for Linked List while its O(n-index) for ArrayList

#### 3. None had a problem with splice other then that everything went well.

