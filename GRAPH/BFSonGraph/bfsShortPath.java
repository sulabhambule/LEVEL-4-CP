import java.util.*;

// problem link : https://www.spoj.com/problems/ADACYCLE/
public class bfsShortPath {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int N = in.nextInt();
    int[][] graph = new int[N][N];

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        graph[i][j] = in.nextInt();
      }
    }

    for (int start = 0; start < N; start++) {
      int shortestCycle = Integer.MAX_VALUE;
      Queue<Integer> queue = new LinkedList<>();
      int[] dist = new int[N];
      Arrays.fill(dist, -1);

      queue.add(start);
      dist[start] = 0;

      while (!queue.isEmpty()) {
        int node = queue.poll();
        for (int next = 0; next < N; next++) {
          if (graph[node][next] == 1) {
            if (dist[next] == -1) {
              dist[next] = dist[node] + 1;
              queue.add(next);
            } else if (next == start) {
              shortestCycle = Math.min(shortestCycle, dist[node] + 1);
            }
          }
        }
      }

      if (shortestCycle == Integer.MAX_VALUE) {
        System.out.println("NO WAY");
      } else {
        System.out.println(shortestCycle);
      }
    }
  }
}
