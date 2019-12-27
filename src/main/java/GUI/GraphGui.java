package GUI;

import StdDraw.StdDraw;
import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.NodeData;
import dataStructure.edge_data;
import dataStructure.node_data;
import utils.Point3D;


import java.awt.*;
import java.util.List;
import java.util.*;

public class GraphGui {
    public static DGraph graph = new DGraph();
    public static Graph_Algo GA = new Graph_Algo();


    public GraphGui() {
        graph = new DGraph();
        GA = new Graph_Algo();
        this.openCanvas();
    }

    public GraphGui(DGraph graph) {
        this.graph = graph;
        this.printGraph();
    }

    public boolean isConnected() {
        GA.init(graph);
        return GA.isConnected();
    }

    public void add_node(double x, double y) {
        Point3D p = new Point3D(x, y);
        NodeData n = new NodeData(p);
        graph.addNode(n);
    }

    public void add_edge(int src, int dest, double weight) {
        graph.connect(src, dest, weight);
    }

    public void remove_edge(int src, int dest) {
        graph.removeEdge(src, dest);

    }

    public void remove_node(int key) {

        graph.removeNode(key);
    }

    public void save(String file_name) {
        GA.init(graph);
        GA.save(file_name);
    }

    public void load(String file_name) {
        GA.init(file_name);
        graph = (DGraph) GA.copy();

    }

    public double ShortestPath(int src, int dest) {
        GA.init(graph);
        return GA.shortestPathDist(src, dest);
    }

    public List<node_data> ShortestPathList(int src, int dest) {
        GA.init(graph);
        return GA.shortestPath(src, dest);
    }

    public void Clean() {
        graph = new DGraph();
        GA.init(graph);
        this.printGraph();
    }


    public void openCanvas() {
        StdDraw.setCanvasSize(1000, 1000);
        StdDraw.setXscale(-100, 100);
        StdDraw.setYscale(-100, 100);
        printGraph();
    }

    public void printGraph() {
        StdDraw.clear();
        StdDraw.setPenColor(Color.RED);
        StdDraw.setPenRadius(0.15);
        DGraph g = this.graph;
        if (g != null) {
            Iterator iter = g.getHash().entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry map = (Map.Entry) iter.next();
                int key = (int) map.getKey();
                Point3D p = g.getNode(key).getLocation();
                StdDraw.filledCircle(p.x(), p.y(), 0.4);
                StdDraw.text(p.x(), p.y() + 0.5, "" + g.getNode(key).getKey());
            }
            StdDraw.setPenColor(Color.BLACK);
            StdDraw.setPenRadius(0.01);
            Iterator it2 = g.getEdgeHash().entrySet().iterator();
            while (it2.hasNext()) {
                Map.Entry map2 = (Map.Entry) it2.next();
                int keySrc = (int) map2.getKey();
                HashMap temp = (HashMap) g.getEdgeHash().get(keySrc);
                Iterator it3 = temp.entrySet().iterator();
                if (temp != null) {
                    while (it3.hasNext()) {
                        Map.Entry map3 = (Map.Entry) it3.next();
                        int keyDest = (int) map3.getKey();
                        if (temp.get(keyDest) != null) {
                            edge_data edge = (edge_data) temp.get(keyDest);
                            double weight = edge.getWeight();
                            node_data srcNode = g.getNode(keySrc);
                            node_data dstNode = g.getNode(keyDest);
                            Point3D srcP = srcNode.getLocation();
                            Point3D dstP = dstNode.getLocation();
                            StdDraw.line(srcP.x(), srcP.y(), dstP.x(), dstP.y());
                            StdDraw.setPenColor(Color.BLACK);
                            StdDraw.text((srcP.x() + dstP.x()) / 2, (srcP.y() + dstP.y()) / 2, "" + weight);

                        }
                    }
                }
            }
        }
    }
}

