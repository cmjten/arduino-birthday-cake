package com.cmjten.arduino.bdaydisplay;

import java.awt.*;
import java.io.IOException;
import javax.swing.*;

/**
 * Arduino Uno Birthday Cake
 * 
 * The Arduino Uno sketch simulates a birthday cake by using a piezo and LEDs.
 * When the user blows on the piezo, the LEDs turn off. The sketch then sends a 
 * signal to this Java program through the serial port to display a birthday
 * message. This Java program is also able to send a signal back to the Arduino
 * Uno to relight the LEDs.
 * 
 * External libraries required:
 * - jSerialComm by Fazecast: https://github.com/Fazecast/jSerialComm (jar file in lib folder)
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
	 * @throws IOException 
	 */
	public BDayDisplay() {
		this.setTitle("Arduino Uno Cake");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setSize(480, 400);
		this.setResizable(false);
		
		// The panel on which the message will be displayed
		this.add(new DisplayPanel(new DisplayController()), BorderLayout.CENTER);
		
		this.setVisible(true);
	}
}
