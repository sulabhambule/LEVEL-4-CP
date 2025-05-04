
import java.io.*;
import java.util.*;

/*
 * parent[node][i] = parent[parent[node][i - 1]][i - 1];
 * This means that the 2^i th parent of the node is
 *  2^i - 1 th parent of the node ka 2^i-1 th parent
 */

public class LCA {
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static FastReader in = new FastReader();
  private static final int MOD = (int) 1e9 + 7;
  // private static final int MOD = 998244353;
  private static final int MAX_LOG = 20;
  private static final int N = (int) 2e5 + 1;
  static int[][] par = new int[N][MAX_LOG];
  static List<List<Integer>> adj = new ArrayList<>();
  static int[] depth = new int[N];

  public static void main(String[] args) {
    int T = 1;
    while (T-- > 0) {
      solve();
    }
    out.close();
  }

  private static void solve() {
    int n = in.nextInt();
    int q = in.nextInt();
    for (int i = 0; i <= n; i++) {
      adj.add(new ArrayList<>());
    }
    for (int i = 2; i <= n; i++) {
      int parnt = in.nextInt();
      adj.get(parnt).add(i);
    }
    dfs(1, 0);

    for (int i = 0; i < q; i++) {
      int a = in.nextInt();
      int b = in.nextInt();
      System.out.println(LCA_(a, b));
    }
  }

  private static int LCA_(int nodeA, int nodeB) {
    if (nodeA == nodeB)
      return nodeA;
    if (depth[nodeA] < depth[nodeB]) {
      int tempNode = nodeA;
      nodeA = nodeB;
      nodeB = tempNode;
    }
    int nodeDiff = depth[nodeA] - depth[nodeB];
    for (int j = MAX_LOG - 1; j >= 0; j--) {
      if (((1 << j) & nodeDiff) != 0) {
        nodeA = par[nodeA][j];
      }
    }
    for (int j = MAX_LOG - 1; j >= 0; j--) {
      if (par[nodeA][j] != par[nodeB][j]) {
        nodeA = par[nodeA][j];
        nodeB = par[nodeB][j];
      }
    }
    return (nodeA != nodeB ? par[nodeA][0] : nodeA);
  }

  private static void dfs(int node, int parent) {
    depth[node] = 1 + depth[parent];
    par[node][0] = parent;
    for (int j = 1; j < MAX_LOG; j++) {
      par[node][j] = par[par[node][j - 1]][j - 1];
    }
    for (int adjNode : adj.get(node)) {
      if (adjNode != parent) {
        dfs(adjNode, node);
      }
    }
  }

  static int Kthparent(int node, int k) {
    for (int i = MAX_LOG - 1; i >= 0; i--) {
      if (((1 << i) & k) != 0) {
        node = par[node][i];
        if (node == 0)
          return 0;
      }
    }
    return node;
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
