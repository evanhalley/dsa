package dev.evanhalley.googleprep;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WordFilter {

    private final TrieNode prefixTrie;
    private final TrieNode suffixTrie;
    private final Map<String, Integer> dictionary;

    public static void main(String[] args) {
        String[] words = new String[] {"cabaabaaaa","ccbcababac","bacaabccba","bcbbcbacaa","abcaccbcaa","accabaccaa","cabcbbbcca","ababccabcb","caccbbcbab","bccbacbcba" };
        WordFilter wordFilter = new WordFilter(words);
        System.out.println(wordFilter.f("bac", "cba"));
    }

    public WordFilter(String[] words) {
        prefixTrie = new TrieNode(1);
        suffixTrie = new TrieNode(-1);
        dictionary = new HashMap<>(words.length);
        populate(words);
    }

    public int f(String prefix, String suffix) {
        Set<String> wordsByPrefix = new HashSet<>();
        Set<String> wordsBySuffix = new HashSet<>();

        prefixTrie.getWords(prefix, 0, new StringBuilder(), wordsByPrefix);
        suffixTrie.getWords(suffix, suffix.length() -  1, new StringBuilder(), wordsBySuffix);

        Set<String> intersection = intersection(wordsByPrefix, wordsBySuffix);
        int index = -1;

        for (String word : intersection) {
            index = Math.max(index, dictionary.get(word));
        }
        return index;
    }

    private Set<String> intersection(Set<String> set1, Set<String> set2) {
        if (set1.isEmpty() || set2.isEmpty()) return new HashSet<>(0);
        Set<String> intersection = new HashSet<>(Math.min(set1.size(), set2.size()));

        for (String word : set1) {

            if (set2.contains(word)) {
                intersection.add(word);
            }
        }
        return intersection;
    }

    private void populate(String[] words) {

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            dictionary.put(word, i);
            prefixTrie.addWord(word, 0);
            suffixTrie.addWord(word, word.length() - 1);
        }
    }


    public static class TrieNode {
        private final Map<Character, TrieNode> children;
        private final int direction;
        private boolean isWord = false;

        public TrieNode(int direction) {
            this.direction = direction;
            children = new HashMap<>(26);
        }

        public void addWord(String word, int index) {

            if ((direction == -1 && index == -1) || (direction == 1 && index == word.length())) {
                isWord = true;
            } else {
                char c = word.charAt(index);
                children.computeIfAbsent(c, t -> new TrieNode(direction))
                        .addWord(word, index + direction);
            }
        }

        public void getWords(String query, int index,
                             StringBuilder word, Set<String> results) {

            if ((direction == -1 && index == -1) || (direction == 1 && index == query.length())) {
                getWords(word, results, direction);
            } else {
                char c = query.charAt(index);

                if (children.containsKey(c)) {
                    word.append(c);
                    TrieNode node = children.get(c);
                    node.getWords(query, index + direction, word, results);
                }
            }
        }

        public void getWords(StringBuilder word, Set<String> results, int direction) {

            if (isWord) {
                if (direction == -1) {
                    results.add(new StringBuilder(word).reverse().toString());
                } else {
                    results.add(word.toString());
                }
            }

            for (Character c : children.keySet()) {
                TrieNode node = children.get(c);
                word.append(c);
                node.getWords(word, results, direction);
                word.deleteCharAt(word.length() - 1);
            }
        }
    }
}
