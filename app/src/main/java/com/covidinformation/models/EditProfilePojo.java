package com.covidinformation.models;

import com.google.gson.annotations.SerializedName;

public class EditProfilePojo {

    @SerializedName("email")
    public String email;

    @SerializedName("name")
    public String name;

    @SerializedName("password")
    public String password;

    @SerializedName("phone")
    public String phone;

    @SerializedName("country")
    private String country;

    @SerializedName("province")
    private String province;


 @SerializedName("image")
    public String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public EditProfilePojo(String email, String name, String password, String phone) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
