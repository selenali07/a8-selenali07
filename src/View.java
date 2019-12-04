

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class View extends JPanel implements ActionListener, PropertyChangeListener, ChangeListener {
	private JLabel _message;
	private int size;
	private JSpotBoard _board;
	private List<ViewListener> listener;
	protected int amountChanged;
	private ArrayList<ChangeListener> change_listeners;
	private PlayTimer playTimer;
	private int delayValue;
	private JButton startButton;
	


	public View(int size) {
		this.size = size;
		_board = new JSpotBoard(size, size);
		_message = new JLabel();
		delayValue = 10;
		setLayout(new BorderLayout());
		add(_board, BorderLayout.CENTER);

		JSlider sliderSize = new JSlider(10, 500);

		_message = new JLabel();
		_message.setText("Welcome to Conway's Game of Life. Click on board to set spot.");
		JPanel reset_message_panel = new JPanel();
		reset_message_panel.setLayout(new BorderLayout());

		reset_message_panel.add(_message, BorderLayout.CENTER);
		add(reset_message_panel, BorderLayout.SOUTH);

		// JButton start = new JButton("Start");
		JButton step = new JButton("Step");
		JButton clear = new JButton("Clear");
		JButton random = new JButton("Random");
		JPanel settingsPanel = new JPanel();
		startButton = new JButton("Start");
		startButton.setActionCommand("start");
		settingsPanel.add(startButton);
		settingsPanel.add(step);
		settingsPanel.add(clear);
		settingsPanel.add(random);

		JLabel delayText = new JLabel("Delay increment in milliseconds from 10 to 1000");
		JSlider delaySlider = new JSlider(10, 1000, 10);
		delayValue = delaySlider.getValue();
		delaySlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!delaySlider.getValueIsAdjusting()) {
					delayValue = delaySlider.getValue();
				}
			}
		});

		JPanel sliderPanel = new JPanel();
		sliderPanel.setLayout(new GridLayout(2, 4));

		sliderPanel.add(delayText);
		sliderPanel.add(delaySlider);
		add(sliderPanel, BorderLayout.NORTH);
		for (Component c : settingsPanel.getComponents()) {
			JButton b = (JButton) c;
			b.addActionListener(this);
		}
		settingsPanel.setPreferredSize(new Dimension(500, 30));
		add(settingsPanel, BorderLayout.SOUTH);
		listener = new ArrayList<ViewListener>();
		change_listeners = new ArrayList<ChangeListener>();
		setFocusable(true);
	}

	public JSpotBoard getBoard() {
		return _board;
	}

	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		String text = button.getText();
		String cmd = e.getActionCommand();

		if (text.contentEquals("Clear")) {
			event(new ClearEvent());
		} else if (text.contentEquals("Random")) {
			event(new RandomEvent());
		} else if (text.contentEquals("Step")) {
			event(new StepEvent());
		} else if (text.contentEquals("Start") || text.contentEquals("Stop")) {
			if (cmd.equals("start")) {
				playTimer = new PlayTimer(this, delayValue);
				playTimer.start();

				startButton.setText("Stop");
				startButton.setActionCommand("stop");
			} else if (cmd.contentEquals("stop")) {
				playTimer.halt();
				try {
					playTimer.join();
				} catch (InterruptedException ahhhh) {
				}
				startButton.setText("Start");
				startButton.setActionCommand("start");

			}

		}
	}

	public void addViewListener(ViewListener event) {
		listener.add(event);
	}

	public void removeLifeViewListener(ViewListener event) {
		listener.remove(event);
	}

	public void event(ViewEvent e) {
		for (ViewListener event : listener) {
			event.handleViewEvent(e);
		}
	}

	public void updateView(boolean[][] points) {
		for (int i = 0; i < points.length; i++) {
			for (int j = 0; j < points[0].length; j++) {
				if (points[i][j]) {
					_board.getSpotAt(i, j).setSpot();
				} else {
					_board.getSpotAt(i, j).clearSpot();
				}
			}
		}
		repaint();
	}

	public void addChangeListener(ChangeListener l) {
		change_listeners.add(l);
	}

	public void removeChangeListener(ChangeListener l) {
		change_listeners.remove(l);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub

	}

	public void valueChanged(ListSelectionEvent e) {

	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub

	}
}