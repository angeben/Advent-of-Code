package AdventOfCode;
import java.io.*;
import java.util.*;

public class Main {

    public static void trailhead(int current, int i, int j, int[][] map, HashSet<String> found){
        if(current == 9)
            found.add(i+"-"+j);
        else{
            if(i-1 >= 0 && map[i-1][j] == current+1) trailhead(current+1,i-1,j,map, found);
            if(i+1 < map.length && map[i+1][j] == current+1) trailhead(current+1,i+1,j,map,found);
            if(j-1 >= 0 && map[i][j-1] == current+1) trailhead(current+1,i,j-1,map,found);
            if(j+1 < map[i].length && map[i][j+1] == current+1) trailhead(current+1,i,j+1,map,found);
        }
    }

    public static int getTrailheadScores(int[][] map){
        int scores = 0;
        HashSet<String> set;
        for(int i = 0; i < map.length; ++i){
            for(int j = 0; j < map[i].length; ++j){
                if(map[i][j] == 0){
                    set = new HashSet<>();
                    trailhead(0,i,j,map,set);
                    scores += set.size();
                }
            }
        }
        return scores;
    }

    public static void main(String[] args) {
        long sol = 0;
        try{
            BufferedReader br = new BufferedReader(new FileReader("src//main//resources//input.txt"));
            BufferedWriter bw = new BufferedWriter(new FileWriter("src//main//resources//output.txt"));
            ArrayList<String> lines = new ArrayList<>();
            String line;
            int sizeX = 0, sizeY = 0;
            while((line = br.readLine()) != null){
                if(sizeX == 0) sizeY = line.length();
                lines.add(line);
                ++sizeX;
            }

            int[][] map = new int[sizeX][sizeY];
            for(int i = 0; i < sizeX; ++i)
                for(int j = 0; j < sizeY; ++j)
                    map[i][j] = Character.getNumericValue(lines.get(i).charAt(j));

            sol = getTrailheadScores(map);

            /* Print map
            for(int i = 0; i < sizeX; ++i){
                String s = "";
                for(int j = 0; j < sizeY; ++j)
                    s += (map[i][j]);
                System.out.println(s);
            }*/

            bw.write(Long.toString(sol));
            br.close();
            bw.close();
        } catch (Exception e) {
            System.out.println("Something went wrong...");
        }
    }
}