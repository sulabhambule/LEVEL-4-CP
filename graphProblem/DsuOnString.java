
import java.util.*;

// cf problem link : https://codeforces.com/problemset/problem/151/D

public class DsuOnString {
  static final int mod = (int) 1e9 + 7;

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int m = in.nextInt();
    int k = in.nextInt();

    DSU dsu = new DSU(n);
    for (int i = 0; i <= n - k; i++) {
      int left = i, right = i + k - 1;
      while (left < right) {
        dsu.union(left, right);
        left++;
        right--;
      }
    }
    int connectedComp = 0;
    for (int i = 0; i < n; i++) {
      connectedComp += (dsu.find(i) == i ? 1 : 0);
    }

    System.out.println(modPow(m, connectedComp, mod));
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
