package AdventOfCode;
import java.io.*;
import java.util.*;

class Antenna{
    public char type;
    public int i;
    public int j;

    public Antenna(char type, int i, int j) {
        this.type = type;
        this.i = i;
        this.j = j;
    }
}

public class Main {

    public static boolean inBounds(int i, int j, int maxI, int maxJ){
        return i >= 0 && i < maxI && j >= 0 && j < maxJ;
    }

    public static int findAntinodesLine(Antenna a1, Antenna a2, int maxI, int maxJ, boolean[][] marked){
        int n = 0;
        int vectorX = a2.i - a1.i;
        int vectorY = a2.j - a1.j;
        // Search in one direction
        int n_i = a2.i + vectorX, n_j = a2.j + vectorY;
        while(inBounds(n_i,n_j,maxI,maxJ)){
            if(!marked[n_i][n_j]){
                ++n;
                marked[n_i][n_j] = true;
            }
            n_i = n_i + vectorX;
            n_j = n_j + vectorY;
        }
        // Search in the other direction
        n_i = a1.i - vectorX;
        n_j = a1.j - vectorY;
        while(inBounds(n_i,n_j,maxI,maxJ)){
            if(!marked[n_i][n_j]){
                ++n;
                marked[n_i][n_j] = true;
            }
            n_i = n_i - vectorX;
            n_j = n_j - vectorY;
        }
        return n;
    }

    public static int checkAntinodes(ArrayList<Antenna> antennas, int maxI, int maxJ){
        int n = 0;
        boolean[][] marked = new boolean[maxI][maxJ];
        HashMap<Character,Integer> antennaNumber = new HashMap<>();
        // Mark positions with an antenna to avoid duplicates later
        // Count the number of each type of antenna
        for (Antenna antenna : antennas) {
            marked[antenna.i][antenna.j] = true;
            if(!antennaNumber.containsKey(antenna.type))
                antennaNumber.put(antenna.type,1);
            else antennaNumber.replace(antenna.type,antennaNumber.get(antenna.type)+1);
        }
        // If there are two or more of a type of antenna, they are antinodes
        for(Map.Entry<Character,Integer> entry: antennaNumber.entrySet())
            if(entry.getValue() > 1)
                n += entry.getValue();
        // Find positions for antinodes
        for(int i = 0; i < antennas.size(); ++i){
            for(int j = i+1; j < antennas.size(); ++j){
                if(antennas.get(i).type == antennas.get(j).type){
                    n += findAntinodesLine(antennas.get(i),antennas.get(j),maxI, maxJ, marked);
                }
            }
        }
        return n;
    }

    public static void main(String[] args) {
        long sol = 0;
        ArrayList<Antenna> antennas = new ArrayList<>();
        try{
            BufferedReader br = new BufferedReader(new FileReader("src//main//resources//input.txt"));
            BufferedWriter bw = new BufferedWriter(new FileWriter("src//main//resources//output.txt"));
            String line;
            int i = 0, lineSize = 0;
            while((line = br.readLine()) != null) {
                if(i == 0)
                    lineSize = line.length();
                for(int j = 0; j < line.length(); ++j)
                    if(Character.isLetter(line.charAt(j)) || Character.isDigit(line.charAt(j)))
                        antennas.add(new Antenna(line.charAt(j),i,j));
                ++i;
            }
            sol = checkAntinodes(antennas,i,lineSize);

            bw.write(Long.toString(sol));
            br.close();
            bw.close();
        } catch (Exception e) {
            System.out.println("Something went wrong...");
        }
    }
}