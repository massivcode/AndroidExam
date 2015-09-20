
package com.prchoe.androidexam.database;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.prchoe.androidexam.R;
import com.prchoe.androidexam.database.util.UserUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by massivCode on 2015-09-18. 회원가입 Activity
 */
public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEditTextNickname, mEditTextEmail, mEditTextPassword,
            mEditTextPasswordConfirm;
    private List<EditText> mEditTexts;
    private List<TextInputLayout> mTILs;
    private TextInputLayout mTIL1, mTIL2, mTIL3, mTIL4;
    private BootstrapButton mButtonSignUp;

    public final String MSG_SUCCESS = "가입되었습니다.";
    public final String MSG_FAILED = "가입 중 오류가 발생하였습니다.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_up);

        mEditTextNickname = (EditText) findViewById(R.id.edit_nickname);
        mEditTextEmail = (EditText) findViewById(R.id.edit_email);
        mEditTextPassword = (EditText) findViewById(R.id.edit_password);
        mEditTextPasswordConfirm = (EditText) findViewById(R.id.edit_password_confirm);

        mEditTexts = new ArrayList<>();
        mEditTexts.add(mEditTextNickname);
        mEditTexts.add(mEditTextEmail);
        mEditTexts.add(mEditTextPassword);
        mEditTexts.add(mEditTextPasswordConfirm);

        mTIL1 = (TextInputLayout) findViewById(R.id.til_1);
        mTIL2 = (TextInputLayout) findViewById(R.id.til_2);
        mTIL3 = (TextInputLayout) findViewById(R.id.til_3);
        mTIL4 = (TextInputLayout) findViewById(R.id.til_4);

        mTILs = new ArrayList<>();
        mTILs.add(mTIL1);
        mTILs.add(mTIL2);
        mTILs.add(mTIL3);
        mTILs.add(mTIL4);

        mTIL1.setErrorEnabled(true);
        mTIL2.setErrorEnabled(true);
        mTIL3.setErrorEnabled(true);
        mTIL4.setErrorEnabled(true);

        mButtonSignUp = (BootstrapButton) findViewById(R.id.btn_sign_up);

        mButtonSignUp.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        boolean result = false;
        result = checkNull(mEditTexts, mTILs);

        if (result) {
            // todo 예외처리 후 값들에 이상없을 시, db에 insert하고 회원가입 액티비티 종료
            boolean success = UserUtil.insert(this, mEditTextNickname.getText().toString(),
                    mEditTextEmail.getText().toString(), mEditTextPassword.getText().toString());

            if (success) {
                new SnackBarAsynk((LinearLayout) findViewById(R.id.sign_up_layout),
                        SignUpActivity.this, MSG_SUCCESS, mEditTexts).execute();
            } else {
                new SnackBarAsynk((LinearLayout) findViewById(R.id.sign_up_layout),
                        SignUpActivity.this, MSG_FAILED, mEditTexts).execute();
            }
        }
    }

    private boolean checkNull(List<EditText> editTexts, List<TextInputLayout> inputLayoutList) {

        boolean result = true;

        List<Boolean> isNullLists = new ArrayList<>();

        for (EditText editText : editTexts) {

            if (editText.getText().toString().isEmpty()) {
                isNullLists.add(true);
            }
            else {
                isNullLists.add(false);
            }
        }

        for (int i = 0; i < isNullLists.size(); i++) {

            if (isNullLists.get(i)) {
                inputLayoutList.get(i).setError("공백입니다.");
            }

        }

        if (!mEditTextPassword.getText().toString()
                .equals(mEditTextPasswordConfirm.getText().toString())) {
            mTIL3.setError("비밀번호 확인과 일치하지 않습니다.");
            mTIL4.setError("비밀번호와 일치하지 않습니다.");
            result = false;
        }

        if (isNullLists.contains(true)) {
            result = false;
        }

        return result;
    }

    private class SnackBarAsynk extends AsyncTask<Void, Void, Void> {

        private Activity mActivity;
        private String mMessage;
        private List<View> mViewList;
        private ViewGroup mParentLayout;
        private boolean isFailed;

        public SnackBarAsynk(ViewGroup parentLayout, Activity activity, String msg,
                List<EditText> views) {
            mMessage = msg;
            mActivity = activity;
            mParentLayout = parentLayout;
            ArrayList<View> viewLists = new ArrayList<>();

            for (View view : views) {
                viewLists.add(view);
            }
            mViewList = viewLists;

            if (msg.equals(MSG_FAILED)) {
                isFailed = true;
            } else if (msg.equals(MSG_SUCCESS)) {
                isFailed = false;
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Snackbar.make(mParentLayout, mMessage, Snackbar.LENGTH_LONG).show();
            if (isFailed == false) {
                for (int i = 0; i < mViewList.size(); i++) {
                    mViewList.get(i).setEnabled(false);
                }
            }
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if (isFailed == false) {
                mActivity.finish();
            }
        }
    }
}
