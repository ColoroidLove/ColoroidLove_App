package com.example.coloroidlove;

import android.graphics.drawable.Drawable;

public class Coloroid {

    Drawable polarImg; //메인화면 프로필 이미지
    String name; //메인화면 유저네임
    String result; //메인화면 유저결과
    Drawable profileImg; //메인화면 폴라로이드 이미지

    public Coloroid(Drawable polarImg, String name, String result, Drawable profileImg) {
        this.polarImg = polarImg;
        this.name = name;
        this.result = result;
        this.profileImg = profileImg;
    }

    public Drawable getPolarImg() {
        return polarImg;
    }

    public void setPolarImg(Drawable polarImg) {
        this.polarImg = polarImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Drawable getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(Drawable profileImg) {
        this.profileImg = profileImg;
    }
}
