#include <deprecated.h>
#include <MFRC522.h>
#include <MFRC522Extended.h>
#include <require_cpp11.h>
#include <Keypad.h>

const byte ROWS = 4;    // 행(rows) 개수
const byte COLS = 4;    // 열(columns) 개수
int i = 0;
int keypad_pin = 13;

char keys[ROWS][COLS] = {
  {'1','2','3','A'},
  {'4','5','6','B'},
  {'7','8','9','C'},
  {'*','0','#','D'}
};

char PW[4] = {};
char PWSet[4] = {'1','2','3','4'};
byte rowPins[ROWS] = {6, 7, 8, 9};   // R1, R2, R3, R4 단자가 연결된 아두이노 핀 번호
byte colPins[COLS] = {5, 4, 3, 2};   // C1, C2, C3, C4 단자가 연결된 아두이노 핀 번호
 
Keypad keypad = Keypad( makeKeymap(keys), rowPins, colPins, ROWS, COLS );

void setup() {
  Serial.begin(9600);
  pinMode(keypad_pin, OUTPUT);
}
   
void loop() {
  char key = keypad.getKey();
  
  if(key){
    Password(key);
  }
}

void Password(char key){  
     switch(key) {
      case 'A': 
        Serial.println("  Back Space");
        if (i < 0){
          Serial.println("지울 값이 없습니다.");
        }
        else{
          i--;
          for(int j=0; j<i; j++){
            Serial.print(PW[j]);
          }
        }
        break;
        
      case 'B': 
        Serial.println("  Clear");
         for(int i = 3; i>0; i--){ 
          PW[i] = {};
         }
         i=0;
        break;
        
      default:
        PW[i] = key;
        Serial.print(PW[i]);
        i++;

        //비밀번호 4자리 입력시 
        if (i==4){
          for(int k=0; k<4; k++)
          {
            if(PWSet[k] != PW[k]){
              Serial.println("  비밀번호 불일치 : LOCK  "); 
                  PW[4] = {};
                i=0;
              break;
            }
          }
          if(i!=0){
             Serial.println("  비밀번호 일치 : OPEN  ");   
             PW[4] = {}; 
             i=0;

             digitalWrite(keypad_pin, HIGH);
             delay(500);
             digitalWrite(keypad_pin, LOW);
             }
        }  
        break; 
    }
}
