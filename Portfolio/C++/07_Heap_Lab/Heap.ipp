#include <string>
using namespace std;

template<class Pri, class T>
Heap<Pri,T>::Heap(){
  arrSize = START_SIZE;
  numItems = 0;
  backingArray = new pair<Pri,T>[START_SIZE];
}

template<class Pri, class T>
Heap<Pri,T>::~Heap(){
  delete[] backingArray;
}

template<class Pri, class T>
void Heap<Pri,T>::grow(){
	arrSize = arrSize * 2;
	pair<Pri,T>* temp = new pair<Pri,T>[arrSize];
	if(temp == NULL)
		throw string("No more memory-thats how school makes me feel sometimes");
	for (int i = 0; i < numItems; i++)
		temp[i] = backingArray[i];
	delete[] backingArray;
	backingArray = temp;
}

template<class Pri, class T>
void Heap<Pri,T>::add(std::pair<Pri,T> toAdd){
	
	if(numItems != arrSize)
	{
	backingArray[numItems] = toAdd;
	bubbleUp(numItems);
	numItems++;
	}
	else
		grow();
}

template<class Pri, class T>
void Heap<Pri,T>::bubbleUp(unsigned long index){
	long pIndex = ((index-1)/2);
	while(backingArray[index] < backingArray[pIndex] && (index > 0))
	{
		 backingArray[pIndex].swap(backingArray[index]);
		 index = pIndex;
		 pIndex = (index-1)/2;
	}
}

template<class Pri, class T>
void Heap<Pri,T>::trickleDown(unsigned long index){
  long lchild = index*2 + 1;
  long rchild = lchild + 1;
  long toSwap;
  bool shouldRun = true;
 
  if(index >= numItems/2)
	  shouldRun = false;

	  while(shouldRun)       
	  {
		  
		  if(rchild < numItems &&  backingArray[lchild] > backingArray[rchild])
            toSwap = rchild;
         
		  else
            toSwap = lchild;

		  if(backingArray[index] <= backingArray[toSwap])
            shouldRun = false;
		  else
			backingArray[index].swap(backingArray[toSwap]); 
         }   
}

template<class Pri, class T>
std::pair<Pri,T> Heap<Pri,T>::remove(){
	if(numItems == 0)
		throw string("No items to remove");

  std::pair<Pri,T> temp = backingArray[0];
  backingArray[0]=backingArray[numItems-1];
  numItems--;
  trickleDown(0);
  return temp;
}

template<class Pri, class T>
unsigned long Heap<Pri,T>::getNumItems(){
  return numItems;
}
