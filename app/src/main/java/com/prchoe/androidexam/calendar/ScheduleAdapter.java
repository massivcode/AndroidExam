
package com.prchoe.androidexam.calendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.prchoe.androidexam.R;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * Created by massivCode on 2015-09-09.
 */
public class ScheduleAdapter extends BaseAdapter {

    private Context mContext;
    private Map<Calendar, List<ScheduleData>> mData;
    private Calendar mCalendar;

    public ScheduleAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void changeDate(Calendar key) {
        mCalendar = key;
        notifyDataSetChanged();
    }

    public void initData(Map<Calendar, List<ScheduleData>> mData, Calendar key) {
        this.mData = mData;
        mCalendar = key;
        notifyDataSetChanged();
    }

    // getCount가 Adapter의 사이즈를 정의해주는 부분
    // getView에서 position의 최대 사이즈 정의
    @Override
    public int getCount() {
        if(mData.get(mCalendar) != null) {
            return mData.get(mCalendar).size();
        } else {
            return mData.size();
        }

    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    public Object getItem(Calendar calendar) {
        return mData.get(calendar);
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

            convertView = LayoutInflater.from(mContext).inflate(R.layout.activity_schedule_item, null);
            holder.scheduleItemTime = (TextView)convertView.findViewById(R.id.schedule_item_time);
            holder.scheduleItemContent = (TextView)convertView.findViewById(R.id.schedule_item_content);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        List<ScheduleData> datas = (List<ScheduleData>)getItem(mCalendar);

        if(datas != null) {
            holder.scheduleItemContent.setText(datas.get(position).getContent());
            holder.scheduleItemTime.setText(datas.get(position).getHour() + "시 " + datas.get(position).getMinute() + "분");
            holder.scheduleItemTime.setVisibility(View.VISIBLE);
        } else {
            holder.scheduleItemContent.setText("데이터가 없습니다.");
            holder.scheduleItemTime.setVisibility(View.GONE);
        }


        return convertView;
    }
    static class ViewHolder{
        TextView scheduleItemTime;
        TextView scheduleItemContent;
    }

}
