package com.covidinformation.models;

import com.google.gson.annotations.SerializedName;

public class QGuideLinesPojo {

    @SerializedName("country")
    public String country;

    @SerializedName("description")
    public String description;

    @SerializedName("province")
    public String province;

    @SerializedName("qid")
    public String qid;

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
}
