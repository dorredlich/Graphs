package dataStructure;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DGraph implements graph {

    private HashMap<Integer, node_data> MapNode;
    private HashMap<Integer, HashMap<Integer, edge_data>> MapEdge;
    private int nodeSize;
    private int edgeSize;
    private int MC;

    /**
     * constructor.
     */
    public DGraph() {
        this.MapEdge = new HashMap<>();
        this.MapNode = new HashMap<>();
        this.nodeSize = 0;
        this.MC = 0;
        this.edgeSize = 0;
    }

    /**
     * get the node by the key of the node.
     * @param key - the node_id
     * @return the node, if their isn't a node with that key return null.
     */
    @Override
    public node_data getNode(int key) {
        if (MapNode.containsKey(key))
            return this.MapNode.get(key);
        return null;
    }

    /**
     * finding the edge between the src and dest
     * @param src the start of the edge
     * @param dest the end of the adge
     * @return if their is a path return the edge else if edge not exist should throw NullPointerException
     */
    @Override
    public edge_data getEdge(int src, int dest) {
        try {
            return this.MapEdge.get(src).get(dest);
        } catch (NullPointerException e) {
            return null;
        }
    }

    /**
     * adding node to the graph, if the node not exist already that add to the graph else print already exist.
     * count the size of the node and count the number of the change i do by mc
     * @param n the node to add
     */
    @Override
    public void addNode(node_data n) {
        if (!MapNode.containsKey(n)) {
            this.MapNode.put(n.getKey(), n);
            this.nodeSize++;
            this.MC++;
        }
        else {
            System.out.println("Node already exist");
        }
    }

    /**
     * make connection between src to dest and set the weight of the edge and count the number of change and the size of edges
     * @param src - the source of the edge.
     * @param dest - the destination of the edge.
     * @param w - positive weight representing the cost (aka time, price, etc) between src-->dest.
     */
    @Override
    public void connect(int src, int dest, double w) {
        edge_data ed = new Edge(src,dest,w);
        if(this.getNode(src)!=null && this.getNode(dest)!=null) {
            if (this.getEdge(src, dest) == null) {
                if(this.MapEdge.get(src) == null) {
                    HashMap<Integer, edge_data> hMe = new HashMap<>();
                    this.MapEdge.put(src,hMe);
                    this.MapEdge.get(src).put(dest,ed);
                }
                else {         //neighburs exist but the edge isnt.
                    this.MapEdge.get(src).put(dest, ed);
                }
            }
            else{
                this.removeEdge(src,dest);
                this.connect(src,dest,w);
            }
        }
        else {
            throw new RuntimeException("Wrong input, Missing node.");
        }
        this.edgeSize++;
        this.MC++;
    }

    /**
     * get the collection of value in node_data
     * @return the values of the nodes
     */
    @Override
    public Collection<node_data> getV() {
        return this.MapNode.values();
    }

    /**
     * get all the edge of a node is connect with.
     * @param node_id the id of the node
     * @return the adges of the node, if he is not connect return null.
     */
    @Override
    public Collection<edge_data> getE(int node_id) {
        if (this.MapEdge.get(node_id) != null) {
            return this.MapEdge.get(node_id).values();
        }
        return null;
    }

    /**
     * removing a node ny is key and count down the number of nodes.
     * also removing all the edges that is connect with.
     * @param key to find the node
     * @return the node will remove.
     */
    @Override
    public node_data removeNode(int key) {
        node_data node = this.getNode(key);
        if (node != null) {
            this.MapNode.remove(key);
            this.nodeSize--;
            if (this.MapEdge.get(key) != null)
                this.MapEdge.remove(key);
            for (Map.Entry m : MapEdge.entrySet()) {
                int OtherKey = (int) m.getKey();
                if (this.MapEdge.get(OtherKey).get(key) != null)
                    removeEdge(OtherKey, key);
            }
        }
        return node;
    }

    /**
     * removing the edge between src to dest and counting down number of the node
     * also counting the change that we do.
     * @param src will start
     * @param dest will end
     * @return the edge will remove
     */
    @Override
    public edge_data removeEdge(int src, int dest) {
        edge_data e = this.getEdge(src, dest);
        try {
            if (e != null) {//check if their is a edge between src and dest
                this.MapEdge.get(src).remove(dest);
                this.MC++;
                this.edgeSize--;
                return e;
            }
        } catch (Exception ex) {
            System.out.println("Error with the remove edge");
        }
        return e;
    }

    /**
     *
     * @return the size of the nodes.
     */
    @Override
    public int nodeSize() { return this.nodeSize; }

    /**
     *
     * @return return the size of the edges
     */
    @Override
    public int edgeSize() { return this.edgeSize;}

    /**
     *
     * @return number of change the we do on the graph.
     */
    @Override
    public int getMC() { return this.MC; }
}
