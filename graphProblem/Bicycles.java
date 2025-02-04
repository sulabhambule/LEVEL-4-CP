import static java.lang.Math.max;
import static java.lang.Math.min;
import java.io.*;
import java.util.*;

/*
 cf problem link : https://codeforces.com/contest/1915/problem/G
 */

public class Bicycles {
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static FASTIO in = new FASTIO();
  static final long INF = Long.MAX_VALUE / 2;
  // static final int MOD = (int) 1e9 + 7;
  static final int MOD = 998244353;

  public static void main(String[] Hi) throws IOException {
    int cp = in.nextInt();
    while (cp-- > 0) {
      Subh();
    }
    out.close();
  }

  static void Subh() throws IOException {
    int n = in.nextInt();
    int m = in.nextInt();
    List<List<int[]>> adj = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      adj.add(new ArrayList<>());
    }

    for (int i = 0; i < m; i++) {
      int u = in.nextInt() - 1;
      int v = in.nextInt() - 1;
      int w = in.nextInt();
      adj.get(u).add(new int[] { v, w });
      adj.get(v).add(new int[] { u, w });
    }

    int[] bikes = new int[n];
    for (int i = 0; i < n; i++) {
      bikes[i] = in.nextInt();
    }

    long[][] dp = new long[n][1001];
    for (int i = 0; i < n; i++) {
      Arrays.fill(dp[i], INF);
    }

    PriorityQueue<Tuple> pq = new PriorityQueue<>();
    pq.add(new Tuple(0, 0, bikes[0]));
    dp[0][bikes[0]] = 0;

    while (!pq.isEmpty()) {
      Tuple t = pq.poll();
      int currNode = t.node;
      long dist = t.cost;
      int bike = t.bikeNum;

      if (dist > dp[currNode][bike])
        continue;

      for (int[] neighbour : adj.get(currNode)) {
        int adjN = neighbour[0];
        int len = neighbour[1];

        int newBike = min(bike, bikes[adjN]);
        long newDist = dist + (long) (bike * len);

        if (newDist < dp[adjN][newBike]) {
          dp[adjN][newBike] = newDist;
          pq.add(new Tuple(adjN, newDist, newBike));
        }
      }
    }

    long minCost = INF;
    for (int i = 0; i < 1001; i++) {
      minCost = min(minCost, dp[n - 1][i]);
    }

    out.println(minCost == INF ? -1 : minCost);
  }

  static class Tuple implements Comparable<Tuple> {
    int node;
    long cost;
    int bikeNum;

    public Tuple(int node, long cost, int bikeNum) {
      this.node = node;
      this.cost = cost;
      this.bikeNum = bikeNum;
    }

    @Override
    public int compareTo(Tuple o) {
      return Long.compare(this.cost, o.cost);
    }
  }
  /*------------------------------------------------------------------------------------------------------------- */

  static class Pair implements Comparable<Pair> {
    long first;
    int second;

    Pair(long f, int s) {
      this.first = f;
      this.second = s;
    }

    @Override
    public int compareTo(Pair other) {
      return (int) (this.first - other.first);
    }
  }

  static class FASTIO {
    BufferedReader br;
    StringTokenizer st;

    public FASTIO() {
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

  /*----------------------------------------------------------------------------------------------------------- */

  static int gcd(int a, int b) {
    if (a == 0)
      return b;
    return gcd(b % a, a);
  }

  static int lcm(int a, int b) {
    return Math.abs(a * b) / gcd(a, b);
  }

  static boolean isPrime(long arr) {
    if (arr <= 1)
      return false;
    for (int i = 2; i <= Math.sqrt(arr); i++) {
      if (arr % i == 0)
        return false;
    }
    return true;
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
      e >>= 1;
    }
    return r;
  }

  static int max_inArr(int[] arr) {
    int m = Integer.MIN_VALUE;
    for (int i : arr)
      m = max(m, i);
    return m;
  }

  static int min_inArr(int[] arr) {
    int m = Integer.MAX_VALUE;
    for (int i : arr)
      m = min(m, i);
    return m;
  }

  static int sumOfArr(int[] a) {
    int s = 0;
    for (int i : a)
      s += i;
    return s;
  }

  static void sort_(int[] a) {
    ArrayList<Integer> ls = new ArrayList<>();
    for (int x : a)
      ls.add(x);
    Collections.sort(ls);
    for (int i = 0; i < a.length; i++)
      a[i] = ls.get(i);
  }

  static void sortRev(int[] a) {
    ArrayList<Integer> ls = new ArrayList<>();
    for (int x : a)
      ls.add(x);
    Collections.sort(ls, Collections.reverseOrder());
    for (int i = 0; i < a.length; i++)
      a[i] = ls.get(i);
  }

  static void sortRev(long[] a) {
    ArrayList<Long> ls = new ArrayList<>();
    for (long x : a)
      ls.add(x);
    Collections.sort(ls, Collections.reverseOrder());
    for (int i = 0; i < a.length; i++)
      a[i] = ls.get(i);
  }

  static <T extends Comparable<T>> void sort(ArrayList<T> list) {
    Collections.sort(list);
  }

  static <T extends Comparable<T>> void sortRev(ArrayList<T> list) {
    Collections.sort(list, Collections.reverseOrder());
  }

  static void sort_(long[] a) {
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
