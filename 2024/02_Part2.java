package AdventOfCode;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

    private static boolean reportIsSafe(ArrayList<Integer> report){
        boolean check = true;
        if(report.get(0) < report.get(1))
            for(int i = 0; i < report.size() - 1; ++i)
                check = check && ((report.get(i+1) - report.get(i)) >= 1) && ((report.get(i+1) - report.get(i)) <= 3);
        else for(int i = 0; i < report.size() - 1; ++i)
            check = check && ((report.get(i) - report.get(i+1)) >= 1) && ((report.get(i) - report.get(i+1)) <= 3);;

        return check;
    }

    private static ArrayList<Integer> createArraywithSkip(ArrayList<Integer> report, int i){
        ArrayList<Integer> copy = (ArrayList<Integer>)report.clone();
        copy.remove(i);
        return copy;
    }

    private static boolean reportIsSafeWithSkip(ArrayList<Integer> report){
        boolean check = false;
        for(int i = 0; !check && i < report.size();++i){
            ArrayList<Integer> arrayCopy = createArraywithSkip(report, i);
            check = reportIsSafe(arrayCopy);
        }
        return check;
    }

    public static void main(String[] args) {
        ArrayList<Integer> report = new ArrayList<>();
        int safe = 0;
        try{
            BufferedReader br = new BufferedReader(new FileReader("src//main//resources//input.txt"));
            BufferedWriter bw = new BufferedWriter(new FileWriter("src//main//resources//output.txt"));
            String line;
            while((line = br.readLine()) != null) {
                String[] splitString = line.split(" ");
                for(String s: splitString)
                    report.add(Integer.parseInt(s));
                if(reportIsSafe(report)){
                    ++safe;
                }else if(reportIsSafeWithSkip(report))
                    ++safe;

                report.clear();
            }

            bw.write(Integer.toString(safe));
            br.close();
            bw.close();
        } catch (Exception e) {
            System.out.println("Something went wrong...");
        }
    }
}