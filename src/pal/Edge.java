package pal;

import java.util.Comparator;

public class Edge implements Comparator<Edge> {
    public int destination;
    public int cost;

    public Edge(int destination, int cost) {
        this.destination = destination;
        this.cost = cost;
    }

    @Override
    public int compare(Edge node1, Edge node2) {
        if (node1.cost < node2.cost)
            return -1;

        if (node1.cost > node2.cost)
            return 1;

        return 0;
    }
}
