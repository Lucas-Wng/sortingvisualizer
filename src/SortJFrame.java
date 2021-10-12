// Lucas Wang and Theo Sampaleanu
// April 6 2021
// ICS3u7 Ms. Strelkovska
// Final Project Sorting Algorithm Visualizer
// SortJFrame Class - controlling class that runs the
// whole sorting visualizer.

import java.awt.Container;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import java.awt.event.ComponentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;
import java.awt.Dimension;
import java.awt.Color;

public class SortJFrame extends JFrame implements ActionListener, ComponentListener {
	private SortJPanel SortJPanel;
	private Container c;
	private static int WIN_H = 718, WIN_W = 1276;
	private JMenuBar menuBar = new JMenuBar();
	private JMenu algorithms = new JMenu("Sorting Algorithms"), styles = new JMenu("Display Styles"),
			options = new JMenu("Options");
	private JMenuItem bubble, selection, insertion, impractical, quick, merge, heap, load, shuffle, reset, dark, light,
			mint, maroon, orange, mcdonalds;
	public static JCheckBoxMenuItem timeComplexity, sound, sliders, swaps, access;
	private ImageIcon ii;

	public SortJFrame(String title) {
		super(title);
		// icon loading
		ii = new ImageIcon("./Assets/logo.png");
		this.setIconImage(ii.getImage());
		// panel creation
		SortJPanel = new SortJPanel();
		c = this.getContentPane();
		c.setLayout(new BorderLayout());
		c.add(SortJPanel, BorderLayout.CENTER);
		c.addComponentListener(this);
		// JMenuBar itemization - Menu Creation
		algorithms.setMnemonic(KeyEvent.VK_A);
		algorithms.getAccessibleContext().setAccessibleDescription("sorting menu");
		styles.setMnemonic(KeyEvent.VK_B);
		styles.getAccessibleContext().setAccessibleDescription("styling menu");
		options.setMnemonic(KeyEvent.VK_C);
		options.getAccessibleContext().setAccessibleDescription("options menu");
		// algorithm JMenuItems
		bubble = new JMenuItem("Bubble Sort", KeyEvent.VK_DOWN); // name of menu item and key to move through it
		bubble.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK)); // creation of shortcut
		bubble.addActionListener(this); // addition of required action listener to detect use of the JMenuItem
		algorithms.add(bubble);
		selection = new JMenuItem("Selection Sort", KeyEvent.VK_DOWN);
		selection.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));
		selection.addActionListener(this);
		algorithms.add(selection);
		insertion = new JMenuItem("Insertion Sort", KeyEvent.VK_DOWN);
		insertion.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, ActionEvent.ALT_MASK));
		insertion.addActionListener(this);
		algorithms.add(insertion);
		impractical = new JMenuItem("Impractical Sort", KeyEvent.VK_DOWN);
		impractical.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4, ActionEvent.ALT_MASK));
		impractical.addActionListener(this);
		algorithms.add(impractical);
		quick = new JMenuItem("Quick Sort", KeyEvent.VK_DOWN);
		quick.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_5, ActionEvent.ALT_MASK));
		quick.addActionListener(this);
		algorithms.add(quick);
		merge = new JMenuItem("Merge Sort", KeyEvent.VK_DOWN);
		merge.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_6, ActionEvent.ALT_MASK));
		merge.addActionListener(this);
		algorithms.add(merge);
		heap = new JMenuItem("Heap Sort", KeyEvent.VK_DOWN);
		heap.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_7, ActionEvent.ALT_MASK));
		heap.addActionListener(this);
		algorithms.add(heap);
		// theme JMenuItems
		dark = new JMenuItem("Dark", KeyEvent.VK_DOWN);
		dark.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.SHIFT_MASK));
		dark.addActionListener(this);
		styles.add(dark);
		light = new JMenuItem("Light", KeyEvent.VK_DOWN);
		light.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.SHIFT_MASK));
		light.addActionListener(this);
		styles.add(light);
		mint = new JMenuItem("Mint", KeyEvent.VK_DOWN);
		mint.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, ActionEvent.SHIFT_MASK));
		mint.addActionListener(this);
		styles.add(mint);
		maroon = new JMenuItem("Maroon", KeyEvent.VK_DOWN);
		maroon.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4, ActionEvent.SHIFT_MASK));
		maroon.addActionListener(this);
		styles.add(maroon);
		orange = new JMenuItem("Orange", KeyEvent.VK_DOWN);
		orange.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_5, ActionEvent.SHIFT_MASK));
		orange.addActionListener(this);
		styles.add(orange);
		mcdonalds = new JMenuItem("Mcdonald's", KeyEvent.VK_DOWN);
		mcdonalds.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_6, ActionEvent.SHIFT_MASK));
		mcdonalds.addActionListener(this);
		styles.add(mcdonalds);
		// utility JMenuItems
		load = new JMenuItem("Load Array", KeyEvent.VK_DOWN);
		load.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.CTRL_MASK));
		load.addActionListener(this);
		shuffle = new JMenuItem("Shuffle", KeyEvent.VK_DOWN);
		shuffle.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.CTRL_MASK));
		shuffle.addActionListener(this);
		reset = new JMenuItem("Reset", KeyEvent.VK_DOWN);
		reset.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, ActionEvent.CTRL_MASK));
		reset.addActionListener(this);
		// option JMenuItems
		timeComplexity = new JCheckBoxMenuItem("Toggle Time Complexity");
		timeComplexity.setMnemonic(KeyEvent.VK_T);
		timeComplexity.setState(true); // initialize boxes as checked
		options.add(timeComplexity);
		sound = new JCheckBoxMenuItem("Toggle Sound");
		sound.setMnemonic(KeyEvent.VK_S);
		sound.setState(true);
		options.add(sound);
		sliders = new JCheckBoxMenuItem("Toggle Sliders");
		sliders.setMnemonic(KeyEvent.VK_L);
		sliders.setState(true);
		options.add(sliders);
		swaps = new JCheckBoxMenuItem("Toggle Move Count");
		swaps.setMnemonic(KeyEvent.VK_M);
		swaps.setState(true);
		options.add(swaps);
		access = new JCheckBoxMenuItem("Toggle Access Count");
		access.setMnemonic(KeyEvent.VK_O);
		access.setState(true);
		options.add(access);

		menuBar.add(algorithms);
		menuBar.add(styles);
		menuBar.add(javax.swing.Box.createHorizontalGlue()); // start sticking items to the right side
		menuBar.add(load);
		menuBar.add(shuffle);
		menuBar.add(reset);
		menuBar.add(options);
		this.setJMenuBar(menuBar);
	}

	// setters and getters
	public static void setHeight(int height) {
		WIN_H = height;
	}

	public static void setWidth(int width) {
		WIN_W = width;
	}

	public static int getWindowsH() {
		return WIN_H;
	}

	public static int getWindowsW() {
		return WIN_W;
	}

	public static void main(String[] args) throws Exception {
		SortJFrame frame = new SortJFrame("Sorting Visualizer");
		Dimension minSize = new Dimension(1270, 710);
		frame.setSize(WIN_W, WIN_H);
		frame.setMinimumSize(minSize);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
	}

	// resizing method
	@Override
	public void componentResized(ComponentEvent ce) {
		setHeight(this.getHeight());
		setWidth(this.getWidth());
	}

	// other empty required methods
	@Override
	public void componentMoved(ComponentEvent ce) {
	}

	@Override
	public void componentShown(ComponentEvent ce) {
	}

	@Override
	public void componentHidden(ComponentEvent ce) {
	}

	// action event handling
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bubble) {
			SortJPanel.bubble();
		}
		if (e.getSource() == selection) {
			SortJPanel.selection();
		}
		if (e.getSource() == insertion) {
			SortJPanel.insertion();
		}
		if (e.getSource() == impractical) {
			SortJPanel.impractical();
		}
		if (e.getSource() == quick) {
			SortJPanel.quick();
		}
		if (e.getSource() == merge) {
			SortJPanel.merge();
		}
		if (e.getSource() == heap) {
			SortJPanel.heap();
		}
		if (e.getSource() == shuffle) {
			SortJPanel.shuffleAnimation();
		}
		if (e.getSource() == reset) {
			SortJPanel.reset();
		}
		if (e.getSource() == load) {
			SortJPanel.load();
		}
		if (e.getSource() == dark) {
			SortJPanel.setTheme(Color.GRAY, Color.BLACK, Color.WHITE);
		}
		if (e.getSource() == light) {
			SortJPanel.setTheme(Color.GRAY, Color.WHITE, Color.BLACK);
		}
		if (e.getSource() == mint) {
			SortJPanel.setTheme(new Color(0, 32, 63), new Color(173, 239, 209), Color.BLACK);
		}
		if (e.getSource() == maroon) {
			SortJPanel.setTheme(new Color(128, 0, 0), new Color(255, 218, 185), Color.BLACK);
		}
		if (e.getSource() == orange) {
			SortJPanel.setTheme(new Color(210, 96, 26), new Color(29, 60, 69), Color.WHITE);
		}
		if (e.getSource() == mcdonalds) {
			SortJPanel.setTheme(Color.RED, Color.YELLOW, Color.BLACK);
		}
	}
}
