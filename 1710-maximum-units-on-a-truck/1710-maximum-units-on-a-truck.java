class Solution {
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes,(a,b)->b[1]-a[1]);
        int maxunit = 0;
        for(int[] box:boxTypes){
            int boxcount = Math.min(box[0],truckSize);
            maxunit += boxcount* box[1];
            truckSize -= boxcount;
            if(truckSize==0){
                break;
            }
        }
        return maxunit;
    }
}