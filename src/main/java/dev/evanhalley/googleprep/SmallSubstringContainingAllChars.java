package dev.evanhalley.googleprep;
import java.io.*;
import java.util.*;

class Solution {

    public String minWindow(String s, String t) {

        if (s.length() == 0 || t.length() == 0) {
            return "";
        }

        // Dictionary which keeps a count of all the unique characters in t.
        Map<Character, Integer> tCounts = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            tCounts.put(t.charAt(i), tCounts.getOrDefault(t.charAt(i), 0) + 1);
        }

        // Number of unique characters in t, which need to be present in the desired window.
        int required = tCounts.size();

        // Left and Right pointer
        int left = 0;
        int right = 0;

        // formed is used to keep track of how many unique characters in t
        // are present in the current window in its desired frequency.
        // e.g. if t is "AABC" then the window must have two A's, one B and one C.
        // Thus formed would be = 3 when all these conditions are met.
        int formed = 0;

        // Dictionary which keeps a count of all the unique characters in the current window.
        Map<Character, Integer> windowCounts = new HashMap<>();

        // ans list of the form (window length, left, right)
        int[] ans = {-1, 0, 0};

        while (right < s.length()) {
            // Add one character from the right to the window
            char c = s.charAt(right);
            windowCounts.put(c, windowCounts.getOrDefault(c, 0) + 1);

            // If the frequency of the current character added equals to the
            // desired count in t then increment the formed count by 1.
            if (tCounts.containsKey(c) && windowCounts.get(c).intValue() == tCounts.get(c).intValue()) {
                formed++;
            }

            // Try and contract the window till the point where it ceases to be 'desirable'.
            while (left <= right && formed == required) {
                c = s.charAt(left);
                // Save the smallest window until now.
                if (ans[0] == -1 || right - left + 1 < ans[0]) {
                    ans[0] = right - left + 1;
                    ans[1] = left;
                    ans[2] = right;
                }

                // The character at the position pointed by the
                // `Left` pointer is no longer a part of the window.
                windowCounts.put(c, windowCounts.get(c) - 1);
                if (tCounts.containsKey(c) && windowCounts.get(c) < tCounts.get(c)) {
                    formed--;
                }

                // Move the left pointer ahead, this would help to look for a new window.
                left++;
            }

            // Keep expanding the window once we are done contracting.
            right++;
        }

        return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
    }

    static String getShortestUniqueSubstring(char[] arr, String str) {
        Set<Character> chars = new HashSet<>();
        Map<Character, Integer> charCounts = new HashMap<>();

        for (char c : arr) {
            chars.add(c);
        }
        int left = str.length() - 1;
        int right = 0;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (chars.contains(c)) {
                charCounts.put(c, charCounts.getOrDefault(c, 0) + 1);
                left = Math.min(left, i);
                right = Math.max(right, i);
            }
        }

        if (charCounts.size() < arr.length) {
            return "";
        }

        while (left <= right) {
            char leftChar = str.charAt(left);
            char rightChar = str.charAt(right);

            if (charCounts.containsKey(leftChar) && charCounts.get(leftChar) > 1) {
                charCounts.put(leftChar, charCounts.get(leftChar) - 1);
                // go to the next character in the set
                for (left++ ; left < str.length(); left++) {
                    if (charCounts.containsKey(str.charAt(left))) break;
                }
            } else if(charCounts.containsKey(rightChar) && charCounts.get(rightChar) > 1) {
                charCounts.put(rightChar, charCounts.get(rightChar) - 1);
                // go to the next character in the set
                for (right-- ; right >= 0; right--) {
                    if (charCounts.containsKey(str.charAt(right))) break;
                }
            } else {
                return str.substring(left, right + 1);
            }
        }
        return "";
    }

    public static void main(String[] args) {
        String str = "ADOBECODEBANCDDD";
        char[] arr = new char[] { 'A', 'B', 'C' };
        System.out.println(getShortestUniqueSubstring(arr, str));
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        return kSum(nums, 0, 4, target);
    }

    private List<List<Integer>> kSum (int[] nums, int start, int k, int target) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<List<Integer>>();

        if(k == 2) { //two pointers from left and right
            int left = start, right = len - 1;

            while(left < right) {
                int sum = nums[left] + nums[right];

                if(sum == target) {
                    res.add(Arrays.asList(nums[left], nums[right]));
                    while(left < right && nums[left] == nums[left + 1]) left++;
                    while(left < right && nums[right] == nums[right - 1]) right--;
                    left++;
                    right--;
                } else if (sum < target) { //move left
                    left++;
                    } else { //move right
                        right--;
                }
            }
        } else {
            for(int i = start; i < len - (k - 1); i++) {
                if(i > start && nums[i] == nums[i - 1]) continue;
                List<List<Integer>> temp = kSum(nums, i + 1, k - 1, target - nums[i]);

                for(List<Integer> t : temp) {
                    t.add(0, nums[i]);
                }
                res.addAll(temp);
            }
        }
        return res;
    }

}