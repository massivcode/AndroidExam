package com.prchoe.androidexam.database.util;

import android.content.ContentValues;
import android.content.Context;

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
}
