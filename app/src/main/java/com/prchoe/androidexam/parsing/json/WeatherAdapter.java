package com.prchoe.androidexam.parsing.json;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.prchoe.androidexam.R;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by massivCode on 2015-09-14.
 */
public class WeatherAdapter extends BaseAdapter {

    private SimpleDateFormat mFormat = new SimpleDateFormat("HH:mm");
    private List<Weather> mList;
    private Context mContext;

    public WeatherAdapter(Context mContext, List<Weather> mList) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null) {
            holder = new ViewHolder();

            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_weather, parent, false);

            holder.descriptionTextView = (TextView)convertView.findViewById(R.id.tv_desc);
            holder.timeTextView = (TextView)convertView.findViewById(R.id.tv_time);
            holder.tempTextView = (TextView)convertView.findViewById(R.id.tv_temp);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        Weather weather = mList.get(position);
        holder.timeTextView.setText("" + mFormat.format(weather.getTime()));
        holder.tempTextView.setText(weather.getTemp() +  "℃");
        holder.descriptionTextView.setText(weather.getDescription());


        return convertView;
    }

    static class ViewHolder {
        TextView timeTextView;
        TextView tempTextView;
        TextView descriptionTextView;
    }
}
