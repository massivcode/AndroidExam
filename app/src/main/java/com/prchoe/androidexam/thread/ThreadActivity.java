
package com.prchoe.androidexam.thread;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.prchoe.androidexam.R;

public class ThreadActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = ThreadActivity.class.getSimpleName();
    private Button mThread1Btn;
    private Button mThread2Btn;

    private TextView mNumberTextView1;
    private TextView mNumberTextView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);

        mThread1Btn = (Button) findViewById(R.id.btn_thread1);
        mThread2Btn = (Button) findViewById(R.id.btn_thread2);

        mNumberTextView1 = (TextView) findViewById(R.id.tv_number1);
        mNumberTextView2 = (TextView) findViewById(R.id.tv_number2);

        mThread1Btn.setOnClickListener(this);
        mThread2Btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_thread1:
                progressDialogExam();

                break;
            case R.id.btn_thread2:
                break;
        }
    }

    private void progressDialogExam() {
        // 다이얼로그는 호출한 애의 컨텍스트를 직접 받아야함
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("다운로드 중");
        progressDialog.setCancelable(false); // 뒤로가기로 캔슬되는 것 막기
        progressDialog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                // 2초동안 다운로드
                for(int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                // 다운로드 끝나면 progressDialog를 닫는다.
                progressDialog.dismiss();

            }
        }).start();
    }

    // 백그라운드 처리는 바로바로 보이지만, UI 변경은 쓰레드 종료시 보여진다.
    private void runOnUiThreadExam() {
        // UI Thread 로 동작하게 해주는 Activity가 제공하는 메서드 (Activity 안에서만 쓸 수 있음)
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {

                    try {
                        Thread.sleep(1000); // 스레드가 잠시 쉰다.
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        // 에러 처리
                    }
                    Log.d(TAG, "" + i);                 //  background
                    mNumberTextView1.setText("" + i);   // foreground
                }
            }
        });
    }


    /**
     * 핸들러만으로도 UI를 변경할 수 있다.
     * 그러나 그 작업이 길어지면 버벅거린다.
     * 고속으로 처리해야 하는 건 쓰레드로 하고,
     * UI를 변경할때는 핸들러로 한다.
     */

    private void threadAndHandler() {
        // Handler 클래스 상속을 생략한 것

        // 보이는 부분에서 동작하는 쓰레드
        // UI Thread
        // Foreground Thread
        // Main Thread
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                mNumberTextView1.setText("" + msg.arg1);
            }
        };

        // Thread 클래스 상속을 생략한 것

        // 안보이는 부분에서 동작하는 Thread
        // Thread
        // Background Thread
        // Worker Thread
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // 스레드로 동작하는 부분
                for (int i = 0; i < 10; i++) {

                    try {
                        Thread.sleep(1000); // 스레드가 잠시 쉰다.
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        // 에러 처리
                    }

                    Message msg = new Message();
                    msg.arg1 = i;
                    handler.sendMessage(msg);
                }
            }
        });

        thread.start();
    }

    // 쓰레드 사용방법 1
    // background 에서 동작
    private void backGroundThread() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // 스레드로 동작하는 부분
                for (int i = 0; i < 10; i++) {

                    try {
                        Thread.sleep(1000); // 스레드가 잠시 쉰다.
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        // 에러 처리
                    }
                    Log.d(TAG, "" + i);

                }
            }
        });

        thread.start();
    }
}
