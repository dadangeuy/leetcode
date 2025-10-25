package dev.rizaldi.leetcode.contest.bc168.q2;

public class Solution {
    public String maxSumOfSquares(int num, int sum) {
        final StringBuilder goodInteger = new StringBuilder();
        for (int digit = 9; digit >= 1; digit--) {
            final int count = Math.min(sum / digit, num);
            for (int i = 0; i < count; i++) {
                goodInteger.append(Character.forDigit(digit, 10));
            }

            num -= count;
            sum -= digit * count;
        }

        if (sum > 0) {
            return "";
        }

        for (int i = 0; i < num; i++) {
            goodInteger.append('0');
        }

        return goodInteger.toString();
    }
}
