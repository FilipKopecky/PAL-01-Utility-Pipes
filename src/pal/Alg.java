package pal;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Alg {
   private int numFarms;
    private int numHubs;
    private int[] hubs;
    private ArrayList<ArrayList<Node>> graph;

    public Alg(int numFarms, int numHubs,int [] hubs, ArrayList<ArrayList<Node>> graph){
        this.numFarms=numFarms;
        this.numHubs=numHubs;
        this.hubs=hubs;
        this.graph=graph;


    }

    public void getUndecided()
    {

        for (int i = 0; i < numHubs; i++) {
            Dijkstra(hubs[i]);
        }
    }

    public int[] Dijkstra(int source) {
        boolean[] visited = new boolean[numFarms];
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        int[] dist = new int[numFarms];
        for (int i = 0; i < numFarms; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[source] = 0;

        priorityQueue.offer(new Node(source, dist[source]));

        while (!priorityQueue.isEmpty()) {
            Node u = priorityQueue.poll();
            if (!visited[u.id]) {
                visited[u.id] = true;
               // u.setLowestDiscovered(dist[u.id]);
                for (Node neighbour : graph.get(u.id)) {
                    if (!visited[neighbour.id]) {
                        int alt = dist[u.id] + neighbour.cost;
                        if (alt < dist[neighbour.id]) {
                            dist[neighbour.id] = alt;
                            priorityQueue.offer(new Node(neighbour.id,alt));
                        }
                    }
                }
            }
        }
        return dist;
    }

}
