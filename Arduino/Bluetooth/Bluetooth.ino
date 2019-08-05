#include <SoftwareSerial.h>

SoftwareSerial mySerial(2,3);

int LED = 13;
char c;
void setup() {
    Serial.begin(9600);
    mySerial.begin(9600);
    pinMode(LED, OUTPUT);
}

void loop() {
   if (Serial.available()){
    c = Serial.read();
    if(c == 'a'){
        digitalWrite(LED, HIGH);
        delay(500);
        digitalWrite(LED, LOW);
      }
    else if(c == 'b'){
        digitalWrite(LED, HIGH);
    }
    else if(c == 'c'){
        digitalWrite(LED, LOW);
    }
    mySerial.write(Serial.read());    
   }

   
   if (mySerial.available()){
    c = mySerial.read();
       if(c == 'a'){
          digitalWrite(LED, HIGH);
          delay(500);
          digitalWrite(LED, LOW);
      }
       else if(c == 'b'){
          digitalWrite(LED, HIGH);
          delay(500);
      }
        else if(c == 'c'){
          digitalWrite(LED, LOW);
      }
    Serial.write(mySerial.read());  
   }
}
