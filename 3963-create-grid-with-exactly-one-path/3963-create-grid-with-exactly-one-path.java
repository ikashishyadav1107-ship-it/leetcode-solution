import java.util.Arrays;

class Solution {
    public String[] createGrid(int m, int n) {
        char[][] g = new char[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(g[i], '#');
        }
        
        Arrays.fill(g[0], '.');
        
        for (int i = 0; i < m; i++) {
            g[i][n - 1] = '.';
        }
        
        String[] result = new String[m];
        for (int i = 0; i < m; i++) {
            result[i] = new String(g[i]);
        }
        
        return result;
    }
}