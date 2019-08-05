package test;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SmileyFaces {

    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
        BufferedReader in = new BufferedReader(reader);
        String line;
        while ((line = in.readLine()) != null){
            System.out.println(evaluateLine(simplifyInput(line)));
        }
    }

    private static String evaluateLine(String input) {
        int HCount = 0;
        int SCount = 0;
        int pairCount = 0;
        for(char c : input.toCharArray()){
            switch (c){
                case 'S':{
                    SCount++;
                    break;
                }
                case 'H':{
                    HCount++;
                    break;
                }
                case '(':{
                    pairCount++;
                    break;
                }
                case ')':{
                    if(pairCount > 0){
                        pairCount--;
                    } else if(SCount > 0) {
                        SCount--;
                    } else {
                        return "NO";
                    }
                    break;
                }
            }
        }
        if(pairCount == 0) {
            return "YES";
        } else {
            if(pairCount - HCount <= 0){
                return "YES";
            } else {
                return "NO";
            }
        }
    }

    private static String simplifyInput(String in) {
        String temp;
        temp = in.replaceAll("H", "");
        temp = temp.replaceAll("S", "");
        temp = temp.replaceAll(":\\)", "H");
        temp = temp.replaceAll(":\\(", "S");
        Pattern p = Pattern.compile("[^()HS]{1}");
        return p.splitAsStream(temp).collect(Collectors.joining());
    }



}
