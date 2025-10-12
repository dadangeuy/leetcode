package dev.rizaldi.leetcode.contest.w471.q1;

public class Solution {
    public int sumDivisibleByK(int[] nums, int k) {
        final int[] frequencies = new int[101];
        for (int num : nums) frequencies[num]++;

        int sum = 0;
        for (int num = 1; num <= 100; num++) {
            final int frequency = frequencies[num];
            final boolean isDivisible = frequency % k == 0;
            if (isDivisible) {
                sum += num * frequency;
            }
        }

        return sum;
    }
}

