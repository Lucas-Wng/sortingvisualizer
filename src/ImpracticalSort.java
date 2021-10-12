// Lucas Wang and Theo Sampaleanu
// April 6 2021
// ICS3u7 Ms. Strelkovska
// Impractical Sort a.k.a. Bogo Sort Class
// Randomly swaps numbers and checks to see if 
// it is sorted.

import java.util.ArrayList;
import java.util.Collections;

public class ImpracticalSort extends Algorithms {

    public void runSort(SortJPanel array) {
        // Uses multithreading to animate and sleep simultaneously
        Thread impractical = new Thread(new Runnable() {
            public void run() {
                // Calculates the estimated average time until it will be sorted (Uses the
                // formula n*n!)
                double seconds, fact = 1.0;
                // Calculates the factorial of the size of the array
                for (double i = 1; i <= (double) array.arraySize(); i++) {
                    fact = fact * i;
                }
                // Calculates the estimated time in seconds and converts it to different units
                // of time
                seconds = ((array.arraySize() * fact) * (52 - array.getSpeed())) / 1000;
                if (seconds <= 60000 && seconds > 1)
                    array.setComplexity(array.getComplexity() + "\n\n\nETA: " + seconds / 60 + " minutes");
                else if (seconds > 60000 && seconds <= 86400000)
                    array.setComplexity(array.getComplexity() + "\n\n\nETA: " + seconds / 86400 + " days");
                else
                    array.setComplexity(array.getComplexity() + "\n\n\nETA: " + seconds / 31536000 + " years");
                // Uses a while loop to compare the array to a sorted array
                ArrayList<Integer> sortedList = new ArrayList<>(array.getArray());
                Collections.sort(sortedList);
                while (!(array.getArray().equals(sortedList))) {
                    // Swaps random indexes and sleeps
                    array.swap((int) (Math.random() * array.arraySize()), (int) (Math.random() * array.arraySize()));
                    array.setAccessCount(array.getAccessCount() + 1);
                    try {
                        Thread.sleep(52 - (array.getSpeed()));
                    } catch (Exception ex) {
                    }
                    // This fixes a bug where Impractical Sort continues when reset
                    sortedList = new ArrayList<>(array.getArray());
                    Collections.sort(sortedList);
                }
            }
        });
        impractical.start();
    }
}
