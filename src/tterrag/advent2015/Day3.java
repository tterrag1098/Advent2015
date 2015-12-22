package tterrag.advent2015;

import java.awt.Point;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;

public class Day3 {

    private static Set<Point> visited = new HashSet<>();

    private static Map<Integer, Point> charmap = new HashMap<>();

    static {
        charmap.put((int) '>', new Point(1, 0));
        charmap.put((int) '<', new Point(-1, 0));
        charmap.put((int) '^', new Point(0, 1));
        charmap.put((int) 'v', new Point(0, -1));
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        String directions = IOUtils.readLines(new FileReader("day3.txt")).get(0);
        Point p = new Point(0, 0);
        int houses = 1;
        visited.add(p);
        for (int i : directions.chars().toArray()) {
            p = new Point(p);
            Point move = charmap.get(i);
            p.translate(move.x, move.y);
            if (visited.add(p)) {
                houses++;
            }
        }
        System.out.println(houses);
    }
}
