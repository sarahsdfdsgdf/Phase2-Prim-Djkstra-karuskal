/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phrase2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Vector;

public class PrimsPriorityQueue {
    
    Graph graph;
    ArrayList<Edge>[] vis;

    public PrimsPriorityQueue(Graph graph) {
        this.graph = graph;
    }
    
    public void excute(){
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
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int src = 0;
        int[] key = new int[graph.V];
        int[] parent = new int[graph.V];
        boolean[] inMST = new boolean[graph.V];
        
        for (int i = 0; i < graph.V; i++) {
            key[i] = Integer.MAX_VALUE;
            parent[i] = -1;
            inMST[i] = false;
        }
        Edge fi = new Edge();
        fi.src = -1;
        fi.dest = graph.edge[0].src;
        fi.weight = 0;
        // Insert source itself in priority queue and initialize
        // its key as 0.
        pq.add(fi);
        key[graph.edge[0].
                src] = 0;
        /* Looping till priority queue becomes empty */
        while(!pq.isEmpty()){
            
            Edge u = pq.poll();
            inMST[u.dest] = true;
            ArrayList<Edge> it = vis[u.dest];
            for (Edge edge : it) {
                int v = edge.dest;
                int weight = edge.weight;
                
                //  If v is not in MST and weight of (u,v) is smaller
                // than current key of v
                
                if (inMST[v] == false && key[v] > weight)
                {                    
                    key[v] = weight;
                    pq.add(edge);
                    parent[v] = u.dest;
                }
            }    
        }
        System.out.println("Prim’s algorithm using priority_queue in Minimum Spanning Tree Algorithm : ");
        
        long cost = 0;
            // Print edges of MST using parent array
        for (int i = 0; i < graph.V; ++i)
            if(parent[i]!=-1){
                System.out.println(parent[i] +" -> "+ i+" = "+key[i]);
                cost+= key[i];
            }
                
        System.out.println("Minimum Cost Spanning Tree "+ cost);
    }
}
