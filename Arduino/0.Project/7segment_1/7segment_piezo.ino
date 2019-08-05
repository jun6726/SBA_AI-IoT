#include <SevSeg.h>

SevSeg sevseg; //init Segment-object

int buzzerPin = A0; //pin for Sound
int number=31; //Counter value 1-30
static unsigned long timer = millis();

void setup()
{
  Serial.begin(9600);
  
  byte numDigits = 4; //number of digits
  byte digitPins[] = {2, 3, 4, 5}; //digit pins
  byte segmentPins[] = {6, 7, 8, 9, 10, 11, 12, 13}; //Pins Segment
  sevseg.begin(COMMON_CATHODE, numDigits, digitPins, segmentPins);
  sevseg.setBrightness(90);

  pinMode(A3, INPUT);
  pinMode(A4, INPUT);
  pinMode(A5, INPUT);
}

void loop()
{
  static int deciSeconds = 0;

  if(millis() - timer >= 1000) {
    timer += 1000;
    deciSeconds++;
    if(number > 0){
      number--;
    }
     if (deciSeconds == 10000) { // Reset to 0 after counting for 1000 seconds.
          deciSeconds=0;
     }
     sevseg.setNumber(number, 4);
  }

  sevseg.refreshDisplay(); // Must run repeatedly
  
  if(Serial.available()){
    if( Serial.read('1')){ //Keypad에서 비밀번호 일치시 전송 받은 값일 경우 
      millis() - timer = 0;
      setColor(0,0,0);
    }
  }
  CountDown_piezo(timer);
}

void CountDown_piezo (long timer){
  
   if(millis() % 2000 == 0 && timer <= 10000)
   {
      setColor(0, 255, 0);
      tone(buzzerPin, 200, 500); 
   }
   
   if(millis() % 1000 == 0 && timer > 10000)
   {
      setColor(255, 255, 0);
      tone(buzzerPin, 200, 300); 
   }
   
   if(millis() % 500 == 0 && timer > 20000)
   {
      setColor(255,0,0);
      tone(buzzerPin, 200, 200); 
   }
   
   if(millis() % 250 == 0 && timer > 25000 && timer < 31000)
   {    
        setColor(255,0,0); 
        tone(buzzerPin, 200, 150);
   }
   
   if(timer > 30000)
   {     
       tone(buzzerPin, 500, 3000); 
       sevseg.refreshDisplay();
  
       StopCountDown();
   }
}

void StopCountDown(){
  
  for(int color=0; color < 10; color++){  
       setColor(255, 0, 0);
       delay(500);
       setColor(0, 0, 255);
       delay(500);
       }
       setColor(0,0,0);
       delay(5100000);
}

void setColor(int red, int green, int blue)
{
  analogWrite(A3, red);
  analogWrite(A4, green);
  analogWrite(A5, blue); 
}
