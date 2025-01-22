
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.abs;
import static java.lang.Math.ceilDiv;
import java.io.*;
import java.util.*;

/*
cf problem link : https://www.tle-eliminators.com/learnings/TLE12_L4
solved using the dp pick and non pick.
 */

public class ParsaTree {
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static FastReader in = new FastReader();
  // static final int MOD = (int) 1e9 + 7;
  static final int MOD = 998244353;

  /*
   * @Sulabh Ambule
   * - Why my rating is not Increasing Codeforces :(
   * - Let's Hope for Best Today !
   */

  public static void main(String[] Hi) {
    int T = in.nextInt();
    while (T-- > 0) {
      solve();
    }
    out.close();
  }

  static void solve() {
    int n = in.nextInt();
    List<List<Integer>> adj = new ArrayList<>();
    for (int i = 0; i <= n; i++) {
      adj.add(new ArrayList<>());
    }
    long[][] value = new long[n + 1][2];
    for (int i = 1; i <= n; i++) {
      value[i][0] = in.nextLong();
      value[i][1] = in.nextLong();
    }
    for (int i = 0; i < n - 1; i++) {
      int a = in.nextInt();
      int b = in.nextInt();
      adj.get(a).add(b);
      adj.get(b).add(a);
    }

    long[][] dp = new long[n + 1][2];
    dfs(1, -1, adj, value, dp);
    out.println(max(dp[1][0], dp[1][1]));
  }

  private static void dfs(int node, int parent, List<List<Integer>> adj, long[][] value, long[][] dp) {

    for (int adjNode : adj.get(node)) {
      if (adjNode != parent) {
        dfs(adjNode, node, adj, value, dp);
        dp[node][1] += max(dp[adjNode][1] + abs(value[node][1] - value[adjNode][1]),
            dp[adjNode][0] + abs(value[node][1] - value[adjNode][0]));
        dp[node][0] += max(dp[adjNode][1] + abs(value[node][0] - value[adjNode][1]),
            dp[adjNode][0] + abs(value[node][0] - value[adjNode][0]));
      }
    }
  }
  /*------------------------------------------------------------------------------------------------- */

  class Pair {
    int first, second;

    Pair(int f, int s) {
      first = f;
      second = s;
    }
  }

  static long gcd(long a, long b) {
    if (a == 0)
      return b;
    return gcd(b % a, a);
  }

  static long lcm(long a, long b) {
    return Math.abs(a * b) / gcd(a, b);
  }

  static void reverse(int[] a, int l, int r) {
    while (l < r) {
      int t = a[l];
      a[l] = a[r];
      a[r] = t;
      l++;
      r--;
    }
  }

  static void reverse(long[] a, int l, int r) {
    while (l < r) {
      long t = a[l];
      a[l] = a[r];
      a[r] = t;
      l++;
      r--;
    }
  }

  static long modPow(long b, long e, long mod) {
    long r = 1;
    b = b % mod;
    while (e > 0) {
      if ((e & 1) == 1) {
        r = (r * b) % mod;
      }
      b = (b * b) % mod;
      r >>= 1;
    }
    return r;
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

  static void sort(int[] a) {
    ArrayList<Integer> ls = new ArrayList<>();
    for (int x : a)
      ls.add(x);
    Collections.sort(ls);
    for (int i = 0; i < a.length; i++)
      a[i] = ls.get(i);
  }

  static void sort(long[] a) {
    ArrayList<Long> ls = new ArrayList<>();
    for (long x : a)
      ls.add(x);
    Collections.sort(ls);
    for (int i = 0; i < a.length; i++)
      a[i] = ls.get(i);
  }

  static void print(int[][] arr) {
    for (int[] a : arr) {
      for (int i : a)
        out.print(i + " ");
      out.println();
    }
    out.println();
  }

  static void print(long[][] arr) {
    for (long[] a : arr) {
      for (long i : a)
        out.print(i + " ");
      out.println();
    }
    out.println();
  }

  static void print(int[] a) {
    for (int i : a)
      out.print(i + " ");
    out.println();
  }

  static void print(char[] a) {
    for (char i : a)
      out.print(i + " ");
    out.println();
  }

  static void print(long[] a) {
    for (long i : a)
      out.print(i + " ");
    out.println();
  }

  static <T extends Number> void print(ArrayList<T> ls) {
    for (T i : ls)
      out.print(i + " ");
    out.println();
  }

  static int[] inputIntArr(int n) {
    int[] a = new int[n];
    for (int i = 0; i < n; i++)
      a[i] = in.nextInt();
    return a;
  }

  static long[] inputLongArr(int n) {
    long[] a = new long[n];
    for (int i = 0; i < n; i++)
      a[i] = in.nextLong();
    return a;
  }

  static int[][] inputIntArr(int n, int m) {
    int[][] a = new int[n][m];
    for (int i = 0; i < n; i++)
      for (int j = 0; j < m; j++)
        a[i][j] = in.nextInt();
    return a;
  }

  static long[][] inputLongArr(int n, int m) {
    long[][] a = new long[n][m];
    for (int i = 0; i < n; i++)
      for (int j = 0; j < m; j++)
        a[i][j] = in.nextLong();
    return a;
  }

  static ArrayList<Integer> inputIntList(int n) {
    ArrayList<Integer> ls = new ArrayList<>();
    for (int i = 0; i < n; i++)
      ls.add(in.nextInt());
    return ls;
  }

  static ArrayList<Long> inputLongList(int n) {
    ArrayList<Long> ls = new ArrayList<>();
    for (int i = 0; i < n; i++)
      ls.add(in.nextLong());
    return ls;
  }
}
