package dev.evanhalley.googleprep;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ModifiedEditDistance {

    public static class Result {
        List<String> operations;
        int distance;

        public Result(List<String> operations, int distance) {
            this.operations = operations;
            this.distance = distance;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "operations=" + operations +
                    ", distance=" + distance +
                    '}';
        }
    }

    public static void main(String[] args) {
        System.out.println(diff("evan", "evanhh"));
    }

    public static Result diff(String source, String target) {
        Result res = diff(source, 0, target, 0);
        Collections.reverse(res.operations);
        return res;
    }

    public static Result diff(String source, int m, String target, int n) {

        if (source.length() == m) {
            List<String> ops = new ArrayList<>();
            for (int i = n; i < target.length(); i++) {
                ops.add(String.format("+%s", target.charAt(i)));
            }
            return new Result(ops, ops.size());
        }

        if (target.length() == n) {
            List<String> ops = new ArrayList<>();
            for (int i = m; i < source.length(); i++) {
                ops.add(String.format("-%s", source.charAt(i)));
            }
            return new Result(ops, ops.size());
        }

        if (source.charAt(m) == target.charAt(n)) {
            Result result = diff(source, m + 1, target, n + 1);
            List<String> ops = result.operations;
            ops.add(String.valueOf(source.charAt(m)));
            return result;
        }

        Result res1 = diff(source, m + 1, target, n);
        Result res2 = diff(source, m, target, n + 1);

        if (res1.distance <= res2.distance) {
            res1.operations.add("-" + source.charAt(m));
            res1.distance++;
            return res1;
        } else {
            res2.operations.add("+" + target.charAt(n));
            res2.distance++;
            return res2;
        }
    }


}
