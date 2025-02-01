import java.util.*;

public class PathPrefix {
  static List<Long> ls;
  static List<Long> ans;
  static long currA;
  static long currB;

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int tt = in.nextInt();
    while (tt-- > 0) {
      currA = 0;
      int n = in.nextInt();
      currB = 0;
      ls = new ArrayList<>();
      ans = new ArrayList<>(Collections.nCopies(n + 1, 0L));
      List<List<long[]>> adj = new ArrayList<>();

      for (int i = 0; i <= n; i++) {
        adj.add(new ArrayList<>());
      }

      for (int i = 2; i <= n; i++) {
        int p = in.nextInt();
        long ai = in.nextLong();
        long bi = in.nextLong();
        adj.get(p).add(new long[] { i, ai, bi });
      }

      dfs(1, -1, adj);

      for (int i = 2; i <= n; i++) {
        System.out.print(ans.get(i) + " ");
      }
      System.out.println();
    }
    in.close();
  }

  private static void dfs(int node, int par, List<List<long[]>> adj) {
    for (long[] neighbour : adj.get(node)) {
      int child = (int) neighbour[0];
      long a1 = neighbour[1];
      long b1 = neighbour[2];

      currA += a1;
      currB += b1;
      ls.add(currB);
      int index = binarySearch(currA);
      ans.set(child, index == -1 ? 0L : (long) (index + 1));

      dfs(child, node, adj);

      // Backtrack
      ls.remove(ls.size() - 1);
      currA -= a1;
      currB -= b1;
    }
  }

  private static int binarySearch(long tar) {
    int low = 0, high = ls.size() - 1;
    int index = -1;

    while (low <= high) {
      int mid = (low + high) / 2;
      if (ls.get(mid) <= tar) {
        index = mid;
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }
    return index;
  }
}