package com.example.calc;

import android.annotation.SuppressLint;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements SensorEventListener{

    Button button0, button1, button2, button3, button4, button5, button6, button7,
            button8, button9, button10, button11, button12, button13, button14, button15,
            button16, buttonClear, buttonDel, buttonDot, buttonL, buttonR, buttonChange;
    EditText editText;
    boolean cal;
    double cal_result;

    private static final String TAG = "AccelerometerActivity";

    private SensorManager sensorManager;
    private Sensor senAccelerometer;

    private long lastUpdate = 0;
    private float last_x, last_y, last_z;
    private static final int SHAKE_THRESHOLD = 700;

    SoundManager sManager;

    private ConstraintLayout constraintLayout;
    private TextView tvSensorStatus;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calc_main);

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
        button10 = findViewById(R.id.button_result);
        button11 = findViewById(R.id.button_div);
        button12 = findViewById(R.id.button_mul);
        button13 = findViewById(R.id.button_plus);
        button14 = findViewById(R.id.button_minus);
        button15 = findViewById(R.id.button_10p);
        button16 = findViewById(R.id.button_15p);
        buttonClear = findViewById(R.id.buttonC);
        buttonDel= findViewById(R.id.button_del);
        buttonL= findViewById(R.id.button_left);
        buttonR= findViewById(R.id.button_right);
        buttonDot = findViewById(R.id.button_dot);
        buttonChange = findViewById(R.id.button_change);
        editText = findViewById(R.id.edit1);

        /*constraintLayout = (ConstraintLayout) findViewById(R.id.cl_sensor);
        tvSensorStatus = (TextView) findViewById(R.id.tv_status);*/ // 원래예제

        sensorManager = (SensorManager) this.getSystemService(SENSOR_SERVICE);
        senAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);


        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText().toString() + 0);
                sManager.play(0);
            }
        });
        button0.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        button0.setBackgroundResource(R.drawable.g0);
                        break;
                    case MotionEvent.ACTION_UP:
                        button0.setBackgroundResource(R.drawable.y0);
                        break;
                }
                return false;
            }
        });


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText().toString() + 1);
                sManager.play(1);
            }
        });
        button1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        button1.setBackgroundResource(R.drawable.g1);
                        break;
                    case MotionEvent.ACTION_UP:
                        button1.setBackgroundResource(R.drawable.y1);
                        break;
                }
                return false;
            }
        });


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText().toString() + 2);
                sManager.play(2);
            }
        });
        button2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        button2.setBackgroundResource(R.drawable.g2);
                        break;
                    case MotionEvent.ACTION_UP:
                        button2.setBackgroundResource(R.drawable.y2);
                        break;
                }
                return false;
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText().toString() + 3);
                sManager.play(3);
            }
        });
        button3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        button3.setBackgroundResource(R.drawable.g3);
                        break;
                    case MotionEvent.ACTION_UP:
                        button3.setBackgroundResource(R.drawable.y3);
                        break;
                }
                return false;
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText().toString() + 4);
                sManager.play(4);
            }
        });
        button4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        button4.setBackgroundResource(R.drawable.g4);
                        break;
                    case MotionEvent.ACTION_UP:
                        button4.setBackgroundResource(R.drawable.y4);
                        break;
                }
                return false;
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText().toString() + 5);
                sManager.play(5);
            }
        });
        button5.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        button5.setBackgroundResource(R.drawable.g5);
                        break;
                    case MotionEvent.ACTION_UP:
                        button5.setBackgroundResource(R.drawable.y5);
                        break;
                }
                return false;
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText().toString() + 6);
                sManager.play(6);
            }
        });
        button6.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        button6.setBackgroundResource(R.drawable.g6);
                        break;
                    case MotionEvent.ACTION_UP:
                        button6.setBackgroundResource(R.drawable.y6);
                        break;
                }
                return false;
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText().toString() + 7);
                sManager.play(7);
            }
        });
        button7.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        button7.setBackgroundResource(R.drawable.g7);
                        break;
                    case MotionEvent.ACTION_UP:
                        button7.setBackgroundResource(R.drawable.y7);
                        break;
                }
                return false;
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText().toString() + 8);
                sManager.play(8);
            }
        });
        button8.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        button8.setBackgroundResource(R.drawable.g8);
                        break;
                    case MotionEvent.ACTION_UP:
                        button8.setBackgroundResource(R.drawable.y8);
                        break;
                }
                return false;
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText().toString() + 9);
                sManager.play(9);
            }
        });
        button9.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        button9.setBackgroundResource(R.drawable.g9);
                        break;
                    case MotionEvent.ACTION_UP:
                        button9.setBackgroundResource(R.drawable.y9);
                        break;
                }
                return false;
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
                sManager.play(10);
            }
        });
        button10.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        button10.setBackgroundResource(R.drawable.gresult);
                        break;
                    case MotionEvent.ACTION_UP:
                        button10.setBackgroundResource(R.drawable.yresult);
                        break;
                }
                return false;
            }
        });
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText().toString() + "/");
                sManager.play(11);
            }

        });
        button11.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        button11.setBackgroundResource(R.drawable.gdiv);
                        break;
                    case MotionEvent.ACTION_UP:
                        button11.setBackgroundResource(R.drawable.ydiv);
                        break;
                }
                return false;
            }
        });

        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText().toString() + "*");
                sManager.play(12);
            }
        });
        button12.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        button12.setBackgroundResource(R.drawable.gmul);
                        break;
                    case MotionEvent.ACTION_UP:
                        button12.setBackgroundResource(R.drawable.ymul);
                        break;
                }
                return false;
            }
        });

        button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText().toString() + "+");
                sManager.play(13);
            }
        });
        button13.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        button13.setBackgroundResource(R.drawable.gplus);
                        break;
                    case MotionEvent.ACTION_UP:
                        button13.setBackgroundResource(R.drawable.yplus);
                        break;
                }
                return false;
            }
        });

        button14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText().toString() + "-");
                sManager.play(14);
            }
        });
        button14.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        button14.setBackgroundResource(R.drawable.gmin);
                        break;
                    case MotionEvent.ACTION_UP:
                        button14.setBackgroundResource(R.drawable.ymin);
                        break;
                }
                return false;
            }
        });


        buttonDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result4del = String.valueOf(editText.getText());
                editText.setText(result4del.substring(0, result4del.length() - 1));
                sManager.play(15);
            }
        });
        buttonDel.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        buttonDel.setBackgroundResource(R.drawable.gdel);
                        break;
                    case MotionEvent.ACTION_UP:
                        buttonDel.setBackgroundResource(R.drawable.ydel);
                        break;
                }
                return false;
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
                    cal_result = (double) (cal_result * 1.1);
                    editText.setText(cal_result + "");
                    cal = false;
                }
            }
        });
        button15.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        button15.setBackgroundResource(R.drawable.g10);
                        break;
                    case MotionEvent.ACTION_UP:
                        button15.setBackgroundResource(R.drawable.y10);
                        break;
                }
                return false;
            }
        });
        button16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cal == false) {
                    Toast.makeText(MainActivity.this, "계산을 완료하고 눌러주세요.", Toast.LENGTH_SHORT).show();
                } else if (cal == true) {
                    //팁 계산
                    cal_result = Integer.parseInt(editText.getText().toString());
                    cal_result = (cal_result * 1.15);
                    editText.setText(cal_result + "");
                    cal = false;
                }
            }
        });
        button16.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        button16.setBackgroundResource(R.drawable.g15);
                        break;
                    case MotionEvent.ACTION_UP:
                        button16.setBackgroundResource(R.drawable.y15);
                        break;
                }
                return false;
            }
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
            }
        });
        buttonClear.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        buttonClear.setBackgroundResource(R.drawable.gc);
                        break;
                    case MotionEvent.ACTION_UP:
                        buttonClear.setBackgroundResource(R.drawable.yc);
                        break;
                }
                return false;
            }
        });

        buttonL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText().toString() + "(");
            }
        });
        buttonL.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        buttonL.setBackgroundResource(R.drawable.gleft);
                        break;
                    case MotionEvent.ACTION_UP:
                        buttonL.setBackgroundResource(R.drawable.yleft);
                        break;
                }
                return false;
            }
        });

        buttonR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText().toString() + ")");
            }
        });
        buttonR.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        buttonR.setBackgroundResource(R.drawable.gright);
                        break;
                    case MotionEvent.ACTION_UP:
                        buttonR.setBackgroundResource(R.drawable.yright);
                        break;
                }
                return false;
            }
        });

        buttonDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText().toString() + ".");
                sManager.play(16);
            }
        });
        buttonDot.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        buttonDot.setBackgroundResource(R.drawable.gdot);
                        break;
                    case MotionEvent.ACTION_UP:
                        buttonDot.setBackgroundResource(R.drawable.ydot);
                        break;
                }
                return false;
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
        sManager.addSound(10,R.raw.f_re);
        sManager.addSound(11,R.raw.f_div);
        sManager.addSound(12,R.raw.f_mul);
        sManager.addSound(13,R.raw.f_plus);
        sManager.addSound(14,R.raw.f_minus);
        sManager.addSound(15,R.raw.f_del);
        sManager.addSound(16,R.raw.f_dot);;

    }






    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor mySensor = sensorEvent.sensor;
        if(mySensor.getType() == Sensor.TYPE_ACCELEROMETER){
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            long curTime = System.currentTimeMillis(); // 현재시간
            if((curTime - lastUpdate) > 100) {
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
    public void onAccuracyChanged(Sensor sensor, int i) {

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

