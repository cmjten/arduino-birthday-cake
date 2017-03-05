import processing.serial.*;

Serial ser;
boolean detected = false;
boolean isLit = true;
int timesBlown = 0;

void setup() {
  try {
    ser = new Serial(this, Serial.list()[0], 9600);
  }
  catch (Exception e) {
    ser = null;
  }
  size(200, 200);
}

void draw() {
  clear();
  if (ser != null) {
    if (ser.available() > 0) {
      ser.read();
      isLit = false;
    }
    if (mousePressed && !isLit) {
      isLit = true;
      ser.write(18);
    }
    if (isLit) {
      textMode(1);
    }
    else {
      textMode(0);
    }
  }
  else {
    textMode(2);
  }
}

/*
 * Displays the messages in text
 * int message: an integer that is associated with a certain message
 */
void textMode(int message) {
  switch (message) {
    case 0:
      // Happy birthday
      text("Happy birthday!", 10, 30);
      break;
    case 1:
      // Blow on candle
      text("Blow on the candle", 10, 30);
      break;
    case 2:
      // No serial port
      text("No serial port detected", 10, 30);
      break;
  }
}