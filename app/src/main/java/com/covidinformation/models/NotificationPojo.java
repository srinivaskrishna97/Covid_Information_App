package com.covidinformation.models;

import com.google.gson.annotations.SerializedName;

public class NotificationPojo {

    @SerializedName("msg")
    public String msg;

    @SerializedName("name")
    public String name;

    @SerializedName("nid")
    public String nid;

    @SerializedName("type")
    public String type;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
