int r = 0, g = 0, b = 0;

void setup() {
//  randomSeed(analogRead(0));
  pinMode(4, INPUT);
  pinMode(3, INPUT);
  pinMode(2, INPUT);
}

void loop() {
//  analogWrite(11, random(256));
//  analogWrite(10, random(256));
//  analogWrite(9, random(256));
//  delay(1000);
  if (digitalRead(4) == HIGH){
    ++r;
    if(r > 255) {
      r = 0;
    }
  }

  if (digitalRead(3) == HIGH){
    ++g;
    if(g > 255) {
      g = 0;
    }
  }
  
   if (digitalRead(2) == HIGH){
    ++b;
    if(b > 255) {
      b = 0;
    }
  }
  analogWrite(11, r);
  analogWrite(10, g);
  analogWrite(9, b);

  delay(10);
  

}
