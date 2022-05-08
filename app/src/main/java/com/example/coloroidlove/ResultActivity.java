package com.example.coloroidlove;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.coloroidlove.CameraActivity;
import androidx.annotation.Nullable;

public class ResultActivity  extends BaseActivity {

    TextView res_title,res_desc, res_name,res_artist,res_item, res_color;
    Button btnEnd;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        String personal = intent.getStringExtra("퍼스널컬러");
        String nickname = intent.getStringExtra("닉네임");
        res_name  = findViewById(R.id.res_name); //이름
        res_title  = findViewById(R.id.res_title); //결과
        res_desc  = findViewById(R.id.res_desc); //설명
        res_artist  = findViewById(R.id.res_artist); //대표적인 연예인
        res_item  = findViewById(R.id.res_item); //추천 아이템
        res_color  = findViewById(R.id.res_color); // 추천 컬러
        btnEnd=findViewById(R.id.btnEnd); //종료하기
        System.out.println("닉네임 값 확인 : "+nickname);

        res_name.setText(nickname+"님의 결과");
        res_title.setText(personal);

        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoClass(MainActivity.class);
            }
        });

    }
}
