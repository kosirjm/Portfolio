/**
   Copyright 2014 Jonathan Kosir
   Lab 12 std::cout std::cin
*/


int main(int argc, char *argv[]) {
   std::ifstream inputFile(argv[1]);
   // Now inputFile can be used just like std::cin
   if(!inputFile.good()){
       std::cerr << "Unable to read input.";
           return 1 ;
   }
   
   std::ofstream outFile(argv[2]);
   // Now outFile can be used just like std::cout
   std::string line:
       int lineCount = 0;
       while (getline(inFile, line)){
           if (line.find(argv[3]) != string::npos){


               outFile << line << std::endl;
               lineCount++;
           }


           outFile << lineCount << " lines were copied from ";
           
           inputFile.close();
           outFile.close();
           
   
           
           
}
