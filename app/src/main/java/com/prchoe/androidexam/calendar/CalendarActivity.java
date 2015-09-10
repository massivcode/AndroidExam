
package com.prchoe.androidexam.calendar;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.prchoe.androidexam.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalendarActivity extends AppCompatActivity implements View.OnClickListener,
        AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    private CalendarAdapter mCalendarAdapter;
    private CalendarView mCalendarView;
    private TextView mPresentTV;
    private Button mPrevButton, mNextButton;

    private ListView mScheduleListView;

    private Map<Calendar, List<ScheduleData>> mScheduleData;

    private List<ScheduleData> scheduleDatas;
    private ScheduleAdapter mScheduleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        initView();
        initListener();

        // 어댑터 준비
        mCalendarAdapter = new CalendarAdapter(this);
        // View에 어댑터를 설정
        mCalendarView = (CalendarView) findViewById(R.id.calendar);
        mCalendarView.setAdapter(mCalendarAdapter);
        mPresentTV.setText(updateTitle(mCalendarAdapter.getCalendar()));

        // 아이템 클릭 이벤트 연결
//        mCalendarView.setOnClickListener(this);
        mCalendarView.setOnItemClickListener(this);
        mCalendarView.setOnItemLongClickListener(this);

        // listView의 데이터 준비
        mScheduleData = new HashMap<Calendar, List<ScheduleData>>();

        // 일정과 그 시간
        scheduleDatas = new ArrayList<ScheduleData>();
        mScheduleAdapter = new ScheduleAdapter(this);
        mScheduleListView.setAdapter(mScheduleAdapter);

    }

    private void initView() {
        mPresentTV = (TextView) findViewById(R.id.present);
        mPrevButton = (Button) findViewById(R.id.prev);
        mNextButton = (Button) findViewById(R.id.next);
        mScheduleListView = (ListView) findViewById(R.id.schedule_list_view);
    }

    private void initListener() {
        mPrevButton.setOnClickListener(this);
        mNextButton.setOnClickListener(this);
    }

    private String updateTitle(Calendar calendar) {
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);

        return year + "년 " + month + "월";
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.prev:
                mCalendarAdapter.prevMonth();
                break;
            case R.id.next:
                mCalendarAdapter.nextMonth();
                break;
        }
        mPresentTV.setText(updateTitle(mCalendarAdapter.getCalendar()));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        mScheduleAdapter.changeDate((Calendar) mCalendarAdapter.getItem(position));
        mCalendarAdapter.setSelectedPosition(position);
        mCalendarAdapter.notifyDataSetChanged();

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        if (mCalendarAdapter.getItem(position) != null) {

            final Calendar calendar = (Calendar) mCalendarAdapter.getItem(position);
            AlertDialog.Builder builder = new AlertDialog.Builder(CalendarActivity.this);
            builder.setTitle("일정 추가");
            builder.setNegativeButton("닫기", null);

            View dialogLayout = getLayoutInflater().inflate(R.layout.dialog_schedule, null);
            // View dialogLayout =
            // LayoutInflator.from(this).inflate(R.layout.dialog_schedule,
            // null);
            builder.setView(dialogLayout);
            final EditText contentET = (EditText) dialogLayout.findViewById(R.id.schedule_content);
            final EditText hourET = (EditText) dialogLayout.findViewById(R.id.schedule_time_hour);
            final EditText minuteET = (EditText) dialogLayout
                    .findViewById(R.id.schedule_time_minute);

            builder.setPositiveButton("저장", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // 뭔가 처리함
                    String content = contentET.getText().toString();
                    int hour = Integer.parseInt(hourET.getText().toString());
                    int minute = Integer.parseInt(minuteET.getText().toString());

                    ScheduleData scheduleData = new ScheduleData(content, hour, minute);

                    if (mScheduleData.containsKey(calendar)) {
                        scheduleDatas = mScheduleData.get(calendar);
                        scheduleDatas.add(scheduleData);

                    } else {
                        scheduleDatas = new ArrayList<ScheduleData>();
                        scheduleDatas.add(scheduleData);
                    }

                    mScheduleData.put(calendar, scheduleDatas);
                    mScheduleAdapter.initData(mScheduleData, calendar);
                }
            });

            builder.show();
        }
        return true;
    }
}
