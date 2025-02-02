import java.util.*;

public class main {
  static final int mod = (int) 1e9 + 7;

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
  }
}

class DSU {
  private int[] parent, rank, size;

  public DSU(int n) {
    parent = new int[n];
    rank = new int[n];
    size = new int[n];
    for (int i = 0; i < n; i++) {
      parent[i] = i;
      size[i] = 1;
    }
  }

  public int find(int x) {
    if (parent[x] != x) {
      parent[x] = find(parent[x]);
    }
    return parent[x];
  }

  public boolean union(int u, int v) {
    int rootU = find(u);
    int rootV = find(v);
    if (rootU == rootV)
      return false;

    if (rank[rootU] > rank[rootV]) {
      parent[rootV] = rootU;
      size[rootU] += size[rootV];
    } else if (rank[rootU] < rank[rootV]) {
      parent[rootU] = rootV;
      size[rootV] += size[rootU];
    } else {
      parent[rootV] = rootU;
      rank[rootU]++;
      size[rootU] += size[rootV];
    }
    return true;
  }

  public int getSize(int x) {
    return size[find(x)];
  }
}
