package com.optional;

public class PartialTree extends Graph
// inherited class from Graph which creates partial tree from the component
{

    int lg; // size of the graph for which we need to find a partial tree
    private void dfs(int node, boolean[][] last_graph)
    {
        uz[node] = true;
        for (int i = 0 ; i < lg; i++) {
            if (!uz[i])
            {
                g[node][i] = g[i][node] = true;
                dfs(i, last_graph);
            }
        }
    }
    public PartialTree(boolean[][] last_graph) {
        lg = last_graph.length;
        uz = new boolean[lg];
        g = new boolean[lg][lg];
        dfs(0, last_graph);
    }
}
