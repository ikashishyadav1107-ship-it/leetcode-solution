import java.util.Arrays;

class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[] prices = new int[n];
        Arrays.fill(prices, Integer.MAX_VALUE);
        prices[src] = 0;
        
        for (int i = 0; i <= k; i++) {
            int[] prevPrices = prices.clone();
            
            for (int[] flight : flights) {
                int u = flight[0];
                int v = flight[1];
                int cost = flight[2];
                
                if (prevPrices[u] != Integer.MAX_VALUE) {
                    prices[v] = Math.min(prices[v], prevPrices[u] + cost);
                }
            }
        }
        
        return prices[dst] == Integer.MAX_VALUE ? -1 : prices[dst];
    }
}