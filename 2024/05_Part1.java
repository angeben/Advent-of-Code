package AdventOfCode;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static int checkUpdate(HashMap<Integer,ArrayList<Integer>> map, ArrayList<Integer> update){
        boolean correct = true;
        for(int i = update.size()-1; correct && i > 0; --i){
            for(int j = i - 1; j >= 0; --j){
                if(!map.containsKey(update.get(i)))
                    continue;
                if(map.get(update.get(i)).contains(update.get(j))){
                    correct = false;
                    break;
                }
            }
        }
        if(correct) return update.get((update.size()-1)/2);
        else return 0;
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
                result += checkUpdate(map, update);

            bw.write(Integer.toString(result));
            br.close();
            bw.close();
        } catch (Exception e) {
            System.out.println("Something went wrong...");
        }
    }
}