package Tests;

import algorithms.Graph_Algo;
import dataStructure.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import utils.Point3D;

import static org.junit.Assert.*;


public class Graph_AlgoTest {
    private NodeData srcNode;
    private NodeData destNode;
    private NodeData nd;
    private Point3D pSrc;
    private Point3D pDest;
    private DGraph graph;
    private Graph_Algo gr;

    @BeforeEach
    void buildGraph(){
        graph=new DGraph();
        NodeData pSrc=new NodeData(new Point3D(1,1,0));
        NodeData pDest= new NodeData(new Point3D(3,3,0));
        NodeData srcNode=new NodeData(pSrc);
        this.destNode=new NodeData(pDest);
        graph.addNode(srcNode);
        graph.addNode(destNode);
        graph.connect(srcNode.getKey(), destNode.getKey(), 12);
        this.nd=new NodeData(new Point3D(2,2,0));
        this.graph.addNode(nd);
        this.graph.connect(destNode.getKey(),nd.getKey(), 3);
        this.graph.connect(nd.getKey(),srcNode.getKey(), 4);
        gr = new Graph_Algo();

    }

    @Test
    public void testIsConnected() {
        this.gr.init(graph);
        assertTrue(gr.isConnected());
    }

    @Test
    public void testCopy() {
        this.gr.init(graph);
        dataStructure.graph g=new DGraph();
        g=this.gr.copy();
        if(this.graph.getNode(srcNode.getKey())!=g.getNode(srcNode.getKey()))
            fail("copy function is not working");
        if(this.graph.getNode(destNode.getKey())!=g.getNode(destNode.getKey()))
            fail("copy function is not working");
        if(this.graph.getEdge(srcNode.getKey(),destNode.getKey()).getSrc()!=g.getEdge(srcNode.getKey(),destNode.getKey()).getSrc())
            fail("copy function is not working");
        if(this.graph.getEdge(srcNode.getKey(),destNode.getKey()).getDest()!=g.getEdge(srcNode.getKey(),destNode.getKey()).getDest())
            fail("copy function is not working");
    }
}



