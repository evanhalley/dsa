package dev.evanhalley.googleprep.dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HowSum {

    public static void main(String[] args) {
        Set<List<Integer>> results = new HashSet<List<Integer>>();
        List<Integer> result = howSum(Arrays.asList(1,2,5), 100);
        System.out.println(result);
    }

    public static List<Integer> howSum(List<Integer> numbers, int target) {

        if (target == 0) {
            return Collections.EMPTY_LIST;
        } else if (target < 0) {
            return null;
        }

        for (Integer num : numbers) {
            int val = target - num;
            List<Integer> result = howSum(numbers, val);

            if (result != null) {
                List<Integer> combination = new ArrayList<>(result);
                combination.add(num);
                return combination;
            }
        }
        return null;
    }

}
