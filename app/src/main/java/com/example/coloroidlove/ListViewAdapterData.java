package com.example.coloroidlove;

public class ListViewAdapterData {

    private int num; // 고유번호
    private String name; // 메인화면 유저네임
    private String result; // 메인화면 유저결과
    private int polarImg; // 메인화면 프로필 이미지
    private int profileImg; // 메인화면 프로필 이미지

    public void setNum(int num){this.num = num;}
    public void setName(String name){this.name = name;}
    public void setResult(String result){this.result = result;}
    public void setPolarImg(int polarImg) {this.polarImg = polarImg;}
    public void setProfileImg(int profileImg) {this.profileImg = profileImg;}

    public int getNum(){return this.num;}
    public String getName(){return this.name;}
    public String getResult(){return this.result;}
    public int getPolarImg() {return polarImg;}
    public int getProfileImg() {return profileImg;}

}
