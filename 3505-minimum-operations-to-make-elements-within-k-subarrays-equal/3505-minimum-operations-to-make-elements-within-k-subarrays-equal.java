import java.util.*;

class Solution {
    private static final long INF = 1L << 60;

    class MyMap {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        long sum = 0;
        int size = 0;
    }

    public long minOperations(int[] nums, int x, int k) {
        List<Long> minOps = getMinOps(nums, x);
        Long[][] mem = new Long[nums.length][k + 1];
        return solveDP(nums, x, 0, k, minOps, mem);
    }

    private long solveDP(int[] nums, int x, int i, int k, List<Long> minOps, Long[][] mem) {
        if (k == 0) return 0;
        if (i == nums.length || nums.length - i < (long) k * x) return INF;
        if (mem[i][k] != null) return mem[i][k];
        
        final long skip = solveDP(nums, x, i + 1, k, minOps, mem);
        final long pick = i + x <= nums.length ? minOps.get(i) + solveDP(nums, x, i + x, k - 1, minOps, mem) : INF;
        
        return mem[i][k] = Math.min(skip, pick);
    }

    private List<Long> getMinOps(int[] nums, int x) {
        List<Long> minOps = new ArrayList<>();
        MyMap lower = new MyMap();
        MyMap upper = new MyMap();

        for (int i = 0; i < nums.length; ++i) {
            if (lower.map.isEmpty() || nums[i] <= lower.map.lastKey()) {
                lower.map.merge(nums[i], 1, Integer::sum);
                lower.sum += nums[i];
                ++lower.size;
            } else {
                upper.map.merge(nums[i], 1, Integer::sum);
                upper.sum += nums[i];
                ++upper.size;
            }

            if (i >= x) {
                final int outNum = nums[i - x];
                if (lower.map.containsKey(outNum)) {
                    lower.map.merge(outNum, -1, Integer::sum);
                    if (lower.map.get(outNum) == 0) lower.map.remove(outNum);
                    lower.sum -= outNum;
                    --lower.size;
                } else {
                    upper.map.merge(outNum, -1, Integer::sum);
                    if (upper.map.get(outNum) == 0) upper.map.remove(outNum);
                    upper.sum -= outNum;
                    --upper.size;
                }
            }

            if (lower.size < upper.size) {
                final int val = upper.map.firstKey();
                upper.map.merge(val, -1, Integer::sum);
                if (upper.map.get(val) == 0) upper.map.remove(val);
                lower.map.merge(val, 1, Integer::sum);
                upper.sum -= val;
                lower.sum += val;
                --upper.size;
                ++lower.size;
            } else if (lower.size - upper.size > 1) {
                final int val = lower.map.lastKey();
                lower.map.merge(val, -1, Integer::sum);
                if (lower.map.get(val) == 0) lower.map.remove(val);
                upper.map.merge(val, 1, Integer::sum);
                lower.sum -= val;
                upper.sum += val;
                --lower.size;
                ++upper.size;
            }

            if (i >= x - 1) {
                final int median = lower.map.lastKey();
                final long ops = (long) median * lower.size - lower.sum + upper.sum - (long) median * upper.size;
                minOps.add(ops);
            }
        }
        return minOps;
    }
}