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

    vector<int> gearNumbers;
    int sum = 0;

    for(int i = 0; i < v.size(); ++i){
        int j = 0;
        while(j < v[i].size()){
            if(number(v[i][j])){
                int k = j + 1;
                while(k < v[i].size() && number(v[i][k]))
                    ++k;
                if(isGearNumber(v,i,j,k)){
                    int number = stoi(v[i].substr(j,k-j));
                    gearNumbers.push_back(number);
                    sum += number;
                }                    
                j = k;
            }
            else ++j;
        }
    }

    //printIV(gearNumbers);
    cout << sum;

    return 0;
}