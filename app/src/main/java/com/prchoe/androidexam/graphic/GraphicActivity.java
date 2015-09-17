
package com.prchoe.androidexam.graphic;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.prchoe.androidexam.R;
import com.prchoe.androidexam.utils.storage.StorageUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class GraphicActivity extends AppCompatActivity {

    private ShapeView mShapeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mShapeView = new ShapeView(this);
        setContentView(mShapeView);

    }

    // 메뉴 생성 부분
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return true;
    }

    // 메뉴 클릭시 처리
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_save:
                save();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void save() {

        // getDrawingCache() 를 가능하게 세팅함.
        mShapeView.setDrawingCacheEnabled(true);
        // 뷰 위에 그려진 모든 걸 비트맵으로 얻어옴.
        Bitmap bitmap = Bitmap.createBitmap(mShapeView.getDrawingCache());

        // 외부 저장소에 접근이 가능하면, 파일 생성
        if (StorageUtil.isExternalStorageWritable()) {

            File file = new File(
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM),
                    "pictureTest.jpg");

            FileOutputStream fos = null;

            try {
                fos = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                fos.flush();
                fos.close();
                Toast.makeText(this, "저장 되었습니다", Toast.LENGTH_SHORT).show();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // 미디어 스캔을 강제로 하도록 브로드캐스트를 발송
            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + file.toString())));

        } else {
            Toast.makeText(GraphicActivity.this, "메모리를 사용할 수 없습니다.", Toast.LENGTH_SHORT).show();
        }


    }

}
