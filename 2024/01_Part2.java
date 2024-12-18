package AdventOfCode;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

    private static int adventProblem01(ArrayList<Integer> left, ArrayList<Integer> right){
        int similarity = 0;
        Map<Integer, Integer> appearances = new HashMap<Integer, Integer>();

        for(Integer i: right)
            if(!appearances.containsKey(i))
                appearances.put(i, 1);
            else appearances.replace(i,appearances.get(i) + 1);

        for(Integer i: left)
            if(appearances.containsKey(i))
                similarity += i * appearances.get(i);

        return similarity;
    }

    public static void main(String[] args) {
        ArrayList<Integer> leftList = new ArrayList<>();
        ArrayList<Integer> rightList = new ArrayList<>();
        try{
            BufferedReader br = new BufferedReader(new FileReader("src//main//resources//input.txt"));
            BufferedWriter bw = new BufferedWriter(new FileWriter("src//main//resources//output.txt"));
            String line;
            while((line = br.readLine()) != null) {
                String[] splitString = line.split("   ");
                leftList.add(Integer.parseInt(splitString[0]));
                rightList.add(Integer.parseInt(splitString[1]));
            }
            int sol = adventProblem01(leftList, rightList);

            bw.write(Integer.toString(sol));
            br.close();
            bw.close();
        } catch (Exception e) {
            System.out.println("Something went wrong...");
        }
    }
}