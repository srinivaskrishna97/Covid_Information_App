package com.covidinformation.models;

import com.google.gson.annotations.SerializedName;

public class VaccinePojo {
    @SerializedName("dat")
    public String dat;

    @SerializedName("des")
    public String des;

    @SerializedName("title")
    public String title;

    @SerializedName("vid")
    public String vid;

    public String getDat() {
        return dat;
    }

    public void setDat(String dat) {
        this.dat = dat;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }
}
