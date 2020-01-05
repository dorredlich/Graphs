# Graph Project
this project is presents a algorithm directed graph between all source node to a destantion that we get.

DGraph:

* I describe DGraph by HashMap of node and edge. the node implement by HashMap<Integer, node_data> and the edge ny       				HashMap<Integer,HashMap<Integer,edge_data>>. 
* the grapgh not include negative value.
* the graph support a large number of node and the connection to the destantion.
* the class of NodeData present the information, weight, tag, key and the location that we put the node in the graph.
* the class of Edge preset the source -> destantion and the weight between.

Graph_Algo:

* In this class I will to all the algorithm on the graph
* deserialize the graph from file and serialize the graph to a file.
* pass on the graph by giving a source node and destantion node and find the shotest path and return the list of those nodes and also the distance between them.
* TSP that check on a sub graph if their is connection.

Graph_GUI:

showing the graph on a window by the extends JFrame and implements ActionListener.
