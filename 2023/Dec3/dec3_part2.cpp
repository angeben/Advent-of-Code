#include <iostream>
#include <iomanip>
#include <fstream>
#include <string>
#include <vector>
using namespace std;

const vector<int> dirX = {-1,-1,-1,0,0,1,1,1};
const vector<int> dirY = {-1,0,1,-1,1,-1,0,1};

void printSV(vector<string> v){
    for(int i = 0; i < v.size(); ++i)
        cout << v[i] << "\n";
}

void printIV(vector<int> v){
    for(int i = 0; i < v.size(); ++i)
        cout << v[i] << "\n";
}

bool number(char a){
    switch(a){
        case '0': return true;
        case '1': return true;
        case '2': return true;
        case '3': return true;
        case '4': return true;
        case '5': return true;
        case '6': return true;
        case '7': return true;
        case '8': return true;
        case '9': return true;
        default: return false;
      }
}

bool char_symbol(char a){
    if (number(a) || a == '.')
        return false;
    else return true;
}

bool symbol_around(vector<string> v, int i, int n){
    bool found = false;
    for(int d = 0; d < dirX.size(); ++d){
        int newi = i + dirX[d];
        int newn = n + dirY[d];
        if(newi >= 0 && newi < v.size() && newn >= 0 && newn < v[i].size())
            found = found || char_symbol(v[newi][newn]);
    }
    return found;
}

bool isGearNumber(vector<string> v, int i, int j, int k){
    bool gear = false;
    int n = j;
    while(n < k && !gear){
        gear = gear || symbol_around(v,i,n);
        ++n;
    }
    return gear;
}

void searchLeftRight(string line, int j, vector<int> & numbers){
    // Number to the left of the *
    if(j - 1 >= 0 && number(line[j-1])){
        int k = j - 2;
        while(k >= 0 && number(line[k]))
            --k;
        numbers.push_back(stoi(line.substr(k+1,j-(k+1))));
    }
    // Number to the right of the *
    if(j + 1 < line.size() && number(line[j+1])){
        int k = j + 2;
        while(k < line.size() && number(line[k]))
            ++k;
        numbers.push_back(stoi(line.substr(j+1,k-(j+1))));
    }
}

void searchTopBot(string line, int j, vector<int> & numbers){
    // Numbers on the line above the *
    if(number(line[j])){
        int lk = j-1;
        while(lk >= 0 && number(line[lk]))
            --lk;
        int rk = j+1;
        while(rk < line.size() && number(line[rk]))
            ++rk;
        numbers.push_back(stoi(line.substr(lk+1)));
    }
    else{
        if(j-1 >= 0 && number(line[j-1])){
            int k = j-2;
            while(k >= 0 && number(line[k]))
                --k;
            numbers.push_back(stoi(line.substr(k+1,j-1-(k+1)+1)));
        }
        if(j+1 < line.size() && number(line[j+1])){
            int k = j+2;
            while(k < line.size() && number(line[k]))
                ++k;
            numbers.push_back(stoi(line.substr(j+1,k-(j+1))));
        }
    }
}

int main() {

    std::ifstream in("datos.txt");
    auto cinbuf = std::cin.rdbuf(in.rdbuf());
   
    vector<string> v;
    string line;
    getline(cin, line);
    while(cin){
        v.push_back(line);
        getline(cin, line);
    }

    
    vector<int> numbers;
    int sum = 0;
    
    for(int i = 0; i < v.size(); ++i){
        int j = 0;
        while(j < v[i].size()){
            if(v[i][j] == '*'){
                if(i-1 >= 0)
                    searchTopBot(v[i-1],j,numbers);
                if(i+1 < v.size())
                    searchTopBot(v[i+1],j,numbers);
                searchLeftRight(v[i],j,numbers);

                if(numbers.size() == 2)
                    sum += numbers[0]*numbers[1];
                numbers = {};
            }
            ++j;
        }
    }

    cout << sum;

    return 0;
}