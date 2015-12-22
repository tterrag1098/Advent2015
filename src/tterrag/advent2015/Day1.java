package tterrag.advent2015;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.ArrayUtils;

public class Day1 {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        String parens = IOUtils.readLines(new FileReader("day1.txt")).stream().findFirst().get();
        System.out.println(count(parens, '(') - count(parens, ')'));
        int pos = 1;
        int floor = 0;
        for (char c : parens.toCharArray()) {
            floor += c == '(' ? 1 : -1;
            if (floor < 0) {
                System.out.println(pos);
            }
            pos++;
        }
    }

    private static long count(String s, char ch) {
        return Arrays.stream(ArrayUtils.toObject(s.toCharArray())).filter(c -> c.equals(ch)).count();
    }
}
