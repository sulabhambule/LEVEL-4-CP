import java.io.*;
import java.util.*;

/*
 * Find the heaviest weight from the given source node in a weighted DAG.
 * The weight of the path is defined as the sum of all weights in the path.
 */

public class problem1 {
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static FastReader in = new FastReader();

  public static void main(String[] args) {
    solve();
    out.close();
  }

  private static void solve() {
    int n = in.nextInt(); // Number of nodes
    int m = in.nextInt(); // Number of edges
    int src = in.nextInt(); // Source node (0-based index)

    List<List<int[]>> adj = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      adj.add(new ArrayList<>());
    }

    // Read edges (u, v, weight)
    for (int i = 0; i < m; i++) {
      int u = in.nextInt();
      int v = in.nextInt();
      int w = in.nextInt();
      adj.get(u).add(new int[] { v, w });
    }

    int result = heaviestPath(adj, src);
    out.println(result);
  }

  public static List<Integer> topoSortBFS(int n, List<List<int[]>> adj) {
    int[] inDegree = new int[n];
    for (int u = 0; u < n; u++) {
      for (int[] edge : adj.get(u)) {
        int v = edge[0];
        inDegree[v]++;
      }
    }

    Queue<Integer> q = new LinkedList<>();
    for (int i = 0; i < n; i++) {
      if (inDegree[i] == 0)
        q.add(i);
    }

    List<Integer> topo = new ArrayList<>();
    while (!q.isEmpty()) {
      int u = q.poll();
      topo.add(u);
      for (int[] edge : adj.get(u)) {
        int v = edge[0];
        if (--inDegree[v] == 0)
          q.add(v);
      }
    }

    return topo.size() == n ? topo : new ArrayList<>();
  }

  public static int heaviestPath(List<List<int[]>> adj, int src) {
    int n = adj.size();
    List<Integer> topo = topoSortBFS(n, adj);
    int[] dp = new int[n];
    Arrays.fill(dp, 0);
    // dp[src] = 0;

    for (int i = n - 1; i >= 0; i--) {
      int node = topo.get(i);
      for (int[] ad : adj.get(node)) {
        dp[node] = Math.max(dp[node], dp[ad[0] + ad[1]]);
        // ad[0] is the adjNode and ad[1] is the adjWieghts
      }
    }
    int ans = 0;
    for (int i : dp) {
      ans = Math.max(ans, i);
    }
    return ans;
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
