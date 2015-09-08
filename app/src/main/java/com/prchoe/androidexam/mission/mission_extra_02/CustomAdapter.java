package com.prchoe.androidexam.mission.mission_extra_02;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.prchoe.androidexam.R;

import java.util.List;

/**
 * Created by massivCode on 2015-09-07.
 */
public class CustomAdapter extends BaseAdapter {

    private List<DataExam> data;
    private LayoutInflater inflater;
    private Animation animation;
    private Context context;

    public CustomAdapter(List<DataExam> data, Context context) {
        this.data = data;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // 1. 레이아웃 구성

    // convertView 처음 로딩 될 때 초기화 과정을 거쳐서 viewHolder에 저장
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;


        // convertView 처음 로딩될 때 초기화 과정을 거쳐서 viewHolder에 저장
        if(convertView == null) {
            // layout을 가져오기
            convertView = inflater.inflate(R.layout.activity_mission_extra02_item, parent, false);

            // 각 View를 소스로 연결
            TextView name = (TextView)convertView.findViewById(R.id.name_tv);
            TextView hobby = (TextView)convertView.findViewById(R.id.hobby_tv);
            TextView gender = (TextView)convertView.findViewById(R.id.gender_tv);

            // ViewHolder를 생성 후, 가져온 View를 연결
            viewHolder = new ViewHolder();
            viewHolder.name = name;
            viewHolder.gender = gender;
            viewHolder.hobby = hobby;

            // ViewHolder를 convertView의 태그로 저장
            convertView.setTag(viewHolder);

        } else {
            // convertView가 다시 로딩될 때에는 viewHolder에서 꺼내와서 재사용
            // 이점 : 속도가 빠르다
            viewHolder = (ViewHolder)convertView.getTag();
        }

        DataExam dataExam = (DataExam)getItem(position);

        viewHolder.gender.setText(dataExam.getGender());
        viewHolder.hobby.setText(dataExam.getHobby());
        viewHolder.name.setText(dataExam.getName());

        animation = AnimationUtils.loadAnimation(context, R.anim.extra02);

        convertView.startAnimation(animation);
        return convertView;
    }

    static class ViewHolder {
        TextView name;
        TextView hobby;
        TextView gender;
    }
}
