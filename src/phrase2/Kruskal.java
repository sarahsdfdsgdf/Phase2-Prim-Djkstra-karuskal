/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phrase2;

import java.util.Arrays;

public class Kruskal {
    Graph graph;

    public Kruskal(Graph graph) {
        this.graph = graph;
    }
    
    
    // A class to represent a subset for
    // union-find
    class subset
    {
        int parent, rank;
    };
    
    // A utility function to find set of an
    // element i (uses path compression technique)
    int find(subset subsets[], int i)
    {
        if (subsets[i].parent != i)
            subsets[i].parent
                = find(subsets, subsets[i].parent);
 
        return subsets[i].parent;
    }
    // A function that does union of two sets
    // of x and y (uses union by rank)
    void Union(subset subsets[], int x, int y)
    {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);
 
        if (subsets[xroot].rank
            < subsets[yroot].rank)
            subsets[xroot].parent = yroot;
        else if (subsets[xroot].rank
                 > subsets[yroot].rank)
            subsets[yroot].parent = xroot;
        // If ranks are same, then make one as
        // root and increment its rank by one
        else {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }
    void excute()
    {
        Edge result[] = new Edge[graph.V];
       
        int e = 0;
        int i = 0;
        
        for (i = 0; i < graph.V; ++i)
            result[i] = new Edge();
 
        Arrays.sort(graph.edge);
        
        subset subsets[] = new subset[graph.V];
        for (i = 0; i < graph.V; ++i)
            subsets[i] = new subset();
 
        for (int v = 0; v < graph.V; ++v)
        {
            subsets[v].parent = v;
            subsets[v].rank = 0;
        }
 
        i = 0;
 
        while (e < graph.V - 1 && i < graph.E-1 )
        {
            Edge next_edge = new Edge();
            next_edge = graph.edge[i++];
 
            int x = find(subsets, next_edge.src);
            int y = find(subsets, next_edge.dest);
            if (x != y) {
                result[e++] = next_edge;
                Union(subsets, x, y);
            }
        }
        System.out.println("Kruskal’s Minimum Spanning Tree Algorithm : ");
                          
        long cost = 0;
        for (i = 0; i < e; ++i)
        {
            System.out.println(result[i].src + " -> "+ result[i].dest+ " = " + result[i].weight);
            cost += result[i].weight;
        }
        System.out.println("Minimum Cost Spanning Tree "+ cost);
    }
 
}
