import java.io.*;
import java.util.*;
/*
CSES problem link : https://cses.fi/problemset/task/1133
THe problem is to calculate the sum of distance from the each node to the rest of the node.

- For calculating we will use the ReRoot DP , we will run traversal from any of the node , and when we reRoot the Root of the node we will see how the ansewer is changing.
- ok so if the curreent dist is ans  then newRoot 
- newAns = ans - subTreeSize[adjNode] + (subTreeSize[1] - subTreeSize[adjNode]);
*/

public class ReRootDp {
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

  private static void solve() {
    int n = in.nextInt();
    List<List<Integer>> adj = new ArrayList<>();
    for (int i = 0; i <= n; i++)
      adj.add(new ArrayList<>());

    for (int i = 0; i < n - 1; i++) {
      int a = in.nextInt();
      int b = in.nextInt();
      adj.get(a).add(b);
      adj.get(b).add(a);
    }
    int[] subTreeSize = new int[n + 1];
    int ansOf1 = dfs(1, -1, 0, adj, subTreeSize);
    int[] answer = new int[n + 1];
    reRoot(1, -1, subTreeSize, adj, ansOf1, answer);
    for (int i = 1; i <= n; i++) {
      System.out.print(answer[i] + " ");
    }
    System.out.println();
  }

  private static int dfs(int node, int parent, int depth, List<List<Integer>> adj, int[] subTreeSize) {
    subTreeSize[node] = 1;
    int answer = depth;
    for (int adjNode : adj.get(node)) {
      if (adjNode != parent) {
        answer += dfs(adjNode, node, depth + 1, adj, subTreeSize);
        subTreeSize[node] += subTreeSize[adjNode];
      }
    }
    return answer;
  }

  private static void reRoot(int node, int parent, int[] subTreeSize, List<List<Integer>> adj, int ans, int[] answer) {
    answer[node] = ans;
    for (int adjNode : adj.get(node)) {
      if (adjNode != parent) {
        int newAns = ans - subTreeSize[adjNode] + (subTreeSize[1] - subTreeSize[adjNode]);
        reRoot(adjNode, node, subTreeSize, adj, newAns, answer);
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
}
