package pal;

import java.util.Comparator;

class Node implements Comparable<Node> {

    // Member variables of this class
    public int id;
    public int cost;
    public Node predecessor;
    public boolean undecided;
    public Node hub;


    // Constructors of this class

    // Constructor 1
    public Node() {
    }

    // Constructor 2
    public Node(int node, int cost) {

        // This keyword refers to current instance itself
        this.id = node;
        this.cost = cost;
        this.undecided = false;
        this.predecessor=null;
        this.hub=null;
    }

    @Override
    public int compareTo(Node o) {
       return this.cost-o.cost;
    }
}
