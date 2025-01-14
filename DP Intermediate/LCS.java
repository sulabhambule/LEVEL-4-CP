
import java.io.*;
import java.util.*;
// Longest common substring in String (S and T  with answer construction).

public class LCS {
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
    String s = in.next();
    String t = in.next();
    int n = s.length();
    int m = t.length();
    int[][] dp = new int[n + 1][m + 1];

    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= m; j++) {
        if (s.charAt(i - 1) == t.charAt(j - 1)) {
          dp[i][j] = 1 + dp[i - 1][j - 1];
        } else {
          dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        }
      }
    }

    int x = n, y = m;
    StringBuilder ans = new StringBuilder();
    while (x > 0 && y > 0) {
      if (s.charAt(x - 1) == t.charAt(y - 1)) {
        ans.append(s.charAt(x - 1));
        x--;
        y--;
      } else if (dp[x - 1][y] == dp[x][y]) {
        x--;
      } else if (dp[x][y - 1] == dp[x][y]) {
        y--;
      }
    }

    out.println(ans.reverse().toString());
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
