
package com.prchoe.androidexam.calendar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.prchoe.androidexam.R;

import java.util.Calendar;

public class CalendarActivity extends AppCompatActivity implements View.OnClickListener {

    private CalendarAdapter mCalendarAdapter;
    private CalendarView mCalendarView;
    private TextView mPresentTV;
    private Button mPrevButton, mNextButton;


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



    }

    private void initView() {
        mPresentTV = (TextView) findViewById(R.id.present);
        mPrevButton = (Button) findViewById(R.id.prev);
        mNextButton = (Button) findViewById(R.id.next);
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
}
