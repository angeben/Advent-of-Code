package AdventOfCode;
import java.io.*;
import java.util.ArrayList;

public class Main {

    private static int isXMASMatch(String diag1, String diag2){
        if((diag1.equals("MAS") || diag1.equals("SAM")) && (diag2.equals("MAS") || diag2.equals("SAM")))
            return 1;
        else return 0;
    }

    private static boolean inBounds(int i, int j, int sizeI, int sizeJ){
        return i+1 < sizeI && j+1 < sizeJ && i-1 >= 0 && j-1 >= 0;
    }

    private static int findXMAS(ArrayList<String> matrix){
        int res = 0;
        int sizeI = matrix.size();
        int sizeJ = matrix.get(0).length();
        for(int i = 0; i < sizeI; ++i){
            for(int j = 0; j < sizeJ; ++j){
                if(matrix.get(i).charAt(j) == 'A' && inBounds(i,j,sizeI,sizeJ)){
                    String diag1 = new String(""), diag2 = new String("");
                    for(int n = -1; n < 2; ++n){
                        diag1 = diag1.concat(matrix.get(i+n).substring(j+n,j+n+1));
                        diag2 = diag2.concat(matrix.get(i-n).substring(j+n,j+n+1));
                    }
                    res += isXMASMatch(diag1,diag2);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int result = 0;
        ArrayList<String> matrix = new ArrayList<String>();
        try{
            BufferedReader br = new BufferedReader(new FileReader("src//main//resources//input.txt"));
            BufferedWriter bw = new BufferedWriter(new FileWriter("src//main//resources//output.txt"));
            String line;
            while((line = br.readLine()) != null) {
                matrix.add(line);
            }
            result = findXMAS(matrix);
            bw.write(Integer.toString(result));
            br.close();
            bw.close();
        } catch (Exception e) {
            System.out.println("Something went wrong...");
        }
    }
}