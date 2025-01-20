import java.io.*;
import java.util.*;

/*
 * Find the min value on Path. from node u to v
 * Find the max value on the Path.
 * Find the sumn on the path
 * Find the gcd over the path.
 * In the below code just replace the min with max gcd etc
 */

/*
 1) Find the min value on Path : min(a, b) -> min(min(a, lca), min(b. lca));
 - in our binary lifting table we gina be store the min of everry powers of two
 - par[node][i] = {2ith par, min from node to th ith parent}
 */
public class minValPath {
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static FastReader in = new FastReader();
  private static final int MOD = (int) 1e9 + 7;
  // private static final int MOD = 998244353;
  private static final int MAX_LOG = 20;
  private static final int N = (int) 2e5 + 1;
  static Pair[][] par = new Pair[N][MAX_LOG];
  static List<List<Integer>> adj;
  static int[] depth = new int[N];
  static int[] arr = new int[N];

  public static void main(String[] args) {
    int T = 1;
    while (T-- > 0) {
      solve();
    }
    out.close();
  }

  private static void solve() {
    adj = new ArrayList<>();
    int n = in.nextInt();
    int q = in.nextInt();
    for (int i = 1; i <= n; i++) {
      arr[i] = in.nextInt();
    }
    for (int i = 0; i <= n; i++) {
      adj.add(new ArrayList<>());
    }
    for (int i = 2; i <= n; i++) {
      int parnt = in.nextInt();
      adj.get(parnt).add(i);
    }
    dfs(1, 1);

    for (int i = 0; i < q; i++) {
      int a = in.nextInt();
      int b = in.nextInt();
      System.out.println(MinDistance(a, b));
    }
  }

  private static int MinDistance(int nodeA, int nodeB) {
    if (nodeA == nodeB)
      return nodeA;

    if (depth[nodeA] < depth[nodeB]) {
      int tempNode = nodeA;
      nodeA = nodeB;
      nodeB = tempNode;
    }

    int min = Math.min(arr[nodeA], arr[nodeB]), nodeDiff = depth[nodeA] - depth[nodeB];
    for (int j = MAX_LOG - 1; j >= 0; j--) {
      if (((1 << j) & nodeDiff) != 0) {
        nodeA = par[nodeA][j].first;
        min = Math.min(min, par[nodeA][j].second);
      }
    }

    for (int j = MAX_LOG - 1; j >= 0; j--) {
      if (par[nodeA][j].first != par[nodeB][j].first) {
        min = Math.min(par[nodeA][j - 1].second, par[nodeB][j - 1].second);
        nodeA = par[nodeA][j].first;
        nodeB = par[nodeB][j].first;
      }
    }

    return (nodeA != nodeB ? Math.min(par[nodeA][0].second, min) : min);
  }

  private static void dfs(int node, int parent) {
    for (int i = 0; i < MAX_LOG; i++)
      par[node][i] = new Pair(0, Integer.MAX_VALUE);
    depth[node] = 1 + depth[parent];
    par[node][0] = new Pair(parent, Math.min(arr[node], arr[parent]));
    for (int j = 1; j < MAX_LOG; j++) {
      par[node][j] = new Pair(par[par[node][j - 1].first][j - 1].first,
          Math.min(par[par[node][j - 1].first][j - 1].second,
              par[node][j - 1].second));
    }
    for (int adjNode : adj.get(node)) {
      if (adjNode != parent) {
        dfs(adjNode, node);
      }
    }
  }

  // static int Kthparent(int node, int k) {
  // for (int i = MAX_LOG - 1; i >= 0; i--) {
  // if (((1 << i) & k) != 0) {
  // node = par[node][i];
  // if (node == 0)
  // return 0;
  // }
  // }
  // return node;
  // }

  static class Pair {
    int first, second;

    public Pair(int f, int s) {
      first = f;
      second = s;
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

}
