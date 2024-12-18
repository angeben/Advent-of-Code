#include <iostream>
#include <iomanip>
#include <fstream>
#include <string>
using namespace std;

int number(char a){
    switch(a){
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

int checkletters(string word, int i){
    int n = -1;
    string sub = word.substr(i,3);
    if(sub == "one")
        n = 1;
    else if(sub == "two")
        n = 2;
    else if(sub == "six")
        n = 6;
    
    if(n == -1 && i+3 < word.size()){
        sub = word.substr(i,4);
        if(sub == "four")
            n = 4;
        else if(sub == "five")
            n = 5;
        else if(sub == "nine")
            n = 9;
    }

    if(n == -1 && i+4 < word.size()){
        sub = word.substr(i,5);
        if(sub == "three")
            n = 3;
        else if(sub == "seven")
            n = 7;
        else if(sub == "eight")
            n = 8;
    }
    return n;
}

int firstdigitword(string word){
    int f = -1;
    int i = 0;
    while(f == -1){
        f = number(word[i]);
        if(f == -1 && i+2 < word.size())
            f = checkletters(word,i);
        ++i;
    }
    return f;
}

int lastdigitword(string word){
    int f = number(word[word.size()-1]);
    if(f == -1)
        f = number(word[word.size()-2]);
    int i = word.size() - 3;
    while(f == -1){
        f = number(word[i]);
        if(f == -1)
            f = checkletters(word,i);
        --i;
    }
    return f;
}

int main() {

    std::ifstream in("datos.txt");
    auto cinbuf = std::cin.rdbuf(in.rdbuf());
   
    int sum = 0;
    int fd, ld;
    string word;
    getline(cin, word);
    while(cin){
        fd = firstdigitword(word);
        ld = lastdigitword(word);
        sum += 10*fd+ld;
        //cout << 10*fd+ld << "\n";
        getline(cin, word);
    }
    cout << "Suma = " << sum;

    return 0;
}