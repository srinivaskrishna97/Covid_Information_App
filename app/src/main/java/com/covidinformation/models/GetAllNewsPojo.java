package com.covidinformation.models;


import com.google.gson.annotations.SerializedName;

public class GetAllNewsPojo {

    @SerializedName("title")
    public String title;

    @SerializedName("nid")
    public String nid;

    @SerializedName("image")
    public String image;

    @SerializedName("des")
    public String des;

    @SerializedName("dat")
    public String dat;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getDat() {
        return dat;
    }

    public void setDat(String dat) {
        this.dat = dat;
    }
}
