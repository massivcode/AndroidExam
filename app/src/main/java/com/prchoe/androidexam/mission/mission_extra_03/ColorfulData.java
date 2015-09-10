
package com.prchoe.androidexam.mission.mission_extra_03;

/**
 * Created by massivCode on 2015-09-10.
 */
public class ColorfulData {
    private int imageResourceId;
    private String title;
    private String subTitle;

    public ColorfulData() {
    }

    public ColorfulData(int imageResourceId, String title, String subTitle) {
        this.imageResourceId = imageResourceId;
        this.title = title;
        this.subTitle = subTitle;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }
}
