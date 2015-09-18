
package com.prchoe.androidexam.database.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.prchoe.androidexam.database.contract.UserContract;

/**
 * Created by massivCode on 2015-09-18.
 */
public class userDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "User.db";
    public static final int DATABASE_VERSION = 1;
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + UserContract.UserEntry.TABLE_NAME + " ( " +
                    UserContract.UserEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    UserContract.UserEntry.COLUMN_NAME_NICKNAME + " TEXT NOT NULL, " +
                    UserContract.UserEntry.COLUMN_NAME_EMAIL + " TEXT NOT NULL UNIQUE, " +
                    UserContract.UserEntry.COLUMN_NAME_PASSWORD + " TEXT NOT NULL );";

    public userDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
