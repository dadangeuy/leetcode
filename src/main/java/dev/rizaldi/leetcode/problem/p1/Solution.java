package dev.rizaldi.leetcode.problem.p1;

import java.util.HashMap;

public class Solution {
    public int[] twoSum(int[] nums, int target) {
        var idxPerNum = new HashMap<Integer, Integer>();
        for(int i=0; i < nums.length; ++i) {
            var num = nums[i];
            var missingNum = target - num;
            var missingIdx = idxPerNum.get(missingNum);

            if (missingIdx != null) {
                return new int[]{missingIdx, i};
            }

            idxPerNum.put(num, i);
        }
        return null;
    }
}
