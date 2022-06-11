package com.example.coloroidlove;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {

    ArrayList<ListViewAdapterData> list = new ArrayList<ListViewAdapterData>();

    // 리스트뷰에 띄울 폴라 사진(인덱스에 맞는 폴라 가져오기)
    Integer[] WarmPolar = {R.drawable.polar_springlight, R.drawable.polar_springbright,
            R.drawable.polar_fallmute, R.drawable.polar_fallstrong, R.drawable.polar_falldeep};
    Integer[] CoolPolar = {
            R.drawable.polar_summerlight, R.drawable.polar_summermute, R.drawable.polar_summerbright, R.drawable.polar_summerlowerbrightmute,
            R.drawable.polar_wintertrue, R.drawable.polar_winterbright, R.drawable.polar_winterdeep};

    // 리스트뷰에 띄울 닉네임 + 결과 프로필(랜덤)
    Integer[] profileImg = {R.drawable.profile_1, R.drawable.profile_2, R.drawable.profile_3, R.drawable.profile_4, R.drawable.profile_5}; // profile

    @Override
    public int getCount() {
        return list.size(); //그냥 배열의 크기를 반환하면 됨
    }

    @Override
    public Object getItem(int i) {
        return list.get(i); //배열에 아이템을 현재 위치값을 넣어 가져옴
    }

    @Override
    public long getItemId(int i) {
        return i; //그냥 위치값을 반환해도 되지만 원한다면 아이템의 num 을 반환해도 된다.
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        final Context context = viewGroup.getContext();

        //리스트뷰에 아이템이 인플레이트 되어있는지 확인한후
        //아이템이 없다면 아래처럼 아이템 레이아웃을 인플레이트 하고 view객체에 담는다.
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listitem,viewGroup,false);
        }

        //이제 아이템에 존재하는 객체들을 view객체에서 찾아 가져온다
        ImageView imgProfile = (ImageView) view.findViewById(R.id.profileImg);
        TextView tvName = (TextView)view.findViewById(R.id.name);
        TextView tvResult = (TextView)view.findViewById(R.id.result);
        ImageView imgPolar = (ImageView)view.findViewById(R.id.polarImg);

        //현재 포지션에 해당하는 아이템에 글자를 적용하기 위해 list배열에서 객체를 가져온다.
        ListViewAdapterData listdata = list.get(i);

        //가져온 객체안에 있는 값들을 각 뷰에 적용한다
        imgProfile.setImageDrawable(context.getResources().getDrawable(profileImg[listdata.getProfileImg()]));
        tvName.setText(listdata.getName());
        tvResult.setText(listdata.getResult());




        if(listdata.getBase()==0){
            String s = "id : " + listdata.getId() + ", name : "+ listdata.getName() + "base : " + listdata.getBase() + ", polarImg : " + listdata.getPolarImg();
            Log.d("", s);
            imgPolar.setImageDrawable(context.getResources().getDrawable(listdata.getPolarImg()));
        }else{
            String s = "name : "+ listdata.getName() + ", base : " + listdata.getBase() + ", polarImg : " + listdata.getPolarImg();
            Log.d("", s);
            imgPolar.setImageDrawable(context.getResources().getDrawable(listdata.getPolarImg()));
        }


        return view;
    }

    //ArrayList로 선언된 list 변수에 목록을 채워주기 위함 다른방시으로 구현해도 됨
    public void addItemToList(String name, String result, int base, int polarImg, int profileImg){
        ListViewAdapterData listdata = new ListViewAdapterData();

        listdata.setProfileImg(listdata.getRandomIndex());
        listdata.setName(name);
        listdata.setResult(result);
        if(base == 0){
            listdata.setPolarImg(WarmPolar[polarImg]);
        }else{
            listdata.setPolarImg(CoolPolar[polarImg]);
        }

        //값들의 조립이 완성된 listdata객체 한개를 list배열에 추가
        list.add(listdata);

    }
}