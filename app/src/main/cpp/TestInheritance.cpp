//
// Created by Apple on 2019-10-23.
//

#include <iostream>

using namespace std;

class Animal {

protected:
    int age;

public:
    int publicAge;

    Animal(){
        cout  << "animal construct \n";
    }
};

// protected将成为pubicAge的access level
class Human : protected Animal {

public:
    Human() {
        cout << "Im human construct, age: " << age;
    }

};


class Child : Human {
public:
    Child() {
        cout << "Im human construct, age: " << age;
    }
};

int main() {
    Human human;

    cout << "create human over \n";
    Child child;
    //protected修饰Animal，导致所有继承自Animal的member，最多是protected属性
//    cout << "public age: " << human.publicAge << "\n";

    return 0;
}


