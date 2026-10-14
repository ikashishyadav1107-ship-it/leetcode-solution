class Solution {
    public String maxSumOfSquares(int num, int sum) {
        if (sum > 9 * num || sum < 1) {
            return "";
        }
        
        int[] digits = new int[num];
        int remSum = sum;
        
        for (int i = 0; i < num; i++) {
            if (remSum >= 9) {
                digits[i] = 9;
                remSum -= 9;
            } else {
                digits[i] = remSum;
                remSum = 0;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for (int d : digits) {
            sb.append(d);
        }
        
        return sb.toString();
    }
}