package com.cmjten.arduino.bdaydisplay;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;
import javax.swing.*;

/**
 * Panel that displays the message
 */
public class DisplayPanel extends JPanel implements Observer, MouseListener {
	
	private DisplayController displayController;
	
	/**
	 * Constructor
	 * @param displayController	controller that contains serial port data
	 */
	public DisplayPanel(DisplayController displayController) {
		this.displayController = displayController;
		this.displayController.addObserver(this);
		this.addMouseListener(this);
	}
	
	/**
	 * Paints the panel
	 * @param g	Graphics library
	 */
	public void paintComponent(Graphics g) {
		// Allows usage of advanced graphics library
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;	// Advanced Graphics library
		
		// ***** CHANGE LATER *****
		if (!this.displayController.getIsLit()) {
			// After blowing the candles, displays the message and the amount of time
			// the candles have been blown (temporary)
			this.setBackground(Color.WHITE);
			g2d.drawString("Happy Birthday!", 0, 10);
			
			String timesBlownString = "Times blown: " + this.displayController.getTimesBlown();
			g2d.drawString(timesBlownString, 0, 25);
		}
		else {
			// If the candles are lit, displays a black background (temporary)
			this.setBackground(Color.BLACK);
		}
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		this.repaint();	// Repaints the panel
	}
	
	/**
	 * @return	DisplayController object that stores serial port data
	 */
	public DisplayController getDisplayController() {
		return this.displayController;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Re-lights the candle after it has been blown by pressing anywhere on the panel
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		try {
			this.displayController.light();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
