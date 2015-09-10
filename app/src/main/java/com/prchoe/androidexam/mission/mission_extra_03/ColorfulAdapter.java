
package com.prchoe.androidexam.mission.mission_extra_03;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.prchoe.androidexam.R;

import java.util.List;

/**
 * Created by massivCode on 2015-09-10.
 */
public class ColorfulAdapter extends BaseAdapter {

    List<ColorfulData> mData;
    Context mContext;

    public ColorfulAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<ColorfulData> Data) {
        this.mData = Data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.activity_colorful_item,
                    parent, false);

            holder.colorfulIV = (ImageView) convertView.findViewById(R.id.colorful_image);
            holder.colorfulTitleTV = (TextView) convertView.findViewById(R.id.colorful_title);
            holder.colorfulSubTitleTV = (TextView) convertView.findViewById(R.id.colorful_subtitle);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.colorfulIV.setImageResource(mData.get(position).getImageResourceId());
        holder.colorfulTitleTV.setText(mData.get(position).getTitle());
        holder.colorfulSubTitleTV.setText(mData.get(position).getSubTitle());

        int alpha, red, green, blue;
        alpha = (int) (Math.random() * 254 + 1);
        red = (int) (Math.random() * 254 + 1);
        green = (int) (Math.random() * 254 + 1);
        blue = (int) (Math.random() * 254 + 1);

        convertView.setBackgroundColor(Color.argb(alpha, red, green, blue));

       Animation anim =  AnimationUtils.loadAnimation(mContext, R.anim.extra02);

        convertView.startAnimation(anim);

        return convertView;
    }

    static class ViewHolder {
        ImageView colorfulIV;
        TextView colorfulTitleTV;
        TextView colorfulSubTitleTV;
    }

}
