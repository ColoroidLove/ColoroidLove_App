package com.example.coloroidlove;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    final static String DB_NAME = "data.db";  //DB이름
    final static int DB_VERSION = 2; //DB버전


    //생성자
    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //테이블의 구조는 여기서 설계
        String qry = "CREATE TABLE usersTB(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT NOT NULL, result TEXT, base INTEGER(2), polarImg INTEGER(2), profileImg INTEGER(2))";
        sqLiteDatabase.execSQL(qry);

        //없으면 썰렁하니 아무 데이터라도 넣어주기

        qry = "INSERT INTO usersTB(name, result, base, polarImg, profileImg) VALUES('황수고', '겨울트루', 1, 4, 2)";
        sqLiteDatabase.execSQL(qry);
        qry = "INSERT INTO usersTB(name, result, base, polarImg, profileImg) VALUES('김영우', '겨울딥', 1, 6, 0)";
        sqLiteDatabase.execSQL(qry);
        qry = "INSERT INTO usersTB(name, result, base, polarImg, profileImg) VALUES('양정원', '겨울브라이트', 1, 5, 3)";
        sqLiteDatabase.execSQL(qry);

    }


    //버전 업데이트 될때마다 호출 되는데 마지막에 onCreate도 같이 실행되기 때문에 여기서 먼저 DB에 존재하는 테이블들을 지워줘야함.
    //한마디로 초기화역할
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String qry = "DROP TABLE IF EXISTS usersTB";
        sqLiteDatabase.execSQL(qry);

        onCreate(sqLiteDatabase);

    }
}