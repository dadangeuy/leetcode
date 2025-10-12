package dev.rizaldi.leetcode.contest.w471.q2;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {
    public int longestBalanced(String s) {
        final HashMap<Integer, Set<Character>> lettersPerCount = new HashMap<>();
        final Map<Character, Integer> countPerLetter = new HashMap<>();

        for (char letter = 'a'; letter <= 'z'; letter++) {
            lettersPerCount.computeIfAbsent(0, k -> new HashSet<>()).add(letter);
            countPerLetter.put(letter, 0);
        }

        int maxLength = 0;
        for (int i = 0; i < s.length(); i++) {
            lettersPerCount.clear();
            countPerLetter.clear();

            for (int j = i; j < s.length(); j++) {
                final char letter = s.charAt(j);
                final int oldCount = countPerLetter.getOrDefault(letter, 0);
                final int newCount = oldCount + 1;

                countPerLetter.put(letter, newCount);
                lettersPerCount.getOrDefault(oldCount, Collections.emptySet()).remove(letter);
                lettersPerCount.computeIfAbsent(newCount, k -> new HashSet<>()).add(letter);
                if (lettersPerCount.getOrDefault(oldCount, Collections.emptySet()).isEmpty()) {
                    lettersPerCount.remove(oldCount);
                }

                final int length = j - i + 1;
                final boolean isBalanced = lettersPerCount.size() == 1;
                if (isBalanced) {
                    maxLength = Math.max(maxLength, length);
                }
            }
        }

        return maxLength;
    }
}
