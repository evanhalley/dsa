package dev.evanhalley.googleprep.dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BestSum {

    public static void main(String[] args) {
        Set<List<Integer>> results = new HashSet<List<Integer>>();
        List<Integer> result = bestSum(Arrays.asList(1,2,5,25), 100, new HashMap<>());
        System.out.println(result);
    }

    public static List<Integer> bestSum(List<Integer> numbers, int target, Map<Integer, List<Integer>> memo) {

        if (memo.containsKey(target)) {
            return memo.get(target);
        }
        if (target == 0) {
            return new ArrayList<>();
        } else if (target < 0) {
            return null;
        }
        List<Integer> shortest = null;

        for (Integer num : numbers) {
            int remainder = target - num;
            List<Integer> result = bestSum(numbers, remainder, memo);

            if (result != null) {
                List<Integer> combination = new ArrayList<>(result);
                combination.add(num);

                if (shortest == null || combination.size() < shortest.size()) {
                    shortest = combination;
                }
            }
        }
        memo.put(target, shortest);
        return shortest;
    }

}
