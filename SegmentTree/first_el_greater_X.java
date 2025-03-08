import java.io.*;
import java.util.*;

public class first_el_greater_X {
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static FASTIO in = new FASTIO();
  static final int MOD = 998244353;

  public static void main(String[] Hi) throws IOException {
    int cp = 1;
    while (cp-- > 0) {
      solve();
    }
    out.close();
  }

  // find the 1st element > x in the range

  static void solve() {
    int n = in.nextInt();
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = in.nextInt();
    }
    SegTree tree = new SegTree(n, arr);

    int q = in.nextInt();
    while (q-- > 0) {
      int type = in.nextInt();
      if (type == 1) {
        int ind = in.nextInt();
        int x = in.nextInt();
        tree.makeUpdate(ind, x);
      } else {
        int l = in.nextInt();
        int r = in.nextInt();
        int x = in.nextInt();
        // find out first i such that a[i] > x int the range from l -> r.
        int low = l, high = r;
        int ans = -1;
        while (low <= high) {
          int mid = (low + high) / 2;
          if (tree.makeQuery(low, mid).val > x) {
            ans = mid;
            high = mid - 1;
          } else {
            low = mid + 1;
          }
        }
        if (ans == -1) {
          out.println(-1);
        } else {
          out.println(ans);
        }
      }
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
}

class SegTree {
  private Node[] tree;
  private int[] arr;
  private int n;
  private int s;

  public SegTree(int a_len, int[] a) {
    this.arr = a;
    this.n = a_len;
    this.s = 1;
    while (s < 2 * n) {
      s = s << 1;
    }
    tree = new Node[s];
    Arrays.fill(tree, new Node());
    build(0, n - 1, 1);
  }

  private void build(int start, int end, int index) {
    if (start == end) {
      tree[index] = new Node(arr[start]);
      return;
    }
    int mid = (start + end) / 2;
    build(start, mid, 2 * index);
    build(mid + 1, end, 2 * index + 1);
    tree[index] = new Node();
    tree[index].merge(tree[2 * index], tree[2 * index + 1]);
  }

  private void update(int start, int end, int index, int queryIndex, Update u) {
    if (start == end) {
      u.apply(tree[index]);
      return;
    }
    int mid = (start + end) / 2;
    if (mid >= queryIndex) {
      update(start, mid, 2 * index, queryIndex, u);
    } else {
      update(mid + 1, end, 2 * index + 1, queryIndex, u);
    }
    tree[index].merge(tree[2 * index], tree[2 * index + 1]);
  }

  private Node query(int start, int end, int index, int left, int right) {
    if (start > right || end < left) {
      return new Node();
    }
    if (start >= left && end <= right) {
      return tree[index];
    }
    int mid = (start + end) / 2;
    Node l = query(start, mid, 2 * index, left, right);
    Node r = query(mid + 1, end, 2 * index + 1, left, right);
    Node ans = new Node();
    ans.merge(l, r);
    return ans;
  }

  public void makeUpdate(int index, int x) {
    Update newUpdate = new Update(x);
    update(0, n - 1, 1, index, newUpdate);
  }

  public Node makeQuery(int left, int right) {
    return query(0, n - 1, 1, left, right);
  }
}

class Node {
  int val;

  public Node() {
    this.val = (int) 1e9;
  }

  public Node(int p1) {
    val = p1;
  }

  public void merge(Node l, Node r) {
    val = Math.max(l.val, r.val);
  }
}

class Update {
  int x;

  public Update(int x1) {
    this.x = x1;
  }

  public void apply(Node a) {
    a.val = x;
  }
}
