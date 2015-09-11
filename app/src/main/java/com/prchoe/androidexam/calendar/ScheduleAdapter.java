
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
    private Calendar mKey;
    private String TAG = "test";

    public ScheduleAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void changeDate(Calendar key) {
        mKey = key;
        notifyDataSetChanged();
    }

    public void initData(Map<Calendar, List<ScheduleData>> mData, Calendar calendar) {
        this.mData = mData;
        mKey = calendar;
        notifyDataSetChanged();
    }

    // getCount가 Adapter의 사이즈를 정의해주는 부분
    // getView에서 position의 최대 사이즈 정의
    @Override
    public int getCount() {

        if (mData != null) {
            if (mData.get(mKey) != null) {
                return mData.get(mKey).size();
            } else {
                return 1;
            }
        } else {
            return 1;
        }

    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    public Object getItem(Calendar calendar) {

        if (mData != null) {
            return mData.get(calendar);
        } else {
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();

            convertView = LayoutInflater.from(mContext).inflate(R.layout.activity_schedule_item,
                    null);
            holder.scheduleItemTime = (TextView) convertView.findViewById(R.id.schedule_item_time);
            holder.scheduleItemContent = (TextView) convertView
                    .findViewById(R.id.schedule_item_content);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        List<ScheduleData> datas =
                (List<ScheduleData>) getItem(mKey);

        if (datas != null) {
            holder.scheduleItemContent.setText(datas.get(position).getContent());
            holder.scheduleItemTime.setText(datas.get(position).getHour() + "시 "
                    + datas.get(position).getMinute() + "분");
            holder.scheduleItemTime.setVisibility(View.VISIBLE);
        } else {
            holder.scheduleItemContent.setText("데이터가 없습니다.");
            holder.scheduleItemTime.setVisibility(View.GONE);
        }

        return convertView;
    }

    static class ViewHolder {
        TextView scheduleItemTime;
        TextView scheduleItemContent;
    }


}
