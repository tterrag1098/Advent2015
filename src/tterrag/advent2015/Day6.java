package tterrag.advent2015;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.IntUnaryOperator;

import lombok.AllArgsConstructor;

import com.google.common.collect.ObjectArrays;

public class Day6 {

    private static int[][] grid1 = new int[1000][1000];
    private static int[][] grid2 = new int[1000][1000];

    @AllArgsConstructor
    private enum Op {
        ON(i -> 1, i -> i + 1),
        TOGGLE(i-> ~i & 1, i -> i + 2),
        OFF(i -> 0, i -> Math.max(0, i - 1));
        
        final IntUnaryOperator op1, op2;
        
        public static Op fromStrings(String s1, String s2) {
            return s1.equals("toggle") ? TOGGLE : s2.equals("on") ? ON : OFF;
        }
    }
    
    @AllArgsConstructor
    private static class Instruction {
        Op op;
        int minx, miny, maxx, maxy;

        public void run() {
            run(grid1, op.op1);
            run(grid2, op.op2);
        }
        
        private void run(int[][] arr, IntUnaryOperator func) {
            for (int x = minx; x <= maxx; x++) {
                for (int y = miny; y <= maxy; y++) {
                    arr[x][y] = func.applyAsInt(arr[x][y]);
                }
            }
        }
        
        public static Instruction fromString(String s) {
            String[] data = s.split(" ");
            Op op = Op.fromStrings(data[0], data[1]);
            String s1 = op == Op.TOGGLE ? data[1] : data[2];
            String s2 = op == Op.TOGGLE ? data[3] : data[4];
            int[] nums = Arrays.stream(ObjectArrays.concat(s1.split(","), s2.split(","), String.class)).mapToInt(Integer::valueOf).toArray();
            return new Instruction(op, nums[0], nums[1], nums[2], nums[3]);
        }
    }

    public static void main(String[] args) throws IOException {
        Files.readAllLines(Paths.get("day6.txt")).stream().forEach(s -> Instruction.fromString(s).run());
        System.out.println(Arrays.stream(grid1).mapToInt(arr -> Arrays.stream(arr).sum()).sum());
        System.out.println(Arrays.stream(grid2).mapToInt(arr -> Arrays.stream(arr).sum()).sum());
    }
}
