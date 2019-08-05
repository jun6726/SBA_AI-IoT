#include <SoftwareSerial.h> 

SoftwareSerial ESP_wifi(2,3); 

void setup() { 
    Serial.begin(9600); 
    ESP_wifi.begin(9600); 
    ESP_wifi.setTimeout(5000); 
    delay(1000); 
    }
     
    void loop() { 
      if (Serial.available()){
         ESP_wifi.write(Serial.read()); 
      } 
      if (ESP_wifi.available()) { 
        Serial.write(ESP_wifi.read()); 
      }
    }
