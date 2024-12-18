package AdventOfCode;
import java.io.*;
import java.util.*;

public class Main {

    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static LinkedList<String> getDiskRepresentation(String input, StringBuilder aux){
        LinkedList<String> disk = new LinkedList<>();
        int id = 0;
        for(int i = 0; i < input.length(); ++i){
            if(i % 2 == 0) { // File
                for(int k = 0; k < Character.getNumericValue(input.charAt(i)); ++k){
                    disk.add(Integer.toString(id));
                    aux.append("X");
                }
                ++id;
            }
            else // Free space
                for(int k = 0; k < Character.getNumericValue(input.charAt(i)); ++k){
                    disk.add(".");
                    aux.append(".");
                }
        }
        return disk;
    }

    public static void rearrangeDisk(LinkedList<String> disk, StringBuilder aux){
        int i = disk.size() - 1;
        while(i > 0){
            if(isNumeric(disk.get(i))){ // We found a file
                int j = i;
                while(j-1 >= 0 && disk.get(j-1).equals(disk.get(i)))
                    --j;
                int fileSize = i - j + 1;
                boolean foundSpace = false;
                int pos;
                for(int k = fileSize; !foundSpace && k < 10; ++k){ // Free spaces can have size from 1 to 9
                    if((pos = aux.indexOf(".".repeat(k))) != -1 && pos < i){ // We found a big enough free space
                        String tmp = disk.get(i);
                        // Update the aux string and list with th change
                        for(int n = pos; n <= pos+fileSize-1; ++n){
                            aux.replace(n,n+1,"X");
                            disk.set(n,tmp);
                        }
                        for(int n = j; n <= i; ++n){
                            aux.replace(n,n+1,".");
                            disk.set(n,".");
                        }
                        foundSpace = true;
                    }
                }
                if(!foundSpace)
                    i = j - 1;
            }
            else --i; // Empty space
        }
    }

    public static long getChecksum(LinkedList<String> disk){
        long res = 0;
        for(int i = 0; i < disk.size(); ++i)
            if(!disk.get(i).equals(".")) res +=  i * Long.parseLong(disk.get(i));
        return res;
    }

    public static void main(String[] args) {
        long sol = 0;
        try{
            BufferedReader br = new BufferedReader(new FileReader("src//main//resources//input.txt"));
            BufferedWriter bw = new BufferedWriter(new FileWriter("src//main//resources//output.txt"));
            String line = br.readLine();

            StringBuilder aux = new StringBuilder();
            LinkedList<String> disk = getDiskRepresentation(line, aux);
            rearrangeDisk(disk, aux);
            sol = getChecksum(disk);

            bw.write(Long.toString(sol));
            br.close();
            bw.close();
        } catch (Exception e) {
            System.out.println("Something went wrong...");
        }
    }
}