package com.prchoe.androidexam.utils.storage;

import android.os.Environment;

/**
 * Created by massivCode on 2015-09-17.
 *
 * 스토리지 유틸틸 */
public class StorageUtil {

    /**
     * Checks if external storage is available for read and write
     * @return
     */
    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();

        if(Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /**
     * Checks if external storage is available to at least read
     * @return
     */
    public static boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }


}
