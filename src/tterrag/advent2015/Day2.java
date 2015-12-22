package tterrag.advent2015;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;

public class Day2 {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        List<String> presents = IOUtils.readLines(new FileReader("day2.txt"));
        System.out.println(presents.stream().mapToInt(Day2::getPaper).sum());
        System.out.println(presents.stream().mapToInt(Day2::getRibbon).sum());
    }

    private static int getPaper(String s) {
        String[] data = s.split("x");
        List<Integer> dims = Arrays.stream(data).map(Integer::valueOf).collect(Collectors.toList());
        List<Integer> areas = Arrays.asList(2 * dims.get(0) * dims.get(1), 2 * dims.get(1) * dims.get(2), 2 * dims.get(2) * dims.get(0));
        IntSummaryStatistics stats = areas.stream().mapToInt(Integer::valueOf).summaryStatistics();
        return (int) (stats.getSum() + (stats.getMin() / 2));
    }
    
    private static int getRibbon(String s) {
        String[] data = s.split("x");
        List<Integer> dims = Arrays.stream(data).map(Integer::valueOf).collect(Collectors.toList());
        return dims.stream().sorted().limit(2).mapToInt(i -> i + i).sum() + dims.stream().mapToInt(Integer::intValue).reduce((i1, i2) -> i1 * i2).getAsInt();        
    }
}
