/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package phrase2;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.nashorn.internal.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements; 

public class DijkstraCity {
    //initialize the variable
    private int dist[];
    private Set<Integer> settled;
    private PriorityQueue<nodeGraph> pq;
    private int V;
    List<List<nodeGraph> > adj;
    int parent[];
  
    public DijkstraCity(int V)
    {
        this.V = V;
        dist = new int[V];
        settled = new HashSet<Integer>();
        pq = new PriorityQueue<nodeGraph>(V, new nodeGraph());
        parent = new int[V];
    }
  
    public void dijkstra(List<List<nodeGraph> > adj, int src)
    {
        this.adj = adj;
        //set distance max value
        for (int i = 0; i < V; i++)
            dist[i] = Integer.MAX_VALUE;
  
        //added to proierty queue
        pq.add(new nodeGraph(src, 0));
  
        dist[src] = 0;
        //settled contain number vertex
        while (settled.size() != V) {
            //return node and remove it
            int u = pq.remove().node;
  
            settled.add(u);
            
            parent[u] = -1;
  
            Neighbours(u);
        }
    }
    private void Neighbours(int u)
    {
        int edgeDistance = -1;
        int newDistance = -1;
  
        //looping in graph
        for (int i = 0; i < adj.get(u).size(); i++) {
            nodeGraph v = adj.get(u).get(i);
  
            if (!settled.contains(v.node)) {
                edgeDistance = v.cost;
                newDistance = dist[u] + edgeDistance;  
                if (newDistance < dist[v.node]){
                    dist[v.node] = newDistance;
                    parent[v.node] = u;
                    
                }                    
                pq.add(new nodeGraph(v.node, dist[v.node]));
            }
        }
    }
    
    private static void printSolution(int startVertex,int[] distances,int[] parents)
    {
        int nVertices = distances.length;
        System.out.print("Vertex\t\tDistance\tPath");
          
        for (int vertexIndex = 0;vertexIndex < nVertices;vertexIndex++) 
        {
            if (vertexIndex != startVertex) 
            {
                System.out.print("\n" + startVertex + " -> ");
                System.out.print(vertexIndex + " \t\t ");
                System.out.print(distances[vertexIndex] + "\t\t");
                printPath(vertexIndex,parents);
            }
        }
        System.out.println();
    }
    private static void printPath(int current,int[] parents)
    { 
        if (current == -1)
        {
            return;
        }
        printPath(parents[current], parents);
        System.out.print(current + " ");
    }
    public static void main(String arg[])
    {
        int V = 12;
        int source = 0;
  
        List<List<nodeGraph> > adj = new ArrayList<List<nodeGraph> >();
  
        for (int i = 0; i < V; i++) {
            List<nodeGraph> item = new ArrayList<nodeGraph>();
            adj.add(item);
        }
        ArrayList<String> no = new ArrayList<>();
        Document doc2;
        try {

            //there we need to converte table in html to dataset
            doc2 = Jsoup.connect("http://www.the-saudi.net/saudi-arabia/saudi_city_distances.htm").get();
            String masthead = doc2.select("td.vbk2").html();  
            String[] val = masthead.split("\n");
            for(String v:val){
                if(v.contains("POPULATION")){
                    break;
                }
                if(!v.contains("font")&&!v.contains("<b>")){
                    no.add(v);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(DijkstraCity.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Iterator it = no.iterator();
        for(int i=0;i<12;i++){
            for(int j=0;j<12;j++){
                String value = (String) it.next();
                int num = Integer.parseInt(value);
                adj.get(i).add(new nodeGraph(j, num));
            }
        }
       
        DijkstraCity dpq = new DijkstraCity(V);
        dpq.dijkstra(adj, source);
        printSolution(source, dpq.dist, dpq.parent);
    }
}

