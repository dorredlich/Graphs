package Tests;

import dataStructure.DGraph;
import dataStructure.Edge;
import dataStructure.NodeData;
import dataStructure.edge_data;
import org.junit.Before;
import org.junit.Test;
import utils.Point3D;

import static org.junit.Assert.*;

public class DGraphTest {
    static DGraph Graph = new DGraph();
    static Point3D point[] = new Point3D[6];
    static NodeData d[] = new NodeData[6];

    @Before
    public void BeforeEach() {
        point[0] = new Point3D(7, 2, 0);
        point[1] = new Point3D(5, 3, 0);
        point[2] = new Point3D(1, 12, 0);
        point[3] = new Point3D(0, 8, 0);
        point[4] = new Point3D(2, 16, 0);
        point[5] = new Point3D(4, 12, 0);
        d[0] = new NodeData(1, 20, 0, point[0], "a");
        d[1] = new NodeData(2, 10, 0, point[1], "b");
        d[2] = new NodeData(3, 15, 0, point[2], "c");
        d[3] = new NodeData(4, 5, 0, point[3], "d");
        d[4] = new NodeData(5, 0, 0, point[4], "e");
        d[5] = new NodeData(6, 25, 0, point[5], "f");
        for (int i = 0; i < point.length; i++) {
            Graph.addNode(d[i]);
        }

        Graph.connect(1, 4, 20);
        Graph.connect(2, 1, 10);
        Graph.connect(6, 1, 5);
        Graph.connect(1, 6, 6);
        Graph.connect(4, 3, 440);
        Graph.connect(5, 6, 1);
        Graph.connect(6, 3, 22);
        Graph.connect(3, 4, 20);
        Graph.connect(2, 5, 10);
    }

    @Test
    public void getNode() {
        for (int i = 0; i < d.length; i++) {
            assertEquals(d[i], Graph.getNode(i + 1));
        }
    }

    @Test
    public void getEdge() {
        edge_data ans[] = new edge_data[9];
        ans[0] = new Edge(1, 4, 20);
        ans[1] = new Edge(2, 1, 10);
        ans[2] = new Edge(6, 1, 5);
        ans[3] = new Edge(1, 6, 6);
        ans[4] = new Edge(4, 3, 440);
        ans[5] = new Edge(5, 6, 1);
        ans[6] = new Edge(6, 3, 22);
        ans[7] = new Edge(3, 4, 20);
        ans[8] = new Edge(2, 5, 10);

        for (int i = 0; i < ans.length; i++) {
            assertEquals(Graph.getEdge(ans[i].getSrc(), ans[i].getDest()).toString(), ans[i].toString());
            System.out.println(Graph.getEdge(ans[i].getSrc(), ans[i].getDest()).toString() + "," + ans[i].toString());
        }

    }
}
