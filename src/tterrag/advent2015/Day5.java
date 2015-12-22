package tterrag.advent2015;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

public class Day5 {

    private static String[] vowels = new String[] { "a", "e", "i", "o", "u" };

    public static void main(String[] args) throws FileNotFoundException, IOException {
        List<String> lines = IOUtils.readLines(new FileReader("day5.txt"));
        System.out.println(lines.stream().filter(s -> Arrays.stream(vowels).mapToInt(v -> StringUtils.countMatches(s, v)).sum() >= 3).filter(Day5::containsDouble).filter(s -> !(s.contains("ab") || s.contains("cd") || s.contains("pq") || s.contains("xy"))).count());
        System.out.println(lines.stream().filter(Day5::containsDoublePair).filter(Day5::containsDoubleSplit).count());
    }

    private static boolean containsDouble(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length - 1; i++) {
            if (chars[i] == chars[i + 1]) {
                return true;
            }
        }
        return false;
    }

    private static boolean containsDoublePair(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length - 1; i++) {
            char[] match = new char[] { chars[i], chars[i + 1] };
            for (int j = 0; j < chars.length - 1; j++) {
                if ((j < i - 1 || j > i + 1) && chars[j] == match[0] && chars[j + 1] == match[1]) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean containsDoubleSplit(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length - 2; i++) {
            if (chars[i] == chars[i + 2]) {
                return true;
            }
        }
        return false;
    }
}
