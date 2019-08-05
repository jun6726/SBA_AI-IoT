package com.example.myapplication;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imageView1, imageView2, imageView3, imageView4;
    Button btn_github, btn_exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //
        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);

        btn_exit = findViewById(R.id.btn_exit);
        btn_github = findViewById(R.id.btn_github);

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("이태현 프로필").setMessage("좋아하는 음식 : 부대찌개\n생일 : 11월 23일\n거주지 : 경기도 안양\nTMI : 생각을 오래 함/형광색 좋아 함");
                dialog.show();
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("진소라 프로필").setMessage("좋아하는 음식 : 양념게장\n생일 : 8월 15일\n거주지 : 서울 성동구\nTMI : 아.아 못 먹음 / 송민호 좋아함");
                dialog.show();
            }
        });
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("서지희 프로필").setMessage("좋아하는 음식 : 샌드위치\n생일 : 8월 7일\n거주지 : 서울 강남구\nTMI : 9월에 이사 감\n\t\t\t\t\t\t얼마전에 킥보드 타다 넘어짐");
                dialog.show();
            }
        });
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("최연준 프로필").setMessage("좋아하는 음식 : 돈까스\n생일 : 4월 17일\n거주지 : 서울 관악구\nTMI : 발 사이즈 240/성격 급함");
                dialog.show();
            }
        });

        btn_github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/jun6726/"));
                startActivity(intent);
            }
        });

        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
