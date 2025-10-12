package dev.rizaldi.leetcode.contest.bc167.q1;

public class Solution {
    public boolean scoreBalance(String s) {
        int sum1 = 0;
        int sum2 = 0;
        for (int i = 0; i < s.length(); i++) sum2 += (int) s.charAt(i) - 'a' + 1;

        for (int index = 0; index < s.length() - 1; index++) {
            final int score = s.charAt(index) - 'a' + 1;
            sum1 += score;
            sum2 -= score;

            if (sum1 == sum2) return true;
        }

        return false;
    }
}
