import java.util.Arrays;

class Solution {
    public int minimizeSum(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int changeTwoMin = nums[n - 1] - nums[2];
        int changeTwoMax = nums[n - 3] - nums[0];
        int changeBothEnds = nums[n - 2] - nums[1];
        return Math.min(changeTwoMin, Math.min(changeTwoMax, changeBothEnds));
    }
}