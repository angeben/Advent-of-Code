package AdventOfCode;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void switchPositions(ArrayList<Integer> v, int i, int j){
        Integer tmp = v.get(j);
        for(int k = j; k < i; ++k)
            v.set(k,v.get(k+1));
        v.set(i,tmp);
    }

    // boolean isCorrect will be used to see is the update was initially correctly ordered (if it is true)
    // or if it is correct after rearranging some numbers (if it is false)
    public static int checkUpdate(HashMap<Integer,ArrayList<Integer>> map, ArrayList<Integer> update, boolean isCorrect){
        for(int i = update.size()-1; i > 0; --i){
            for(int j = i - 1; j >= 0; --j){
                if(!map.containsKey(update.get(i))){
                    if(i == update.size()-1) continue;
                    else{
                        switchPositions(update, update.size()-1, i);
                        System.out.println("holaaa");
                        return checkUpdate(map,update, false);
                    }
                }
                if(map.get(update.get(i)).contains(update.get(j))){
                    switchPositions(update, i, j);
                    return checkUpdate(map,update, false);
                }
            }
        }
        if(isCorrect) return 0;
        else return update.get((update.size()-1)/2);
    }

    public static void main(String[] args) {
        int result = 0;
        boolean blankLine = false;
        HashMap<Integer,ArrayList<Integer>> map = new HashMap<>();
        ArrayList<ArrayList<Integer>> updates = new ArrayList<>();
        try{
            BufferedReader br = new BufferedReader(new FileReader("src//main//resources//input.txt"));
            BufferedWriter bw = new BufferedWriter(new FileWriter("src//main//resources//output.txt"));
            String line;
            while((line = br.readLine()) != null) {
                if(line.isEmpty()){ // Detecting blank line
                    blankLine = true;
                    continue;
                }
                if(!blankLine){ // Reading ordering rules
                    String[] splitLine = line.split("\\|");
                    if(!map.containsKey(Integer.parseInt(splitLine[0]))) {
                        Integer key = Integer.parseInt(splitLine[0]);
                        ArrayList<Integer> value = new ArrayList<>();
                        value.add(Integer.parseInt(splitLine[1]));
                        map.put(key, value);
                    } else{
                        Integer key = Integer.parseInt(splitLine[0]);
                        ArrayList<Integer> value = map.get(key);
                        value.add(Integer.parseInt(splitLine[1]));
                        map.replace(key,value);
                    }
                }
                else{ // Reading updates
                    String[] splitUpdate = line.split(",");
                    ArrayList<Integer> update = new ArrayList<>();
                    for(String s: splitUpdate)
                        update.add(Integer.parseInt(s));
                    updates.add(update);
                }
            }

            for(ArrayList<Integer> update: updates)
                result += checkUpdate(map, update, true);

            bw.write(Integer.toString(result));
            br.close();
            bw.close();
        } catch (Exception e) {
            System.out.println("Something went wrong...");
        }
    }
}