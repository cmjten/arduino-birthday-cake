# Arduino Birthday Cake

This project includes an Arduino sketch and a Java program. The Arduino sketch simulates a birthday cake using
LEDs and a piezo. When the user blows on the piezo, it detects a vibration and the Arduino Uno turns off the LEDs.
It also sends a signal to the computer through the serial port.

The Java program receives this signal and displays the birthday message. When the birthday message is clicked, it 
resets the program and sends a signal to the Arduino Uno to relight the LEDs.

###[Demo Video](https://vid.me/ySh1)

### Components
- Arduino Uno
- LEDs x 2
- 220 Ohm Resistors x 2
- 1 Megaohm Resistor x 1
- Piezo

### Required Third-Party Libraries
- [jSerialComm](http://fazecast.github.io/jSerialComm/) for the Java program (included in the lib folder)

### Images

<img src=https://github.com/cmjten/arduino-birthday-cake/blob/master/setup_images/arduino_birthday_cake_setup_lit.png width=600/>

<img src=https://github.com/cmjten/arduino-birthday-cake/blob/master/setup_images/arduino_birthday_cake_setup_unlit.png width=600/>

<img src=https://github.com/cmjten/arduino-birthday-cake/blob/master/setup_images/arduino_birthday_cake_breadboard.png width=600/>
