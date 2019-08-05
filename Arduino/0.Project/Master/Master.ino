#include <Wire.h>
int SLAVE[1]={1};

void setup() {
  Wire.begin();
  Serial.begin(9600);
}

void loop() {
  if(Serial.available()){
    char e = Serial.read();
    byte c = e - 48;
    if(c<5){
      Wire.beginTransmission(SLAVE[c-1]);

      Wire.write('o');
      Wire.endTransmission(SLAVE[c-1]);

      delay(10);

      i2c_communication(c-1);
      delay(10);
    }
  }
}

void i2c_communication(int i){
  Wire.requestFrom(SLAVE[i], 12);

  for(int j=0; j<12; j++){
    char c = Wire.read();
    Serial.print(c);
  }
  Serial.println();
}
