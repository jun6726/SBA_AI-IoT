#include <Servo.h>

Servo myservo;

void setup() {
//    myservo.attach(9);
}
 
void loop() {
//  for (int i = 0; i <150; ++i)
//  {
//    myservo.write(i);
//    delay(15);
//  }
//    myservo.write(0);
//    delay(1000);

  int val = analogRead(A0);
  int rad = map(val, 0, 1023, 0, 255);
//  myservo.write(rad);

  analogWrite(11, rad);
  delay(100);                                
}
