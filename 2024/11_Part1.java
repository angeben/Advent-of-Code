package AdventOfCode;
import java.io.*;
import java.util.*;

public class Main {

    public static int numberLength(long number){
        int size = 0;
        while(number > 0){
            number /= 10;
            ++size;
        }
        return size;
    }

    public static int blink(ArrayList<Long> stones, int times){
        if(times == 0)
            return stones.size();
        else{
            ArrayList<Long> newStones = new ArrayList<>();
            int aux;
            for(Long number : stones){
                if(number == 0)
                    newStones.add(1L);
                else if((aux = numberLength(number)) % 2 == 0){
                    newStones.add(number / (long) Math.pow(10,aux/2));
                    newStones.add(number % (long) Math.pow(10,aux/2));
                } else newStones.add(number * 2024);
            }
            return blink(newStones, times-1);
        }
    }

    public static void main(String[] args) {
        long sol = 0;
        try{
            BufferedReader br = new BufferedReader(new FileReader("src//main//resources//input.txt"));
            BufferedWriter bw = new BufferedWriter(new FileWriter("src//main//resources//output.txt"));
            ArrayList<Long> stones = new ArrayList<>();
            String line = br.readLine();
            for(String s : line.split(" "))
                stones.add(Long.parseLong(s));

            sol = blink(stones, 25);

            bw.write(Long.toString(sol));
            br.close();
            bw.close();
        } catch (Exception e) {
            System.out.println("Something went wrong...");
        }
    }
}