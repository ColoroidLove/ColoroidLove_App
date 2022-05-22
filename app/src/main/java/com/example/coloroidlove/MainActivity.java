package com.example.coloroidlove;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    ListView cList;
    ColoroidAdapter cAdapter;
    ArrayList<Coloroid> cArray;
    Coloroid cItem;
    Button btn_start;


    //초기 데이터
    Integer[] profile = {R.drawable.profile_1};
    String[] name = {"박종성여친"};
    String[] result = {"여름뮤트"};
    Integer[] polar = {R.drawable.polar_falldeep};

    // 커스텀 다이얼로그
    Dialog dilaog01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cList = findViewById(R.id.list);
        cArray = new ArrayList<Coloroid>();
        btn_start = findViewById(R.id.btn_start);



        for (int i = 0; i < profile.length; i++) {
            cItem = new Coloroid(ContextCompat.getDrawable(this, polar[i]), name[i], result[i],
                    ContextCompat.getDrawable(this, profile[i]));
            cArray.add(cItem);
        }
        cAdapter = new ColoroidAdapter(this, cArray);
        cList.setAdapter(cAdapter);

        dilaog01 = new Dialog(MainActivity.this);       // Dialog 초기화
        dilaog01.requestWindowFeature(Window.FEATURE_NO_TITLE); // 타이틀 제거
        dilaog01.setContentView(R.layout.dialog1);             // xml 레이아웃 파일과 연결


        // 버튼: 커스텀 다이얼로그 띄우기
        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog01(); // 아래 showDialog01() 함수 호출
            }
        });
    }
        // dialog01을 디자인하는 함수
        public void showDialog01 () {
            dilaog01.show(); // 다이얼로그 띄우기
            dilaog01.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // 투명 배경
            /* 이 함수 안에 원하는 디자인과 기능을 구현하면 된다. */

            // 위젯 연결 방식은 각자 취향대로~
            // '아래 아니오 버튼'처럼 일반적인 방법대로 연결하면 재사용에 용이하고,
            // '아래 네 버튼'처럼 바로 연결하면 일회성으로 사용하기 편함.
            // *주의할 점: findViewById()를 쓸 때는 -> 앞에 반드시 다이얼로그 이름을 붙여야 한다.

            EditText nickname=dilaog01.findViewById(R.id.nickname);
            Intent intent1 = new Intent(MainActivity.this, CameraActivity.class);
            // 시작
            Button noBtn = dilaog01.findViewById(R.id.noBtn);
            noBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String name=nickname.getText().toString();
                    System.out.println("닉네임 : "+name);

                    if ( name.length() == 0 ) {
                        Toast.makeText(getApplicationContext(), "닉네임을 입력해주세요", Toast.LENGTH_SHORT).show();
                    } else {

                        intent1.putExtra("닉네임", name);
                        startActivity(intent1);
                        dilaog01.dismiss(); // 다이얼로그 닫기
                    }



                }
            });
            // 취소
            dilaog01.findViewById(R.id.yesBtn).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // 원하는 기능 구현
                    gotoClass(MainActivity.class);         // 앱 종료
                }
            });
        }
    }
