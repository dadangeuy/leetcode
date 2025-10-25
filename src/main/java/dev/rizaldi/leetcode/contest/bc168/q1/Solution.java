package dev.rizaldi.leetcode.contest.bc168.q1;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class Solution {
    private static final Comparator<String> ORDER = Comparator.naturalOrder();

    public String lexSmallest(String s) {
        String minText = s;
        final List<Character> letters = s.chars().mapToObj(c -> (char) c).toList();

        final LinkedList<Character> reversedLetters = new LinkedList<>();
        final LinkedList<Character> normalLetters = new LinkedList<>();
        normalLetters.addAll(letters);

        for (int i = 0; i < s.length(); i++) {
            final char letter = normalLetters.removeFirst();
            reversedLetters.addFirst(letter);

            final String text = toString(reversedLetters, normalLetters);
            minText = Stream.of(minText, text).min(ORDER).get();
        }

        reversedLetters.clear();
        normalLetters.clear();
        normalLetters.addAll(letters);

        for (int i = s.length() - 1; i >= 0; i--) {
            final char letter = normalLetters.removeLast();
            reversedLetters.addLast(letter);

            final String text = toString(normalLetters, reversedLetters);
            minText = Stream.of(minText, text).min(ORDER).get();
        }

        return minText;
    }

    private String toString(final LinkedList<Character> s1, final LinkedList<Character> s2) {
        final StringBuilder builder = new StringBuilder();
        for (char c : s1) builder.append(c);
        for (char c : s2) builder.append(c);
        return builder.toString();
    }
}
