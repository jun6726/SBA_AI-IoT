void setup() {
  // put your setup code here, to run once:

}

void loop() {
  int light = analogRead(A0);
  int hertz = map(light, 0, 1023, 31, 4978);
  tone(8, hertz, 1000);
  delay(100);
  noTone(8);
  delay(200);
//  int ledLight = map(light, 0, 1023, 255, 0);
//  analogWrite(9, ledLight);
}
