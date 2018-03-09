#include "ArrayQueue.h"
#include <iostream>

//Check to make sure that numItems returns correct result on a fresh
// instance of the ArrayQueue class
void testCtor(ArrayQueue<int>& testQueue){
  if(testQueue.getNumItems() == 0){
    std::cout << "SUCCESS: Fresh queue has 0 items" << std::endl;
  } else {
    std::cout << "ERROR: Fresh queue should have 0 items, but has " << testQueue.getNumItems() << std::endl;
  }
}

//Add and remove some items, making sure they come back in the
// correct order
void testAddRemove(ArrayQueue<int>& testQueue){
  testQueue.add(5);
  testQueue.add(10);
  testQueue.add(4);
  if(testQueue.getNumItems() == 3){
    std::cout << "SUCCESS: 3 items added" << std::endl;
  } else {
    std::cout << "ERROR: Added 3 items, but getNumItems says " << testQueue.getNumItems() << std::endl;
    return;
  }
  int x = testQueue.remove();
  int y = testQueue.remove();
  int z = testQueue.remove();
  if(x != 5 || y != 10 || z != 4){
    std::cout << "ERROR: Expected 5, 10, 4, but got " << x <<", " << y << ", " << "z" << std::endl;
  } else {
    std::cout << "SUCCESS: 3 added items came back out in the correct order" << std::endl;
  }
}

//Test to see if your queue still works if we do add, remove, add, remove
// many times
void testAroundTheHorn(ArrayQueue<int>& testQueue){
  for(int i=0;i<1000;i++){
    testQueue.add(i);
    int t = testQueue.remove();
    if(t != i){
      std::cout << "ERROR: Added " << i << " but got back " << t << std::endl;
      return;
    }
  }
  std::cout << "SUCCESS: Added and removed 1000 items successfully" << std::endl;
}

//Test to see if your queue can grow to handle lots of items
void testGrow(ArrayQueue<int>& testQueue){
  for(int i=0;i<1000;i++){
    testQueue.add(i);
  }

  if(testQueue.getNumItems() != 1000){
    std::cout << "ERROR: Should have 1000 items in queue, but only found " << testQueue.getNumItems() << std::endl;
    return;
  }

  for(int i=0;i<1000;i++){
    int t = testQueue.remove();
    if(t != i){
      std::cout << "ERROR: Added " << i << " but got back " << t << std::endl;
      return;
    }
  }
  std::cout << "SUCCESS: Added 1000 items, then removed 1000" << std::endl;
}

//Test to make sure you are throwing an exception if remove is
// called improperly
void testRemoveException(ArrayQueue<int>& testQueue){
  try {
    int t = testQueue.remove();
  } catch (std::string s) {
    std::cout << "SUCCESS: Caught exception: " << s << std::endl;
    return;
  } catch (...) {
    std::cout << "ERROR: Caught an exception, but it wasn't a string type" << std::endl;
    return;
  }

  std::cout << "ERROR: Tried to remove from an empty queue, but did not get an exception" << std::endl;
}

//A simple main function which creates a queue, and tests it.
int main(){
  ArrayQueue<int> testQueue;
  testCtor(testQueue);
  testAddRemove(testQueue);
  testAroundTheHorn(testQueue);
  testGrow(testQueue);
  testRemoveException(testQueue);

  return 0;
}
