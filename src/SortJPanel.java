// Lucas Wang and Theo Sampaleanu
// April 6 2021
// ICS3u7 Ms. Strelkovska
// Final Project Sorting Algorithm Visualizer
// SortJPanel Class - main processing class; is the contianer
// for all the elements that make up the sorting visualizer.

import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JSlider;
import java.util.Collections;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SortJPanel extends JPanel implements ChangeListener {
    public ArrayList<Integer> array;
    private int size, speed, bar_width, WIN_H, WIN_W, swap1, swap2, pivot, moveCount, accessCount;
    private Color barColor, backgroundColor, textColor;
    private double bar_height_ratio;
    private String timeComplexity = "";
    private JSlider sizeSlider = new JSlider(1, 200), speedSlider = new JSlider(1, 51),
            shuffleSlider = new JSlider(100, 500);
    private JFileChooser fc;
    private File def = new File(""), openedFile = null;
    private MidiPlayer soundPlayer;

    public SortJPanel() {
        super();
        // variable initializations
        WIN_H = SortJFrame.getWindowsH();
        WIN_W = SortJFrame.getWindowsW();
        barColor = Color.GRAY;
        backgroundColor = Color.BLACK;
        textColor = Color.WHITE;
        size = 100;
        speed = 26;
        Shuffle.setShuffle(300);
        pivot = -1;
        moveCount = 0;
        accessCount = 0;
        bar_width = (WIN_W - size * 2) / size;
        array = new ArrayList<Integer>();
        newArray();
        resetHeightWidth();
        fc = new JFileChooser();
        // slider creation
        sizeSlider.setValue(100);
        sizeSlider.addChangeListener(this);
        add(sizeSlider);
        speedSlider.setValue(26);
        speedSlider.addChangeListener(this);
        add(speedSlider);
        shuffleSlider.setValue(300);
        shuffleSlider.addChangeListener(this);
        add(shuffleSlider);
    }

    // new array creation method
    public void newArray() {
        array.clear();
        for (int i = 1; i <= size; i++) {
            // array.add((int) (Math.random() * size)); - implement if you want random
            // values
            array.add(i);
        }
        soundPlayer = new MidiPlayer(Collections.max(array));
        bar_height_ratio = (WIN_H - 100) / Collections.max(array);
    }

    public void paintComponent(Graphics g) {
        Graphics2D graphics = (Graphics2D) g; // Graphics2D elements are easier on processing
        // equalization of WIN_H and WIN_W variables accross classes
        WIN_H = SortJFrame.getWindowsH();
        WIN_W = SortJFrame.getWindowsW();
        // reset bar and window formatting
        resetHeightWidth();
        super.paintComponent(graphics);
        this.setBackground(backgroundColor);
        // drawing of bars
        g.setColor(barColor);
        for (int i = 0; i < size; i++) {
            int height = (int) Math.round(array.get(i) * bar_height_ratio);
            int xRect = 2 + (bar_width + 2) * i;
            int yRect = WIN_H - height - 40;
            // making the swapping bars red
            if (i == swap1 || i == swap2) {
                g.setColor(Color.red);
                g.fillRect(xRect, yRect, bar_width, height);
                continue;
            }
            // making the pivot bar blue
            if (i == pivot) {
                g.setColor(Color.blue);
                g.fillRect(xRect, yRect, bar_width, height);
                continue;
            }
            // making the rest of the bars
            g.setColor(barColor);
            g.fillRect(xRect, yRect, bar_width, height);
        }
        // making all bars green if successfully sorted
        ArrayList<Integer> sortedList = new ArrayList<>(array);
        Collections.sort(sortedList);
        if (array.equals(sortedList)) {
            g.setColor(Color.green);
            for (int i = 0; i < size; i++) {
                int height = (int) Math.round(array.get(i) * bar_height_ratio);
                int xRect = 2 + (bar_width + 2) * i;
                int yRect = WIN_H - height - 40;
                g.fillRect(xRect, yRect, bar_width, height);
            }
        }
        // correspondant check for slider toggle
        if (!SortJFrame.sliders.getState()) {
            sizeSlider.setVisible(false);
            speedSlider.setVisible(false);
            shuffleSlider.setVisible(false);
        }
        // correspondant check for slider toggle - text portion
        if (SortJFrame.sliders.getState()) {
            sizeSlider.setVisible(true);
            speedSlider.setVisible(true);
            shuffleSlider.setVisible(true);
            // slider text drawing
            g.setColor(textColor);
            g.setFont(new Font("Helvetica", Font.PLAIN, 20));
            drawString(g, "Size: " + size, sizeSlider.getX() + 62, sizeSlider.getY() + 20);
            drawString(g, "Speed", speedSlider.getX() + 68, speedSlider.getY() + 20);
            g.setFont(new Font("Helvetica", Font.PLAIN, 17));
            drawString(g, "Shuffle amount: " + Shuffle.getShuffle(), shuffleSlider.getX() + 26,
                    shuffleSlider.getY() + 22);
        }
        // time complexity, moves, and array access text drawing
        g.setFont(new Font("Helvetica", Font.PLAIN, 20));
        g.setColor(textColor);
        if (SortJFrame.swaps.getState())
            drawString(g, "Moves done: " + moveCount, 20, 100);
        if (SortJFrame.timeComplexity.getState())
            drawString(g, timeComplexity, 20, 20);
        if (SortJFrame.access.getState())
            drawString(g, "Array accessed: " + accessCount + " times", 20, 125);
    }

    // custom draw string method to allow for using drawString on Strings with
    // newline characters
    public void drawString(Graphics g, String text, int x, int y) {
        for (String line : text.split("\n"))
            g.drawString(line, x, y += g.getFontMetrics().getHeight());
    }

    // shuffle method
    public void shuffleAnimation() {
        pivot = -1;
        moveCount = 0;
        accessCount = 0;
        timeComplexity = "";
        new Shuffle().runShuffle(this);
    }

    // all algorithm methods
    public void bubble() {
        moveCount = 0; // reset move and access counts
        accessCount = 0;
        timeComplexity = "Average Time Complexity: \u03F4(n\u00B2)\nBest Time Complexity: \u03A9(n)\nWorst Time Complexity: O(n\u00B2)";
        new BubbleSort().runSort(this); // run the algorithm from its class
    }

    public void insertion() {
        moveCount = 0;
        accessCount = 0;
        timeComplexity = "Average Time Complexity: \u03F4(n\u00B2)\nBest Time Complexity: \u03A9(n)\nWorst Time Complexity: O(n\u00B2)";
        new InsertionSort().runSort(this);
    }

    public void selection() {
        moveCount = 0;
        accessCount = 0;
        timeComplexity = "Average Time Complexity: \u03F4(n\u00B2)\nBest Time Complexity: \u03A9(n\u00B2)\nWorst Time Complexity: O(n\u00B2)";
        new SelectionSort().runSort(this);
    }

    public void impractical() {
        moveCount = 0;
        accessCount = 0;
        new ImpracticalSort().runSort(this);
        timeComplexity = "Average Time Complexity: \u03F4(n*n!)\nBest Time Complexity: \u03A9(n)\nWorst Time Complexity: Infinite";
    }

    public void merge() {
        moveCount = 0;
        accessCount = 0;
        timeComplexity = "Average Time Complexity: \u03F4(n log(n))\nBest Time Complexity: \u03A9(n log(n))\nWorst Time Complexity:  O(n log(n))";
        new MergeSort().runSort(this);
    }

    public void quick() {
        moveCount = 0;
        accessCount = 0;
        timeComplexity = "Average Time Complexity: \u03F4(n log(n))\nBest Time Complexity: \u03A9(n log(n))\nWorst Time Complexity:  O(n\u00B2)";
        pivot = arraySize() - 1;
        new QuickSort().runSort(this);
    }

    public void heap() {
        moveCount = 0;
        accessCount = 0;
        timeComplexity = "Average Time Complexity: \u03F4(n log(n))\nBest Time Complexity: \u03A9(n log(n))\nWorst Time Complexity:  O(n log(n))";
        new HeapSort().runSort(this);
    }

    // setters and getters
    public int arraySize() {
        return array.size();
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int spd) {
        speed = spd;
    }

    public int getValue(int index) {
        return array.get(index);
    }

    public void setComplexity(String change) {
        timeComplexity = change;
    }

    public String getComplexity() {
        return timeComplexity;
    }

    public ArrayList<Integer> getArray() {
        return array;
    }

    public void setTheme(Color bar, Color background, Color text) {
        barColor = bar;
        backgroundColor = background;
        textColor = text;
    }

    public void setAccessCount(int accessCount) {
        this.accessCount = accessCount;
    }

    public int getAccessCount() {
        return accessCount;
    }

    // user input array obtaining method
    public void userInputArray() {
        try {
            Scanner sc = new Scanner(openedFile);
            array.clear();
            int userSize = 0;
            while (sc.hasNextInt()) {
                array.add(sc.nextInt());
                userSize++;
            }
            setSize(userSize);
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // loading user input array method
    public void load() {
        fc.setSelectedFile(def);
        int returnVal;
        returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            openedFile = fc.getSelectedFile();
            userInputArray();
            resetHeightWidth();
            soundPlayer = new MidiPlayer(Collections.max(array));
            repaint();
        }
    }

    // bar formatting method
    public void resetHeightWidth() {
        bar_width = (WIN_W - size * 2) / size;
        bar_height_ratio = (WIN_H - 100) / Collections.max(array);
    }

    // reset method
    public void reset() {
        setSize(100);
        newArray();
        sizeSlider.setValue(100);
        speedSlider.setValue(26);
        shuffleSlider.setValue(300);
        timeComplexity = "";
        resetHeightWidth();
        repaint();
    }

    // swap method
    public void swap(int first, int second) {
        moveCount++; // increase move count
        int temp = array.get(first);
        swap1 = first;
        swap2 = second;
        array.set(first, array.get(second));
        array.set(second, temp);
        // sound creation component
        if (SortJFrame.sound.getState()) {
            soundPlayer.makeSound(array.get(first));
            soundPlayer.makeSound(array.get(second));
        }
        repaint();
    }

    // move method
    public void move(int index, int value) {
        moveCount++; // increase move count
        array.set(index, value);
        swap1 = index;
        swap2 = -1;
        // sound creation component
        if (SortJFrame.sound.getState())
            soundPlayer.makeSound(value);
        repaint();
    }

    // public method to be used in pivoting algorithms' classes
    public void drawPivot(int pivot) {
        this.pivot = pivot;
    }

    // slider event handling
    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider) e.getSource(); // change event source
        // size slider change handling
        if (source == sizeSlider) {
            ArrayList<Integer> sortedList = new ArrayList<>(array);
            Collections.sort(sortedList);
            if (array.equals(sortedList)) {
                if (!source.getValueIsAdjusting())
                    this.setSize(sizeSlider.getValue());
                newArray();
                resetHeightWidth();
                repaint();

            }
        }
        // speed slider change handling
        if (source == speedSlider) {
            if (!source.getValueIsAdjusting()) {
                this.setSpeed(speedSlider.getValue());
            }
            repaint();
        }
        // shuffle amount slider change handling
        if (source == shuffleSlider) {
            if (!source.getValueIsAdjusting()) {
                Shuffle.setShuffle(shuffleSlider.getValue());
            }
            repaint();
        }
    }
}
