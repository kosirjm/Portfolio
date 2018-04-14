// Copyright 2014 raodm@miamiOH.edu

#include <iostream>
#include <cmath>
#include <algorithm>
#include <iterator>
#include <fstream>
#include <stdexcept>
#include <vector>
#include <string>

// Shortcut to refer to std::vector<int>
typedef std::vector<int> NumList;

/**
   Method to determine if a given number if prime.

   This method essentially checks to see if the given number has a
   factor in the range 2 <= factor <= sqrt(num).  If a factor is
   found, then the given number is not prime. Otherwise the number
   must be prime.

   NOTE: Use routine loops to implement this method.
   
   \param[in] num The number to be checked to determine if it is prime.
   
   \return Returns true if the given num is prime. Otherwise this
   method returns false.
*/
bool isPrime(const int num) {

    for(int i = 2; i <= sqrt(num); i++){
        if(num % i == 0){
            return false;
        }
    }
    return true;
}

/** Count the number of prime factors for a given number.

    This method iterates over the range 1 < fact <= num to search
    for prime factors for a given number.  It uses the isPrime()
    method to check if a factor of num is indeed prime.

    NOTE: Use routine loops to implement this method.
   
    \param[in] num The number whose prime factors are to be
    returned by this method.

    \return The number of prime factors of num.
*/
int getPrimeFactorCount(const int num) {

    int primeCount = 0;
    
    for(int i = 2; i <= num; i++){
        if(num % i == 0){
            if(isPrime(i)){
                primeCount++;
            }
        }
    }
        
    return primeCount;
}

/** Convenience method to determine if num1 has more prime factors
    than num2.

    NOTE: One liner.
   
    \param[in] num1 The first number whose prime factors are to be
    counted.

    \param[in] num2 The second number whose prime factors are to be
    counted.

    \return This method returns true if num1 has more prime factors
    than num2.
*/
bool hasMorePrimeFactors(const int num1, const int num2) {
    int primeNum1 = getPrimeFactorCount(num1);
    int primeNum2 = getPrimeFactorCount(num2);
   
    return (primeNum1 > primeNum2);
}

/** Convenience method to load numbers from a given file.

    NOTE: Using iterators and std::copy makes this method about 4-5
    lines of code.
    
    \param[in] fileName The file from which the numbers are to be
    loaded.

    \return A vector containing the numbers loaded from the file.
 */
NumList loadNumbers(const std::string& fileName) {
    std::ifstream inFile(fileName);
    if(!inFile.good()){
        std::cerr << "Unable to read the file\n";
        exit(1);
    }
    std::vector<int> numList;
    int num;
    while((inFile>>num)){
        numList.push_back(num);
    }
    return numList;
}

/** Convenience method to write numbers to a given file.

    NOTE: Using iterators and std::copy makes this method about 4-5
    lines of code.
    
    \param[in] fileName The file name to write the numbers to.

    \param[in] numList The list of numbers to be written to specified
    output file.
*/
void writeTo(const std::string& fileName, const NumList& numList) {
    std::ofstream outFile(fileName);
    if(!outFile){
        std::cerr << "couldnt open file";
        exit(1);
    }
    copy(numList.begin(), numList.end(),
         std::ostream_iterator<int> (outFile, "\n"));

    
}

/** The main method for this program

    The main method uses the various methods in this program to:

    1. Load numbers from a given file into a vector
    
    2. Sort numbers using std::sort using hasMorePrimeFactors

    3. Resizes the list of numbers to the value of N.

    4. Depending on whether a 3rd command-line argument is specified,
       write top-N numbers to std::cout (using std::copy algorithm) or
       the specified file (via call to writeTo() method).

    5. Returns 0 (to indicate successful completion)
*/
int main(int argc, char *argv[]) {

    //Part 1
    std::cout << "Enter file name";
    std::string fileName;
    std::cin >> fileName;

    //Part 2
    std::vector<int> numList = loadNumbers(fileName);
    std::sort(numList.begin(), numList.end(), hasMorePrimeFactors);

    
    //Part 3
    int N;
    std::cout << "Enter list numbers";
    std::cin >> N;
    numList.resize(N);
    
    //Part 4
    std::cout << "File name for output";
    std::string outFile;
    std::cin >> outFile;
    writeTo(outFile,numList);

    //Part 5
    return 0;
    
}

// End of source code
