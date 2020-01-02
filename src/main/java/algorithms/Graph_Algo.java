package algorithms;

import java.io.*;
import java.util.*;

import com.sun.corba.se.impl.orbutil.graph.Graph;
import dataStructure.*;

/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author
 *
 */
public class Graph_Algo implements graph_algorithms,Serializable {
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

    /**
     * function to set the tags.
     * @return
     */
    private boolean checkTag() {
        for(node_data node :  GraphAlgo.getV()) {
            if(node.getTag() == -1)
                return false;
            else
                node.setTag(-1);
        }
        return true;
    }

    @Override
    public boolean isConnected() {
        for (node_data node : GraphAlgo.getV()) {
            node.setTag(-1);
        }
        for (node_data node : GraphAlgo.getV()) {
            node.setTag(1);
            ifConRecursive(GraphAlgo.getE(node.getKey()));
            if(!checkTag())
                return false;
        }
        return true;
    }

    /**
     *  recursive help function for isConnected method
     * */
    private void ifConRecursive(Collection<edge_data> edge) {
        for(edge_data ed : edge) {
            if(GraphAlgo.getNode(ed.getDest()).getTag() == -1 ) {
                GraphAlgo.getNode(ed.getDest()).setTag(1);
                ifConRecursive(GraphAlgo.getE(ed.getDest()));
            }
        }
        return;
    }

//    private boolean markVisited(node_data start,Collection<edge_data> cg) {
//        if(cg.size() != 0 ) {
//            start.setTag(5);
//        }
//        if(cg.size() == 0) {
//            return false;
//        }
//        else {
//            boolean ans = true;
//            for (edge_data e : cg) {
//                if (e.getTag() != 5) {
//                    e.setTag(5);
//                    node_data dest = this.GraphAlgo.getNode(e.getDest());
//                    Collection<edge_data> c = this.GraphAlgo.getE(e.getDest());
//                    ans = markVisited(dest, c);
//                }
//            }
//            if (ans == true) {
//                return true;
//            }
//            else {
//                return false;
//            }
//        }
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
        List<node_data> list = new ArrayList<node_data>();
        for (int i = 0; i < targets.size(); i++) {
            if(this.GraphAlgo.getNode(targets.get(i)) == null) {
                return list;
            }
        }
        if(targets.size() == 1) {

            node_data one = this.GraphAlgo.getNode(targets.get(0));
            list.add(one);
            return list;
        }
        else {
            list = new ArrayList<>();
            List<node_data> listTemp = new ArrayList<>();
            int i = 0;
            while(i+1<targets.size()) {
                listTemp = shortestPath(targets.get(i), targets.get(i+1));
                for (int k = listTemp.size()-1 ; k >= 0  ; k--) {

                    if(list.contains(listTemp.get(k)) && k == listTemp.size()-1) {
                        ;
                    }
                    else {
                        list.add(listTemp.get(k));
                    }
                }
                i++;
            }
            return list;
        }
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

