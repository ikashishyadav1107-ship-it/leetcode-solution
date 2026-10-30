import java.util.*;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        List<int[]>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            adj[e[0]].add(new int[]{e[1], e[2]});
        }
        
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));
        long[][] dist = new long[n][k + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Long.MAX_VALUE);
        }
        
        int startStreak = 1;
        if (startStreak > k) return -1;
        pq.offer(new long[]{0, 0, startStreak});
        dist[0][startStreak] = 0;
        
        while (!pq.isEmpty()) {
            long[] curr = pq.poll();
            long cost = curr[0];
            int u = (int) curr[1];
            int streak = (int) curr[2];
            
            if (u == n - 1) {
                return (int) cost;
            }
            
            if (cost > dist[u][streak]) {
                continue;
            }
            
            for (int[] edge : adj[u]) {
                int v = edge[0];
                int weight = edge[1];
                
                int nextStreak;
                if (labels.charAt(v) == labels.charAt(u)) {
                    nextStreak = streak + 1;
                } else {
                    nextStreak = 1;
                }
                
                if (nextStreak <= k) {
                    if (cost + weight < dist[v][nextStreak]) {
                        dist[v][nextStreak] = cost + weight;
                        pq.offer(new long[]{cost + weight, v, nextStreak});
                    }
                }
            }
        }
        
        long minCost = Long.MAX_VALUE;
        for (int c = 1; c <= k; c++) {
            minCost = Math.min(minCost, dist[n - 1][c]);
        }
        
        return minCost == Long.MAX_VALUE ? -1 : (int) minCost;
    }
}