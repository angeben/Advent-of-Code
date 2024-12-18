#include <iostream>
#include <iomanip>
#include <fstream>
#include <string>
#include <sstream>
#include <cmath>
#include <vector>
#include <set>
using namespace std;

set<int> stringToSet(string str){
    stringstream ss(str);
    set<int> s;
    string num;
    while(ss >> num)
        s.insert(stoi(num));
    return s;
}

int main() {
    std::ifstream in("datos.txt");
    auto cinbuf = std::cin.rdbuf(in.rdbuf());
   
    string line;
    vector<int> cards(220,1);
    int i = 0;
    getline(cin, line);

    while(cin){
        int colon = line.find(':') + 1;
        string numbers = line.substr(colon, line.size() - colon);

        int hash = numbers.find('|');
        set<int> winNumbers = stringToSet(numbers.substr(0,hash));
        set<int> myNumbers = stringToSet(numbers.substr(hash+1,numbers.size()-hash-1));

        int found = 0;
        for (set<int>::iterator it = winNumbers.begin(); it != winNumbers.end(); ++it){
            if(myNumbers.count(*it) != 0)
                ++found;
        }
        if(found != 0)
            for(int j = i + 1; j < cards.size() && j <= i + found; ++j)
                cards[j] += cards[i];

        ++i;
        getline(cin, line);
    }

    int sum = 0;
    for(int i = 0; i < cards.size(); ++i)
        sum += cards[i];
    cout << sum;
    return 0;
}
