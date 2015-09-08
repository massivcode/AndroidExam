package com.prchoe.androidexam.calendar;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by massivCode on 2015-09-08.
 */
public class CalendarView extends GridView {

    // 모든 뷰는 반드시 생성자가 3개 있어야 한다.

    // 코드 상에서 생성될 때 호출하는 생성자
    public CalendarView(Context context) {
        this(context, null);
    }


    // XML에 정의되었을 때 호출되는 생성자
    public CalendarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    // XML에서 넘어온 속성을 멤버변수로 셋팅하는 역할을 한다.
    public CalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setNumColumns(7);       // 7열로 설정
        setBackgroundResource(android.R.color.darker_gray);     // 배경을 회색으로
        setHorizontalSpacing(1);
        setVerticalSpacing(1);
    }

}
