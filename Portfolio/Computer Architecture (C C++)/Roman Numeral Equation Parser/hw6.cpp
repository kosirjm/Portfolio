/**--------------------------------------------------------------------------
   Copyright 2014 Jonathan Kosir
   CSE 381 Operating Systems
   Homework 6 Roman Numerals

   
   This program does simple Roman numeral math using the 
   expressions - and +.  It can be assumed all input will be correct
   the number will not be higher then 999,999, and will only use the
   Roman numerals up to m.  Also none out the roman numberals will be
   uppercase and will have a space in between operations using one single line
   -----------------------------------------------------------------------------
*/

#include <iostream>
#include <string>
#include <sstream> 

using namespace std;

class hw6{
  
int main();
int romanNumToInt(char romanNum);
int romanSolve(std::string romanNum);
};

 

int romanNumToInt(char romanNum){

int num;

switch(romanNum){
 case 'i': num = 10;
break;
 case 'v': num = 10;
break;
 case 'x': num = 10;
break;
 case 'l': num = 50;
break;
 case 'c': num = 100;
break;
 case 'd': num = 500;
break;
 case 'm': num = 1000;
break;
}
return num;
}
 
int romanSolve(std::string romanNum){
  
std::string str = romanNum;
int num;
bool check = false;
for(std::string::size_type i = 0; i < str.size(); ++i) {

if(check = true){
num = romanNumToInt(str[i]) - romanNumToInt(str[i-1]);
check = false;
}
 else{
num = num + romanNumToInt(str[i]);   
}
if(romanNumToInt(str[i]) < romanNumToInt(str[i+1]))
  {
check = true;
}
}
return num;
}

//std::string intToText(int num);
  
/*
  void parseWord(std::string word){
  istringstream iss(str1);
 
  while (iss) {
  string word;
  iss >> word;
  cout << word << endl;
  }
    
  int problemSolve(int number1, int number2, char sign){
*/
    
int main(){

char romanNumerals;

cout << "Insert roman numerals math equation ";
cout << romanSolve("iv");
/**
std::getline(std::cin, romanNumerals);
string arr[3];
int i = 0;
stringstream ssin(romanNumerals);

while (ssin.good() && i < 4){
ssin >> arr[i];
++i;
}

for(i = 0; i < 4; i++){
cout << arr[i] << endl;
} 
    
problemSolve(arr[0], arr[1], arr[2]);
cout << romanSolve();
return 0;
*/
}
