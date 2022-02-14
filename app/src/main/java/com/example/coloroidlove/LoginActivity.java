package com.example.coloroidlove;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends BaseActivity {

    EditText mEmailText, mPasswordText;
    Button bt_login, bt_join;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmailText = findViewById(R.id.edtID);
        mPasswordText = findViewById(R.id.edtPW);
        bt_join = findViewById(R.id.btnJoin);
        bt_login = findViewById(R.id.btnLogin);
        
        // 회원가입을 클릭시 회원가입 창으로 전환
        bt_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoClass(JoinActivity.class);
            }
        });

        // 로그인 클릭시 메인 창으로 전환
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoClass(MainActivity.class);
            }
        });
    }
}
