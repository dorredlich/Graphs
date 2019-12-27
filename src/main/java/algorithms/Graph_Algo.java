package algorithms;

import java.io.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.sun.corba.se.impl.orbutil.graph.Graph;
import dataStructure.*;

/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author
 *
 */
public class Graph_Algo implements graph_algorithms {
    private graph GraphAlgo;

    @Override
    public void init(graph g) {
        this.GraphAlgo = GraphAlgo;
    }

    @Override
    public void init(String file_name) {
        try {
            FileInputStream file = new FileInputStream(file_name);
            ObjectInputStream in = new ObjectInputStream(file);

            this.GraphAlgo = (graph) in.readObject();

            in.close();
            file.close();

            System.out.println("Object has been deserialized");
        }

        catch (IOException ex) {
            System.out.println("Error with the file");
        }

        catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        }

    }

    @Override
    public void save(String file_name) {
        try {
            FileOutputStream file = new FileOutputStream(file_name);
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(this.GraphAlgo);

            out.close();
            file.close();

            System.out.println("Object has benn serialized");
        } catch (IOException e) {
            System.err.println("Error with save the file.");
        }

    }

    private void  tagZero(graph g){
        Collection<node_data> node = g.getV();
        for(node_data n: node)
            n.setTag(0);
    }

    @Override
    public boolean isConnected() {
        graph original = this.copy();
        graph trans = TranspostEdge(new DGraph(),original);
        int count = 0;
        int count2 = 0;
        for(node_data node: original.getV()) {
            if (original.getE(node.getKey()) != null) {
                Collection<edge_data> c2 = original.getE(node.getKey());
                for (edge_data e : c2) {
                    NodeData node2 = (NodeData) original.getNode(e.getDest());
                    if (!node2.isVisited()) {
                        node2.setVisited(true);
                        count++;
                    }
                }
            }
        }
        for(node_data node: trans.getV()) {
            if (trans.getE(node.getKey()) != null) {
                Collection<edge_data> c2 = trans.getE(node.getKey());
                for (edge_data e : c2) {
                    NodeData node2 = (NodeData) trans.getNode(e.getDest());
                    if (!node2.isVisited()) {
                        node2.setVisited(true);
                        count2++;
                    }
                }
            }
        }
        if(count == count2) return true;
        return false;

    }

//    private boolean markVisited(node_data node, graph g , node_data start) {
//        if (g.getE(node.getKey()) != null) {
//            for (edge_data e : g.getE(node.getKey())) {
//                if (g.getNode(e.getDest()).getTag() != 1) {
//                    node.setTag(1);
//                    g.getNode(e.getDest()).setTag(1);
//                    markVisited(g.getNode(e.getDest()), g , start);
//                }
//            }
//        }
//        if(node.getKey() == start.getKey()) {
//            for (node_data v : g.getV()) {
//                if (v.getTag() == 0) {
//                    return false;
//                }
//            }
//        }
//
//        return true;
//    }

    private graph TranspostEdge(graph trans, graph g) {
        for (node_data n : g.getV()) {
            node_data n2 = new NodeData(n.getKey(), n.getWeight(), n.getTag(), n.getLocation(), n.getInfo());
            trans.addNode(n2);
        }
        for (node_data n : g.getV()) {
            if (g.getE(n.getKey()) != null) {
                for (edge_data edge : g.getE(n.getKey()))
                    trans.connect(edge.getDest(), edge.getSrc(), edge.getWeight());
            }
        }
        return trans;
    }

    /**
     * function that return length of the shortest path between src ans dest using the dijekstra function.
     * @param src - start node
     * @param dest - end (target) node
     * @return
     */
    @Override
    public double shortestPathDist(int src, int dest) {
        graph g2 = this.copy();
        dijekstra(g2, src);
        return g2.getNode(dest).getWeight();
    }

    /**
     * function that return the list of nodes of the shortest path between src ans dest using the dijekstra function.
     * @param src - start node
     * @param dest - end (target) node
     * @return
     */
    @Override
    public List<node_data> shortestPath(int src, int dest) {
        graph g = this.copy();
        dijekstra(g, src);
        List<node_data> list = new LinkedList<>();
        node_data temp = g.getNode(dest);
        try {
            while (temp.getKey() != src) {//begin from dest-node to src-node
                list.add(temp);
                temp = g.getNode(temp.getTag());

            }
            list.add(temp);//add src node
            int i = list.size() - 1;
            List<node_data> ans = new LinkedList<>();
            ans.add(temp);
            i--;
            while (i != 0) {
                ans.add(list.get(i));
                i--;
            }
            ans.add(list.get(0));
            return ans;
        }
        catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<node_data> TSP(List<Integer> targets) {

        return null;
    }

    @Override
    public graph copy() {
        graph g = new DGraph();
        for (node_data node : this.GraphAlgo.getV()) {
            if (node != null) {
                node_data node2 = new NodeData(node.getKey(),node.getWeight(),node.getTag() ,node.getLocation(),node.getInfo());
                g.addNode(node2);
            }
            if (this.GraphAlgo.getE(node.getKey()) != null) {
                for (edge_data e : this.GraphAlgo.getE(node.getKey())) {
                    if (e != null) {
                        edge_data e1 = new dataStructure.Edge(e.getSrc(), e.getDest(), e.getWeight());
                        g.connect(e1.getSrc(), e1.getDest(), e1.getWeight());
                    }
                }
            }
        }
        return g;
    }

    /**
     * function that set all the weight of the nodes depending of the weight the edge is and
     * help by marking all the visited.
     * @param g the graph
     * @param src the start.
     */

    private void dijekstra(graph g, int src) {
        Collection<node_data> c = g.getV();
        HeapMin heap = new HeapMin();
        g.getNode(src).setWeight(0);
        for (node_data nodes : c) {
            heap.insert(nodes);
        }
        while (heap.getArray().size() != 0) {
            NodeData node = (NodeData) heap.extractMin();
            if (g.getE(node.getKey()) != null) {
                Collection<edge_data> c2 = g.getE(node.getKey());
                for (edge_data e : c2) {
                    NodeData node2 = (NodeData) g.getNode(e.getDest());
                    if (!node2.isVisited()) {
                        double weight = node.getWeight() + e.getWeight();
                        if (weight < node2.getWeight()) {
                            node2.setWeight(weight);
                            node2.setTag(node.getKey());// to know the previous node with the lowest cost
                            heap.build();
                        }
                    }
                }
                node.setVisited(true);
            }
        }
    }

}

