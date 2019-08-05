void setup() {
  // put your setup code here, to run once:
  pinMode(13, OUTPUT);
  pinMode(8, INPUT);
}

void loop() {
  // put your main code here, to run repeatedly:
  int value = digitalRead(8);

  if(value == HIGH){
    digitalWrite(13, HIGH);
  }
  else{
    digitalWrite(13,LOW);
  }
}
