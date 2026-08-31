import java.util.HashSet;
import java.util.Set;

class Solution {
    public int[] fairCandySwap(int[] aliceSizes, int[] bobSizes) {
        int sumA = 0;
        int sumB = 0;
        
        for (int x : aliceSizes) {
            sumA += x;
        }
        
        Set<Integer> bobSet = new HashSet<>();
        for (int y : bobSizes) {
            sumB += y;
            bobSet.add(y);
        }
        
        int delta = (sumB - sumA) / 2;
        
        for (int x : aliceSizes) {
            int targetY = x + delta;
            if (bobSet.contains(targetY)) {
                return new int[]{x, targetY};
            }
        }
        
        return new int[0];
    }
}