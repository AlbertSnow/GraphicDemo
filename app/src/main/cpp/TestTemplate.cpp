//
// Created by Apple on 2019-10-21.
//

#include <iostream>

using namespace std;


// Function template
template <class T>
T sum (T a, T b)
{
    T result;
    result = a + b;

    return result;
}

//No-type template
template<class T, int n>
T testNoTypeTemplate(T val) {
    return val * n;
}

// ---- Function template ----

//template<class E>
//class MyPair {
//    E value[2];
//
//public:
//
//    MyPair(E first, E second) {
//        value[0] =  first;
//        value[1] = second;
//    }
//
//    E getMax();
////    E getMax(){
////        E retvl;
////        retvl = value[0] > value[1] ? value[0] : value[1];
////        return retvl;
////    }
//};

template<class E>
class MyPair {
    E value[2];

public:

    MyPair(E first, E second) {
        value[0] =  first;
        value[1] = second;
    }

    E getMax();

    ~MyPair(){
        cout << "I'm destructor \n";
    }
};



template <class E>
E MyPair<E>::getMax() {
    E retvl;
    retvl = value[0] > value[1] ? value[0] : value[1];
    return retvl;
}

int main() {
    int i = 5, j = 6, k;
    double f = 2.0, g = 0.5, h;

    k = sum<int>(i, j);
    h = sum<double>(f, g);

    cout << "result int: " << k << '\n';
    cout << "result double: " << h << '\n';

    cout << "no template result: " << (testNoTypeTemplate<float, 2>(3.0f)) << '\n';

    // template decide at compile time, so no type must be constant expr
    cout << "no template result: " << (testNoTypeTemplate<float, 3>(3.0f)) << '\n';


    MyPair<int>* instance = new MyPair<int>(2, 10);
    cout << "max value: " << instance->getMax() << "\n";

    MyPair<int> instance1 (3, 11);
    cout << "max value1: " << instance1.getMax() << "\n";


    MyPair<int> instance2 (4, 12);
    cout << "max value2: " << instance2.getMax() << "\n";

    delete instance; // dynamic memory need call delete key word

    return 0;
}

//
//template <class T>
//class Person {
//
//    public:
//        T name;
//};
//
//int main() {
//    Person<int> *p1 = new Person<int>();
//    p1->name = 1;
//
//    cout << "Name: " << p1->name << "\n";
//
//    return 0;
//}
//

