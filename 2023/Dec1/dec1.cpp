#include <iostream>
#include <iomanip>
#include <fstream>
#include <string>
using namespace std;

int number(char a){
    switch(a){
        case '0': return 0;
        case '1': return 1;
        case '2': return 2;
        case '3': return 3;
        case '4': return 4;
        case '5': return 5;
        case '6': return 6;
        case '7': return 7;
        case '8': return 8;
        case '9': return 9;
        default: return -1;
      }
}

int firstdigit(string word){
    int i = 0;
    while(number(word[i]) == -1)
        ++i;
    return number(word[i]);
}

int lastdigit(string word){
    int i = word.size() - 1;
    while(number(word[i]) == -1)
        --i;
    return number(word[i]);
}

int main() {

    std::ifstream in("datos.txt");
    auto cinbuf = std::cin.rdbuf(in.rdbuf());
   
    int sum = 0;
    int fd, ld;
    string word;
    getline(cin, word);
    while(cin){
        fd = firstdigit(word);
        ld = lastdigit(word);
        sum += 10*fd+ld;
        //cout << 10*fd+ld << "\n";
        getline(cin, word);
    }
    cout << "Suma = " << sum;
    return 0;
}