/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phrase2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;

public class PrimsMinHeap {
    Graph graph;
    ArrayList<Edge>[] vis;

    public PrimsMinHeap(Graph graph) {
        this.graph = graph;
    }
    
    class node {
        int vertex;
        int key;
    }
    class comparator implements Comparator<node> {
  
        @Override
        public int compare(node node0, node node1)
        {
            return node0.key - node1.key;
        }
    }
    void excute()
    {
        
        vis = new ArrayList[graph.E];
        
        for (int i = 0; i < graph.E; i++)
            vis[i]= new ArrayList<Edge>();
        
        for (int i = 0; i < graph.E; i++) {
            vis[graph.edge[i].src].add(graph.edge[i]);
            Edge v = new Edge();
            v.src = graph.edge[i].dest;
            v.weight = graph.edge[i].weight;
            v.dest = graph.edge[i].src;
            vis[graph.edge[i].dest].add(v);
        }
        
        Boolean[] mstset = new Boolean[graph.V];
        node[] e = new node[graph.V];
  
        int[] parent = new int[graph.V];
  
        for (int i = 0; i < graph.V; i++)
            e[i] = new node();
        
        // Initialize all keys as INFINITE 
        for (int i = 0; i < graph.V; i++) {
            mstset[i] = false;
  
            e[i].key = Integer.MAX_VALUE;
            e[i].vertex = i;
            parent[i] = -1;
        }
  
        Edge fi = new Edge();
        fi.src = -1;
        fi.dest = graph.edge[0].src;
        fi.weight = 0;
  
        e[graph.edge[0].src].key = 0;
  
        TreeSet<node> queue = new TreeSet<node>(new comparator());
  
        for (int o = 0; o < graph.V; o++)
            queue.add(e[o]);
  
        while (!queue.isEmpty()) {
  
            node node0 = queue.pollFirst();
  
            mstset[node0.vertex] = true;
            
            ArrayList<Edge> it = vis[node0.vertex];
            for (Edge iterator : it) {
  
                if (mstset[iterator.dest] == false) {
                    
                    if (e[iterator.dest].key > iterator.weight) {
                        queue.remove(e[iterator.dest]);
                        e[iterator.dest].key = iterator.weight;
                        queue.add(e[iterator.dest]);
                        parent[iterator.dest] = node0.vertex;
                    }
                }
            }
        }
        System.out.println("Prim’s algorithm using min_heap in Minimum Spanning Tree Algorithm : ");
        long cost = 0;
        for (int i = 0; i < graph.V; i++)
            if(parent[i]!=-1){
                System.out.println(parent[i] + " -> " +i+" = "+e[i].key);
                cost+= e[i].key;
            }
        
        System.out.println("Minimum Cost Spanning Tree "+ cost);
    }
}
