package com.cmjten.bdaydisplay;

import java.io.*;
import com.fazecast.jSerialComm.*;

public class SerialListenerTest implements SerialPortDataListener{
	
	public static void main(String[] args) {
		SerialPort serialPort = SerialPort.getCommPorts()[0]; // Gets the first serial port
		serialPort.addDataListener(new SerialListenerTest());
		serialPort.openPort();
	}
	
	/**
	 * When data is available, serialEvent() is triggered
	 */
	@Override
	public int getListeningEvents() {
		// TODO Auto-generated method stub	
		return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
	}
	
	/**
	 * Determines what should be done when triggered by a listening event
	 */
	@Override
	public void serialEvent(SerialPortEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getEventType() == SerialPort.LISTENING_EVENT_DATA_AVAILABLE) {
			// Prints out the byte received and "Happy Birthday"
			try {
				System.out.println(arg0.getSerialPort().getInputStream().read());
				System.out.println("Happy Birthday!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
