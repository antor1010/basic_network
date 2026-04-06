import java.util.*;

class Dijkstra {
    static final int V = 5;

    int minDistance(int dist[], boolean visited[]) {
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int i = 0; i < V; i++) {
            if (!visited[i] && dist[i] <= min) {
                min = dist[i];
                min_index = i;
            }
        }
        return min_index;
    }

    void printPath(int parent[], int j) {
        if (parent[j] == -1)
            return;

        printPath(parent, parent[j]);
        System.out.print(j + " ");
    }

    void dijkstra(int graph[][], int src) {
        int dist[] = new int[V];
        boolean visited[] = new boolean[V];
        int parent[] = new int[V];

        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
            visited[i] = false;
            parent[i] = -1;
        }

        dist[src] = 0;

        for (int count = 0; count < V - 1; count++) {
            int u = minDistance(dist, visited);
            visited[u] = true;

            for (int v = 0; v < V; v++) {
                if (!visited[v] && graph[u][v] != 0 &&
                    dist[u] != Integer.MAX_VALUE &&
                    dist[u] + graph[u][v] < dist[v]) {

                    dist[v] = dist[u] + graph[u][v];
                    parent[v] = u;   // 🔥 path store
                }
            }
        }

        // print result
        System.out.println("Shortest Paths from Source " + src + ":");
        for (int i = 0; i < V; i++) {
            System.out.print("To " + i + " Path: " + src + " ");
            printPath(parent, i);
            System.out.println(" | Distance: " + dist[i]);
        }
    }

    public static void main(String[] args) {
        int graph[][] = {
            {0, 10, 0, 5, 0},
            {0, 0, 1, 2, 0},
            {0, 0, 0, 0, 4},
            {0, 3, 9, 0, 2},
            {7, 0, 6, 0, 0}
        };

        Dijkstra d = new Dijkstra();
        d.dijkstra(graph, 0);
    }
}
