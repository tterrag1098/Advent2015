package tterrag.advent2015;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


public class Day8 {
    public static void main(String[] args) throws IOException { 
        List<String> raw = Files.readAllLines(Paths.get("day8.txt"));
        System.out.println(raw.stream().mapToInt(s -> s.length() -  s.substring(1, s.length() - 1).replace("\\\"", "$").replace("\\\\", "$").replaceAll("\\\\x..", "_").length()).sum());
        System.out.println(raw.stream().mapToInt(s -> "\"".concat(s.replace("\\", "\\\\").replace("\"", "\\\"")).concat("\"").length() - s.length()).sum());
    }
}
