package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {
    Button button0, button1, button2, button3, button4, button5, button6, button7, button8, button9, button10, button11, button12, button13, button14, button15, button16;
    EditText editText;
    boolean cal;
    int cal_result;

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
        editText = findViewById(R.id.editText);

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText().toString() + 0);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText().toString() + 1);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText().toString() + 2);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText().toString() + 3);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText().toString() + 4);
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText().toString() + 5);
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText().toString() + 6);
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText().toString() + 7);
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText().toString() + 8);
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText().toString() + 9);
            }
        });
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText().toString() + "=");
            }
        });
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText().toString() + "-");
            }
        });
        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText().toString() + "+");
            }
        });
        button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText().toString() + "*");
            }
        });
        button14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText().toString() + "/");
            }
        });
        button15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cal == false) {
                    Toast.makeText(MainActivity.this, "계산을 완료하고 눌러주세요.", Toast.LENGTH_SHORT).show();
                } else if (cal == true) {
                    //팁 계산
                    cal_result = Integer.parseInt(editText.getText().toString());
                    cal_result = (int) (cal_result * 1.1);
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
                    cal_result = Integer.parseInt(editText.getText().toString());
                    cal_result = (int) (cal_result * 1.15);
                }
            }
        });
    }

    private static int getPriority(String parm1) {
        switch (parm1) {
            case "(":
            case ")":
                return 0;
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
        }
        return -1;

    }


    private static ArrayList<String> toPostfix(String str) {

        ArrayList<String> result = new ArrayList<>();
        String[] calcTarget = str.split(" ");
        Stack<String> stack = new Stack<>();
        String forPrint = "";

        for (int i = 0; i < calcTarget.length; i++) {
            switch (calcTarget[i]) {
                case "(":
                    stack.push(calcTarget[i]);
                    break;

                case ")":
                    forPrint = stack.pop();
                    while (!forPrint.equals("(")) {
                        result.add(forPrint);
                        forPrint = stack.pop();
                    }
                    break;
                case "+":
                case "-":
                case "/":
                case "*":
                    while (!stack.isEmpty() && getPriority(calcTarget[i]) <= getPriority(stack.peek())){
                        forPrint = stack.pop();
                        result.add(forPrint);
                    }
                    stack.push(calcTarget[i]);
                    break;
                    default:
                        result.add(calcTarget[i]);
                        break;
            }
            while(!stack.isEmpty()){
                forPrint = stack.pop();
                result.add(forPrint);
            }
        }
        return result;
    }
}