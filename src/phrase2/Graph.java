
package phrase2;

import java.util.*;
import java.lang.*;
import java.io.*;

public class Graph {
    
    
    //vertex and edge
    int V, E;
    Edge edge[];
    Graph(int v, int e)
    {
        V = v;
        E = e;
        edge = new Edge[E];
        for (int i = 0; i < e; ++i)
            edge[i] = new Edge();
    }
    
     //for to create new graph randomly
    public static Graph make_graph(Graph graph){
        Random rand = new Random();
        for (int i = 0; i < graph.E; i++) {
            int u = rand.nextInt(graph.V);
            int v = rand.nextInt(graph.V);
            graph.edge[i].src = u;
            graph.edge[i].dest = v;
            graph.edge[i].weight = rand.nextInt(Integer.MAX_VALUE);
            System.out.println("[ "+graph.edge[i].src+" -- "+graph.edge[i].weight+" --> "+graph.edge[i].dest+" ]");
        }
        return graph;
    }
    
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter  Number of Case: ");
        int ca;
        ca = scan.nextInt();
        long firstTime[] = new long[ca+1];
        long secondTime[] = new long[ca+1];
        long thirdTime[] = new long[ca+1];
        for(int i=1;i<=ca;i++){
            System.out.print("Enter Number of Node: ");
            int V = scan.nextInt();
            System.out.print("Enter Number of Edge: ");
            int E = scan.nextInt();
            
            Graph graph = new Graph(V, E);

            graph = make_graph(graph);
            
            long start = System.currentTimeMillis();
       
            Kruskal KruskalAlgo = new Kruskal(graph);
            KruskalAlgo.excute();
            
            long end = System.currentTimeMillis();
            
            firstTime[i] = end-start;
            
            long start1 = System.currentTimeMillis();
            
            PrimsPriorityQueue ppq = new PrimsPriorityQueue(graph);
            ppq.excute();
            
            long end1 = System.currentTimeMillis();
            
            secondTime[i] = end1-start1;
            
            long start2 = System.currentTimeMillis();

            PrimsMinHeap pmh = new PrimsMinHeap(graph);
            pmh.excute();
            
            long end2 = System.currentTimeMillis();
             
            thirdTime[i] = end2-start2;
            
        }
        System.out.println();
        for(int i=1;i<=ca;i++)
            System.err.println("Case "+i+": Kruskal Time:"+firstTime[i]+" , Prim's Priority Queue Time: "+secondTime[i]+" , Prim's Min Heap Time: "+thirdTime[i]);
        //first time is the time that Kruskal algorithme need
         //second time is the time that Prim's Priority Queue need

        freeChart KruWithPrimsPQ = new freeChart("Kruskal algorithm", "Prim's Priority Queue");
        KruWithPrimsPQ.initChart(firstTime,secondTime,ca);
        
        freeChart PrimsPQWithPrimsMH = new freeChart("Prim's Priority Queue", "Prim's Min Heap");
        PrimsPQWithPrimsMH.initChart(secondTime,thirdTime,ca);
    }
}

