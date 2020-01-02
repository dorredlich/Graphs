package dataStructure;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DGraph implements graph {

    private HashMap<Integer, node_data> MapNode = new HashMap<>();
    private HashMap<Integer, HashMap<Integer, edge_data>> MapEdge = new HashMap<>();
    private int nodeSize;
    private int edgeSize;
    private int MC;

    public DGraph() {
        this.MapEdge = new HashMap<>();
        this.MapNode = new HashMap<>();
        this.nodeSize = 0;
        this.MC = 0;
        this.edgeSize = 0;
    }

    @Override
    public node_data getNode(int key) {

        return this.MapNode.get(key);
    }

    @Override
    public edge_data getEdge(int src, int dest) {
        try {
            return (edge_data) this.MapEdge.get(src).get(dest);
        } catch (NullPointerException ex) {
            return null;
        }
    }

    @Override
    public void addNode(node_data n) {
        this.MapNode.put(n.getKey(), n);
        this.nodeSize++;
        this.MC++;
    }

    @Override
    public void connect(int src, int dest, double w) {
        edge_data n = new Edge(src, dest, w);
        HashMap<Integer, edge_data> add = new HashMap<>();
        if (this.getNode(src) != null && this.getNode(dest) == null)//if their is a src and not a dest but still wand to
            this.MapEdge.put(src, add); // connect edge to src
        if (this.getNode(src) != null && this.getNode(dest) != null) {
            try {
                if (this.getEdge(src, dest) == null) {
                    if (this.MapEdge.get(src) != null)
                        this.MapEdge.get(src).put(dest, n);
                    if (this.MapEdge.get(src) == null) {
                        this.MapEdge.put(src, add);
                        this.MapEdge.get(src).put(dest, n);
                    }
                }
            } catch (Exception e) {
                System.out.println("Error with connect");
            }
        }
        this.edgeSize++;
        this.MC++;
    }

    @Override
    public Collection<node_data> getV() {
        return this.MapNode.values();
    }

    @Override
    public Collection<edge_data> getE(int node_id) {
        return this.MapEdge.get(node_id).values();
    }

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

    @Override
    public int nodeSize() {

        return this.nodeSize;
    }

    @Override
    public int edgeSize() {

        return this.edgeSize;
    }

    @Override
    public int getMC() {

        return this.MC;
    }


    public HashMap getHash() {
        return this.MapNode;
    }

    public HashMap getEdgeHash() {
        return this.MapEdge;
    }



}
