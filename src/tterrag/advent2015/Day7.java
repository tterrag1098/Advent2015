package tterrag.advent2015;

import gnu.trove.map.TObjectIntMap;
import gnu.trove.map.hash.TObjectIntHashMap;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.IntBinaryOperator;
import java.util.function.IntSupplier;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.experimental.NonFinal;

import org.apache.commons.lang.math.NumberUtils;

public class Day7 {

    @RequiredArgsConstructor
    private enum Operator implements IntBinaryOperator {
        AND((a, b) -> a & b),
        OR((a, b) -> a | b),
        NOT((a, b) -> ~a),
        LSHIFT((a, b) -> a << b),
        RSHIFT((a, b) -> a >>> b),
        CONSTANT((a, b) -> a);

        private final IntBinaryOperator op;
        
        @Override
        public int applyAsInt(int left, int right) {
            return op.applyAsInt(left, right) & 0xFFFF;
        }
    }

    private static TObjectIntMap<String> values = new TObjectIntHashMap<>();
    private static Map<String, Instruction> instructions = new HashMap<>();

    @Value
    @RequiredArgsConstructor
    @NonFinal
    private static class Instruction implements IntSupplier {

        Operator op;
        String inputA, inputB, output;

        @Override
        public int getAsInt() {
            Instruction a = NumberUtils.isNumber(inputA) ? constant(Integer.parseInt(inputA), output) : instructions.get(inputA);
            Instruction b = NumberUtils.isNumber(inputB) ? constant(Integer.parseInt(inputB), output) : instructions.get(inputB);
            if (values.containsKey(output)) {
                return values.get(output);
            }
            int res = op.applyAsInt(a.getAsInt(), b == null ? 0 : b.getAsInt());
            values.put(output, res);
            return res;
        }

        public static Instruction fromString(String s) {
            String[] input = s.split(" ");
            Operator op;
            String inputA = "", inputB = "", output;
            try {
                op = Operator.valueOf(input[0]);
                inputA = input[1];
                output = input[3];
            } catch (Exception e) {
                try {
                    inputA = input[0];
                    op = Operator.valueOf(input[1]);
                    inputB = input[2];
                    output = input[4];
                } catch (Exception e2) {
                    try {
                        int i = Integer.valueOf(input[0]);
                        output = input[2];
                        return constant(i, output);
                    } catch (Exception e3) {
                        return new Instruction(Operator.CONSTANT, input[0], "", input[2]);
                    }
                }
            }
            return new Instruction(op, inputA, inputB, output);
        }

        public static Instruction constant(int ret, String output) {
            return new Instruction(Operator.CONSTANT, "" + ret, "", output){
                @Override
                public int getAsInt() {
                    // This is the recursion base case
                    if (values.containsKey(getOutput())) {
                        return values.get(getOutput());
                    }
                    values.put(getOutput(), Integer.parseInt(getInputA()));
                    return values.get(getOutput());
                }
            };
        }
    }

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("day7.txt"));
        lines.forEach(s -> {
            Instruction i = Instruction.fromString(s);
            instructions.put(i.getOutput(), i);
        });
        
        int output = instructions.get("a").getAsInt();
        System.out.println("a1: " + output); 
        values.clear();
        instructions.put("b", Instruction.fromString(output + " -> b"));
        System.out.println("a2: " + instructions.get("a").getAsInt());
    }

}
