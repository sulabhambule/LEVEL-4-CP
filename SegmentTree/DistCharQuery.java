
import java.io.*;
import java.util.*;

/*codeforces quetion link : https://codeforces.com/problemset/problem/1234/D */

public class DistCharQuery {
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

  static void solve() {
    String s = in.next();
    char[] arr = s.toCharArray();
    int n = s.length();
    SegTree tree = new SegTree(n, arr);

    int q = in.nextInt();
    while (q-- > 0) {
      int type = in.nextInt();
      if (type == 1) {
        int pos = in.nextInt() - 1;
        char c = in.next().charAt(0);
        tree.makeUpdate(pos, c);
      } else {
        int l = in.nextInt() - 1;
        int r = in.nextInt() - 1;
        Node res = tree.makeQuery(l, r);
        int distinctCount = 0;
        for (int i = 0; i < 26; i++) {
          if (res.freq[i] > 0)
            distinctCount++;
        }
        out.println(distinctCount);
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
  private char[] arr;
  private int n;
  private int s;

  public SegTree(int a_len, char[] a) {
    this.arr = a;
    this.n = a_len;
    this.s = 1;
    while (s < 2 * n) {
      s = s << 1;
    }
    tree = new Node[s];
    for (int i = 0; i < s; i++) {
      tree[i] = new Node();
    }
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

  public void makeUpdate(int index, char newChar) {
    char oldChar = arr[index];
    if (oldChar == newChar) {
      return;
    }
    arr[index] = newChar;
    Update newUpdate = new Update(newChar, oldChar);
    update(0, n - 1, 1, index, newUpdate);
  }

  public Node makeQuery(int left, int right) {
    return query(0, n - 1, 1, left, right);
  }
}

class Node {
  int[] freq = new int[26];

  public Node() {
    Arrays.fill(freq, 0);
  }

  public Node(char c) {
    freq[c - 'a']++;
  }

  public void merge(Node left, Node right) {
    for (int i = 0; i < 26; i++) {
      freq[i] = left.freq[i] + right.freq[i];
    }
  }
}

class Update {
  char newChar;
  char oldChar;

  public Update(char newChar, char oldChar) {
    this.newChar = newChar;
    this.oldChar = oldChar;
  }

  public void apply(Node a) {
    a.freq[oldChar - 'a']--;
    a.freq[newChar - 'a']++;
  }
}
