package com.covidinformation.models;

import com.google.gson.annotations.SerializedName;

public class Faqs {

    @SerializedName("fid")
    private String fid;

    @SerializedName("question")
    private String question;

    @SerializedName("ans")
    private String ans;


    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }
}
