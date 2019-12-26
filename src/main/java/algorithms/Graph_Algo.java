package algorithms;

import java.io.*;
import java.util.List;

import com.sun.corba.se.impl.orbutil.graph.Graph;
import dataStructure.*;

/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author
 *
 */
public class Graph_Algo implements graph_algorithms{
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

    @Override
    public boolean isConnected() {

        return false;
    }

    @Override
    public double shortestPathDist(int src, int dest) {

        return 0;
    }

    @Override
    public List<node_data> shortestPath(int src, int dest) {

        return null;
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
                node_data node2 = new NodeData(node.getKey(),node.getWeight(),node.getTag() ,node.getLocation(),node.getInfo()); // creat new node for deep copy
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

}
