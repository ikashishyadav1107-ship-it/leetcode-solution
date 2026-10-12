import java.util.List;

class Solution {
    public int maxSum(List<Integer> nums, int k) {
        int[] bitCount = new int[32];
        for (int x : nums) {
            for (int i = 0; i < 32; i++) {
                if ((x & (1 << i)) != 0) {
                    bitCount[i]++;
                }
            }
        }
        
        long totalSum = 0;
        long MOD = 1_000_000_007;
        
        for (int step = 0; step < k; step++) {
            long curr = 0;
            for (int i = 0; i < 32; i++) {
                if (bitCount[i] > 0) {
                    curr |= (1L << i);
                    bitCount[i]--;
                }
            }
            totalSum = (totalSum + (curr * curr) % MOD) % MOD;
        }
        
        return (int) totalSum;
    }
}