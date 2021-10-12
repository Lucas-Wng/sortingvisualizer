// Lucas Wang and Theo Sampaleanu
// April 6 2021
// ICS3u7 Ms. Strelkovska
// Heapsort Class
// Similar to Selection sort. Uses a Binary Tree which stores numbers in a special order such that the parent node is larger than the two children nodes.
// Puts the parent node's number (the largest) at the end and repeats until all the numbers are sorted.

public class HeapSort {
    public void runSort(SortJPanel array) {
        // Uses multithreading to animate and sleep simultaneously
        Thread Heap = new Thread(new Runnable() {
            public void run() {
                int size = array.arraySize();
                // Rearranges the array to build the binary tree
                for (int i = size / 2 - 1; i >= 0; i--) {
                    heapify(array, size, i);
                }
                // One by one, puts the largest number at the end
                for (int i = size - 1; i > 0; i--) {
                    array.swap(0, i);
                    // Rearranges the reduced binary tree
                    heapify(array, i, 0);
                }
            }
        });
        Heap.start();
    }

    public void heapify(SortJPanel array, int size, int index) {
        int largest = index;
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        // If the left child is larger than parent
        if (left < size && array.getValue(left) > array.getValue(largest)) {
            largest = left;
            array.setAccessCount(array.getAccessCount() + 1);
        }
        // If the right child is larger than parent
        if (right < size && array.getValue(right) > array.getValue(largest)) {
            largest = right;
            array.setAccessCount(array.getAccessCount() + 1);
        }
        // if the largest is not the parent
        if (largest != index) {
            // Swaps and uses recursion to rearrange the binary tree
            array.swap(index, largest);
            heapify(array, size, largest);
            array.setAccessCount(array.getAccessCount() + 1);
            try {
                Thread.sleep(154 - (array.getSpeed() * 3));
            } catch (Exception e) {
            }
        }
    }
}
