//You will need this so you can make a string to throw in
// remove
#include <string>
using namespace std;
//Syntax note: C++ is not very good at figuring out which methods belong
// to which classes. That is why we have to use the scope operator to
// tell the compiler that this LinkedList() method belongs to the
// LinkedList<T> class.
template <class T>

LinkedList<T>::LinkedList(){
  dummyNode = new Node();
  dummyNode->next = dummyNode;
  dummyNode->prev = dummyNode;
  numItems = 0;
}

template <class T>
LinkedList<T>::~LinkedList() {
	while (numItems > 0)
		remove(0);
	delete dummyNode;
}

template <class T>
typename LinkedList<T>::Node* LinkedList<T>::find(unsigned long i){

	if(i > numItems || i < 0)
		throw string("Not in bounds");

	if(i == (numItems))
		return dummyNode;

	else
	{
		Node* temp = dummyNode->next;
		int j = 0;
		while(j < i)
		{ 
			j++;
			temp = temp->next;
		}
		return temp;
	}
}

template <class T>
void LinkedList<T>::set(unsigned long i, T x){
  Node* setNode = find(i);
  setNode->data = x;
}

template <class T>
void LinkedList<T>::add(unsigned long i, T x){
  Node* current = find(i);
  Node* newNode = new Node;
  newNode -> data = x;
  newNode -> next  = current;
  newNode -> prev = current -> prev;
  newNode -> prev -> next = newNode;
  newNode -> next -> prev = newNode;
  numItems++;
}

template <class T>
void LinkedList<T>::remove(unsigned long i){
  Node* toRemove = find(i);
  if(numItems == 0)
	  throw string("No Items To remove");
  toRemove -> next -> prev = toRemove -> prev;
  toRemove -> prev -> next = toRemove -> next;
  delete toRemove;
  numItems--;
}

template <class T>
T LinkedList<T>::get(unsigned long i){
  return find(i)->data;
}

template <class T>
void LinkedList<T>::splice(unsigned long i, unsigned long len, LinkedList<T>& target, unsigned long t){
  //TODO
}

template <class T>
unsigned long LinkedList<T>::size(){
	return numItems;
}
