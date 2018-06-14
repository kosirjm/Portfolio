#define NULL 0
#include <string>
using namespace std;

template <class Key, class T>
BST<Key,T>::BST(){
	root = NULL;
}

template <class Key, class T>
BST<Key,T>::~BST(){
 while(root != NULL)
 {
	 remove(root->k);
	 root = NULL;
 }

}
  
//Return the number of items currently in the SSet
template <class Key, class T>
unsigned long BST<Key,T>::size(){
  return size(root);
}

template <class Key, class T>
unsigned long BST<Key,T>::size(Node<Key,T>* r){
	if(r==NULL)
		return 0;
	
	if(r->right != NULL && r->left != NULL){
	return size(r->right) + size(r->left)+1;}
	
	if(r->right == NULL && r->left != NULL){
	return size(r->left) + 1;}

	if(r->right != NULL && r->left == NULL){
	return size(r->right) + 1;}

	else
		return 1;
}

//Add a new item, x, with Key k.
// If an item with Key k already exists, overwrite it
template <class Key, class T>
void BST<Key,T>::add(Key k, T x){
  root = add(k,x,root);
}

//Remove the item with Key k. If there is no such item, do nothing.
template <class Key, class T>
void BST<Key,T>::remove(Key k){
 root = remove(k,root);
}

//Return the item with Key k. 
// If there is no such item, throw an exception.
template <class Key, class T>
T BST<Key,T>::find(Key k){

 Node<Key,T>* item= find(k,root);
 if(item == NULL)
	 throw string("No such item exists!");
 else
	 return item->data;
}
//Return true if there is an item with Key k in the table. If not,
// return false
template <class Key, class T>
bool BST<Key,T>::keyExists(Key k){
  Node<Key,T>* item = find(k,root);
  if(item != NULL)
	  return true;
  else
	  return false;
}

//If there is a key in the set that is > k,
// return the first such key. If not, return k
template <class Key, class T>
Key BST<Key,T>::next(Key k){
  Node<Key,T>* item = next(k,root);
 if(item != NULL)
	  return item->k;
	else
		return k;
}

template <class Key, class T>
Node<Key,T>* BST<Key,T>::next(Key k, Node<Key,T>* r){
	if(r == NULL)
		return NULL;
	
	else if(r->k < k)
		prev(k, r->right);
	
	else if(r->k > k)
		if(r->left != NULL && !(r-> left->k <= k))
			next(k, r->left);
		else
			return r;
	else
		next(k, r->left);
	
}

//If there is a key in the set that is < k,
// return the first such key. If not, return k
template <class Key, class T>
Key BST<Key,T>::prev(Key k){
  Node<Key,T>* item = prev(k,root);
  if(item !=NULL)
		return item->k;
	else
		return k;
}

template <class Key, class T>
Node<Key,T>* BST<Key,T>::prev(Key k, Node<Key,T>* r){
 if(r == NULL)
		return NULL;
	
 if(r->k > k)
		prev(k, r->left);
		
 else if(r->k < k)
		if(r->right != NULL && !(r-> right->k >= k))
			prev(k, r->right);
		else
			return r;
 else
		prev(k, r->left);
	
}


template <class Key, class T>
Node<Key,T>* BST<Key,T>::add(Key k, T x, Node<Key,T>* r){
  if(r == NULL){
	  r = new Node<Key, T>();
	  r ->k = k;
	  r -> data = x;
	  r -> right = NULL;
	  r -> left = NULL;
  }
  else if(k == (r->k))
	  r->data = x;

  else if(k < r->k)
	  r->left = add(k,x,r->left);

  else
	  r->right = add(k,x,r->right);

  return r;
}

template <class Key, class T>
Node<Key,T>* BST<Key,T>::remove(Key k, Node<Key,T>* r){
	//Found algorithm to help me online from http://www.stanford.edu/class/cs106b/lectures.shtml#today lecture 10-08

	if (r == NULL) 
		return NULL;//These are not the droids you are looking for

	else if(r->k == k)
	{ 
		if(r->left == NULL && r->right == NULL)
		{
			delete r;
			r= NULL;
		}

		 else if (r->left == NULL && r->right != NULL) 
		 {
            r = r->right;
		}

         else if (r->right == NULL && r->left != NULL)
		 {
            r = r->left;
		 }

         else
		 { 

          Node<Key, T>* temp = max(r -> left);
          
		  T tempT = temp -> data;
          temp-> data = r -> data;
          r -> data = tempT;
		  
		  Key tempK = temp -> k;
          temp -> k = r -> k;
          r -> k = tempK;

          r -> left = remove(k, r -> left);
		 }
	}
	else if (r->k < k) 
		r-> right = remove(k, r->right);
	
	else
		r-> left = remove(k, r->left);


return r;
}

template <class Key, class T>
Node<Key,T>* BST<Key,T>::find(Key k, Node<Key,T>* r){
  if(r == NULL)
	  return NULL;

  else if(k < r->k)
	 find(k, r->left);

  else if(k > r-> k)
	 find(k, r->right);

  else
	  return r;
}

template <class Key, class T>
Node<Key,T>* BST<Key,T>::max(Node<Key,T>* r){
  if(r->right == NULL || r == NULL)
	  return r;
  
  else
	 return max(r->right);
}

template <class Key, class T>
Node<Key,T>* BST<Key,T>::min(Node<Key,T>* r){
  if(r->left ==NULL || r== NULL)
	  return r;
  else
	 return  min(r->left);
}
