// Lucas Wang and Theo Sampaleanu
// April 6 2021
// ICS3u7 Ms. Strelkovska
// Insertion Sort Class
// Goes through the array and checks the numbers 
// before it and inserts it in its place.

public class InsertionSort extends Algorithms {

    public void runSort(SortJPanel array) {
        // Uses multithreading to animate and sleep simultaneously
        Thread Insertion = new Thread(new Runnable() {
            public void run() {
                for (int i = 1; i < array.arraySize(); i++) {
                    int key = array.getValue(i);
                    int j = i - 1;
                    // Moves the numbers of array[0,...,i-1] which are greater than the key, one
                    // position ahead so the key can be inserted.
                    while (j >= 0 && array.getValue(j) > key) {
                        array.move(j + 1, array.getValue(j));
                        j--;
                        array.setAccessCount(array.getAccessCount() + 1);
                    }
                    // Moves the key to the correct position and sleeps
                    array.move(j + 1, key);
                    try {
                        Thread.sleep(104 - (array.getSpeed() * 2));
                    } catch (Exception e) {
                    }
                }
            }
        });
        Insertion.start();
    }

}
