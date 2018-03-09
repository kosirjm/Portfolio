//You will need this so you can make a string to throw in
// remove
#include <string>

//Syntax note: This uses the pre-processor to create a constant
// You could also use "const static" to make a constant, as in Java.
// Notice, however, that START_SIZE is NOT a variable! Instead, any
// place that the pre-processor sees "START_SIZE" it will replace it with
// 10 ... so this is like a global "find and replace".
#define START_SIZE 10

//Syntax note: C++ is not very good at figuring out which methods belong
// to which classes. That is why we have to use the scope operator to
// tell the compiler that this ArrayQueue() method belongs to the
// ArrayQueue<T> class.

/*
Constructor
*/
template <class T>
ArrayQueue<T>::ArrayQueue(){
	backingArraySize = START_SIZE;
	numItems = 0;
	front = 0;
	backingArray = new T[backingArraySize];
}

/*
Destructor
*/
template <class T>
ArrayQueue<T>::~ArrayQueue() {
	delete[] backingArray;
}

/*
Our add method
Adds an item to our Queue
return - void
*/
template <class T>
void ArrayQueue<T>::add(T toAdd){
	
	//If to big grow array
	if(numItems == backingArraySize)
		grow();

	//Use Modulus to creat circuler array
	backingArray[(front + numItems) % backingArraySize] = toAdd;

	//Add One to num items in list
	numItems++;
}

/*
Our remove method
Removes an item to our Queue
returns - our new array without the removed item
*/
template <class T>
T ArrayQueue<T>::remove(){

	if(numItems == 0)
		throw std::string("The Queue has zero items cannot remove");

	T  itemRemoved = backingArray[front];

	
	front = (front + 1) % backingArraySize;
	numItems--;

	return itemRemoved;
}

/*
Our remove method
Gives us the number of items in the Queue
returns - a long of the number of items
*/
template <class T>
unsigned long ArrayQueue<T>::getNumItems(){
return numItems;
}


/*
Our remove method
Grows our Queue by doubling it
returns - void
*/
template <class T>
void ArrayQueue<T>::grow(){

	T* newQueue = new T[backingArraySize*2];
	 
	if(newQueue == NULL)
		throw std::string("Array Is too Big! How did you do this!!");

	for(unsigned long i = 0; i < backingArraySize; i++)
	{
		newQueue[front + i] = backingArray[(front+i) % backingArraySize];
	}

	delete[] backingArray;
	backingArraySize = backingArraySize*2;
	backingArray = newQueue;
}
