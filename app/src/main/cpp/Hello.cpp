//
// Created by Albert Snow on 11/4/2019.
//

#include <iostream>
#include <sys/types.h>
#include <unistd.h>

using namespace std;

int main() {
    cout << "hello, world" << endl;

    int pid = fork();
    if (pid > 0) {
        printf(" I am the parent of pid = %d!\n", pid);
    } else if (!pid) {
        printf (" I am the child!\n");
    } else if (pid == -1) {
        perror ("fork");
    }


    return 0;
}
