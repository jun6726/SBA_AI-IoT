#include <Wire.h>

#define SLAVE 1

byte count = 0;
char temp;

void setup() {
  Wire.begin(SLAVE);
  Wire.onReceive(receiveFromMaster);

  Wire.onRequest(sendToMaster);
  pinMode(A1, OUTPUT);
  Serial.begin(9600);
}

void loop() {
  if(temp=='o'){
    play();
  }
}

void receiveFromMaster(int bytes){
  char ch[2];
  for(int i=0; i<bytes; i++){
    ch[i] = Wire.read();
  }
  temp = ch[0];
}

void play(){
  digitalWrite(13, HIGH);
  delay(500);
  digitalWrite(13, LOW);
  delay(500);
  temp = 0;
}

void sendToMaster(){
  Wire.write("1th Arduino");
}
