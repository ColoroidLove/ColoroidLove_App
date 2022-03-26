package com.example.coloroidlove;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    ListView cList;
    ColoroidAdapter cAdapter;
    ArrayList<Coloroid> cArray;
    Coloroid cItem;
    Button btn_start;

    //초기 데이터
    Integer[] profile={R.drawable.profile};
    String[] name={"박종성여친"};
    String[] result={"여름뮤트"};
    Integer[] polar={R.drawable.polar};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cList=findViewById(R.id.list);
        cArray=new ArrayList<Coloroid>();
        btn_start = findViewById(R.id.btn_start);

        for(int i=0; i<profile.length; i++){
            cItem=new Coloroid(ContextCompat.getDrawable(this,polar[i]),name[i],result[i],
                    ContextCompat.getDrawable(this,profile[i]));
                    cArray.add(cItem);
        }
        cAdapter=new ColoroidAdapter(this,cArray);
        cList.setAdapter(cAdapter);


    }
}