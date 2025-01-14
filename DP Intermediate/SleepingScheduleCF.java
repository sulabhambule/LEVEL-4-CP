
import java.io.*;
import java.util.*;

// codeforces problem link : https://codeforces.com/contest/1324/problem/E

public class SleepingScheduleCF {
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
    int h = in.nextInt();
    int l = in.nextInt();
    int r = in.nextInt();

    int[] a = new int[n];
    for (int i = 0; i < n; i++) {
      a[i] = in.nextInt();
    }

    // State : dp[i][j] = Max no. of good sleep if we are sleeping ith time ans
    // if I am at the ith index, and the running sleep hour is j
    // so we can either sleep for j + a[i] hours or j + a[i] - 1 hours
    // also if the j in which we are landing if it lies in the range
    // of (l --> r) we have to increament our ans. So in the tabilation DP we
    // traverse in reverse.
    // dp[i][j] =
    // max(dp[i - 1][(j - a[i] + h) % h], dp[i - 1][(j - a[i] - 1+ h) % h)]);

    int[][] dp = new int[n + 1][h + 1];
    for (int i = 0; i < n; i++) {
      Arrays.fill(dp[i], (int) (-1e9));
    }
    dp[0][0] = 0;
    for (int i = 1; i <= n; i++) {
      for (int j = 0; j < h; j++) {
        int one = 0, two = 0;
        if ((j - a[i - 1] + h) % h >= 0) {
          one = dp[i - 1][(j - a[i - 1] + h) % h];
        }
        if ((j - a[i - 1] - 1 + h) % h >= 0) {
          two = dp[i - 1][(j - a[i - 1] + 1 + h) % h];
        }
        dp[i][j] = Math.max(one, two);
        if (j >= l && j <= r && dp[i][j] != (-1e9)) {
          dp[i][j]++;
        }
      }
    }
    int ans = 0;
    for (int j = 0; j < h; j++) {
      ans = Math.max(ans, dp[n][j]);
    }
    System.out.println(ans);
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
