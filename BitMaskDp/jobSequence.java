import java.io.*;
import java.util.*;

public class jobSequence {
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
    int[][] arr = new int[n][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        arr[i][j] = in.nextInt();
      }
    }
    int[][] dp = new int[n][1 << n];
    for (int i = 0; i < n; i++) {
      Arrays.fill(dp[i], -1);
    }
    int ans = fun(0, 0, arr, dp);
    System.out.println(ans);
  }

  private static int fun(int index, int bitmask, int[][] arr, int[][] dp) {
    int n = arr.length;
    if (index == n) {
      return 0;
    }
    if (dp[index][bitmask] != -1) {
      return dp[index][bitmask];
    }
    int maxProfit = (int) 1e9;
    for (int i = 0; i < n; i++) {
      if (((1 << i) & bitmask) == 0) {
        maxProfit = Math.min(maxProfit, arr[index][i] + fun(index + 1, bitmask | (1 << i), arr, dp));
      }
    }
    return dp[index][bitmask] = maxProfit;
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
