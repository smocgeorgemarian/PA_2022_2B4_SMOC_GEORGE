package com.bonus;

import java.util.Random;

public class RootedTree {
    RootedTree[] children;
    public RootedTree(int lvlLimit, int lvlAct, Random random) {
        if (lvlLimit == lvlAct)
        {
            children = new RootedTree[0]; // a leaf
            return;
        }
        int numberOfChildren = random.nextInt(5);
        children = new RootedTree[numberOfChildren];
        for (int i = 0 ; i < numberOfChildren; i++)
        {
            RootedTree child = new RootedTree(lvlLimit, lvlAct + 1, random); // create child for actual node
            children[i] = child;
        }
    }
    public int printTextualRepresentation(int index, int lvlAct)
    {
        String indent = "  ";
        if (children.length > 0)
            System.out.println(indent.repeat(lvlAct) + "+node" + index);
        else
            System.out.println(indent.repeat(lvlAct) + "-node" + index);

        for (RootedTree node : children)
        {
            index = node.printTextualRepresentation(index + 1, lvlAct + 1);
        }
        return index; // last index used to print nodes
    }
}
