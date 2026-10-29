import java.util.*;

class Solution {
    class SegmentTree {
        int n;
        int[] tree;
        
        public SegmentTree(int n) {
            this.n = n;
            tree = new int[4 * n];
        }
        
        public void update(int node, int start, int end, int idx, int val) {
            if (start == end) {
                tree[node] = val;
                return;
            }
            int mid = (start + end) / 2;
            if (idx <= mid) update(2 * node, start, mid, idx, val);
            else update(2 * node + 1, mid + 1, end, idx, val);
            tree[node] = tree[2 * node] ^ tree[2 * node + 1];
        }
        
        public int query(int node, int start, int end, int l, int r) {
            if (r < start || end < l) return 0;
            if (l <= start && end <= r) return tree[node];
            int mid = (start + end) / 2;
            return query(2 * node, start, mid, l, r) ^ query(2 * node + 1, mid + 1, end, l, r);
        }
    }

    private List<Integer>[] adj;
    private int[] parent, depth, heavy, head, pos, sz;
    private int curPos;

    private int dfs(int v, int p, int d, char[] s) {
        depth[v] = d;
        parent[v] = p;
        sz[v] = 1;
        int maxSub = 0;
        heavy[v] = -1;
        for (int u : adj[v]) {
            if (u != p) {
                int sub = dfs(u, v, d + 1, s);
                sz[v] += sub;
                if (sub > maxSub) {
                    maxSub = sub;
                    heavy[v] = u;
                }
            }
        }
        return sz[v];
    }

    private void decompose(int v, int h, char[] s, SegmentTree tree) {
        head[v] = h;
        pos[v] = curPos;
        tree.update(1, 0, adj.length - 1, curPos++, 1 << (s[v] - 'a'));
        if (heavy[v] != -1) {
            decompose(heavy[v], h, s, tree);
        }
        for (int u : adj[v]) {
            if (u != parent[v] && u != heavy[v]) {
                decompose(u, u, s, tree);
            }
        }
    }

    public List<Boolean> palindromePath(int n, int[][] edges, String sStr, String[] queries) {
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }
        
        parent = new int[n];
        depth = new int[n];
        heavy = new int[n];
        head = new int[n];
        pos = new int[n];
        sz = new int[n];
        
        char[] s = sStr.toCharArray();
        dfs(0, -1, 0, s);
        
        SegmentTree tree = new SegmentTree(n);
        curPos = 0;
        decompose(0, 0, s, tree);
        
        List<Boolean> ans = new ArrayList<>();
        for (String q : queries) {
            String[] parts = q.split(" ");
            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);
                s[u] = c;
                tree.update(1, 0, n - 1, pos[u], 1 << (c - 'a'));
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);
                int pathXor = 0;
                
                while (head[u] != head[v]) {
                    if (depth[head[u]] > depth[head[v]]) {
                        int temp = u; u = v; v = temp;
                    }
                    pathXor ^= tree.query(1, 0, n - 1, pos[head[v]], pos[v]);
                    v = parent[head[v]];
                }
                if (depth[u] > depth[v]) {
                    int temp = u; u = v; v = temp;
                }
                pathXor ^= tree.query(1, 0, n - 1, pos[u], pos[v]);
                
                ans.add((pathXor & (pathXor - 1)) == 0);
            }
        }
        
        return ans;
    }
}