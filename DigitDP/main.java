
import java.io.*;
import java.util.*;

public class main {
  public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
  static FastReader in = new FastReader();
  // private static final int MOD = (int) 1e9 + 7;
  private static final int MOD = 998244353;

  public static void main(String[] args) {
    int T = 1;
    while (T-- > 0) {
      solve();
    }
    out.close();
  }

  private static int fun(int index, int numberMade) {
    // code to print the number from 0 --> 99999
    if (index == 5) {
      System.out.println(numberMade);
      return 1;
    }

    int numbers = 0;

    for (int i = 0; i <= 9; i++) {
      numbers += fun(index + 1, numberMade * 10 + i);
    }

    return numbers;
  }

  private static void solve() {
    System.out.println(fun(0, 0));
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
