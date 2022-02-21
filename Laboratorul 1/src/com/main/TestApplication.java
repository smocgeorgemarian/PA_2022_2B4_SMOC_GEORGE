package com.main;

import com.compulsory.Compulsory;
import com.optional.Graph;
import com.bonus.RootedTree;
import com.optional.PartialTree;

import java.util.Random;

public class TestApplication {
    public static void main(String[] args) {
        final int MAX_PRINT_SIZE = 30;
        final int LVL_LIMIT = 3;
        final int LVL_ACT = 0;
        final int START_INDEX = 0;
        Random random = new Random();
        long start = System.currentTimeMillis(); // time representing the start of the App
        Compulsory.solve(); // no need for a class instance for these tasks

        int size = Graph.validateInteger(args); // validating parameters of App
        Graph graph = new Graph(size); // constructor of Graph class
        if (size <= MAX_PRINT_SIZE)
            graph.prettyPrintGraph(); // print graph
        graph.checkConnectivity(); // check number of components via dfs
        PartialTree partialTree = new PartialTree(graph.getMatrix()); // create an instance of an inherited class
        if (size <= MAX_PRINT_SIZE)  // if it is easily readable
            partialTree.prettyPrintGraph(); // print Tree

        RootedTree rootedTree = new RootedTree(LVL_LIMIT, LVL_ACT, random);
        /*
         create a rooted tree with maximum LVL_LIMIT levels, levels indexed from LVL_ACT value, each node generates
         a random number of children using instance random of the class Random
        */
        rootedTree.printTextualRepresentation(START_INDEX, LVL_ACT);
        /*
         print the representation with nodes indexed from START_INDEX, levels indexed from LVL_ACT index
        */

        long end = System.currentTimeMillis(); // time representing the end of the Application
        if (size > MAX_PRINT_SIZE) // if the size has a high value print the time the Application ran
            System.out.println("Time elapsed (in ms): " + (end - start));
    }
}
