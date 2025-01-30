import java.util.*;

public class Main {
    static int INF = (int) 1e9;
    static int n, m;
    static List<int[]> edges = new ArrayList<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        n = in.nextInt();
        m = in.nextInt();

        for (int i = 0; i < m; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            int w = in.nextInt();
            edges.add(new int[] { u, v, w });
        }

        int source = in.nextInt(); // Source node
        int[] dist = bellmanFord(source);

        if (dist == null) {
            System.out.println("Negative cycle detected!");
        } else {
            for (int i = 0; i < n; i++) {
                System.out.println("Distance from " + source + " to " + i + ": " + (dist[i] == INF ? "INF" : dist[i]));
            }
        }
    }

    private static int[] bellmanFord(int source) {
        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        dist[source] = 0;

        // Relax all edges n - 1 times
        for (int i = 0; i < n - 1; i++) {
            for (int[] e : edges) {
                int u = e[0], v = e[1], w = e[2];
                if (dist[u] != INF && dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                }
            }
        }

        // Negative cycle detection
        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            if (dist[u] != INF && dist[u] + w < dist[v]) {
                return null; // Negative cycle detected
            }
        }

        return dist;
    }
}
