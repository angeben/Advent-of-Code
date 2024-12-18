package AdventOfCode;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Main {

    public static int[] findInitialPosition(ArrayList<String> map){
        int[] positions = new int[2];
        for(int i = 0; i < map.size(); ++i)
            for(int j = 0; j < map.get(i).length(); ++j)
                if(map.get(i).charAt(j) == '^'){
                    positions[0] = i;
                    positions[1] = j;
                }
        return positions;
    }

    public static int calculatePositions(ArrayList<String> map, char[][] visited){
        int[] positions = findInitialPosition(map);
        int i = positions[0], j = positions[1];
        visited[i][j] = 'X';
        int positionsVisited = 1;
        char direction = map.get(i).charAt(j);
        boolean endReached = false;
        while(!endReached){
            if(direction == '^'){ // Moving Up
                while(i-1 >= 0 && map.get(i-1).charAt(j) != '#'){
                    i--;
                    if(visited[i][j] != 'X'){
                        visited[i][j] = 'X';
                        ++positionsVisited;
                    }
                }
                if(i-1 < 0)
                    endReached = true;
                else direction = '>';
            } else if(direction == '>'){ // Moving right
                while(j+1 < map.get(i).length() && map.get(i).charAt(j+1) != '#'){
                    j++;
                    if(visited[i][j] != 'X'){
                        visited[i][j] = 'X';
                        ++positionsVisited;
                    }
                }
                if(j+1 == map.get(i).length())
                    endReached = true;
                else direction = 'v';
            } else if(direction == 'v'){ // Moving down
                while(i+1 < map.size() && map.get(i+1).charAt(j) != '#'){
                    i++;
                    if(visited[i][j] != 'X'){
                        visited[i][j] = 'X';
                        ++positionsVisited;
                    }
                }
                if(i+1 == map.size())
                    endReached = true;
                else direction = '<';
            } else{ //Moving left
                while(j-1 >= 0 && map.get(i).charAt(j-1) != '#'){
                    j--;
                    if(visited[i][j] != 'X'){
                        visited[i][j] = 'X';
                        ++positionsVisited;
                    }
                }
                if(j-1 < 0)
                    endReached = true;
                else direction = '^';
            }
        }
        return positionsVisited;
    }

    public static void main(String[] args) {
        int result = 0;
        ArrayList<String> map = new ArrayList<>();
        try{
            BufferedReader br = new BufferedReader(new FileReader("src//main//resources//input.txt"));
            BufferedWriter bw = new BufferedWriter(new FileWriter("src//main//resources//output.txt"));
            String line;
            while((line = br.readLine()) != null) {
                map.add(line);
            }

            // Create visited map
            char[][] visited = new char[map.size()][map.get(0).length()];
            for(char[] array: visited)
                Arrays.fill(array,'-');

            result = calculatePositions(map, visited);

            bw.write(Integer.toString(result));
            br.close();
            bw.close();
        } catch (Exception e) {
            System.out.println("Something went wrong...");
        }
    }
}