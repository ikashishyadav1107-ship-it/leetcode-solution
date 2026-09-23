class Solution {
    public int maxProduct(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int x = nums[n-1];
        int y = nums[n-2];
        int ans = (x-1)*(y-1);
        return ans;
    }
}