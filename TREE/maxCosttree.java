
import java.io.*;
import java.util.*;

// cf problme link : https://codeforces.com/contest/1092/problem/F

public class maxCosttree {

  static int n;
  static long totalSum;
  static List<List<Integer>> g;
  static long[] prefixSum, val, dp;

  static void dfs1(int node, int parent) {
    if (node != 1) {
      long value = dp[parent];
      value -= prefixSum[node];
      value += totalSum - prefixSum[node];
      dp[node] = value;
    }

    for (int child : g.get(node)) {
      if (child == parent)
        continue;
      dfs1(child, node);
    }
  }

  static void dfs(int node, int parent, int d) {
    prefixSum[node] += val[node];
    dp[1] += d * val[node];
    for (int child : g.get(node)) {
      if (parent == child)
        continue;
      dfs(child, node, d + 1);
      prefixSum[node] += prefixSum[child];
    }
  }

  static void solve() throws IOException {
    n = Integer.parseInt(in.nextLine());

    g = new ArrayList<>();
    for (int i = 0; i <= n; i++) {
      g.add(new ArrayList<>());
    }

    prefixSum = new long[n + 1];
    val = new long[n + 1];
    dp = new long[n + 1];

    String[] valInput = in.nextLine().split(" ");
    for (int i = 1; i <= n; i++) {
      val[i] = Long.parseLong(valInput[i - 1]);
    }

    for (int i = 1; i <= n - 1; i++) {
      String[] edge = in.nextLine().split(" ");
      int x = Integer.parseInt(edge[0]);
      int y = Integer.parseInt(edge[1]);
      g.get(x).add(y);
      g.get(y).add(x);
    }
    dfs(1, 0, 0);
    totalSum = prefixSum[1];
    dfs1(1, 0);

    long maxDp = 0;
    for (int i = 1; i <= n; i++) {
      maxDp = Math.max(maxDp, dp[i]);
    }

    out.println(maxDp);
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

    String nextLine() throws IOException {
      return br.readLine();
    }
  }

  static FastReader in = new FastReader();
  static PrintWriter out = new PrintWriter(System.out);

  public static void main(String[] args) throws IOException {
    solve();
    out.close();
  }
}
