package pal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Alg {
    private int numFarms;
    private int numHubs;
    private int[] hubs;
    private HashMap<Integer, ArrayList<Node>> graph;
    private Node[] nodes;

    public Alg(int numFarms, int numHubs, int[] hubs, HashMap<Integer, ArrayList<Node>> graph) {
        this.numFarms = numFarms;
        this.numHubs = numHubs;
        this.hubs = hubs;
        this.graph = graph;
        this.nodes = new Node[numFarms];
        for (int i = 0; i < numFarms; i++) {
            nodes[i] = new Node(i, Integer.MAX_VALUE);
        }


    }

    public final void getUndecided() {
        long start = System.nanoTime();
        for (int i = 0; i < numHubs; i++) {
            nodes[hubs[i]].cost = 0;
        }
        for (int i = 0; i < numHubs; i++) {
            Dijkstra(hubs[i]);
        }

        int undecided=0;
        for (int i = 0; i < numFarms; i++) {
            if (nodes[i].undecided || (nodes[i].predecessor != null && nodes[i].predecessor.undecided)) {
               nodes[i].undecided=true;
                undecided++;
            }
        }
        System.out.println("Undecided: "+undecided);
        long finish = System.nanoTime();
        long timeElapsed = finish - start;
        System.out.println(timeElapsed / 1000000);
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
                    nodes[neighborIndex].hub=nodes[source];
                    priorityQueue.offer(nodes[neighborIndex]);
                } else if (alt == nodes[neighborIndex].cost) {
                    nodes[neighborIndex].undecided = true;
                }


            }
        }
    }

    public final void removeEdges()
    {
        

    }

}


