package algorithms;

import dataStructure.NodeData;
import dataStructure.node_data;

import java.util.ArrayList;

public class HeapMin {

    private ArrayList<node_data> array;

    public ArrayList<node_data> getArray(){
        return array;
    }

    public HeapMin(){
        this.array = new ArrayList<node_data>();
    }

    // Constructor
    public HeapMin(ArrayList<node_data> ar) {
        this.array = ar;
        build();
    }

    public void insert(node_data item) {

        array.add(item);
        int i = array.size() - 1;
        int parent = parent(i);

        while (parent != i && array.get(i).getWeight() < array.get(parent).getWeight()) {

            swap(i, parent);
            i = parent;
            parent = parent(i);
        }
    }

    // Build Min Heap
    public void build() {

        for (int i = (array.size() / 2) - 1; i >= 0; i--) {
            minHeapify(i);
        }
    }

    // Min Heapify
    // Additional conditions to keep track of heap_index
    private void minHeapify(int i) {
        int l = left(i);
        int r = right(i);
        int smallest = -1;
        if (l <= array.size()-1) {
            if (getDist(array.get(l)) < getDist(array.get(i))) {
                smallest = l;
            } else {
                smallest = i;
            }
        }
        if (r <= array.size()-1) {
            if (getDist(array.get(r)) < getDist(array.get(smallest))) {
                smallest = r;
            }
        }
        if (smallest != i) {
            swap(i,smallest);
            minHeapify(smallest);
        }
    }


    /**
    * Removing min node of the array and using minheapify to find the place of the node
     */
    public node_data extractMin() {
        if (array.size() == 0) {
            throw new IllegalStateException("Min-Heap undeflow!");
        }
        if(array.size() == 1){
            node_data min = array.get(0);
        }
        //setting the last node to be the root
        node_data min = array.get(0);
        node_data last = array.remove(array.size() - 1);
        array.set(0, last);
        minHeapify(0);

        return min;
    }
//
//    // Decrease Priority/Key
//    public void decreaseKey(node_data i, double key) {
//        if (sortedQ.get(i).getWeight() < key) {
//            return;
//        }
//        sortedQ.get(i).getKey() = key;
//        while (i > 0 && sortedQ.get(parent(i)).getWeight() > sortedQ.get(i).getWeight()) {
//            // Change heap_index
//            sortedQ.get(parent(i)).heap_index = i;
//            sortedQ.get(i).heap_index = parent(i);
//
//            node_data temp = sortedQ.get(i);
//            sortedQ.set(i, sortedQ.get(parent(i)));
//            sortedQ.set(parent(i), temp);
//            i = parent(i);
//        }
//    }

    // Get the Parent of a node
    private int parent(int i) {
        return (i - 1) / 2;
    }

    // Get the left child of the node
    private int left(int i) {
        return 2 * i + 1;
    }

    // Get the right child of the node
    private int right(int i) {
        return 2 * i + 2;
    }

    // Check the heap is empty
    public boolean isEmpty() {
        return array.isEmpty();
    }


    // Change this method to change the value based on which the queue should be
    // sorted
    private double getDist(node_data v) {
        return v.getWeight();
    }

    //swap between nodes
    private void swap(int i, int parent) {
        node_data temp = array.get(parent);
        array.set(parent, array.get(i));
        array.set(i, temp);
    }

}
