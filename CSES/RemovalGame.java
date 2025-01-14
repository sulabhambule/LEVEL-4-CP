
import java.io.*;
import java.util.*;

// cses removal game problem link : https://cses.fi/problemset/task/1097

public class RemovalGame {
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static FastReader in = new FastReader();
  private static final int MOD = (int) 1e9 + 7;

  public static void main(String[] args) {
    int T = 1;
    while (T-- > 0) {
      solve();
    }
    out.close();
  }

  private static void solve() {
    int n = in.nextInt();
    long[] x = new long[n];
    for (int i = 0; i < n; i++) {
      x[i] = in.nextLong();
    }

    // dp[i][j] will be the max score of 1st player in the array from i --> j, plays
    // optimaly
    // dp[i][j] = Math.max(a[i] + min(dp[i + 1][j - 1], dp[i + 2][j]), a[j] +
    // min(dp[i][j - 2], dp[i + 1][j - 1]));

    long[][] dp = new long[n + 1][n + 1];
    for (int i = 0; i <= n; i++) {
      Arrays.fill(dp[i], -1);
    }

    long ans = fun(0, n - 1, x, dp);
    System.out.println(ans);
  }

  private static long fun(int i, int j, long[] x, long[][] dp) {
    if (i > j)
      return 0;

    if (dp[i][j] != -1) {
      return dp[i][j];
    }

    return dp[i][j] = Math.max(x[i] + Math.min(fun(i + 1, j - 1, x, dp), fun(i + 2, j, x, dp)),
        x[j] + Math.min(fun(i, j - 2, x, dp), fun(i + 1, j - 1, x, dp)));
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

  static class Pair {
    int first, second;

    Pair(int first, int second) {
      this.first = first;
      this.second = second;
    }

    @Override
    public boolean equals(Object obj) {
      if (obj == this)
        return true;
      if (!(obj instanceof Pair))
        return false;
      Pair pair = (Pair) obj;
      return pair.first == this.first && pair.second == this.second;
    }

    @Override
    public int hashCode() {
      return Objects.hash(first, second);
    }
  }
}
