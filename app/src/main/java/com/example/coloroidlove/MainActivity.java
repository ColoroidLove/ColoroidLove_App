package com.example.coloroidlove;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    ListView cList;

    Button btn_start;

    // 커스텀 다이얼로그
    Dialog dilaog01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cList = findViewById(R.id.list);

        btn_start = findViewById(R.id.btn_start);

        displayList();


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

    // list 출력
    void displayList(){
        //Dbhelper의 읽기모드 객체를 가져와 SQLiteDatabase에 담아 사용준비
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase database = helper.getReadableDatabase();

        //Cursor라는 그릇에 목록을 담아주기
        Cursor cursor = database.rawQuery("SELECT * FROM usersTB",null);

        //리스트뷰에 목록 채워주는 도구인 adapter준비
        ListViewAdapter adapter = new ListViewAdapter();

        //목록의 개수만큼 순회하여 adapter에 있는 list배열에 add
        while(cursor.moveToNext()){
            //num 행은 가장 첫번째에 있으니 0번이 되고, name은 1번
            adapter.addItemToList(cursor.getString(1),cursor.getString(2), cursor.getInt(3), cursor.getInt(4),cursor.getInt(5));
        }

        //리스트뷰의 어댑터 대상을 여태 설계한 adapter로 설정
        cList.setAdapter(adapter);
    }

    // database 삭제
    public void Drop(MainActivity view){
        String DB_PATH = "/data/data/" + getPackageName();
        String DB_NAME = "data.db";
        String DB_FULLPATH = DB_PATH + "/databases/" + DB_NAME;

        File dbFile = new File(DB_FULLPATH);
        if(dbFile.delete()){
            System.out.println("삭제 성공");
        }else{
            System.out.println("삭제 실패");
        }
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
