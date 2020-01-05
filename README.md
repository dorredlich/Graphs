# Graph Project

this project represents a dataStructure, algorithm, and gui of showing a graphics.

dataStructure: 

* I describe a DGraph by HashMap that implements the nodes-Hashtable<Integer, node_data>, and edges - Hashtable<node_data, Hashtable<Integer, edge_data>>.
* the class also represent a function like getting the node of the graph, the edge between source and destantion.
* checking if the graph is connection, remove edge and node also to add nodes and edge to the graph.
* the class of NodeData getting all the information on the node, like: key, tag, the weight, and the location by x and y to put the node.
* the class of Edge represent getting all the information about the edge of the source that the edge start to the destantion of the nide the the weight of the edge.

algorithm:

* the class Graph_Algo represent all the algorithm of the garph.
* the fuction ShortesPathDis find the shorte path between source to destantion and return the distance and  ShortesPath return list of the shorte path by the help of the dijekstra function.
* the TSP function find the if their is a path by getting list of nodes.
* the copy function make a deep copy the original graph.
* the function init make deserialized from a file. 
* the function save make serialized to a file.

Graph_GUI:

the class showing the graph on a graphics window by extands JFrame and implement ActionListener.

