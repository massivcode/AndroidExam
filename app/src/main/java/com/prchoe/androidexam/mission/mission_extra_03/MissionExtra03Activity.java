package com.prchoe.androidexam.mission.mission_extra_03;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.prchoe.androidexam.R;

import java.util.ArrayList;
import java.util.List;

public class MissionExtra03Activity extends Activity {

    ListView mColorfulListView;
    List<ColorfulData> mData;
    ColorfulAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission_extra03);

        initData();
        initAdapter();

        mAdapter.setData(mData);

        mColorfulListView = (ListView)findViewById(R.id.colorful_list_view);
        mColorfulListView.setAdapter(mAdapter);


    }

    private void initData() {
        mData = new ArrayList<ColorfulData>();

        ColorfulData colorfulData;

        for(int i = 1; i < 51; i++) {
            colorfulData = new ColorfulData();
            colorfulData.setTitle("제목 " + i);
            colorfulData.setSubTitle("내용 " + i);
            colorfulData.setImageResourceId(R.mipmap.ic_launcher);
            mData.add(colorfulData);
        }
    }

    private void initAdapter() {
        mAdapter = new ColorfulAdapter(getApplicationContext());
    }


}
