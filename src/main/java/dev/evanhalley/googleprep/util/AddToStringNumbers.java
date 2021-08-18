package dev.evanhalley.googleprep.util;

import java.util.ArrayDeque;
import java.util.Deque;

public class AddToStringNumbers {

    public String addStrings(String num1, String num2) {
        Deque<Integer> stack = new ArrayDeque<>(Math.max(num1.length(), num2.length()) + 1);
        addStrings(num1, num2, 0, 0, stack);

        StringBuilder sb = new StringBuilder(stack.size());

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }

    public static void addStrings(String num1, String num2, int c, int i, Deque<Integer> stack) {
        int digit1 = i < num1.length() ? num1.charAt((num1.length() - 1) - i) - '0' : 0;
        int digit2 = i < num2.length() ? num2.charAt((num2.length() - 1) - i) - '0' : 0;

        int carry = (digit1 + digit2 + c) / 10;
        int sum = (digit1 + digit2 + c) % 10;

        if (i < Math.max(num1.length(), num2.length())) {
            stack.push(sum);
            addStrings(num1, num2, carry, ++i, stack);
        } else if (sum > 0) {
            stack.push(sum);
        }
    }
}
