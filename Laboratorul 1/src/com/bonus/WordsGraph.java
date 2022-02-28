package com.bonus;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Random;

public class WordsGraph {
    private final String[] args;
    private String[] generatedArray;
    private String[] letters;
    private int[][] adjacencyList;
    private HashSet[] frequency;
    private List<Integer> maximumList;
    private final Random random;
    private int[] before;
    private int n;
    private int p;
    private int lastNode;
    private boolean[] uz;
    private boolean noPrintRequired;

    public WordsGraph(String[] args) {
        this.args = args;
        this.random = new Random();
    }

    public void validateArguments() {
        if (args.length < 3) {
            System.err.println("Too few arguments");
            System.exit(1);
        }
        try {
            n = Integer.parseInt(args[0]);
            p = Integer.parseInt(args[1]);
            if (n < 0 || p < 0) {
                System.err.println("n and p values must be both > 0");
                System.exit(2);
            }
            for (int i = 2; i < args.length; i++)
                if (args[i].length() > 1) {

                    System.err.println("Argument must be a single character");
                    System.exit(3);
                }
            letters = new String[args.length - 2];
            System.arraycopy(args, 2, letters, 0, letters.length);
        }
        catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void generateArray() {
        generatedArray = new String[n];
        System.out.print("Generated words: ");
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < p; j++) {
                int randomIndexLetter = random.nextInt(letters.length);
                sb.append(letters[randomIndexLetter]);
            }
            generatedArray[i] = sb.toString();
            System.out.print(generatedArray[i] + " ");
        }
        System.out.println();
    }

    private boolean areNeighbours(int i, int j) {
        for (Object it: frequency[i])
            if (frequency[j].contains(it)) {
                return true;
            }
        return false;
    }

    private void createSetArray() {
        frequency = new HashSet[n];
        for (int i = 0; i < n; i++) {
            frequency[i] = new HashSet<>();
            for (int j = 0; j < p; j++) {
                String substr = generatedArray[i].substring(j, j + 1);
                frequency[i].add(substr);
            }
        }
    }

    private boolean[] getIsDuplicate() {
        boolean[] isDuplicate = new boolean[n];
        for (int i = 0; i < n - 1; i++)
            if (!isDuplicate[i]) {
                for (int j = i + 1; j < n; j++) {
                    if (generatedArray[i].equals(generatedArray[j]))
                        isDuplicate[j] = true;
                }
            }
        return isDuplicate;
    }

    private int getNeighboursNumber(int node, boolean[] isDuplicate) {
        int neighoursNumber = 0;
        for (int j = 0; j < n; j++)
            if (!isDuplicate[j] && node != j && areNeighbours(node, j))
                neighoursNumber++;
        return neighoursNumber;
    }

    public void createAdjacencyList() {
        boolean[] isDuplicate = getIsDuplicate();
        adjacencyList = new int[n][];
        createSetArray();
        for (int i = 0; i < n; i++)
            if (!isDuplicate[i]) {
                int cate = getNeighboursNumber(i, isDuplicate);
                adjacencyList[i] = new int[cate];
                cate = 0;
                for (int j = 0; j < n; j++)
                    if (!isDuplicate[j] && i != j && areNeighbours(i, j))
                        adjacencyList[i][cate++] = j;
            }
            else
                adjacencyList[i] = new int[0];
    }

    public void prettyPrintAdjacencyList() {
        System.out.println("Adjacency List");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < adjacencyList[i].length; j++) {
                System.out.print(adjacencyList[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void processNode(int node) {
        if (node == lastNode) {
            System.out.println();
            this.noPrintRequired = true;
        }
        if (!noPrintRequired)
            System.out.print(generatedArray[node] + " ");
    }

    private boolean dfs(int node, int father) {
        uz[node] = true;
        for (int ngb: adjacencyList[node]) {
            if (uz[ngb] && ngb != father) { // found circuit
                lastNode = ngb;
                System.out.print("Component found: " + generatedArray[ngb] + " " + generatedArray[node] + " ");
                return true;
            }
            else if (!uz[ngb]) { // look for circuit
                boolean verdict = dfs(ngb, node);
                if (verdict) {
                    processNode(node);
                    return true;
                }
            }
        }
        return false;
    }

    private void updateMaximum(int node, int ngb) {
        List<Integer> actualList = new ArrayList<>();
        while (true) {
            if (node == ngb || node == -1) {
                if (node != -1)
                    actualList.add(ngb);
                break;
            }
            actualList.add(node);
            node = this.before[node];
        }
        if (actualList.size() > this.maximumList.size())
            this.maximumList = actualList;
    }

    private void dfsMaximum(int node, int father) {
        uz[node] = true;
        before[node] = father;
        for (int ngb: adjacencyList[node]) {
            if (uz[ngb] && ngb != father)
                updateMaximum(node, ngb);
            else
            if (!uz[ngb])
                dfsMaximum(ngb, node);
        }
    }

    public void printFirstSubset() {
        this.uz = new boolean[n];
        boolean verdict = false;
        for (int i = 0; i < n; i++) {
            this.noPrintRequired = false;
            this.lastNode = -1;
            verdict = dfs(i, -1);
            if (verdict)
                return;
        }
        if (!verdict)
            System.out.println("No component found");
    }

    public void printMaximumSubset() {
        this.uz = new boolean[n];
        this.before = new int[n];
        this.maximumList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            dfsMaximum(i, -1);
        }
        System.out.print("Maximum list: ");
        if (!maximumList.isEmpty())
            for (Integer it: this.maximumList)
                System.out.print(generatedArray[it] + " ");
        else
            System.out.println("No maximum component found");
    }
}
