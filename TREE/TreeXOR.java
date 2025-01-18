import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.abs;
import java.io.*;
import java.util.*;

// cf : problem link : https://codeforces.com/problemset/problem/1882/D

public class TreeXOR {
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static FastReader in = new FastReader();
  // private static final int MOD = (int) 1e9 + 7;
  private static final int MOD = 998244353;

  public static void main(String[] Hi) {
    int T = in.nextInt();
    while (T-- > 0) {
      solve();
    }
    out.close();
  }

  /*
   * @Sulabh Ambule
   * - Let's Hope for the best!
   */

  private static void solve() {
    int n = in.nextInt();
    long[] a = new long[n];
    for (int i = 0; i < n; i++) {
      a[i] = in.nextLong();
    }
    List<List<Integer>> adj = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      adj.add(new ArrayList<>());
    }
    for (int i = 0; i < n - 1; i++) {
      int u = in.nextInt();
      int v = in.nextInt();
      u--;
      v--;
      adj.get(u).add(v);
      adj.get(v).add(u);
    }
    int[] subTreeSize = new int[n];
    long[] answer = new long[n];

    dfs(0, -1, a, adj, subTreeSize, answer);
    dfs2(0, 0, answer[0], adj, answer, a, subTreeSize, n);

    for (long i : answer) {
      out.print(i + " ");
    }
    out.println();
  }

  private static void dfs(int node, int parent, long[] a, List<List<Integer>> adj, int[] subTreeSize, long[] answer) {
    subTreeSize[node] = 1;
    for (int adjNode : adj.get(node)) {
      if (adjNode != parent) {
        dfs(adjNode, node, a, adj, subTreeSize, answer);
        subTreeSize[node] += subTreeSize[adjNode];
        answer[node] += answer[adjNode] + (a[adjNode] ^ a[node]) * subTreeSize[adjNode];
      }
    }
  }

  private static void dfs2(int node, int parent, long value, List<List<Integer>> adj, long[] answer, long[] a,
      int[] subTreeSize, int n) {
    answer[node] = value;
    for (int adjNode : adj.get(node)) {
      if (adjNode != parent) {
        long newValue = value + (a[node] ^ a[adjNode]) * (n - 2 * subTreeSize[adjNode]);
        dfs2(adjNode, node, newValue, adj, answer, a, subTreeSize, n);
      }
    }
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

  public static void sort(int[] arr) {
    ArrayList<Integer> ls = new ArrayList<Integer>();
    for (int x : arr)
      ls.add(x);
    Collections.sort(ls);
    for (int i = 0; i < arr.length; i++)
      arr[i] = ls.get(i);
  }
}
