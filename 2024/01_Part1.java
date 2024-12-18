package AdventOfCode;
import java.io.*;
import java.util.ArrayList;

public class Main {

    private static int adventProblem01(ArrayList<Integer> left, ArrayList<Integer> right){
        int distance = 0;
        left.sort(null);
        right.sort(null);
        for(int i = 0; i < left.size(); ++i)
            distance += Math.abs(left.get(i)-right.get(i));
        return distance;
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