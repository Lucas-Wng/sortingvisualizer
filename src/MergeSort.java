// Lucas Wang and Theo Sampaleanu
// April 6 2021
// ICS3u7 Ms. Strelkovska
// Merge Sort Class
// Continuously divides the array into two halfs until it is 
// split into individual numbers. Then uses the merge() method
// to repeatedly combine the two halves until it the array is sorted.

public class MergeSort extends Algorithms {
    // Uses runSort method for better encapsulation
    public void runSort(SortJPanel array) {
        int left = 0;
        int right = array.arraySize() - 1;
        // Uses threads to display the animation
        Thread Merge = new Thread(new Runnable() {
            public void run() {
                mergeSort(array, left, right);
            }
        });
        Merge.start();
    }

    public void mergeSort(SortJPanel array, int left, int right) {
        // Uses recursion and repeats until it is sorted
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSort(array, left, middle);
            mergeSort(array, middle + 1, right);
            merge(left, middle, right, array);
        }
    }

    public void merge(int left, int middle, int right, SortJPanel array) {
        int leftlength = middle - left + 1;
        int rightlength = right - middle;
        // Creates two new arrays which are filled with each halves
        int leftarray[] = new int[leftlength];
        int rightarray[] = new int[rightlength];
        for (int i = 0; i < leftlength; i++) {
            leftarray[i] = array.getValue(left + i);
        }
        for (int i = 0; i < rightlength; i++) {
            rightarray[i] = array.getValue(middle + 1 + i);
        }
        int i = 0;
        int j = 0;
        int k = left;
        // Compares the numbers of each of the arrays and moves it the main array
        while (i < leftlength && j < rightlength) {
            if (leftarray[i] <= rightarray[j]) {
                array.move(k, leftarray[i]);
                i++;
            } else {
                array.move(k, rightarray[j]);
                j++;
            }
            k++;
            array.setAccessCount(array.getAccessCount() + 1);
            try {
                Thread.sleep(154 - (array.getSpeed() * 3));
            } catch (Exception e) {
            }
        }
        // Two while loops in the end in case all the numbers of one array are used and
        // there are still leftover numbers in the other
        while (i < leftlength) {
            array.move(k, leftarray[i]);
            i++;
            k++;
            array.setAccessCount(array.getAccessCount() + 1);
            try {
                Thread.sleep(154 - (array.getSpeed() * 3));
            } catch (Exception e) {
            }
        }
        while (j < rightlength) {
            array.move(k, rightarray[j]);
            j++;
            k++;
            array.setAccessCount(array.getAccessCount() + 1);
            try {
                Thread.sleep(154 - (array.getSpeed() * 3));
            } catch (Exception e) {
            }
        }
    }
}
