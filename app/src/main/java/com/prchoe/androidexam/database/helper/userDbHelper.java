
package com.prchoe.androidexam.database.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.prchoe.androidexam.database.contract.UserContract;

/**
 * Created by massivCode on 2015-09-18.
 */
public class UserDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "User.db";

    public static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + UserContract.UserEntry.TABLE_NAME + " ( " +
                    UserContract.UserEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    UserContract.UserEntry.COLUMN_NAME_NICKNAME + " TEXT NOT NULL, " +
                    UserContract.UserEntry.COLUMN_NAME_EMAIL + " TEXT NOT NULL UNIQUE, " +
                    UserContract.UserEntry.COLUMN_NAME_PASSWORD + " TEXT NOT NULL " +
                    ");";

    public UserDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insert(ContentValues values) {
        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

        return db.insert(UserContract.UserEntry.TABLE_NAME, null, values);
    }

    public Cursor query(String selection, String[] selectionArgs) {
        SQLiteDatabase db = getReadableDatabase();

        // 가져올 값의 컬럼명 정의
        String[] projection = {
                UserContract.UserEntry._ID,
                UserContract.UserEntry.COLUMN_NAME_NICKNAME,
                UserContract.UserEntry.COLUMN_NAME_EMAIL,
                UserContract.UserEntry.COLUMN_NAME_PASSWORD
        };

        //

        Cursor c = db.query(
                UserContract.UserEntry.TABLE_NAME, // 테이블 명
                projection, // 컬럼명 배열
                selection, // where 절의 컬럼명
                selectionArgs, // where 절의 값
                null, // group by (그룹핑)
                null, // having (그룹핑)
                null // order by (정렬)
        );

        return c;
    }

    public int update(String email, String newPassword) {
        SQLiteDatabase db = getReadableDatabase();

        // 패스워드를 변경
        ContentValues values = new ContentValues();
        values.put(UserContract.UserEntry.COLUMN_NAME_PASSWORD, newPassword);

        // Email이 ?와 같다면
        String selection = UserContract.UserEntry.COLUMN_NAME_EMAIL + " = ?";
        // ?에 들어갈 값을 바인딩
        String[] selectionArgs = {email};

        int count = db.update(
                UserContract.UserEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        return count;
    }

    public void delete(String email) {
        SQLiteDatabase db = getWritableDatabase();

        // 지울 조건
        String selection = UserContract.UserEntry.COLUMN_NAME_EMAIL + " = ?";
        String[] selectionArgs = { email };
        db.delete(UserContract.UserEntry.TABLE_NAME, selection, selectionArgs);
    }

}
