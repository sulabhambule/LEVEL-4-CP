import java.io.*;
import java.util.*;

public class diameter2 {
    public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
    static FastReader in = new FastReader();
    private static final int MOD = (int) 1e9 + 7;
    // private static final int MOD = 998244353;

    public static void main(String[] args) {
        int T = 1;
        while (T-- > 0) {
            solve();
        }
        out.close();
    }

    private static void solve() {
        int n = in.nextInt();
        List<List<Integer>> edges = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            edges.add(new ArrayList<>());
        }
        for (int i = 0; i < n - 1; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            edges.get(u).add(v);
            edges.get(v).add(u);
        }

        int[] distX = new int[n + 1];
        int[] distY = new int[n + 1];
        Arrays.fill(distX, -1);
        Arrays.fill(distY, -1);
        int x = 1;

        // First DFS from a random node to find a farthest node
        dfs(x, edges, -1, distX);
        int y = farthestNode(n, distX);

        // Second DFS from farthest node to find the farthest node from it
        dfs(y, edges, -1, distY);
        int z = farthestNode(n, distY);

        // Print the diameter of the tree
        System.out.println(distY[z]);
    }

    private static void dfs(int curr, List<List<Integer>> edges, int parent, int[] level) {
        if (parent == -1) {
            level[curr] = 0;
        } else {
            level[curr] = level[parent] + 1;
        }

        for (int neighbor : edges.get(curr)) {
            if (neighbor != parent) {
                dfs(neighbor, edges, curr, level);
            }
        }
    }

    // Find the farthest node from a given node
    private static int farthestNode(int n, int[] dist) {
        int farthest = 0;
        for (int i = 0; i <= n; i++) {
            if (dist[i] > dist[farthest]) {
                farthest = i;
            }
        }
        return farthest;
    }

    static class FastReader {

        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}