// Lucas Wang and Theo Sampaleanu
// April 6 2021
// ICS3u7 Ms. Strelkovska
// Quick Sort Class
// Selects a number as a pivot and partitions the numbers 
// according to whether they are lesser or greater than 
// the pivot. Repeat the process until it is sorted.

public class QuickSort extends Algorithms {
    // Uses runSort method for better encapsulation
    public void runSort(SortJPanel array) {
        int low = 0;
        int high = array.arraySize() - 1;
        quickSort(array, low, high);
    }

    public void quickSort(SortJPanel array, int low, int high) {
        // Uses threads to display the animation
        Thread Quick = new Thread(new Runnable() {
            public void run() {
                // Uses recursion and repeats until it is sorted
                if (low < high) {
                    int part = partition(array, low, high);
                    array.drawPivot(part);
                    quickSort(array, low, part - 1);
                    quickSort(array, part + 1, high);
                }
            }
        });
        Quick.start();
    }

    public int partition(SortJPanel array, int low, int high) {
        int pivot = array.getValue(high);
        int i = low - 1;
        // Finds the pivot's index through a partitioning algorithm
        for (int j = low; j < high; j++) {
            if (array.getValue(j) < pivot) {
                i++;
                array.swap(i, j);
                try {
                    Thread.sleep(154 - (array.getSpeed() * 3));
                } catch (Exception e) {
                }
            }
            array.setAccessCount(array.getAccessCount() + 1);
        }
        // Swaps the pivot to correct index and sleeps
        array.swap(i + 1, high);
        try {
            Thread.sleep(154 - (array.getSpeed() * 3));
        } catch (Exception e) {
        }
        // Returns the pivot index for recursion
        return i + 1;
    }
}
