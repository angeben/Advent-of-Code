package AdventOfCode;
import java.io.*;
import java.util.*;

class Garden{
    public int x, y;

    public Garden(int x, int y){
        this.x = x;
        this.y = y;
    }
}

class Region{
    public HashSet<Garden> gardens = new HashSet<>();
    public int area = 0, perimeter = 0;
}

public class Main {

    public static void getRegion(char[][] map, boolean[][] visited, int i, int j, char c, Region region){
        if(!visited[i][j] && map[i][j] == c){
            // Add the garden to the current region
            visited[i][j] = true;
            region.gardens.add(new Garden(i,j));
            region.area++;
            // Check if adjacent gardens belong to the same region
            int surrounding = 0;
            if(i-1 >= 0){
                getRegion(map, visited,i-1,j,c,region);
                if(map[i-1][j] == c) ++surrounding;
            }
            if(i+1 < map.length){
                getRegion(map, visited,i+1, j, c, region);
                if(map[i+1][j] == c) ++surrounding;
            }
            if(j-1 >= 0){
                getRegion(map, visited, i,j-1,c,region);
                if(map[i][j-1] == c) ++surrounding;
            }
            if(j+1 < map[0].length) {
                getRegion(map, visited, i,j+1, c, region);
                if(map[i][j+1] == c) ++surrounding;
            }
            region.perimeter += 4 - surrounding;
        }
    }

    public static ArrayList<Region> generateRegions(char[][] map){
        boolean[][] visited = new boolean[map.length][map[0].length];
        ArrayList<Region> regions = new ArrayList<>();
        for(int i = 0; i < map.length; ++i){
            for(int j = 0; j < map[0].length; ++j){
                if(!visited[i][j]){
                    Region region = new Region();
                    getRegion(map, visited, i, j, map[i][j], region);
                    regions.add(region);
                }
            }
        }
        return regions;
    }

    public static void main(String[] args) {
        int sol = 0;
        try{
            BufferedReader br = new BufferedReader(new FileReader("src//main//resources//input.txt"));
            BufferedWriter bw = new BufferedWriter(new FileWriter("src//main//resources//output.txt"));
            // Read input
            ArrayList<String> lines = new ArrayList<>();
            String line;
            while((line = br.readLine()) != null)
                lines.add(line);

            // Create the char matrix
            char[][] map = new char[lines.size()][lines.get(0).length()];
            for(int i = 0; i < lines.size(); ++i)
                for(int j = 0; j < lines.get(i).length(); ++j)
                    map[i][j] = lines.get(i).charAt(j);

            // Solve problem
            ArrayList<Region> regions = generateRegions(map);
            for(Region region: regions){
                //System.out.println("New Region: Area = " + region.area + " Perimeter = " + region.perimeter);
                sol += region.area * region.perimeter;
            }

            // Write solution
            bw.write(Long.toString(sol));
            br.close();
            bw.close();
        } catch (Exception e) {
            System.out.println("Something went wrong...");
            //e.printStackTrace();
        }
    }
}