package com.covidinformation.models;

import com.google.gson.annotations.SerializedName;

public class GetCovidCentersPojo {

    @SerializedName("address1")
    public String address1;

    @SerializedName("address2")
    public String address2;

    @SerializedName("cid")
    public String cid;

    @SerializedName("lat")
    public String lat;

    @SerializedName("lg")
    public String lg;

    @SerializedName("location")
    public String location;

    @SerializedName("name")
    public String name;

    @SerializedName("phone")
    public String phone;

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLg() {
        return lg;
    }

    public void setLg(String lg) {
        this.lg = lg;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
