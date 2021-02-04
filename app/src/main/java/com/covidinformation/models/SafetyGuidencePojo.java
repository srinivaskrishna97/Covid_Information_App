package com.covidinformation.models;

import com.google.gson.annotations.SerializedName;

public class SafetyGuidencePojo {

    @SerializedName("message")
    public String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SafetyGuidencePojo(String message) {
        this.message = message;
    }
}
