package com.example.coloroidlove;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class JoinActivity extends BaseActivity {

    EditText mEmailText, mPasswordText, mName;
    Button mregisterBtn;
    TextView joinLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);


        mEmailText = findViewById(R.id.edtID);
        mPasswordText = findViewById(R.id.edtPW);
        mName = findViewById(R.id.edtName);
        mregisterBtn = findViewById(R.id.btnReg);
        joinLogin = findViewById(R.id.joinLogin);


        // 로그인 버튼 클릭시 로그인 화면으로 전환
        joinLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoClass(LoginActivity.class);
            }
        });
        
    }
}
