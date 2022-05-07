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




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        String personal = intent.getStringExtra("퍼스널컬러");


        res_title  = findViewById(R.id.res_title);

        res_title.setText(personal);

    }
}
