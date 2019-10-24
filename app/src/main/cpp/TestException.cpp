//
// Created by Apple on 2019-10-24.
//
#include <iostream>
#include <exception>


using namespace std;

class MyException : public exception {
    virtual const char* what() const throw() {
        return "My exception occur";
    }
} myException;


void testException() {

//    throw 1;
    throw myException;

}


int main() {

    cout << "Start testException \n";

    try {

//        testException();
        throw myException;
    } catch (int params) {
        cout << "Exception error params: " << params << "\n";
    } catch (exception& e) {
        cout << "My exception: " << e.what() << endl;
    } catch (...) {
        cout << "Catch any exception \n";
    }

    cout << "End testException \n";

}


