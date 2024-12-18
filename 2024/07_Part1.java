package AdventOfCode;
import java.io.*;
import java.util.*;

public class Main {

    public static boolean checkResult(long result, ArrayList<Long> operands, int i){
       if(i == 0){
           return result == operands.get(i);
        } else{
            if(result % operands.get(i) == 0)
                return checkResult(result/operands.get(i), operands, i-1) || checkResult(result-operands.get(i), operands, i-1);
            else return checkResult(result-operands.get(i), operands, i-1);
        }
    }

    public static void main(String[] args) {
        long sol = 0;
        try{
            BufferedReader br = new BufferedReader(new FileReader("src//main//resources//input.txt"));
            BufferedWriter bw = new BufferedWriter(new FileWriter("src//main//resources//output.txt"));
            String line;
            while((line = br.readLine()) != null) {
                String[] splitLine = line.split(": ");
                long result = Long.parseLong(splitLine[0]);
                ArrayList<Long> operands = new ArrayList<>();
                for(String s: splitLine[1].split(" "))
                    operands.add(Long.parseLong(s));
                if(checkResult(result,operands,operands.size()-1))
                    sol += result;
            }


            bw.write(Long.toString(sol));
            br.close();
            bw.close();
        } catch (Exception e) {
            System.out.println("Something went wrong...");
        }
    }
}