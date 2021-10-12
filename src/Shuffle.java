// Lucas Wang and Theo Sampaleanu
// April 6 2021
// ICS3u7 Ms. Strelkovska
// Shuffle Animation Class
// Swaps two random numbers 

public class Shuffle {
    private static int numReps = 300;

    public void runShuffle(SortJPanel array) {
        // Uses multithreading to animate and sleep simultaneously
        Thread shuffling = new Thread(new Runnable() {
            public void run() {
                // Repeats for the numReps
                for (int i = 0; i < numReps; i++) {
                    // Swaps two random numbers and sleeps
                    array.swap((int) (Math.random() * array.arraySize()), (int) (Math.random() * array.arraySize()));
                    try {
                        Thread.sleep(2);
                    } catch (Exception e) {
                    }
                }
            }
        });
        shuffling.start();
    }

    // Setters and getters for changing the numReps
    public static void setShuffle(int newNum) {
        numReps = newNum;
    }

    public static int getShuffle() {
        return numReps;
    }
}
