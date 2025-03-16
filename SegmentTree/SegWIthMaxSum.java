
import java.util.*;

public class SegWIthMaxSum {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int m = in.nextInt();
    long[] arr = new long[n];
    for (int i = 0; i < n; i++) {
      arr[i] = in.nextLong();
    }
    SegTree segTree = new SegTree(n, arr);
    System.out.println(Math.max(0, segTree.makeQuery(0, n - 1).maxSubarray));
    for (int i = 0; i < m; i++) {
      int index = in.nextInt();
      long value = in.nextLong();
      segTree.makeUpdate(index, value);
      System.out.println(Math.max(0, segTree.makeQuery(0, n - 1).maxSubarray));
    }
    in.close();
  }
}

class SegTree {
  private Node[] tree;
  private long[] arr;
  private int n;
  private int s;

  public SegTree(int a_len, long[] a) {
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

  public void makeUpdate(int index, long val) {
    Update newUpdate = new Update(val);
    update(0, n - 1, 1, index, newUpdate);
  }

  public Node makeQuery(int left, int right) {
    return query(0, n - 1, 1, left, right);
  }
}

class Node {
  long sum, prefix, suffix, maxSubarray;

  Node(long value) {
    sum = prefix = suffix = maxSubarray = value;
  }

  Node() {
    sum = prefix = suffix = maxSubarray = 0;
  }

  public void merge(Node left, Node right) {
    sum = left.sum + right.sum;
    prefix = Math.max(left.prefix, left.sum + right.prefix);
    suffix = Math.max(right.suffix, right.sum + left.suffix);
    maxSubarray = Math.max(Math.max(left.maxSubarray, right.maxSubarray), left.suffix + right.prefix);
  }
}

class Update {
  long val;

  public Update(long p1) {
    this.val = p1;
  }

  public void apply(Node a) {
    a.sum = a.prefix = a.suffix = a.maxSubarray = val;
  }
}
