//
// Created by Apple on 2019-10-24.
//

#include <iostream>
#include <fstream>
#include <string>

using namespace std;


void writeFile(string fileName) {
    ofstream myfile (fileName);

    if (myfile.is_open()) {
        myfile << "This is already opened. \n";
        myfile.close();
    }

    else cout << "Unable to open file";

}

void readFile(string fileName) {
    string line;
    ifstream myfile (fileName);
    if (myfile.is_open()) {

        while (getline(myfile, line)) {
            cout << line << '\n';
        }

        cout << "is eof: " << (myfile.eof() ? "Yes" : "No") << endl;

        myfile.close();
    }

    else cout << "Unable to open file";
}


void getSizeByPosition(const string fileName) {
    streampos begin, end;

    ifstream myfile(fileName, ios::binary);
    begin = myfile.tellg();

    myfile.seekg (0, ios::end);
    end = myfile.tellg();

    myfile.close();

    cout << "size is: " << (end - begin) << "bytes.\n";
}

void readBinaryFile(const string fileName) {
    streampos size;
    char * memblock;

    ifstream file (fileName, ios::in|ios::binary|ios::ate);

    if (file.is_open()) {
        size = file.tellg();
        memblock = new char[size];

        file.seekg (0, ios::beg);
        file.read(memblock, size);
        file.close();

        cout << "file content in memory\n";

        cout << "output file content \n";

        for(int i = 0; i<size; i++) {
            cout << memblock[i];
        }

        delete[] memblock;
    }
    else  cout << "unable to open file \n";
}

int main() {
    const string fileName = "hello.txt";
    writeFile(fileName);
    readFile(fileName);
    getSizeByPosition(fileName);
    readBinaryFile(fileName);
    return 0;
}
