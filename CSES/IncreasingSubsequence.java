import java.io.*;
import java.util.*;

public class IncreasingSubsequence {
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static FastReader in = new FastReader();
  private static final int MOD = (int) 1e9 + 7;

  public static void main(String[] args) throws Exception {
    int T = 1;
    while (T-- > 0) {
      TLAM();
    }
    out.close();
  }

  private static void TLAM() {
    int n = in.nextInt();
    long[] arr = new long[n];
    for (int i = 0; i < n; i++) {
      arr[i] = in.nextLong();
    }
    List<Long> dp = new ArrayList<>();

    for (long x : arr) {
      // Find the position to replace or extend
      int pos = Collections.binarySearch(dp, x);
      if (pos < 0)
        pos = -(pos + 1); // If not found, get insertion point

      // If pos is within dp, replace the element
      if (pos < dp.size()) {
        dp.set(pos, x);
      } else {
        // Else, extend the subsequence
        dp.add(x);
      }
    }

    // The length of dp is the length of the LIS
    out.println(dp.size());
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
