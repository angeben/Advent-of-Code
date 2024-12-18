package AdventOfCode;
import java.io.*;
import java.util.*;

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

    // If a loop is found returns true
    public static boolean updateVisited(HashMap<String,HashSet<Character>> visited, int i, int j, char direction){
        if(!visited.containsKey(i+"-"+j)){
            HashSet<Character> set = new HashSet<>();
            set.add(direction);
            visited.put(i+"-"+j, set);
        } else if(!visited.get(i+"-"+j).contains(direction)){
            HashSet<Character> set = visited.get(i+"-"+j);
            set.add(direction);
            visited.replace(i+"-"+j,set);
        } else { // We previously visited the positions in the same direction -> loop
            return true;
        }
        return false;
    }

    public static int drawRoute(ArrayList<String> map, int i, int j, boolean isFirst, HashSet<String> regularRoute){
        char direction = map.get(i).charAt(j);
        HashMap<String,HashSet<Character>> visited = new HashMap<>();

        boolean endReached = false, loopFound = false;
        while(!endReached && !loopFound){
            if(direction == '^'){ // Moving Up
                while(i-1 >= 0 && map.get(i-1).charAt(j) != '#'){
                    i--;
                    // For the first iteration, we save the route of the guard without additional obstacles
                    if(isFirst)
                        regularRoute.add(i+"-"+j);
                    loopFound = updateVisited(visited,i,j,direction);
                }
                if(i-1 < 0)
                    endReached = true;
                else direction = '>';
            } else if(direction == '>'){ // Moving right
                while(j+1 < map.get(i).length() && map.get(i).charAt(j+1) != '#'){
                    j++;
                    // For the first iteration, we save the route of the guard without additional obstacles
                    if(isFirst)
                        regularRoute.add(i+"-"+j);
                    loopFound = updateVisited(visited,i,j,direction);
                }
                if(j+1 == map.get(i).length())
                    endReached = true;
                else direction = 'v';
            } else if(direction == 'v'){ // Moving down
                while(i+1 < map.size() && map.get(i+1).charAt(j) != '#'){
                    i++;
                    // For the first iteration, we save the route of the guard without additional obstacles
                    if(isFirst)
                        regularRoute.add(i+"-"+j);
                    loopFound = updateVisited(visited,i,j,direction);
                }
                if(i+1 == map.size())
                    endReached = true;
                else direction = '<';
            } else{ //Moving left
                while(j-1 >= 0 && map.get(i).charAt(j-1) != '#'){
                    j--;
                    // For the first iteration, we save the route of the guard without additional obstacles
                    if(isFirst)
                        regularRoute.add(i+"-"+j);
                    loopFound = updateVisited(visited,i,j,direction);
                }
                if(j-1 < 0)
                    endReached = true;
                else direction = '^';
            }
        }
        if(loopFound) return 1; // We exited the while with loopFound = true;
        else return 0; // We exited the while with endReached = true;
    }

    public static int findLoops(ArrayList<String> map){
        int loops = 0;
        int[] positions = findInitialPosition(map);
        int i = positions[0], j = positions[1];
        HashSet<String> regularRoute = new HashSet<>();
        drawRoute(map,i,j,true,regularRoute); // Updates regularRoute

        // We iterate over the possible positions to place an obstacle
        for(String s : regularRoute){
            int i_index = Integer.parseInt(s.split("-")[0]);
            int j_index = Integer.parseInt(s.split("-")[1]);
            String oldLine = map.get(i_index);
            String newLine = oldLine.substring(0,j_index)+'#'+oldLine.substring(j_index+1);
            map.set(i_index,newLine);
            loops += drawRoute(map,i,j,false,null);
            map.set(i_index,oldLine);
        }
        return loops;
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
            result = findLoops(map);

            bw.write(Integer.toString(result));
            br.close();
            bw.close();
        } catch (Exception e) {
            System.out.println("Something went wrong...");
        }
    }
}