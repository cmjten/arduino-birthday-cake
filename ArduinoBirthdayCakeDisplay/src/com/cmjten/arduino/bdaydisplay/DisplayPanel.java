package com.cmjten.arduino.bdaydisplay;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;

/**
 * Panel that displays the message
 */
public class DisplayPanel extends JPanel implements Observer, MouseListener {
	
	private DisplayController displayController;
	private BufferedImage birthdayMessage;
	private BufferedImage blowMessage;
	private BufferedImage noPortMessage;
	
	/**
	 * Constructor
	 * @param displayController	controller that contains serial port data
	 */
	public DisplayPanel(DisplayController displayController) {
		this.displayController = displayController;
		this.displayController.addObserver(this);
		this.addMouseListener(this);
		
		try {
			// Gets the images required by the program
			this.birthdayMessage = ImageIO.read(getClass().getResource("/cake.png"));
			this.blowMessage = ImageIO.read(getClass().getResource("/blow_on_the_cake.png"));
			this.noPortMessage = ImageIO.read(getClass().getResource("/no_port_detected.png"));
		} 
		catch (IOException e) {
			// If at least one of the images are not found, sets all instance
			// variables for the images to null
			this.birthdayMessage = null;
			this.blowMessage = null;
			this.noPortMessage = null;
			System.out.println("One or more image file(s) not found");
		}
	}
	
	/**
	 * Paints the panel
	 * @param g	Graphics library
	 */
	public void paintComponent(Graphics g) {
		// Allows usage of advanced graphics library
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;	// Advanced Graphics library
		this.setBackground(Color.WHITE);
		
		if (this.birthdayMessage != null) {
			// No need to check if the other messages are not null because it's
			// either all images are found or all are null
			this.displayImages(g2d);
		}
		else {
			this.displayText(g2d);
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
	
	/**
	 * Displays the images if the image files exist
	 * @param g2d	Graphics library
	 */
	public void displayImages(Graphics2D g2d) {
		if (this.displayController.getSerialPort() == null) {
			// No port detected
			g2d.drawImage(this.noPortMessage, 0, 0, 480, 360, null);
		}
		else if (!this.displayController.getIsLit()) {
			// After blowing the candles, displays the birthday message and the 
			// number of times the candles have been blown 
			g2d.drawImage(this.birthdayMessage, 0, 0, 480, 360, null);
			
			String timesBlown = "Times blown: " + this.displayController.getTimesBlown();
			g2d.drawString(timesBlown, 5, 360);
		}
		else {
			// If the candles are lit, displays a message to blow the candles
			g2d.drawImage(this.blowMessage, 0, 0, 480, 360, null);
		}
	}
	
	/**
	 * Displays text if at least one of the image files does not exist
	 * @param g2d	Graphics library
	 */
	public void displayText(Graphics2D g2d) {
		if (this.displayController.getSerialPort() == null) {
			// No port detected
			g2d.drawString("No port detected", 5, 360);
		}
		else if (!this.displayController.getIsLit()) {
			// After blowing the candles, displays the birthday message and the 
			// number of times the candles have been blown 
			g2d.drawString("Happy birthday!", 5, 340);
			
			String timesBlown = "Times blown: " + this.displayController.getTimesBlown();
			g2d.drawString(timesBlown, 5, 360);
		}
		else {
			// If the candles are lit, displays a message to blow the candles
			g2d.drawString("Blow on the cake", 5, 360);
		}
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
		} 
		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
