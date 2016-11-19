package com.cmjten.arduino.bdaydisplay;

import java.io.*;
import java.util.*;
import com.fazecast.jSerialComm.*;

/**
 * Contains serial port data
 */
public class DisplayController extends Observable implements SerialPortDataListener {
	
	private SerialPort serialPort;
	private int timesBlown;
	private boolean isLit;
	
	/**
	 * Constructor for the DisplayController
	 */
	public DisplayController() {
		try {
			// Gets the first serial port. This assumes that the Arduino Uno is the only 
			// device plugged into a serial port.
			this.serialPort = SerialPort.getCommPorts()[0];
			this.serialPort.addDataListener(this);
			this.serialPort.openPort();
			this.isLit = true;
		}
		catch (ArrayIndexOutOfBoundsException e) {
			// No serial port is detected
			this.serialPort = null;
			this.isLit = false;
			System.out.println("No serial port was detected");
		}
		
		this.timesBlown = 0;
	}
	
	/**
	 * @return	Serial port where the Arduino Uno is connected to
	 */
	public SerialPort getSerialPort() {
		return this.serialPort;
	}
	
	/**
	 * @return	Amount of times candle has been blown
	 */
	public int getTimesBlown() {
		return this.timesBlown;
	}
	
	/**
	 * @return	Candle is lit or not
	 */
	public boolean getIsLit() {
		return this.isLit;
	}
	
	/**
	 * Lights up the candles if the cake exist
	 * @throws IOException
	 */
	public void light() throws IOException {
		if (serialPort != null && !this.isLit) {
			this.isLit = true;
			serialPort.getOutputStream().write(18);
			this.setChanged();
			this.notifyObservers();
		}
	}
	
	/**
	 * Blows the candles if the cake exists
	 * @throws IOException 
	 */
	public void blow() throws IOException {
		if (serialPort != null) {
			System.out.println("Happy Birthday!");
			this.isLit = false;
			this.timesBlown++;
			this.setChanged();
			this.notifyObservers();
			System.out.println(serialPort.getInputStream().read());
		}
	}
	
	/**
	 * Triggers serialEvent when data is available
	 */
	@Override
	public int getListeningEvents() {
		// TODO Auto-generated method stub
		return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
	}
	
	/**
	 * Triggered when data is available
	 */
	@Override
	public void serialEvent(SerialPortEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getEventType() == SerialPort.LISTENING_EVENT_DATA_AVAILABLE) {
			// Blows the candle when data is available
			try {
				this.blow();
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
