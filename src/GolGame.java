
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;




public class GolGame {
	private static int value;
	protected static View view;
	private static Model model;
	protected static Controller controller;

	public static void main(String[] args) {
		JLabel text = new JLabel("Grid Size: " + 10);
		JFrame main_frame = new JFrame();
		main_frame.setTitle("Game Of Life");
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel top_panel = new JPanel();
		top_panel.setLayout(new BorderLayout());
		main_frame.setContentPane(top_panel);
		
		JPanel gridText = new JPanel();
		gridText.setLayout(new GridLayout(2, 1));
	/*	String cuteMessage = JOptionPane.showInputDialog("Welcome to Conways Game of Life" 
				+" Im sorry this is super glitchy"
				+ " when the board size is over 100,"
				+ " but i tried super hard."
				);*/
	//	String plzGiveMeAnA = JOptionPane.showInputDialog("I <3 Mike Watt");
		JSlider sliderSize = new JSlider(10, 500, 10);
		sliderSize.setPreferredSize(new Dimension(100, 20));
		//sliderSize.setValue(10);
		value = sliderSize.getValue();
		sliderSize.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent e) {
				if(!sliderSize.getValueIsAdjusting()) {

				JSlider gridValue = (JSlider)e.getSource();
				
				value = gridValue.getValue();
				
				model = new Model(value);
				view = new View(value);
				controller = new Controller(view, model);
				
				JLabel text = new JLabel("Grid Size: " + gridValue.getValue());
				top_panel.removeAll();
				gridText.removeAll();
				gridText.add(text);
				gridText.add(sliderSize);
				top_panel.add(gridText, BorderLayout.SOUTH);
				top_panel.add(view, BorderLayout.CENTER);
				
				top_panel.updateUI();
				}
			}
		});
		
		gridText.add(text);
		gridText.add(sliderSize);
		
		top_panel.add(gridText, BorderLayout.SOUTH);
		
		model = new Model(value);
		view = new View(value);
		top_panel.add(view, BorderLayout.CENTER);
		
		controller = new Controller(view, model);
		top_panel.add(view, BorderLayout.CENTER);
		

		main_frame.pack();
		main_frame.setVisible(true);	
	}
}

