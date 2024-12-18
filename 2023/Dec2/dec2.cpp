#include <iostream>
#include <iomanip>
#include <fstream>
#include <string>
#include <vector>
using namespace std;

const int maxRED = 12;
const int maxBLUE = 14;
const int maxGREEN = 13;

struct cubes {
    int n;
    string color;
};

int getgameID(string str){
    int i = str.find(' ');
    string strnumber = str.substr(i+1, str.size()-i-1);
    return stoi(strnumber);
}

vector<cubes> strTocubes(vector<string> rolls){
    vector<cubes> c;
    for(int j = 0; j < rolls.size(); ++j){
        int k = rolls[j].find(' ');
        c.push_back({stoi(rolls[j].substr(0,k)),rolls[j].substr(k+1,rolls[j].size() - k - 1)});
    }
    return c;
}

vector<cubes> get_cubes(string roll){
    // Split by ','
    // "2 green, 1 red" -> {"2 green", "1 red"}
    string aux2 = roll;
    vector<string> rolls2;
    int j = aux2.find(",");
    while(j < aux2.size()){
        rolls2.push_back(aux2.substr(0,j));        
        //cout << "Insertado :" << aux2.substr(0,j) << "\n";
        aux2 = aux2.substr(j+2,aux2.size()-j-2);
        //cout << "Nuevo aux :" << aux2 << "\n";
        j = aux2.find(",");
    }
    rolls2.push_back(aux2);
    // Convert string vector to cubes vector
    return strTocubes(rolls2);
}

// "1 red, 2 blue; 2 green, 1 red" -> {"1 red, 2 blue","2 green, 1 red"}
vector<string> get_inidividual_rolls(string game_rolls){
    // Get individual rolls in vector rolls
    string aux = game_rolls;
    vector<string> rolls;
    int i = aux.find(';');
    while(i < aux.size()){
        rolls.push_back(aux.substr(0,i));
        aux = aux.substr(i+2,aux.size()-i-2);
        i = aux.find(';');
    }
    rolls.push_back(aux);
    return rolls;
}

bool possibleGame(string game_rolls){
    vector<string> ind_rolls = get_inidividual_rolls(game_rolls);

    // Analyze each roll for RGB cubes
    bool possible = true;
    for(int i = 0; possible && i < ind_rolls.size(); ++i){
        vector<cubes> c = get_cubes(ind_rolls[i]);
        for(int j = 0; possible && j < c.size(); ++j){
            if(c[j].color == "red")
                possible = c[j].n <= maxRED;
            else if(c[j].color == "blue")
                possible = c[j].n <= maxBLUE;
            else possible = c[j].n <= maxGREEN;
        }
    }
    return possible;
}

int main() {

    std::ifstream in("datos.txt");
    auto cinbuf = std::cin.rdbuf(in.rdbuf());
   
    int sum = 0;
    string line;
    getline(cin, line);
    while(cin){
        int semi = line.find(':');
        string game = line.substr(0, semi);
        string game_rolls = line.substr(semi+2, line.size()-(semi+2));

        if(possibleGame(game_rolls))
            sum += getgameID(game);

        getline(cin, line);
    }
    cout << sum;
    return 0;
}