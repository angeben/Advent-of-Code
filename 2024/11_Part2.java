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

    public static long getNumberOfStones(HashMap<Long,Long> stones){
        long n = 0;
        for(Map.Entry<Long,Long> e: stones.entrySet())
            n += e.getValue();
        return n;
    }

    public static long blink(HashMap<Long,Long> stones, int times){
        //System.out.println(6-times + " " + stones.size());
        //System.out.println(stones);
        if(times == 0)
            return getNumberOfStones(stones);
        else{
            HashMap<Long,Long> stones2 = new HashMap<>();
            int size;
            for(Map.Entry<Long,Long> e: stones.entrySet()){
                if(e.getKey() == 0){
                    if(stones2.containsKey(1L))
                        stones2.replace(1L, stones2.get(1L) + e.getValue());
                    else stones2.put(1L,e.getValue());
                }
                else if((size = numberLength(e.getKey())) % 2 == 0){
                    long number1 = e.getKey() / (long) Math.pow(10,size/2);
                    if(stones2.containsKey(number1))
                        stones2.replace(number1, stones2.get(number1) + e.getValue());
                    else stones2.put(number1, e.getValue());
                    long number2 = e.getKey() % (long) Math.pow(10,size/2);
                    if(stones2.containsKey(number2))
                        stones2.replace(number2, stones2.get(number2) + e.getValue());
                    else stones2.put(number2, e.getValue());
                } else {
                    if(stones2.containsKey(e.getKey() * 2024))
                        stones2.replace(e.getKey() * 2024, stones2.get(e.getKey() * 2024) + e.getValue());
                    else stones2.put(e.getKey() * 2024, e.getValue());
                }
            }
            return blink(stones2, times-1);
        }
    }

    public static void main(String[] args) {
        long sol = 0;
        try{
            BufferedReader br = new BufferedReader(new FileReader("src//main//resources//input.txt"));
            BufferedWriter bw = new BufferedWriter(new FileWriter("src//main//resources//output.txt"));
            HashMap<Long,Long> stones = new HashMap<>();
            String line = br.readLine();
            long i = 0L;
            for(String s : line.split(" ")){
                if(!stones.containsKey(Long.parseLong(s)))
                    stones.put(Long.parseLong(s), 1L);
                else stones.replace(Long.parseLong(s), stones.get(Long.parseLong(s))+1);
            }

            sol = blink(stones, 75);

            bw.write(Long.toString(sol));
            br.close();
            bw.close();
        } catch (Exception e) {
            System.out.println("Something went wrong...");
            e.printStackTrace();
        }
    }
}