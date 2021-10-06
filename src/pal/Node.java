package pal;

import java.util.Comparator;

class Node implements Comparable<Node> {

    // Member variables of this class
    public int id;
    public int cost;
    public int hub;


    // Constructors of this class

    // Constructor 1
    public Node() {
    }

    // Constructor 2
    public Node(int node, int cost) {

        // This keyword refers to current instance itself
        this.id = node;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {
        if (this.cost < o.cost)
            return -1;

        if (this.cost > o.cost)
            return 1;

        return 0;
    }

    @Override
    public int hashCode() {
        return id;
    }

    /**public void setLowestDiscovered(int lowestDiscovered) {
        if(this.lowestDiscovered==lowestDiscovered)
        {
            this.undecided=true;
        }

        if (this.lowestDiscovered > lowestDiscovered) {
            this.lowestDiscovered = lowestDiscovered;
            this.undecided=false;
        }

    }**/
}
