import java.util.*;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));
        int m = queries.length;
        int[][] qs = new int[m][2];
        for (int i = 0; i < m; i++) {
            qs[i] = new int[]{queries[i], i};
        }
        Arrays.sort(qs, (a, b) -> Integer.compare(a[0], b[0]));
        
        int[] ans = new int[m];
        Map<Integer, Integer> cnt = new HashMap<>();
        int j = 0, k = 0;
        
        for (int[] q : qs) {
            int r = q[0];
            int idx = q[1];
            int l = r - x;
            
            while (k < logs.length && logs[k][1] <= r) {
                cnt.put(logs[k][0], cnt.getOrDefault(logs[k][0], 0) + 1);
                k++;
            }
            
            while (j < logs.length && logs[j][1] < l) {
                int server = logs[j][0];
                cnt.put(server, cnt.get(server) - 1);
                if (cnt.get(server) == 0) {
                    cnt.remove(server);
                }
                j++;
            }
            
            ans[idx] = n - cnt.size();
        }
        
        return ans;
    }
}