
import java.io.*;
import java.util.*;

public class main3 {
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

  static int[][][] dp;

  private static int fun(int index, int tight, int sumMadeYet, String k, int d) {
    if (index == k.length()) {
      if (sumMadeYet % d == 0)
        return 1;
      return 0;
    }

    if (dp[index][tight][sumMadeYet] != -1)
      return dp[index][tight][sumMadeYet];

    int bound = (tight == 1 ? k.charAt(index) - '0' : 9);
    int sum = 0;
    for (int i = 0; i <= bound; i++) {
      sum = (sum + fun(index + 1, (((tight == 1) && (i == k.charAt(index) - '0'))) ? 1 : 0, (i + sumMadeYet) % d, k, d))
          % MOD;
    }

    return dp[index][tight][sumMadeYet] = sum % MOD;
  }

  private static void solve() {
    String k = in.next();
    int d = in.nextInt();

    int n = k.length();
    dp = new int[n][2][d];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < 2; j++) {
        Arrays.fill(dp[i][j], -1);
      }
    }

    int result = fun(0, 1, 0, k, d) % MOD - 1;
    if (result < 0)
      result += MOD;
    System.out.println(result);
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
