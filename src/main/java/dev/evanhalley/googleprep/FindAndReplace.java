package dev.evanhalley.googleprep;

import java.util.Arrays;
import java.util.Comparator;

public class FindAndReplace {

    public static void main(String[] args) {

        /*
"mhnbzxkwzxtaanmhtoirxheyanoplbvjrovzudznmetkkxrdmr"
[46,29,2,44,31,26,42,9,38,23,36,12,16,7,33,18]
["rym","kv","nbzxu","vx","js","tp","tc","jta","zqm","ya","uz","avm","tz","wn","yv","ird"]
["gfhc","uq","dntkw","wql","s","dmp","jqi","fp","hs","aqz","ix","jag","n","l","y","zww"]
         */

        new Solution().findReplaceString("mhnbzxkwzxtaanmhtoirxheyanoplbvjrovzudznmetkkxrdmr",
                new int[] {46,29,2,44,31,26,42,9,38,23,36,12,16,7,33,18},
                new String[]{ "rym","kv","nbzxu","vx","js","tp","tc","jta","zqm","ya","uz","avm","tz","wn","yv","ird" },
                new String[]{ "gfhc","uq","dntkw","wql","s","dmp","jqi","fp","hs","aqz","ix","jag","n","l","y","zww" });
    }

    static class Solution {

        public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
            Replacement[] replacements = new Replacement[indices.length];

            if (indices.length == 0) {
                return s;
            } else {
                for (int i = 0; i < indices.length; i++) {
                    replacements[i] = new Replacement(sources[i], targets[i], indices[i]);
                }
            }
            Arrays.sort(replacements, Comparator.comparingInt(r -> r.index));

            int replacementIdx = 0;
            StringBuilder result = new StringBuilder();

            for (int i = 0; i < s.length(); i++) {

                if (i > replacements[replacementIdx].index && replacementIdx + 1 < replacements.length) {
                   replacementIdx++;
                }

                char c = s.charAt(i);

                if (replacementIdx < sources.length && c == replacements[replacementIdx].source.charAt(0)) {
                    String source = replacements[replacementIdx].source;
                    boolean sourceMatches = i == replacements[replacementIdx].index;

                    for (int j = 1; j < source.length(); j++) {

                        if (i + j >= s.length() || source.charAt(j) != s.charAt(i + j)) {
                            sourceMatches = false;
                            break;
                        }
                    }

                    if (sourceMatches) {
                        result.append(replacements[replacementIdx].replacement);
                        replacementIdx++;
                        i = i + (source.length() - 1);
                    } else {
                        result.append(c);
                    }
                } else {
                    result.append(c);
                }
            }
            return result.toString();
        }
    }

    public static class Replacement {
        final String source;
        final String replacement;
        final int index;

        public Replacement(String source, String replacement, int index) {
            this.source = source;
            this.replacement = replacement;
            this.index = index;
        }
    }
}
