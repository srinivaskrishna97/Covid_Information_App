package com.covidinformation.models;

import com.google.gson.annotations.SerializedName;

public class GetQGPojo {

    @SerializedName("country")
    public String country;

    @SerializedName("description")
    public String description;

    @SerializedName("province")
    public String province;

    @SerializedName("qid")
    public String qid;


    @SerializedName("tid")
    public String tid;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getQid() {
        return qid;
    }

    public void setQid(String qid) {
        this.qid = qid;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }
}
