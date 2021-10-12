// Lucas Wang and Theo Sampaleanu
// April 6 2021
// ICS3u7 Ms. Strelkovska
// Bubble Sort Class
// Compares adjacent numbers and swaps them 
// if they are in the wrong order.

public class BubbleSort extends Algorithms {

    public void runSort(SortJPanel array) {
        // Uses multithreading to animate and sleep simultaneously
        Thread bubbling = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < array.arraySize() - 1; i++) {
                    for (int j = 0; j < array.arraySize() - 1; j++) {
                        // Compares the adjacent numbers
                        if (array.getValue(j) > array.getValue(j + 1)) {
                            // Swaps and sleeps
                            array.setAccessCount(array.getAccessCount() + 1);
                            array.swap(j, j + 1);
                            try {
                                Thread.sleep(52 - (array.getSpeed()));
                            } catch (Exception e) {
                            }
                        }
                    }
                }
            }
        });
        bubbling.start();
    }
}
