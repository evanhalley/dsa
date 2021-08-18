package dev.evanhalley.googleprep;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BullsAndCows {

    public static void main(String[] args) {
        //"858605648"
        //"365352465"
        new BullsAndCows().getHint("1870", "7810");
    }

    public String getHint(String secret, String guess) {
        int[] digitCount = new int[10];
        int bulls = 0, cows = 0;
        int length = guess.length();

        for (int i = 0; i < length; ++i) {
            int secretDigit = secret.charAt(i) - '0';
            int guessDigit = guess.charAt(i) - '0';

            if (secretDigit == guessDigit) {
                bulls++;
            } else {
                // digitCount @ secretDigit is negative, this secret digit occurred earlier in the guess, than the secret
                // s = 012
                // g = 103
                // ie. secretDigit == 1
                if (digitCount[secretDigit] < 0) {
                    cows++;
                }

                // digitCount @ guessDigit is positive, this guess digit occurred later in the guess, than the secret
                // s = 012
                // g = 103
                // ie. guessDigit == 0
                if (digitCount[guessDigit] > 0) {
                    cows++;
                }

                // the key insight here is that we are using negative/positivity to track the occurrence of digits in
                // the secret or guess, in one pass
                digitCount[secretDigit]++;
                digitCount[guessDigit]--;
            }
        }

        return new StringBuilder()
                .append(bulls)
                .append("A")
                .append(cows)
                .append("B")
                .toString();
    }

    public String getHintMine(String secret, String guess) {

        if (guess == null || guess.length() == 0) {
            return "0A0B";
        }

        if (guess.equals(secret)) {
            return String.format("%dA0B", guess.length());
        }

        // remove the bulls, counting them is of higher priority

        Pair<String, String> secretAndGuess = removeBulls(secret, guess);
        secret = secretAndGuess.getKey();
        final int bulls = guess.length() - secret.length();
        guess = secretAndGuess.getValue();

        // read guess into a hashmap of hashsets
        Map<Character, Integer> secretMap = secretsToMap(secret);

        int cows = 0;

        for (char c : guess.toCharArray()) {

            if (secretMap.containsKey(c)) {
                cows++;
                removeDigitInstance(c, secretMap);
            }
        }
        return String.format("%dA%dB", bulls, cows);
    }

    public Pair<String, String> removeBulls(String secret, String guess) {
        StringBuilder secretBuilder = new StringBuilder();
        StringBuilder guessBuilder = new StringBuilder();

        for (int i = 0; i < secret.length(); i++) {

            if (secret.charAt(i) != guess.charAt(i)) {
                secretBuilder.append(secret.charAt(i));
                guessBuilder.append(guess.charAt(i));
            }
        }
        return new Pair<>(secretBuilder.toString(), guessBuilder.toString());
    }

    public Map<Character, Integer> secretsToMap(String secret) {
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < secret.length(); i++) {
            Character c = secret.charAt(i);

            if (map.containsKey(c)) {
                Integer count = map.get(c);
                map.put(c, ++count);
            } else {
                map.put(c, 1);
            }
        }
        return map;
    }

    public void removeDigitInstance(Character c, Map<Character, Integer> map) {
        Integer count = map.get(c);

        if (count != null) {
            count--;

            if (count == 0) {
                map.remove(c);
            } else {
                map.put(c, count);
            }
        }
    }
}
