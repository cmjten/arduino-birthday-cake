const int piezoPin = A0;
const int ledPin = 2;
int piezoVal = 0;
int currentLedVal = HIGH;
int previousLedVal = HIGH;

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  pinMode(ledPin, OUTPUT);
}

void loop() {
  // put your main code here, to run repeatedly:
  digitalWrite(ledPin, currentLedVal);
  piezoVal = analogRead(piezoPin);
  if (piezoVal > 2) {
    // Turns off the LEDs if the piezo detects a vibration
    currentLedVal = LOW;
  }
  if (currentLedVal != previousLedVal && currentLedVal == LOW) {
    // If the sketch detects a state change from HIGH to LOW,
    // it sends a byte 5 through the serial port
    Serial.write(5);
  }
  previousLedVal = currentLedVal;
}
