
package com.prchoe.androidexam;

/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.prchoe.androidexam.activity.ActivityExamActivity;
import com.prchoe.androidexam.activity.WebActivity;
import com.prchoe.androidexam.animation.AnimationActivity;
import com.prchoe.androidexam.layout.FrameLayoutActivity;
import com.prchoe.androidexam.mission.Mission01Activity;
import com.prchoe.androidexam.mission.Mission02Activity;
import com.prchoe.androidexam.mission.Mission05Activity;
import com.prchoe.androidexam.mission.Mission06Activity;
import com.prchoe.androidexam.mission.MissionExtra01Activity;
import com.prchoe.androidexam.mission.mission03.Mission03Activity;
import com.prchoe.androidexam.mission.mission_extra_02.MissionExtra02Activity;
import com.prchoe.androidexam.practice.CalendarActivity;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new SimpleAdapter(this, getData(),
                android.R.layout.simple_list_item_1, new String[] {
                        "title"
                },
                new int[] {
                        android.R.id.text1
                }));
        getListView().setTextFilterEnabled(true);
    }

    protected List<Map<String, Object>> getData() {
        List<Map<String, Object>> myData = new ArrayList<>();

        // 메뉴 추가 부분
        addItem(myData, "FrameLayout", FrameLayoutActivity.class);
        addItem(myData, "도전! 01: 한 화면에 두개의 이미지 뷰 배치하기", Mission01Activity.class);
        addItem(myData, "도전! 02: SMS 입력 화면 만들고 글자수 표시하기", Mission02Activity.class);
        addItem(myData, "도전! 03 & 04: 영업관리 앱용 화면 만들기", Mission03Activity.class);
        addItem(myData, "도전! 05: 고객 정보 입력 화면의 구성", Mission05Activity.class);
        addItem(myData, "도전! 06: 웹브라우저 화면 구성", Mission06Activity.class);
        addItem(myData, "Extra 01: 갤러리에서 사진 선택하여 표시하기", MissionExtra01Activity.class);
        addItem(myData, "Extra 02: ListView에 Animation 적용하기", MissionExtra02Activity.class);
        addItem(myData, "월별 캘린더 만들기", CalendarActivity.class);
        addItem(myData, "화면이동 예제", ActivityExamActivity.class);
        addItem(myData, "WebView 연습", WebActivity.class);
        addItem(myData, "Animation 연습", AnimationActivity.class);
        // ----- 메뉴 추가 여기까지

        // 이름 순 정렬
        Collections.sort(myData, sDisplayNameComparator);

        return myData;
    }

    private final static Comparator<Map<String, Object>> sDisplayNameComparator =
            new Comparator<Map<String, Object>>() {
                private final Collator collator = Collator.getInstance();

                public int compare(Map<String, Object> map1, Map<String, Object> map2) {
                    return collator.compare(map1.get("title"), map2.get("title"));
                }
            };

    protected void addItem(List<Map<String, Object>> data, String name, Intent intent) {
        Map<String, Object> temp = new HashMap<>();
        temp.put("title", name);
        temp.put("intent", intent);
        data.add(temp);
    }

    protected void addItem(List<Map<String, Object>> data, String name, Class cls) {
        this.addItem(data, name, new Intent(this, cls));
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Map<String, Object> map = (Map<String, Object>) l.getItemAtPosition(position);

        Intent intent = (Intent) map.get("intent");
        startActivity(intent);
    }
}
