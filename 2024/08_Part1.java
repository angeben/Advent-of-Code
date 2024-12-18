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

    public static int checkAntinodes(ArrayList<Antenna> antennas, int maxI, int maxJ){
        int n = 0;
        boolean[][] marked = new boolean[maxI][maxJ];
        for(int i = 0; i < antennas.size(); ++i){
            for(int j = i+1; j < antennas.size(); ++j){
                if(antennas.get(i).type == antennas.get(j).type){
                    // First possible antinode
                    int i1 = 2*antennas.get(i).i - antennas.get(j).i;
                    int j1 = 2*antennas.get(i).j - antennas.get(j).j;
                    if(inBounds(i1,j1,maxI,maxJ) && !marked[i1][j1]){
                        ++n;
                        marked[i1][j1] = true;
                    }
                    // Second possible antinode
                    int i2 = 2*antennas.get(j).i - antennas.get(i).i;
                    int j2 = 2*antennas.get(j).j - antennas.get(i).j;
                    if(inBounds(i2,j2,maxI,maxJ) && !marked[i2][j2]){
                        ++n;
                        marked[i2][j2] = true;
                    }
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