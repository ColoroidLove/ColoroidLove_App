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

    TextView res_title,res_desc, res_web;
/*    Intent intent = getIntent();
    String Name = intent.getStringExtra("결과");*/



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        res_title  = findViewById(R.id.res_title);

        res_title.setText("조해정");

    }
}
