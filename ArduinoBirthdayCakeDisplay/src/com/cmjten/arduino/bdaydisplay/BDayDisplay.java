package com.cmjten.arduino.bdaydisplay;

import java.awt.*;
import javax.swing.*;

/**
 * The frame for the Birthday Display
 */
public class BDayDisplay extends JFrame {

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new BDayDisplay();
			}
		});
	}
	
	/**
	 * Creates the frame of the program and adds the components
	 */
	public BDayDisplay() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setSize(480, 360);
		
		// The panel on which the message will be displayed
		this.add(new DisplayPanel(new DisplayController()), BorderLayout.CENTER);
		
		this.setVisible(true);
	}
}
