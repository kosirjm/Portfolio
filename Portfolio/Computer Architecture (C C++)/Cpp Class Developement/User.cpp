#include "User.h"

User::User() {
    // std::cout << "User::User() called.\n";
}

User::~User() {
    // std::cout << "User::~User() called.\n";
}

User&
User::operator=(const User& other) {
    this->userID    = other.userID;
    this->firstName = other.firstName;
    this->lastName  = other.lastName;
    return *this;
}

User::User(const std::string& userID, const std::string& firstName,
           const std::string& lastName) : userID(userID), firstName(firstName),
                                          lastName(lastName) {
    // Nothing else to be done in the constructor
    // std::cout << "User::User(...) called.\n";
}

User::User(const User& src){
    userID = src.userID;
    firstName = src.firstName;
    lastName = src.lastName;
}

User::User(User&& src){
    userID = std::move(src.userID);
    firstName = std::move(src.firstName);
    lastName = std::move(src.lastName);
}

bool User::operator<(const User& other) const{
    return (this->userID < other.userID)&&
        (this->firstName < other.firstName)&&
        (this->lastName < other.lastName);
}

bool User::operator==(const User& other) const{
    return (this->userID == other.userID)&&
        (this->firstName == other.firstName)&&
        (this->lastName == other.lastName);
}

bool User::operator()(const User& other) const{
    
}
