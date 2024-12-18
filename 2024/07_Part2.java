package AdventOfCode;
import java.io.*;
import java.util.*;

public class Main {

    public static ArrayList<Long> generateNewArray(ArrayList<Long> operands, String op){
        ArrayList<Long> newOperands = new ArrayList<>();
        switch (op) {
            case "sum" -> newOperands.add(operands.get(0) + operands.get(1));
            case "mul" -> newOperands.add(operands.get(0) * operands.get(1));
            case "con" -> newOperands.add(Long.parseLong(operands.get(0).toString() + operands.get(1).toString()));
        }
        for(int i = 2; i < operands.size(); ++i)
            newOperands.add(operands.get(i));
        return newOperands;
    }

    public static boolean checkResult(long result, ArrayList<Long> operands){
       if(operands.size() == 1)
           return result == operands.get(0);
       else return checkResult(result,generateNewArray(operands, "sum"))
               || checkResult(result,generateNewArray(operands, "mul"))
               || checkResult(result,generateNewArray(operands, "con")) ;
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
                if(checkResult(result,operands))
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