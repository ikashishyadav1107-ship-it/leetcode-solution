class Solution {
    public int[] decrypt(int[] code, int k) {

        int [] result = new int[code.length];
        
        if(k>0)
        {
            for(int i=0;i<code.length;i++)
            {
                int sum = 0;
                for(int j=1;j<=k;j++)
                {
                    int idx = (i+j) % code.length;
                    sum = sum + code[idx];
                    result[i] = sum;
                }
            }
        }
        else if(k<0)
        {
           for (int i = 0; i < code.length; i++) 
           {
                int sum = 0;
                for (int j = 1; j <= -k; j++) 
                {
                    int idx = (i - j + code.length) % code.length;
                    sum = sum + code[idx];
                }
            result[i] = sum;
            }
        }
        else
        {
            return result;
        }
        return result;
    }
}