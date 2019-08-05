int melody[] = {294, 523, 1023};

void setup() {
  pinMode(2, OUTPUT);
  pinMode(3, INPUT);
}

void loop() {
  analogWrite(11, 0);
  analogWrite(10, 0);
  analogWrite(9, 0);
 
  digitalWrite(2, HIGH);
  delayMicroseconds(10);
  digitalWrite(2, LOW);

  long duration = pulseIn(3, HIGH);

  if(duration == 0 ){
    return;
  }
  long distance = duration / 58.2;

    if(distance <10){
      analogWrite(11, 255);
      sound(0);
    } else if( distance <20) {
      analogWrite(10,255);
      sound(1);
    } else if(distance <30){
      analogWrite(9,255);
      sound(2);
    }

  delay(100);
 
}

void sound(int i){
      tone(8, melody[i], 2000*i);
      delay(200+ 200*i);
      noTone(8);
}
