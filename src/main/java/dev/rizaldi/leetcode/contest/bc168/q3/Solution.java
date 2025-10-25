package dev.rizaldi.leetcode.contest.bc168.q3;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Solution {
    public long minOperations(int[] nums1, int[] nums2) {
        final long[] deltaPerNum = IntStream.range(0, nums1.length)
            .map(i -> Math.abs(nums1[i] - nums2[i]))
            .mapToLong(i -> i)
            .toArray();

        final int lastDigit = nums2[nums2.length - 1];
        int minDeltaLastDigit = Math.abs(nums1[0] - lastDigit);
        for (int i = 0; i < nums1.length; i++) {
            final int num1 = nums1[i];
            final int num2 = nums2[i];
            final int minNum = Math.min(num1, num2);
            final int maxNum = Math.max(num1, num2);

            final boolean withinRange = minNum <= lastDigit && lastDigit <= maxNum;
            if (withinRange) {
                minDeltaLastDigit = 0;
                break;
            } else {
                final int delta1 = Math.abs(num1 - lastDigit);
                final int delta2 = Math.abs(num2 - lastDigit);
                minDeltaLastDigit = Math.min(minDeltaLastDigit, Math.min(delta1, delta2));
            }
        }

        long totalOperations = Arrays.stream(deltaPerNum).sum() + (minDeltaLastDigit + 1L);

        return totalOperations;
    }
}
