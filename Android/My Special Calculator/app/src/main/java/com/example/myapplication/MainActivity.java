package com.example.myapplication;

import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity implements SensorEventListener {
    Button button0, button1, button2, button3, button4, button5, button6, button7, button8, button9, button10, button11, button12, button13, button14, button15, button16, buttonClear, buttonDel,button_left, button_right, buttondot;
    static EditText editText;
    boolean cal;
    double cal_result;

    private SensorManager sensorManager;
    private android.hardware.Sensor senAccelerometer;

    private long lastUpdate = 0;
    private float last_x, last_y, last_z;
    private static final int SHAKE_THRESHOLD = 700;

    SoundManager sManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        button10 = findViewById(R.id.button10);
        button11 = findViewById(R.id.button11);
        button12 = findViewById(R.id.button12);
        button13 = findViewById(R.id.button13);
        button14 = findViewById(R.id.button14);
        button15 = findViewById(R.id.button15);
        button16 = findViewById(R.id.button16);
        buttonClear = findViewById(R.id.buttonClear);
        buttonDel = findViewById(R.id.buttonDel);
        button_left = findViewById(R.id.button_left);
        button_right = findViewById(R.id.button_right);
        buttondot = findViewById(R.id.buttondot);
        editText = findViewById(R.id.editText);

        sensorManager = (SensorManager) this.getSystemService(SENSOR_SERVICE);
        senAccelerometer = sensorManager.getDefaultSensor(android.hardware.Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText().toString() + 0);
                sManager.play(0);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText().toString() + 1);
                sManager.play(1);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText().toString() + 2);
                sManager.play(2);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText().toString() + 3);
                sManager.play(3);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText().toString() + 4);
                sManager.play(4);
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText().toString() + 5);
                sManager.play(5);
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText().toString() + 6);
                sManager.play(6);
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText().toString() + 7);
                sManager.play(7);
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText().toString() + 8);
                sManager.play(8);
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText().toString() + 9);
                sManager.play(9);
            }
        });
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result4cal = editText.getText().toString();
                Calculator calculator = new Calculator();
                try {
                    String resultVal = calculator.bracketCalMain(result4cal);
                    cal = true;
                    editText.setText(resultVal);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText().toString() + "/");
            }
        });
        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText().toString() + "*");
            }
        });
        button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText().toString() + "+");
            }
        });
        button14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText().toString() + "-");
            }
        });
        buttonDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result4del = String.valueOf(editText.getText());
                editText.setText(result4del.substring(0, result4del.length() - 1));
            }
        });
        button15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cal == false) {
                    Toast.makeText(MainActivity.this, "계산을 완료하고 눌러주세요.", Toast.LENGTH_SHORT).show();
                } else if (cal == true) {
                    //팁 계산
                    cal_result = Double.parseDouble(editText.getText().toString());
                    cal_result =  (cal_result * 1.1);
                    editText.setText(cal_result + "");
                    cal = false;
                }
            }
        });
        button16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cal == false) {
                    Toast.makeText(MainActivity.this, "계산을 완료하고 눌러주세요.", Toast.LENGTH_SHORT).show();
                } else if (cal == true) {
                    //팁 계산

                    cal_result = Double.parseDouble(editText.getText().toString());
                    cal_result = cal_result * 1.15;
                    editText.setText(cal_result + "");
                    cal = false;
                }
            }
        });
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
            }
        });
        button_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText().toString() + "(");
            }
        });
        button_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText().toString() + ")");
            }
        });

        buttondot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText().toString() + ".");
            }
        });

        //soundManager 객체의 참조값 얻어오기
        sManager = SoundManager.getInstance();
        //필요한 객체를 전달해서 사용할 수 있게 초기화 한다.
        sManager.init(this);
        //사운드 등록하기(Thread와 같이 로딩작업을 시작하고 아래 작업으로 넘어간다)
        sManager.addSound(0,R.raw.f_0);
        sManager.addSound(1,R.raw.f_1);
        sManager.addSound(2,R.raw.f_2);
        sManager.addSound(3,R.raw.f_3);
        sManager.addSound(4,R.raw.f_4);
        sManager.addSound(5,R.raw.f_5);
        sManager.addSound(6,R.raw.f_6);
        sManager.addSound(7,R.raw.f_7);
        sManager.addSound(8,R.raw.f_8);
        sManager.addSound(9,R.raw.f_9);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        android.hardware.Sensor mySensor = sensorEvent.sensor;
        if (mySensor.getType() == android.hardware.Sensor.TYPE_ACCELEROMETER) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            long curTime = System.currentTimeMillis(); // 현재시간
            if ((curTime - lastUpdate) > 100) {
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;

                float speed = Math.abs(x + y + z - last_x - last_y - last_z) / diffTime * 10000;
                if (speed > SHAKE_THRESHOLD) {
                    //지정된 수치이상 흔들림이 있으면 실행
                    editText.setText("");
                } else if (speed < 10) {
                }
                //갱신
                last_x = x;
                last_y = y;
                last_z = z;
            }
        }
    }

    @Override
    public void onAccuracyChanged(android.hardware.Sensor sensor, int accuracy) {
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }
}