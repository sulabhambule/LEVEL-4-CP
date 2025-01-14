import java.io.*;
import java.util.*;

// transition optimiztion:
// atcoder link: https://atcoder.jp/contests/dp/tasks/dp_m

public class Candies {
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static FastReader in = new FastReader();
  private static final int MOD = (int) 1e9 + 7;
  // private static final int MOD = (int) 998244353;

  public static void main(String[] args) {
    int T = 1;
    while (T-- > 0) {
      solve();
    }
    out.close();
  }

  private static void solve() {
    int n = in.nextInt();
    int k = in.nextInt();
    int[] a = new int[n + 1];
    for (int i = 1; i <= n; i++) {
      a[i] = in.nextInt();
    }
    // state -> dp[i][j] = number of ways to distribute the j candies among i
    // children such that j <= a[i];
    // transition :
    // (if we distrubute the 0 candies to the ith children then we have subProblem
    // of j candies upto the i - 1 children, if we give x candies to the ith
    // childewn we have j - x candies for the rest of the childrens(i - 1))
    // dp[i][j] = dp[i-1][j] + dp[i-1][j-1].. + dp[i][j-a[i]].
    // so we will maintain the prefix sum
    // prefix[i - 1][k] = dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2] ... + dp[i -
    // 1][k]
    // dp[i][j] = prefix[i - 1][j] - prefix[j - a[i] + 1]
    // final sub Problem dp[n][k]

    long[][] dp = new long[n + 1][k + 1];
    long[][] prefix = new long[n + 1][k + 1];
    dp[0][0] = 1;
    prefix[0][0] = 1;

    for (int i = 1; i <= n; i++) {
      for (int j = 0; j <= k; j++) {
        dp[i][j] = prefix[i - 1][j] % MOD; // Add all ways up to j from previous child
        if (j - a[i] - 1 >= 0) {
          dp[i][j] = (dp[i][j] - prefix[i - 1][j - a[i] - 1] + MOD) % MOD;
        }
        prefix[i][j] = (dp[i][j] + (j > 0 ? prefix[i][j - 1] : 0)) % MOD; // Update prefix sum
      }
    }

    System.out.println(prefix[n][k]);

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
