// Lucas Wang and Theo Sampaleanu
// April 6 2021
// ICS3u7 Ms. Strelkovska
// Selection Sort Class
// Repeatedly finds the smallest number and 
// puts it in ascending order.

public class SelectionSort extends Algorithms {

    public void runSort(SortJPanel array) {
        // Uses multithreading to animate and sleep simultaneously
        Thread Selection = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < array.arraySize() - 1; i++) {
                    int min_ind = i;
                    // Finds the smallest number and sets the variable min_ind to j
                    for (int j = i + 1; j < array.arraySize(); j++) {
                        if (array.getValue(j) < array.getValue(min_ind))
                            min_ind = j;
                        array.setAccessCount(array.getAccessCount() + 1);
                    }
                    // Swaps and sleeps after finding the minimum index
                    array.swap(i, min_ind);
                    try {
                        Thread.sleep(104 - (array.getSpeed() * 2));
                    } catch (Exception e) {
                    }
                }
            }
        });
        Selection.start();
    }
}
