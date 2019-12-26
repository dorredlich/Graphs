package algorithms;

import dataStructure.NodeData;
import dataStructure.node_data;

import java.util.ArrayList;

public class HeapMin {

    private ArrayList<node_data> sortedQ;

    // Constructor
    public HeapMin(ArrayList<node_data> ar) {
        this.sortedQ = ar;
        build();
    }

    public void insert(node_data item) {

        sortedQ.add(item);
        int i = sortedQ.size() - 1;
        int parent = parent(i);

        while (parent != i && sortedQ.get(i).getWeight() < sortedQ.get(parent).getWeight()) {

            swap(i, parent);
            i = parent;
            parent = parent(i);
        }
    }

    // Build Min Heap
    private void build() {

        for (int i = (sortedQ.size() / 2) - 1; i >= 0; i--) {
            minHeapify(sortedQ,i);
        }
    }

    // Min Heapify
    // Additional conditions to keep track of heap_index
    private void minHeapify(ArrayList<node_data> unsortedQ, int i) {
        int l = left(i);
        int r = right(i);
        int smallest = -1;
        if (l <= unsortedQ.size()-1) {
            if (getDist(unsortedQ.get(l)) < getDist(unsortedQ.get(i))) {
                smallest = l;
            } else {
                smallest = i;
            }
        }
        if (r <= unsortedQ.size()-1) {
            if (getDist(unsortedQ.get(r)) < getDist(unsortedQ.get(smallest))) {
                smallest = r;
            }
        }
        if (smallest != i) {
            swap(i,smallest);
            minHeapify(unsortedQ, smallest);
        }
    }

    // ExtractMin
    // Removing last element in ArrayList
    public node_data extractMin() {
        if (sortedQ.size() < 1) {
            throw new IllegalStateException("Min-Heap undeflow!");
        }
        node_data min = sortedQ.get(0);
        sortedQ.set(0, sortedQ.get(sortedQ.size()-1));
        sortedQ.remove(sortedQ.size()-1);
        sortedQ.size();
        if (sortedQ.size() > 0) {
            minHeapify(sortedQ,0);
        }

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
        return sortedQ.isEmpty();
    }


    // Change this method to change the value based on which the queue should be
    // sorted
    private double getDist(node_data v) {
        return v.getWeight();
    }

    private void swap(int i, int parent) {

        node_data temp = sortedQ.get(parent);
        sortedQ.set(parent, sortedQ.get(i));
        sortedQ.set(i, temp);
    }

}
