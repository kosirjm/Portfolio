//You will need this so you can make a string to throw in
// remove
#include <string>
unsigned long hash(char c){return 32*((unsigned long)c)%7;}


template <class Key, class T>
HashTable<Key,T>::HashTable(){
	numItems = 0;
	numRemoved = 0;
	backingArraySize = hashPrimes[0];
	backingArray = new HashRecord[backingArraySize];
}

template <class Key, class T>
HashTable<Key,T>::~HashTable() {
	delete[] backingArray;
	numItems = 0;
	numRemoved = 0;
}

template <class Key, class T>
void HashTable<Key,T>::add(Key k, T x){
  if(numItems + numRemoved >= backingArraySize/2)
		grow();
  
  long pos = hash(k) % backingArraySize;
	
	while(backingArray[pos].isNull != true && backingArray[pos].k != k)
	{
		pos += (1 + (pos % (backingArraySize-1)));
		pos = pos % backingArraySize;
	}

		backingArray[pos].x = x;
		backingArray[pos].k = k;
		backingArray[pos].isNull = false;
		numItems++;
}

template <class Key, class T>
void HashTable<Key,T>::remove(Key k){

 long pos = hash(k) % backingArraySize;
 
        while(backingArray[pos].isNull != true)
		{
				if((backingArray[pos].isDel == false) && (backingArray[pos].k==k))
					break;

		pos += (1 + (pos % (backingArraySize-1)));
		pos = pos % backingArraySize;
		}
        
	 backingArray[pos].isDel = true;
	 backingArray[pos].isNull = false;
	 numItems--;
	 numRemoved++;
                
}


template <class Key, class T>
T HashTable<Key,T>::find(Key k){
	if(!keyExists(k))
				throw std::string("Key does not exist!");

 
 long pos = hash(k) % backingArraySize;

 while(backingArray[pos].isNull != true)
		{
				if((backingArray[pos].isDel == false) && (backingArray[pos].k==k))
				return backingArray[pos].x;

		pos += (1 + (pos % (backingArraySize-1)));
		pos = pos % backingArraySize;
       }
}
  

template <class Key, class T>
bool HashTable<Key,T>::keyExists(Key k){
 
 long pos = hash(k) % backingArraySize;

 while(backingArray[pos].isNull != true)
		{
				if((backingArray[pos].isDel == false) && (backingArray[pos].k==k))
				return true;

		pos += (1 + (pos % (backingArraySize-1)));
		pos = pos % backingArraySize;
       }
 return false;
		
}

template <class Key, class T>
unsigned long HashTable<Key,T>::size(){
	return numItems;
}

template <class Key, class T>
void HashTable<Key,T>::grow(){
        int primesI = 0;

		while(hashPrimes[primesI] <= backingArraySize)
			primesI++;

		if(hashPrimes[primesI] == NULL)
			throw std::string("Array Is too Big! How did you do this!!");
		else
		{
			HashRecord* temp = backingArray;
			long tempSize = backingArraySize;
			backingArraySize = hashPrimes[primesI];
			backingArray = new HashRecord[backingArraySize];

			numItems=0;
			numRemoved = 0;
			for (int i = 0; i < tempSize; i++)
				 if(temp[i].isNull != true && temp[i].isDel != true)
				   add(temp[i].k , temp[i].x);

        delete[] temp;
		}
}
