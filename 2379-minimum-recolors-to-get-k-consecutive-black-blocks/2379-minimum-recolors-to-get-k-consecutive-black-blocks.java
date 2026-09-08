class Solution {
    public int minimumRecolors(String blocks, int k) {
    int n = blocks.length();
    int l=0;
    int r=k-1;
    int whiteCount = 0;
    for(int i=0;i<k;i++){
        if(blocks.charAt(i)=='W') whiteCount++;
        }
        int minConvert=whiteCount;
        while(r<n){
            minConvert=Math.min(minConvert,whiteCount);
            if(blocks.charAt(l)=='W') whiteCount--;
            if(whiteCount<0) whiteCount=0;
            l++;
            r++;
            if(r<n && blocks.charAt(r)=='W') whiteCount++;
        }

        return minConvert;

    }

}
