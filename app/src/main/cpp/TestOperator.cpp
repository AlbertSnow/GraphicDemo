//
// Created by Apple on 2019-10-21.
//

#include <iostream>

using namespace std;

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


int main() {
    int i = 5, j = 6, k;
    double f = 2.0, g = 0.5, h;

    k = sum<int>(i, j);
    h = sum<double>(f, g);

    cout << "result int: " << k << '\n';
    cout << "result double: " << h << '\n';

    cout << "no template result: " << (testNoTypeTemplate<float, 2>(3.0f));

    // template decide at compile time, so no type must be constant expr
    cout << "no template result: " << (testNoTypeTemplate<float, 3>(3.0f));

    return 0;
}