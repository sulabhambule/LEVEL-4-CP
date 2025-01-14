import java.io.*;
import java.util.*;

// Transition optimization is used HERE/
// atcoder question link : https://atcoder.jp/contests/abc222/tasks/abc222_d

public class BetweenTwoArrays {
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static FastReader in = new FastReader();
  // private static final int MOD = (int) 1e9 + 7;
  private static final int MOD = (int) 998244353;

  public static void main(String[] args) {
    int T = 1;
    while (T-- > 0) {
      solve();
    }
    out.close();
  }

  private static void solve() {
    int n = in.nextInt();
    int[] a = new int[n + 1];
    for (int i = 1; i <= n; i++) {
      a[i] = in.nextInt();
    }
    int[] b = new int[n + 1];
    for (int i = 1; i <= n; i++) {
      b[i] = in.nextInt();
    }

    // STATE: Dp[i][j] = number of ways till index i to get ci = j
    // transition: dp[i][j] = sum of dp[i - 1][k] k <= j;

    int max = b[n];
    int[][] dp = new int[n + 1][max + 1];
    int[][] sum = new int[n + 1][max + 1];
    dp[0][0] = 1;
    for (int j = 0; j <= max; j++) {
      sum[0][j] = 1;
    }

    for (int i = 1; i <= n; i++) {
      for (int j = 0; j <= max; j++) {
        if (j >= a[i] && j <= b[i]) {
          dp[i][j] = (dp[i][j] + sum[i - 1][j]) % MOD;
        }
        // calculating the prefix sum
        sum[i][j] = ((j > 0 ? sum[i][j - 1] : 0) + dp[i][j]) % MOD;
      }
    }
    System.out.println(sum[n][max]);
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
