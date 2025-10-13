package dev.rizaldi.leetcode.problem.p3714;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>3714. Longest Balanced Substring II</p>
 * <p>Difficulty: Medium</p>
 * <p>Verdict: Accepted</p>
 * <p>Link: <a href="https://leetcode.com/problems/longest-balanced-substring-ii/">LeetCode</a></p>
 */
public class Solution {
    public int longestBalanced(String s) {
        final char[] letters = s.toCharArray();

        final int maxSingleLength = findMaxSingleLength(letters);
        final int maxDoubleLength = findMaxDoubleLength(letters);
        final int maxTripleLength = findMaxTripleLength(letters);

        final int maxLength = Math.max(maxSingleLength, Math.max(maxDoubleLength, maxTripleLength));
        return maxLength;
    }

    private int findMaxSingleLength(final char[] letters) {
        int maxLength = 0;

        int length = 0;
        char letter = '0';

        for (int i = 0; i < letters.length; i++) {
            if (letters[i] == letter) {
                length++;
            } else {
                letter = letters[i];
                length = 1;
            }
            maxLength = Math.max(maxLength, length);
        }

        return maxLength;
    }

    private int findMaxDoubleLength(final char[] letters) {
        final int lengthAB = findMaxDoubleLength(letters, 'a', 'b', 'c');
        final int lengthAC = findMaxDoubleLength(letters, 'a', 'c', 'b');
        final int lengthBC = findMaxDoubleLength(letters, 'b', 'c', 'a');
        return Math.max(lengthAB, Math.max(lengthAC, lengthBC));
    }

    private int findMaxDoubleLength(
            final char[] letters,
            final char letter1,
            final char letter2,
            final char letter3
    ) {
        final Map<Pair<Integer>, Integer> minIndexPerDelta = new HashMap<>();

        int sum1 = 0;
        int sum2 = 0;
        int sum3 = 0;

        minIndexPerDelta.put(new Pair<>(0, 0), -1);

        int maxLength = 0;
        for (int index = 0; index < letters.length; index++) {
            final char letter = letters[index];

            if (letter == letter1) sum1++;
            else if (letter == letter2) sum2++;
            else if (letter == letter3) sum3++;

            final var delta12 = sum1 - sum2;
            final var delta3 = sum3;
            final var delta = new Pair<>(delta12, delta3);

            minIndexPerDelta.putIfAbsent(delta, index);

            final var minIndex = minIndexPerDelta.get(delta);
            final var length = index - minIndex;
            maxLength = Math.max(maxLength, length);
        }

        return maxLength;
    }

    private int findMaxTripleLength(final char[] letters) {
        final Map<Pair<Integer>, Integer> minIndexPerDelta = new HashMap<>();

        int sum1 = 0;
        int sum2 = 0;
        int sum3 = 0;

        minIndexPerDelta.put(new Pair<>(0, 0), -1);

        int maxLength = 0;
        for (int index = 0; index < letters.length; index++) {
            final char letter = letters[index];

            if (letter == 'a') sum1++;
            else if (letter == 'b') sum2++;
            else if (letter == 'c') sum3++;

            final int delta12 = sum1 - sum2;
            final int delta13 = sum1 - sum3;
            final Pair<Integer> pair = new Pair<>(delta12, delta13);

            minIndexPerDelta.putIfAbsent(pair, index);

            final int minIndex = minIndexPerDelta.get(pair);
            final int length = index - minIndex;
            maxLength = Math.max(maxLength, length);
        }

        return maxLength;
    }
}

class Pair<V> {
    public final V first;
    public final V second;

    public Pair(final V first1, final V second1) {
        this.first = first1;
        this.second = second1;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof final Pair<?> pair) {
            return first.equals(pair.first) && second.equals(pair.second);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}
