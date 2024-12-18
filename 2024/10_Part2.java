package AdventOfCode;
import java.io.*;
import java.util.*;

public class Main {

    public static int trailhead(int current, int i, int j, int[][] map){
        if(current == 9)
            return 1;
        else{
            int top = 0, bot = 0, left = 0, right = 0;
            if(i-1 >= 0 && map[i-1][j] == current+1) top = trailhead(current+1,i-1,j,map);
            if(i+1 < map.length && map[i+1][j] == current+1) bot = trailhead(current+1,i+1,j,map);
            if(j-1 >= 0 && map[i][j-1] == current+1) left = trailhead(current+1,i,j-1,map);
            if(j+1 < map[i].length && map[i][j+1] == current+1) right = trailhead(current+1,i,j+1,map);
            return top + bot + left + right;
        }
    }

    public static int getTrailheadScores(int[][] map){
        int scores = 0;
        for(int i = 0; i < map.length; ++i){
            for(int j = 0; j < map[i].length; ++j){
                if(map[i][j] == 0){
                    scores += trailhead(0,i,j,map);
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