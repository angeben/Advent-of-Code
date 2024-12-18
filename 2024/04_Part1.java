package AdventOfCode;
import java.io.*;
import java.util.ArrayList;

public class Main {

    private static int isXMASMatch(String word, int i, int j){
        if(word.equals("XMAS") || word.equals("SAMX")){
            //System.out.println("Word found at "+ i + " " + j);
            return 1;
        }
        else return 0;
    }

    private static int findXMAS(ArrayList<String> matrix){
        int res = 0;
        for(int i = 0; i < matrix.size(); ++i){
            for(int j = 0; j < matrix.get(i).length(); ++j){
                if(j+3 < matrix.get(i).length()){
                    String word = matrix.get(i).substring(j,j+4);
                    res += isXMASMatch(word,i,j);
                }
                if(i+3 < matrix.size()){
                    String word = new String();
                    for(int k = 0; k < 4; ++k)
                        word = word.concat(matrix.get(i+k).substring(j,j+1));
                    res += isXMASMatch(word,i,j);
                }
                if(j+3 < matrix.get(i).length() && i+3 < matrix.size()){
                    String word = new String();
                    for(int k = 0; k < 4; ++k)
                        word = word.concat(matrix.get(i+k).substring(j+k,j+k+1));
                    res += isXMASMatch(word,i,j);
                }
                if(j-3 >= 0 && i+3 < matrix.size()){
                    String word = new String();
                    for(int k = 0; k < 4; ++k)
                        word = word.concat(matrix.get(i+k).substring(j-k,j+1-k));
                    res += isXMASMatch(word,i,j);
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