import processing.serial.*;

Serial ser = null;
PImage birthday = null;
PImage blow = null;
PImage noSerial = null;
boolean isLit = true;
int timesBlown = 0;
int mode = 0; // 0 for text, 1 for image

void setup() {
  try {
    // Finds serial port
    ser = new Serial(this, Serial.list()[0], 9600);
  }
  catch (Exception e) {
    // Serial port wasn't loaded
    println("No serial port detected");
  }
  try {
    // Load images
    birthday = loadImage("images/cake.png");
    blow = loadImage("images/blow_on_the_cake.png");
    noSerial = loadImage("images/no_port_detected.png");
    mode = 1;
  }
  catch (Exception e) {
    // One or more of the images weren't found
    println("Couldn't load images");
  }
  size(360, 270);
}

void draw() {
  clear();
  background(126, 192, 238);
  if (ser != null) {
    if (ser.available() > 0 && isLit) {
      // Blows the cake
      ser.read();
      isLit = false;
      timesBlown++;
    }
    else if (mousePressed && !isLit) {
      // Resets the cake
      isLit = true;
      ser.write(18);
    }
    if (isLit) {
      // Displays blow on cake message
      if (mode == 0) displayText(1);
      else displayImage(1);
    }
    else {
      // Displays happy birthday message
      if (mode == 0) displayText(0);
      else displayImage(0);
    }
    displayText(3); // Amount of times blown
  }
  else {
    // No serial port detected
    if (mode == 0) displayText(2);
    else displayImage(2);
  }
}

/*
 * Displays the messages in text
 * int message: an integer that is associated with a certain message
 */
void displayText(int message) {
  switch (message) {
    case 0:
      // Happy birthday
      textSize(15);
      textAlign(CENTER);
      fill(0, 0, 0);
      text("Happy birthday!", 180, 135);
      break;
    case 1:
      // Blow on candle
      textSize(15);
      textAlign(CENTER);
      fill(0, 0, 0);
      text("Blow on the candle", 180, 135);
      break;
    case 2:
      // No serial port
      textSize(15);
      textAlign(CENTER);
      fill(0, 0, 0);
      text("No serial port detected", 180, 135);
      break;
    case 3:
      // Amount of times blown
      textSize(15);
      textAlign(LEFT);
      fill(0, 0, 0);
      text("Times blown: " + timesBlown, 10, 260);
      break;
  }
}

/*
 * Displays the image for the messages 
 * int message: an integer that is associated with a certain message
 */
void displayImage(int message) {
  switch (message) {
    case 0:
      // Happy birthday
      image(birthday, 0, 0, 360, 270);
      break;
    case 1:
      // Blow on candle
      image(blow, 0, 0, 360, 270);
      break;
    case 2:
      // No serial port
      image(noSerial, 0, 0, 360, 270);
      break;
  }
}