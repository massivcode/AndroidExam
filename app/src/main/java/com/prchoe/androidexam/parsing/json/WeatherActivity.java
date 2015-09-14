package com.prchoe.androidexam.parsing.json;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.prchoe.androidexam.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * 나씨 정보 보여주는 앱
 */
public class WeatherActivity extends AppCompatActivity {

    // 날씨 예보 제공 URL
    private final static String URL_FORECAST = "http://api.openweathermap.org/data/2.5/forecast?q=suwon&units=metric";
    private final static String TAG = WeatherActivity.class.getSimpleName();
    private EditText mCityEditText;
    private ListView mWeatherListView;
    private WeatherAdapter mAdapter;
    private ProgressBar mProgressBar;



    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mWeatherListView.setAdapter(mAdapter);
            mProgressBar.setVisibility(View.GONE);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        mCityEditText = (EditText) findViewById(R.id.et_city);
        mWeatherListView = (ListView) findViewById(R.id.lv_weather);

        mProgressBar = (ProgressBar)findViewById(R.id.progressbar);

        showWeatherInfo();

    }

    public void showWeatherInfo() {
        mProgressBar.setVisibility(View.VISIBLE);

        // 안드로이드 4.0 부터 네트워킹 제약
        // 네트워킹 처리는 반드시 쓰레드에서 해야한다
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // HTTP에서 내용을 String으로 받아온다.
                    String jsonString = getReturnString(getUrlConnection());

                    // 받아온 JSON String을 JSON Object로 변환한다.
                    JSONObject jsonObject = new JSONObject(jsonString);
                    JSONArray jsonArray = jsonObject.getJSONArray("list");

                    // 날씨 정보 저장할 리스트
                    List<Weather> weatherList = new ArrayList<Weather>();

                    for(int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        long time = object.getLong("dt");
                        String temp = object.getJSONObject("main").getString("temp");
                        String description = object.getJSONArray("weather").getJSONObject(0).getString("description");

                        weatherList.add(new Weather(time, temp, description));
                        Log.d(TAG, weatherList.get(i).toString());
                    }

                    mAdapter = new WeatherAdapter(WeatherActivity.this, weatherList);

                    // UI 갱신을 핸들러에 요청
                    mHandler.sendEmptyMessage(0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * useUrl
     *
     * @throws IOException
     * @throws Exception
     * @Note : URL커넥션 사용
     */
    public void useUrl() throws IOException, Exception {

        String testVal = getReturnString(getUrlConnection());
        Log.d(TAG, testVal);
    }


    /**
     * getUrlConnection
     *
     * @return
     * @throws Exception
     * @Note : url 커넥션
     */
    public static URLConnection getUrlConnection()
            throws Exception {

        // URL 조합
        String urlString = URL_FORECAST;

        URL url = new URL(urlString); // 넘어오는 URL밎정보
        URLConnection connection = url.openConnection(); // 커넥션
        connection.setDoOutput(true);
        return connection;
    }


    /**
     * getReturnString
     *
     * @param connection
     * @return
     * @throws IOException
     * @Note : 커넥션된 결과값
     */
    public static String getReturnString(URLConnection connection)
            throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(
                connection.getInputStream(), "UTF-8")); // 반환되는 값이 UTF-8 경우
        StringBuffer buffer = new StringBuffer();
        String decodedString;

        while ((decodedString = in.readLine()) != null) {
            buffer.append(decodedString);

        }

        in.close();

        return buffer.toString();
    }


}
