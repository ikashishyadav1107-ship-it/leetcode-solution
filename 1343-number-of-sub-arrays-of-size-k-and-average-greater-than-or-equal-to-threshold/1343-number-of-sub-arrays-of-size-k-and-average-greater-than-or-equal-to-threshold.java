class Solution {
    public int numOfSubarrays(int[] arr, int k, int threshold) {
        double sum = 0;
        int count =0;
        int targetSum = k * threshold;
        for (int i = 0; i < k; i++) {
            sum += arr[i];
        }
        if(sum>=targetSum)
            count++;
        for (int i = k; i < arr.length; i++) {
            sum = sum - arr[i - k] + arr[i]; 
            if(sum>=targetSum)
            count++;
        }

        return count; 
    }
}