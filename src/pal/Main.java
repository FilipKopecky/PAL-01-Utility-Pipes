package pal;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        // HashMap<Integer, ArrayList<Edge>> graph = new HashMap<>();
        HashMap<Integer,ArrayList<Node>> graph = new HashMap<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("datasets/pub08.in")));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        int numFarms = Integer.parseInt(tokenizer.nextToken());
        int numRoads = Integer.parseInt(tokenizer.nextToken());

        for (int i = 0; i < numFarms; i++) {
            graph.put(i,new ArrayList<>());
        }

        for (int i = 0; i < numRoads; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int source = Integer.parseInt(tokenizer.nextToken());
            int destination = Integer.parseInt(tokenizer.nextToken());
            int cost = Integer.parseInt(tokenizer.nextToken());
            graph.get(source).add(new Node(destination, cost));
            graph.get(destination).add(new Node(source, cost));
        }

        int numHubs = Integer.parseInt(reader.readLine());
        int[] hubs = new int[numHubs];
        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < numHubs; i++) {
            hubs[i] = Integer.parseInt(tokenizer.nextToken());
        }

     /**   HashMap<Integer, ArrayList<Integer>> prices = new HashMap<>();
        for (int i = 0; i < numFarms; i++) {
            prices.put(i, new ArrayList<>());
        }
        for (int i = 0; i < numHubs; i++) {
            int[] res = Dijkstra(graph, hubs[i], numFarms);
            for (int j = 0; j < numFarms; j++) {
                prices.get(j).add(res[j]);
            }
        }**/

        Alg alg = new Alg(numFarms, numHubs, hubs, graph);
        alg.getUndecided();
        System.out.println("DONE");

    }



}

