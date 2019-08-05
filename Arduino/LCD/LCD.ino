#include <LiquidCrystal.h>
  LiquidCrystal lcd(12,11,2,3,4,5);
 
void setup() {
  lcd.begin(16,2);
  pinMode(8, OUTPUT);
  pinMode(9, INPUT);
}

void loop() {
//  int light = analogRead(A0);
//  
//  lcd.clear();
//  lcd.print("Light : ");
//  lcd.setCursor(0,1);
//  lcd.print(light);
//  delay(500);

  digitalWrite(8, HIGH);
  delayMicroseconds(10);
  digitalWrite(8, LOW);

  long duration = pulseIn(9, HIGH);

  if(duration == 0){
    return;
  }

  long distance = duration / 58.2;

  lcd. clear();
  lcd.print("Distance : ");
  lcd.setCursor(0,1);
  lcd.print(distance);
  lcd.print("cm");
  delay(500);
}
