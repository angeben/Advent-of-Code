package AdventOfCode;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

    private static boolean doOrNot = true;

    private static boolean isValidNumber(String s){
        if(s.length() > 3)
            return false;
        else {
            for(int i = 0; i < s.length(); ++i)
                if(!Character.isDigit(s.charAt(i)))
                    return false;
        }
        return true;
    }

    private static int correctMul(String line, int i){
        int startPar = line.indexOf('(');
        int endPar = line.indexOf(')');
        String[] expression = line.substring(startPar+1,endPar).split(",");
        if(expression.length == 2 && isValidNumber(expression[0]) && isValidNumber(expression[1])){
            System.out.println("Char " + i + ": Mulyplying " + expression[0] + " by " + expression[1]);
            return Integer.parseInt(expression[0]) * Integer.parseInt(expression[1]);
        }
        else return 0;
    }

    private static int multiplyInLine(String line){
        int result = 0, i = 0;
        // length - 7 for line ending in mul(1,2), don't need to analyze any further
        while(i < line.length() - 7){
            if(line.substring(i,i+4).equals("do()")){
                doOrNot = true;
                i = i+4; //skip the characters in do()
            }
            else if(line.substring(i,i+7).equals("don't()")){
                doOrNot = false;
                i = i+7; //skip the characters in don't()
            }
            else if(doOrNot && line.substring(i,i+4).equals("mul(")){
                int tmp = correctMul(line.substring(i), i);
                if(tmp != 0){
                    result += tmp;
                    i = i+8; //skip the characters in the expression (min 8)
                }
                else ++i;
            } else ++i;
        }
        return result;
    }

    public static void main(String[] args) {
        int result = 0;
        try{
            BufferedReader br = new BufferedReader(new FileReader("src//main//resources//input.txt"));
            BufferedWriter bw = new BufferedWriter(new FileWriter("src//main//resources//output.txt"));
            String line;
            while((line = br.readLine()) != null) {
                result += multiplyInLine(line);
            }
            bw.write(Integer.toString(result));
            br.close();
            bw.close();
        } catch (Exception e) {
            System.out.println("Something went wrong...");
        }
    }
}