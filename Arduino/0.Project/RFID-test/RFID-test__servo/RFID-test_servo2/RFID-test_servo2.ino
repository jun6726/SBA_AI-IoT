#include <SPI.h>
#include <MFRC522.h>

#define SS_PIN 10
#define RST_PIN 9

MFRC522 RFID(SS_PIN, RST_PIN);

#include <Servo.h>
#define servoPin 3 
Servo servo;
int pos = 0;

void setup() {
  servo.attach(servoPin);
  servo.write(0);
  Serial.begin(9600);
  SPI.begin();
  RFID.PCD_Init();
  Serial.println("카드를 인식해주세요");
}

void loop() {
  if ( ! RFID.PICC_IsNewCardPresent()) {
    Serial.println("접촉 된 카드가 없습니다.");
    delay(1500);
    return;
  }

  if ( ! RFID.PICC_ReadCardSerial()){
    Serial.println("카드를 다시 대주세요");
    delay(1500);
    return;
  }
  
  Serial.print("UID tag : ");
  String content = "";
  byte letter;
  for ( byte i = 0; i < RFID.uid.size; i++){
    Serial.print(RFID.uid.uidByte[i] < 0x10 ? " 0" : "");
    Serial.print(RFID.uid.uidByte[i], HEX);
      content.concat(String(RFID.uid.uidByte[i] < 0x10 ? " 0": ""));
      content.concat(String(RFID.uid.uidByte[i],HEX));
  }

  Serial.println();
  Serial.print("Message : ");
  content.toUpperCase();

  if (content.substring(1) == "85 7C FB D1"){
    Serial.println("일치하였습니다");
    servo.write(160);
    delay(2000);
  }
  else {
    Serial.println("인증 실패하였습니다");
    delay(2000);
  }
}
