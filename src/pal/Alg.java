package pal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Alg {
    private int numFarms;
    private int numHubs;
    private int[] hubs;
    private HashMap<Integer, ArrayList<Node>> graph;
    private HashMap<Integer, ArrayList<Node>> graphWithout;
    private Node[] nodes;
    public int undecided = 0;

    public Alg(int numFarms, int numHubs, int[] hubs, HashMap<Integer, ArrayList<Node>> graph) {
        this.numFarms = numFarms;
        this.numHubs = numHubs;
        this.hubs = hubs;
        this.graph = graph;
        graphWithout = new HashMap<>();
        this.nodes = new Node[numFarms];
        for (int i = 0; i < numFarms; i++) {
            nodes[i] = new Node(i, Integer.MAX_VALUE);
        }


    }

    public final void getUndecided() {

        for (int i = 0; i < numHubs; i++) {
            nodes[hubs[i]].cost = 0;
            nodes[hubs[i]].hub = nodes[hubs[i]];
        }
        if(numFarms<=1000){
            for (int i = 0; i < numHubs; i++) {
                Dijkstra(hubs[i]);
            }
        }
        else
        {
            for (int i = 0; i < numHubs; i++) {
                Dijkstra2(hubs[i]);
            }
        }



        for (int i = 0; i < numFarms; i++) {
            if (nodes[i].undecided || (nodes[i].predecessor != null && nodes[i].predecessor.undecided)) {
                nodes[i].undecided = true;
                undecided++;
            }
        }
      //  System.out.println("Undecided: " + undecided);

    }

    public final void Dijkstra(int source) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        priorityQueue.offer(nodes[source]);

        int index;
        int neighborIndex;
        while (!priorityQueue.isEmpty()) {
            Node u = priorityQueue.poll();
            index = u.id;
            for (Node neighbour : graph.get(index)) {
                neighborIndex = neighbour.id;

                int alt = u.cost + neighbour.cost;

                if (alt < nodes[neighborIndex].cost) {
                    nodes[neighborIndex].cost = alt;
                    nodes[neighborIndex].undecided = false;
                    nodes[neighborIndex].predecessor = u;
                    nodes[neighborIndex].hub = nodes[source];
                    priorityQueue.offer(nodes[neighborIndex]);
                } else if (alt == nodes[neighborIndex].cost && nodes[neighborIndex].hub!=u.hub) {
                    nodes[neighborIndex].undecided = true;
                }


            }
        }
    }

    public final void Dijkstra2(int source) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        priorityQueue.offer(nodes[source]);

        int index;
        int neighborIndex;
        while (!priorityQueue.isEmpty()) {
            Node u = priorityQueue.poll();
            index = u.id;
            for (Node neighbour : graph.get(index)) {
                neighborIndex = neighbour.id;

                int alt = u.cost + neighbour.cost;

                if (alt < nodes[neighborIndex].cost) {
                    nodes[neighborIndex].cost = alt;
                    nodes[neighborIndex].undecided = false;
                    nodes[neighborIndex].predecessor = u;
                    nodes[neighborIndex].hub = nodes[source];
                    priorityQueue.offer(nodes[neighborIndex]);
                } else if (alt == nodes[neighborIndex].cost) {
                    nodes[neighborIndex].undecided = true;
                }


            }
        }
    }

    public final void removeEdges() {
        for (int i = 0; i < numFarms; i++) {
            ArrayList<Node> edges = new ArrayList<>();
            if (nodes[i].undecided) {
                graphWithout.put(i, edges);
                continue;
            }
            for (Node neighbor : graph.get(i)) {
                if (nodes[neighbor.id].undecided)
                    continue;
                if (nodes[i].hub == nodes[neighbor.id].hub)
                    edges.add(neighbor);
            }
            graphWithout.put(i, edges);
        }

    }

    public final int prim(int source) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        boolean[] visited = new boolean[numFarms];
        int minCost = 0;
        priorityQueue.offer(nodes[source]);
        while (!priorityQueue.isEmpty()) {
            Node u = priorityQueue.poll();
            if(visited[u.id]) continue;
            minCost+=u.cost;
            visited[u.id]=true;
            for (Node n:graphWithout.get(u.id)) {
                if(!visited[n.id]) priorityQueue.offer(n);
            }
        }
        return minCost;

    }

}


