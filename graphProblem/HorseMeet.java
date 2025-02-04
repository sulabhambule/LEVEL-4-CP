import java.util.*;

// cf problem link : https://codeforces.com/contest/2014/problem/E

public class HorseMeet {
  static final long INF = (long) 1e12;

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int t = in.nextInt();
    while (t-- > 0) {

      int n = in.nextInt();
      int m = in.nextInt();
      int h = in.nextInt();

      HashSet<Integer> horse = new HashSet<>();
      for (int i = 0; i < h; i++) {
        horse.add(in.nextInt());
      }

      List<List<int[]>> adj = new ArrayList<>();
      for (int i = 0; i <= n; i++) {
        adj.add(new ArrayList<>());
      }

      for (int i = 0; i < m; i++) {
        int u = in.nextInt();
        int v = in.nextInt();
        int w = in.nextInt();
        adj.get(u).add(new int[] { v, w });
        adj.get(v).add(new int[] { u, w });
      }

      long[][] dist1 = new long[n + 1][2];
      long[][] distn = new long[n + 1][2];

      dijkstra(1, dist1, adj, horse);
      dijkstra(n, distn, adj, horse);

      long ans = INF;
      for (int i = 1; i <= n; i++) {
        long bestTime = Math.max(Math.min(dist1[i][0], dist1[i][1]),
            Math.min(distn[i][0], distn[i][1]));
        ans = Math.min(ans, bestTime);
      }

      System.out.println(ans == INF ? -1 : ans);
    }
    in.close();
  }

  private static void dijkstra(int start, long[][] dist, List<List<int[]>> adj, HashSet<Integer> horse) {
    for (long[] row : dist)
      Arrays.fill(row, INF);
    dist[start][0] = 0;

    PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));
    pq.add(new long[] { 0, start, horse.contains(start) ? 1 : 0 });
    // {cost, node, hasHorse}

    while (!pq.isEmpty()) {
      long[] curr = pq.poll();
      long cost = curr[0];
      int node = (int) curr[1];
      int hasHorse = (int) curr[2];

      if (cost > dist[node][hasHorse])
        continue;

      for (int[] adJ : adj.get(node)) {
        int adjNode = adJ[0];
        int edgeWeight = adJ[1];

        long newCost = cost + (hasHorse == 1 ? edgeWeight / 2L : edgeWeight);

        int newHorse = hasHorse | (horse.contains(adjNode) ? 1 : 0);

        if (newCost < dist[adjNode][newHorse]) {
          dist[adjNode][newHorse] = newCost;
          pq.add(new long[] { newCost, adjNode, newHorse });
        }
      }
    }
  }
}
