
import java.io.*;
import java.util.*;

// atcoder question link : https://atcoder.jp/contests/dp/tasks/dp_o

public class main {
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

  // dp[index][mask] = represent the number of ways to pair the first index man to
  // the set of women represnt by mask
  // mask is the bitmaks of n bits, where the ith bit is 1 if women i is pared
  // otherwise 0.

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
    System.out.println(fun(0, 0, n, dp, arr));
  }

  private static int fun(int index, int mask, int n, int[][] dp, int[][] arr) {
    if (index == n)
      return 1;

    if (dp[index][mask] != -1)
      return dp[index][mask];

    int ways = 0;

    for (int i = 0; i < n; i++) {
      if (((1 << i) & mask) == 0 && arr[index][i] == 1) {
        ways = (ways + fun(index + 1, mask | (1 << i), n, dp, arr)) % MOD;
      }
    }
    return dp[index][mask] = ways;
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
