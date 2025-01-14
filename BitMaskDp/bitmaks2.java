
import java.io.*;
import java.util.*;

public class bitmaks2 {
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

  // dp[index][mask]
  // represent the number of ways to pair the first index man to
  // the set of women represnt by mask, mask is the bitmaks of n bits, where the
  // ith bit is 1 if women i is pared otherwise 0.
  // OPTIMIZED WITH SPACE OPTIMIZATION.

  private static void solve() {
    int n = in.nextInt();
    int[][] arr = new int[n][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        arr[i][j] = in.nextInt();
      }
    }
    int[] dp = new int[1 << n];
    Arrays.fill(dp, -1);
    System.out.println(BitMask(0, n, arr, dp));
  }

  private static int BitMask(int mask, int n, int[][] arr, int[] dp) {
    int index = Integer.bitCount(mask);
    if (index == n)
      return 1;
    if (dp[mask] != -1)
      return dp[mask];
    int ways = 0;
    for (int i = 0; i < n; i++) {
      if ((((1 << i) & mask) == 0) && arr[index][i] == 1) {
        ways = (ways + BitMask(mask | (1 << i), n, arr, dp)) % MOD;
      }
    }
    return dp[mask] = ways;
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
