import java.io.*;
import java.util.*;

/*
Problem : Given a tree if the node with sum values, just like house robber probem we need to find out the max amount of money that we can stolem
we will use the dp to solve this problem,  also we cant take the values from the adjacent node
 */

public class problem1 {
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
    int[] arr = new int[n + 1];
    for (int i = 1; i <= n; i++) {
      arr[i] = in.nextInt();
    }
    List<List<Integer>> adj = new ArrayList<>();
    for (int i = 0; i <= n; i++) {
      adj.add(new ArrayList<>());
    }
    for (int i = 1; i < n; i++) {
      int u = in.nextInt();
      int v = in.nextInt();
      adj.get(u).add(v);
      adj.get(v).add(u);
    }
    int[][] dp = new int[n + 1][2];
    dfs(1, -1, adj, dp, arr);
    System.out.println(Math.max(dp[1][0], dp[1][1]));
  }

  private static void dfs(int node, int parent, List<List<Integer>> adj, int[][] dp, int[] arr) {
    dp[node][1] = arr[node]; // we will pick that value.

    for (int adjNode : adj.get(node)) {
      if (adjNode != parent) {
        dfs(adjNode, node, adj, dp, arr);
        // if we picked the node then we can only pick from the non pick.
        dp[node][1] += dp[adjNode][0];
        // if earlier we can't picked the values earlier, now we have two choices to
        // pick, either pick or non pick
        dp[node][0] += Math.max(dp[adjNode][1], dp[adjNode][0]);
      }
    }
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
