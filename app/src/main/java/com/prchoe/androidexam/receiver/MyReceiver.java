
package com.prchoe.androidexam.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
       if (intent.getAction().equals(Intent.ACTION_BATTERY_LOW)) {
           Toast.makeText(context, "배터리가 부족합니다", Toast.LENGTH_SHORT).show();

//           내 이후에는 리시버를 안받음
//           이어폰 끼면 앱이 팝업되는 뮤직플레이어가 여러개 있는데,
//           이 메서드를 사용하고, 운좋게 첫번째로 받았을 경우 이후의 리시버는 무시됨
//           abortBroadcast();
       } else if (intent.getAction().equals("android.intent.action.MY_BROADCAST")) {
           Toast.makeText(context, "마이 브로드캐스트를 수신했습니다.", Toast.LENGTH_SHORT).show();
       } else if(intent.getAction().equals(Intent.ACTION_AIRPLANE_MODE_CHANGED)) {
           Toast.makeText(context, "비행기 모드로 변경되었습니다.", Toast.LENGTH_SHORT).show();
       }


    }
}
