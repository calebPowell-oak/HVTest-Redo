package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class PhoneNumbers {

    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
        BufferedReader in = new BufferedReader(reader);
        String line;
        while ((line = in.readLine()) != null) {
            boolean done = false;
            char[] input = line.toCharArray();
            int[] lastCombo = {0,0,0,0,0,0,0};
            Set<String> finalResponse = new TreeSet<String>();
            while(true) {
                finalResponse.add(addLastComboToResults(input, lastCombo));
                if(done) break;
                done = getNextCombo(input, lastCombo);
            }
            System.out.println("Words: " + finalResponse.size());
            System.out.println(finalResponse.toString());
        }
    }

    private static boolean getNextCombo(char[] in, int[] combination){
        for(int i = 0; i < combination.length; i++){
            if(combination[i] < getMapper().get(in[i]).size() - 1){
                combination[i]++;
                break;
            } else {
                if(i == combination.length - 1) return true; //done
                combination[i] = 0;
            }
        }
        return false;
    }

    private static String addLastComboToResults(char[] in, int[] combination) {
        char[] word = new char[7];
        for(int i = 0; i < in.length; i++){
            word[i] = getMapper().get(in[i]).get(combination[i]);
        }
        return new String(word);
    }

    private static Map<Character, List<Character>> getMapper() {
        Map<Character, List<Character>> map = new HashMap<Character, List<Character>>();
        map.put('0', Arrays.asList('0'));
        map.put('1', Arrays.asList('1'));
        map.put('2', Arrays.asList('a', 'b', 'c'));
        map.put('3', Arrays.asList('d', 'e', 'f'));
        map.put('4', Arrays.asList('g', 'h', 'i'));
        map.put('5', Arrays.asList('j', 'k', 'l'));
        map.put('6', Arrays.asList('m', 'n', 'o'));
        map.put('7', Arrays.asList('p', 'q', 'r', 's'));
        map.put('8', Arrays.asList('t', 'u', 'v'));
        map.put('9', Arrays.asList('w', 'x', 'y', 'z'));
        return map;
    }
}
