
import java.io.*;
import java.util.*;

public class main2 {
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static FastReader in = new FastReader();
  // private static final int MOD = (int) 1e9 + 7;
  private static final int MOD = 998244353;

  public static void main(String[] args) {
    int T = 1;
    while (T-- > 0) {
      solve();
    }
    out.close();
  }

  // private static int fun(int index, int n, int[] dp) {
  // // code to print the number from 0 --> 999
  // if (index == n) {
  // // System.out.println(numberMade);
  // return 1;
  // }
  // if (dp[index] != -1) {
  // return dp[index];
  // }
  // int numbers = 0;

  static String num = "100000000";
  static int N = 100;
  static final int MAXSUM = 200;
  static int[][][] dp = new int[N][2][MAXSUM];
  static int SUM = 15;
  // count the number of digits <= 1e18 with the sum of the digits = S(ex, 150)
  // DIGIT DP
  // dp[index][tight][sumMadeYet] = number of number we can make such that we are
  // in the indexth index and tight = tight and the SUM we have till now =
  // sumMadeYet

  private static int fun(int index, int tight, int sumMadeYet) {
    if (sumMadeYet > SUM)
      return 0;
    if (index == num.length()) {
      if (sumMadeYet == SUM)
        return 1;
      return 0;
    }

    if (dp[index][tight][sumMadeYet] != -1)
      return dp[index][tight][sumMadeYet];

    int bound = (tight == 1 ? num.charAt(index) - '0' : 9);
    int sum = 0;
    for (int i = 0; i <= bound; i++) {
      sum += fun(index + 1, (((tight == 1) && (i == num.charAt(index) - '0'))) ? 1 : 0, i + sumMadeYet);
    }

    return dp[index][tight][sumMadeYet] = sum;
  }

  private static void solve() {
    int n = 5;
    // int[] dp = new int[n];
    // Arrays.fill(dp, -1);
    // System.out.println(fun(0, n, dp));
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < 2; j++) {
        Arrays.fill(dp[i][j], -1);
      }
    }
    System.out.println(fun(0, 1, 0));
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
