package com.example.coloroidlove;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ColoroidAdapter extends BaseAdapter {
    Context mContext; //메인액티비티의 컨텍스트를 저장
    ArrayList<Coloroid> mData; //ListViewItem 아이템 데이터를 저장한 배열리스트를 저장

    public ColoroidAdapter(Context mContext, ArrayList<Coloroid> mData) { //어댑터 생성시 컨텍스트와 데이터배열을 가져옴
        this.mContext = mContext;
        this.mData = mData;
    }


    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if(convertView==null){
            convertView=View.inflate(mContext, R.layout.listitem, null);
        }

        ImageView profileImg=convertView.findViewById(R.id.profileImg);
        TextView name=convertView.findViewById(R.id.name);
        TextView result=convertView.findViewById(R.id.result);
        ImageView polarImg=convertView.findViewById(R.id.polarImg);

        profileImg.setImageDrawable(mData.get(position).profileImg);
        name.setText(mData.get(position).name);
        result.setText(mData.get(position).result);
        polarImg.setImageDrawable(mData.get(position).polarImg);
        return convertView;
    }

}





