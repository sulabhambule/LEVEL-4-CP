import java.util.*;

public class Main1 {

    static Scanner in = new Scanner(System.in);
    static int[] arr;
    static int[] sgt;

    public static void main(String[] args) {
        solve();
    }

    static void solve() {
        int n = in.nextInt();
        arr = new int[n];
        sgt = new int[4 * n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        build(0, n - 1, 1);
    }

    static void build(int s, int e, int index) {
        // leaf node
        if (s == e) {
            sgt[index] = arr[s];
            return;
        }
        int mid = (s + e) / 2;
        build(s, mid, 2 * index);
        build(mid + 1, e, 2 * index + 1);
        sgt[index] = sgt[2 * index] + sgt[2 * index + 1];
    }

    static void update(int s, int e, int index, int update_index, int update_value) {
        // Leaf Node
        if (s == e) {
            sgt[index] = update_value;
            return;
        }
        int mid = (s + e) / 2;
        if (mid >= update_index) {
            update(s, mid, 2 * index, update_index, update_value);
        } else {
            update(mid + 1, e, 2 * index + 1, update_index, update_value);
        }
        sgt[index] = sgt[2 * index] + sgt[2 * index + 1];
    }

    static int query(int s, int e, int index, int l, int r) {
        // no overlap
        if (s > r || e < l)
            return 0;
        // complete overlap
        if (s >= l && e <= r)
            return sgt[index];
        // partial overlap
        int mid = (s + e) / 2;
        int leftContribution = query(s, mid, 2 * index, l, r);
        int rightContribution = query(mid + 1, e, 2 * index + 1, l, r);
        return leftContribution + rightContribution;
    }
}
