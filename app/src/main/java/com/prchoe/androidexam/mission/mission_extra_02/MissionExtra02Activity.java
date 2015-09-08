package com.prchoe.androidexam.mission.mission_extra_02;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.prchoe.androidexam.R;

import java.util.ArrayList;
import java.util.List;

public class MissionExtra02Activity extends AppCompatActivity {

    private List<DataExam> mData;
    private ListView mListView;
    private CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission_extra02);

        mListView = (ListView)findViewById(R.id.list_view);
        initData();
        initAdapter();

    }

    private void initData() {
        mData = new ArrayList<>();

       for(int i = 0; i < 15; i++) {
           DataExam dataExam = new DataExam();
           dataExam.setName("안드로이드 " + i);
           dataExam.setHobby("Android " + i);
           dataExam.setGender("중성 " + i);
           mData.add(dataExam);
       }

    }

    private void initAdapter() {
        customAdapter = new CustomAdapter(mData, getApplicationContext());
        mListView.setAdapter(customAdapter);
    }


}
