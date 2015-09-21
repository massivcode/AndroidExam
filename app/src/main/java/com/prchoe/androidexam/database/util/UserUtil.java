package com.prchoe.androidexam.database.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.prchoe.androidexam.database.contract.UserContract;
import com.prchoe.androidexam.database.helper.UserDbHelper;

/**
 * Created by massivCode on 2015-09-18.
 */
public class UserUtil {
    public static boolean insert(Context context, String nickName, String email, String password) {
        boolean result = true;

        ContentValues values = new ContentValues();
        values.put(UserContract.UserEntry.COLUMN_NAME_NICKNAME, nickName);
        values.put(UserContract.UserEntry.COLUMN_NAME_EMAIL, email);
        values.put(UserContract.UserEntry.COLUMN_NAME_PASSWORD, password);

        long rowId = new UserDbHelper(context).insert(values);

        if(rowId == -1) {
            result = false;
        }

        return result;
    }
    public static boolean login(Context context, String email, String password) {
        boolean result = false;

        String where = UserContract.UserEntry.COLUMN_NAME_EMAIL + " = ? AND " + UserContract.UserEntry.COLUMN_NAME_PASSWORD
                 + " = ? ";
        String[] whereArgs = new String[]{email, password};

        Cursor c = new UserDbHelper(context).query(where, whereArgs);

        if(c != null) {
            c.moveToFirst();

            Log.d("test", "index 0 : " + c.getString(0)); // _id
            Log.d("test", "index 1 : " + c.getString(1)); // 닉네임
            Log.d("test", "index 2 : " + c.getString(2)); // email
            Log.d("test", "index 3 : " + c.getString(3)); // password
            if(c.getString(2).equals(email) && c.getString(3).equals(password)) {
                result = true;
            }
        }
        return result;
    }
}
