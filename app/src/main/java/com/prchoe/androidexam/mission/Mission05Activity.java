package com.prchoe.androidexam.mission;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.prchoe.androidexam.R;
import com.prchoe.androidexam.interfaces.Layout;

import java.util.Calendar;

public class Mission05Activity extends AppCompatActivity implements Layout {

    private Button mSaveButton;
    private TextView mBirthTV;
    private EditText mNameET, mAgeET;
    private int mYear;
    private int mMonth;
    private int mDay;

    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mBirthTV.setText(year + "년 " + (monthOfYear+1) + "월 " + dayOfMonth + "일");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission05);

        initView();
        initListener();
        getToday();

        mBirthTV.setText(mYear + "년 " + (mMonth+1) + "월 " + mDay + "일");

    }


    @Override
    public void initView() {
        mSaveButton = (Button)findViewById(R.id.button_save);
        mBirthTV = (TextView)findViewById(R.id.birthDay_text_view);
        mNameET = (EditText)findViewById(R.id.name_edit_text);
        mAgeET = (EditText)findViewById(R.id.age_edit_text);
    }

    @Override
    public void initListener() {
        mSaveButton.setOnClickListener(this);
        mBirthTV.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.birthDay_text_view:
                openDatePickerDialog();
                break;
            case R.id.button_save:
                String message = "이름 : " + mNameET.getText().toString() + "\r\n나이 : " + mAgeET.getText().toString() +
                        "\r\n생년월일 : " + mBirthTV.getText().toString();
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                break;
        }

    }


    private void getToday() {
        Calendar calendar = Calendar.getInstance();
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DATE);

    }

    private void openDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(Mission05Activity.this, dateSetListener, mYear, mMonth, mDay);
        datePickerDialog.show();

    }


}
