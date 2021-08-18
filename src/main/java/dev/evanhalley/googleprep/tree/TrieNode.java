package dev.evanhalley.googleprep.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TrieNode {


    private final Map<Character, TrieNode> children;

    private boolean isTerminal;

    public static void main(String[] args) {
        TrieNode node = new TrieNode();
        node.addWord("evan");
        node.addWord("evanescent");
        node.addWord("evil");
        node.addWord("evoon");
        node.addWord("halley");
        node.addWord("eva");
        node.getWordsByPrefix("ae");
        System.out.println(node.containsWord("evan"));
        System.out.println(node.containsWord("eva"));
        System.out.println(node.containsPrefix("eva"));
        System.out.println(node.containsPrefix("eval"));
        System.out.println(node.containsWord("eva"));
    }

    public TrieNode() {
        children = new HashMap<>();
    }

    public boolean containsPrefix(String word) {
        return contains(word, 0, true);
    }

    public boolean containsWord(String word) {
        return contains(word, 0, false);
    }

    private boolean contains(String word, int index, boolean prefixSearch) {

        if (index == word.length()) {
            return isTerminal || prefixSearch;
        }
        char c = word.charAt(index);

        if (children.containsKey(c)) {
            return children.get(c).contains(word, index + 1, prefixSearch);
        }
        return false;
    }

    public List<String> getWordsByPrefix(String prefix) {
        TrieNode node = getNodeByPrefix(prefix, 0);
        List<String> words = new ArrayList<>();

        if (node != null) {
            getWords(prefix, node, words);
        }
        return words;
    }

    public void getWords(String prefix, TrieNode node, List<String> words) {

        if (node.children.size() == 0 || node.isTerminal) {
            words.add(prefix);
        }

        for (Map.Entry<Character, TrieNode> child : node.children.entrySet()) {
            child.getValue().getWords(prefix + child.getKey(), child.getValue(), words);
        }
    }

    public TrieNode getNodeByPrefix(String prefix, int index) {
        if (index == prefix.length()) {
            return this;
        }
        char c = prefix.charAt(index);

        if (children.containsKey(c)) {
            return children.get(c).getNodeByPrefix(prefix, index + 1);
        }
        return null;
    }

    public void addWord(String word) {
        addWord(word, 0);
    }

    private void addWord(String word, int index) {

        if (index >= word.length()) {
            isTerminal = true;
        } else {

            char c = word.charAt(index);
            TrieNode node;

            if (children.containsKey(c)) {
                node = children.get(c);
            } else {
                node = new TrieNode();
                children.put(c, node);
            }
            node.addWord(word, index + 1);
        }
    }
}
