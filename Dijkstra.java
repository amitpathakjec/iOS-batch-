package Graphs.BFS;
import java.util.*;

public class Dijkstra {
    // custom class for edge
    static class Edge {
        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    // class for pq comparator:
    static class Pair implements Comparable<Pair> {
        int node;
        int dist;

        public Pair(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }

        @Override
        public int compareTo(Pair o) {
            return Integer.compare(this.dist, o.dist);
        }
    }

    // result holder to return distances + parent pointers
    static class DijkstraResult {
        int[] dist;
        int[] parent;
        DijkstraResult(int[] dist, int[] parent) {
            this.dist = dist;
            this.parent = parent;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // num(nodes)
        int m = sc.nextInt(); // num(edges)

        // Use 1..n indexing
        List<Edge>[] graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();

            graph[u].add(new Edge(v, w));
            graph[v].add(new Edge(u, w));
        }

        int src = sc.nextInt();
        int dest = sc.nextInt();
        sc.close();

        DijkstraResult res = dijkstra(src, graph, n);

        // Print distances (1..n). unreachable -> print -1
        System.out.println("Distances from source " + src + ":");
        for (int i = 1; i <= n; i++) {
            if (res.dist[i] == Integer.MAX_VALUE) System.out.println(i + " -> " + -1);
            else System.out.println(i + " -> " + res.dist[i]);
        }

        // Reconstruct path src -> dest
        if (res.dist[dest] == Integer.MAX_VALUE) {
            System.out.println("No path from " + src + " to " + dest);
        } else {
            List<Integer> path = new ArrayList<>();
            for (int cur = dest; cur != -1; cur = res.parent[cur]) path.add(cur);
            Collections.reverse(path);
            System.out.println("Shortest path from " + src + " to " + dest + ": " + path);
            System.out.println("Total cost: " + res.dist[dest]);
        }
    }

    static DijkstraResult dijkstra(int src, List<Edge>[] g, int n) {
        final int INF = Integer.MAX_VALUE;
        int[] dist = new int[n + 1];
        int[] parent = new int[n + 1];
        Arrays.fill(dist, INF);
        Arrays.fill(parent, -1);

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        boolean[] vis = new boolean[n + 1];

        dist[src] = 0;
        pq.add(new Pair(src, 0));

        while (!pq.isEmpty()) {
            Pair p = pq.poll();
            int u = p.node;
            if (vis[u]) continue;
            vis[u] = true;

            for (Edge e : g[u]) {
                int v = e.to;
                int w = e.weight;
                // avoid overflow when dist[u] == INF
                if (dist[u] != INF && dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    parent[v] = u;
                    pq.add(new Pair(v, dist[v]));
                }
            }
        }

        return new DijkstraResult(dist, parent);
    }
}
