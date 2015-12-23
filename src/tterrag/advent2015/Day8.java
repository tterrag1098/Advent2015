package tterrag.advent2015;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class Day8 {

    private static Pattern hexPattern = Pattern.compile("\\\\x([0-9a-f]{2})");
    
    public static void main(String[] args) throws IOException { 
        List<String> raw = Files.readAllLines(Paths.get("day8.txt"));
        List<String> parsed = raw.stream().map(s -> s.substring(1, s.length() - 1).replace("\\\"", "$").replace("\\\\", "$")).collect(Collectors.toList());
        parsed = parsed.stream().map(s -> {
          Matcher m = hexPattern.matcher(s);
          while (m.find()) {
              String found = m.group();
              char c = (char) Integer.parseInt(m.group(1), 16);
              s = s.replace(found, "" + c);
          }
          return s;
        }).collect(Collectors.toList());
        System.out.println(raw.stream().mapToInt(String::length).sum() - parsed.stream().mapToInt(String::length).sum());
    }
}
