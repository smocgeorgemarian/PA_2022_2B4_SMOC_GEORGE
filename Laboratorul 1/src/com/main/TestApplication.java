package com.main;

import com.compulsory.Compulsory;
import com.optional.Graph;
import com.optional.PartialTree;
import com.bonus.WordsGraph;

public class TestApplication {
    public static void main(String[] args) {
        final int MAX_PRINT_SIZE = 30;
        long start = System.currentTimeMillis(); // time representing the start of the App
        Compulsory.solve(); // no need for a class instance for these tasks

        int size = Graph.validateInteger(args); // validating parameters of App
        Graph graph = new Graph(size); // constructor of Graph class
        if (size <= MAX_PRINT_SIZE)
            graph.prettyPrintGraph(); // print graph
        graph.checkConnectivity(); // check number of components via dfs
        PartialTree partialTree = new PartialTree(graph.getMatrix()); // create an instance of an inherited class
        if (size <= MAX_PRINT_SIZE) // if it is easily readable
            partialTree.prettyPrintGraph(); // print Tree

        WordsGraph wordsGraph = new WordsGraph(args);
        wordsGraph.validateArguments();
        wordsGraph.generateArray();
        wordsGraph.createAdjacencyList();
        wordsGraph.prettyPrintAdjacencyList();
        wordsGraph.printFirstSubset();
        wordsGraph.printMaximumSubset();

        long end = System.currentTimeMillis(); // time representing the end of the Application
        if (size > MAX_PRINT_SIZE) // if the size has a high value print the time the Application ran
            System.out.println("Time elapsed (in ms): " + (end - start));
    }
}
