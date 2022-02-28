package com.optional;

import java.util.ArrayList;
import java.util.Random;

public class Graph {
    boolean[] uz; // uz[node] = true iff node has been visited via dfs
    boolean[][] g; // g[node_1][node_2] = g[node_2][node_1] = true iff node_1 and node_2 are connected in graph G
    ArrayList<ArrayList<Integer>> comps = new ArrayList<>(); // connected components of the grapf, dynamically built

    public Graph() {
    } // empty constructor needed for inheritance

    public Graph(int size)
    /* constructor with int parameter needed for creating adjacency matrix of size "size"
       with random values of {0, 1}
    */ {
        g = new boolean[size][size];
        uz = new boolean[g.length];
        Random rand = new Random();
        for (int i = 1; i < size; i++)
            for (int j = 0; j < i; j++) {
                g[i][j] = g[j][i] = (rand.nextInt(2) == 1);
            }
    }

    public void dfs(int node, ArrayList<Integer> arrayList)
    // node = node of which neighbours are to be explored
    // arrayList = actual connected component (c.c.)
    {
        uz[node] = true; // set node as visited
        arrayList.add(node + 1); // add it to the actual c.c.
        for (int i = 0; i < g.length; i++) {
            if (!uz[i] && g[node][i]) { // there exists an edge between verticles node and i
                dfs(i, arrayList); // explore node i
            }
        }
    }

    public void checkConnectivity() {
        for (int i = 0; i < g.length; i++) {
            if (!uz[i]) {
                ArrayList<Integer> arrayList = new ArrayList<>();
                dfs(i, arrayList);
                comps.add(arrayList);
            }
        }

        System.out.println("There is(are) " + comps.size() + " component(s)");
        if (comps.size() > 1) {
            int compsIndex = 0;
            for (ArrayList<Integer> it : comps) {
                compsIndex++;
                System.out.println("Component with index: " + compsIndex);
                for (Integer i : it) {
                    System.out.print(i + " ");
                }
                System.out.println();

            }
        }
    }

    public void prettyPrintGraph() {
        for (boolean[] line : g) {
            for (boolean cell : line) {
                System.out.print((cell ? 1 : 0) + " ");
            }
            System.out.println();
        }
    }

    public boolean[][] getMatrix() {
        int lg = g.length;
        boolean [][] cpy_g = new boolean[lg][lg];
        for(int i = 0; i < lg; i++)
            System.arraycopy(g[i], 0, cpy_g[i], 0, lg);

        return g;
    }
    public static int validateInteger(String[] args)
    {
        // start validation
        int argument = -1;
        try
        {
            argument = Integer.parseInt(args[0]);
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        if (argument <= 0)
        {
            System.err.println("Matrix cannot have size <= 0");
            System.exit(2);
        }
        return argument;
    }
}

