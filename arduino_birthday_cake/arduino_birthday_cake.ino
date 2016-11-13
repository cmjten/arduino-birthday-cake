const int piezoPin = A0;
const int ledPin = 2;
int piezoVal = 0;
int ledVal = HIGH;

void setup() {
  // put your setup code here, to run once:
  pinMode(ledPin, OUTPUT);
}

void loop() {
  // put your main code here, to run repeatedly:
  digitalWrite(ledPin, ledVal);
  piezoVal = analogRead(piezoPin);
  if (piezoVal > 1) {
    // Turns off the LEDs if the piezo detects a vibration
    ledVal = LOW;
  }
}
