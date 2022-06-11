package com.example.coloroidlove;

public class ListViewAdapterData {

    private int id; // 고유번호
    private String name; // 메인화면 유저네임
    private String result; // 메인화면 유저결과
    private int base; // 웜쿨 판단
    private int polarImg; // 메인화면 프로필 이미지
    private int profileImg; // 메인화면 프로필 이미지

    public void setId(int num){this.id = num;}
    public void setName(String name){this.name = name;}
    public void setResult(String result){this.result = result;}
    public void setPolarImg(int polarImg) {this.polarImg = polarImg;}
    public void setProfileImg(int profileImg) {this.profileImg = profileImg;}
    public void setBase(int base) {this.base = base;}

    //난수를 생성해 인덱스 값을 넘겨준다
    public  int getRandomIndex(){
        int rn= (int) (Math.random() *5);
        return rn;

    }

    public int getId(){return this.id;}
    public String getName(){return this.name;}
    public String getResult(){return this.result;}
    public int getPolarImg() {return polarImg;}
    public int getProfileImg() {return profileImg;}
    public int getBase() { return base;}

}
