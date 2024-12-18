package AdventOfCode;
import java.io.*;
import java.util.*;

public class Main {

    public static ArrayList<String> getDiskRepresentation(String input){
        ArrayList<String> disk = new ArrayList<>();
        int id = 0;
        for(int i = 0; i < input.length(); ++i){
            if(i % 2 == 0) { // File
                for(int k = 0; k < Character.getNumericValue(input.charAt(i)); ++k)
                    disk.add(Integer.toString(id));
                ++id;
            }
            else // Free space
                for(int k = 0; k < Character.getNumericValue(input.charAt(i)); ++k)
                    disk.add(".");
        }
        return disk;
    }

    public static void rearrangeDisk(ArrayList<String> disk){
        // Move numbers to free spaces to the left
        boolean foundFreeSpace;
        for(int i = disk.size() - 1; i >= 0; --i){
            foundFreeSpace = false;
            for(int j = 0; !foundFreeSpace && j < i; ++j){
                if(disk.get(j).equals(".")){
                    disk.set(j, disk.get(i));
                    disk.set(i,".");
                    foundFreeSpace = true;
                }
            }
        }
        // Delete the free space at the end
        ArrayList<String> remove = new ArrayList<>();
        remove.add(".");
        disk.removeAll(remove);
    }

    public static long getChecksum(ArrayList<String> disk){
        long res = 0;
        for(int i = 0; i < disk.size(); ++i)
            res +=  i * Long.parseLong(disk.get(i));
        return res;
    }

    public static void main(String[] args) {
        long sol = 0;
        try{
            BufferedReader br = new BufferedReader(new FileReader("src//main//resources//input.txt"));
            BufferedWriter bw = new BufferedWriter(new FileWriter("src//main//resources//output.txt"));
            String line = br.readLine();

            ArrayList<String> disk = getDiskRepresentation(line);
            rearrangeDisk(disk);
            //System.out.println(disk);
            sol = getChecksum(disk);

            bw.write(Long.toString(sol));
            br.close();
            bw.close();
        } catch (Exception e) {
            System.out.println("Something went wrong...");
        }
    }
}