// A simple word count program that counts words from standard input
// Student name: Jonathan Kosir 
// Section: B
// Compile: g++ -g -Wall -std=c++11 WordCount.cpp -o WordCount

#include <string>
#include <iostream>
#include <iterator>

int main() {
    int wordCount = 0;
    std::istream_iterator<std::string> word(std::cin), eof;
    for(; (word != eof); word++, wordCount++);
    std::cout << "Number of words: " << wordCount << std::endl;
    return 0;
}
