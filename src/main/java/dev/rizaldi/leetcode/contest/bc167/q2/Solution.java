package dev.rizaldi.leetcode.contest.bc167.q2;

public class Solution {
    public int longestSubarray(int[] nums) {
        final boolean[] isFibonacciSums = new boolean[nums.length];
        for (int i = 2; i < nums.length; i++) {
            final boolean isFibonacciSum = nums[i] == nums[i - 1] + nums[i - 2];
            isFibonacciSums[i] = isFibonacciSum;
        }

        int maxFibonacciLength = 2;
        for (int i = 0; i < nums.length; i++) {
            if (isFibonacciSums[i]) {
                int count = 0;
                while (i < nums.length && isFibonacciSums[i]) {
                    i++;
                    count++;
                }
                int fibonacciLength = count + 2;
                maxFibonacciLength = Math.max(maxFibonacciLength, fibonacciLength);
            }
        }

        return maxFibonacciLength;
    }
}
