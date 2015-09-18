
package com.prchoe.androidexam.database.contract;

import android.provider.BaseColumns;

/**
 * Created by massivCode on 2015-09-18.
 */
public final class UserContract {
    public UserContract() {
    }

    public static abstract class UserEntry implements BaseColumns {
        public static final String TABLE_NAME           = "User";
        public static final String COLUMN_NAME_NICKNAME = "nickname";
        public static final String COLUMN_NAME_EMAIL    = "email";
        public static final String COLUMN_NAME_PASSWORD = "password";

    }
}
