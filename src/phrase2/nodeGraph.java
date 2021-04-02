/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package phrase2;

import java.util.Comparator;

class nodeGraph implements Comparator<nodeGraph> {
    public int node;
    public int cost;
  
    public nodeGraph()
    {
    }
  
    public nodeGraph(int node, int cost)
    {
        this.node = node;
        this.cost = cost;
    }
  
    @Override
    public int compare(nodeGraph node1, nodeGraph node2)
    {
        if (node1.cost < node2.cost)
            return -1;
        if (node1.cost > node2.cost)
            return 1;
        return 0;
    }
}
