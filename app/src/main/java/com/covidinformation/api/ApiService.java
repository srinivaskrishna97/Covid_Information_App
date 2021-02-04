package com.covidinformation.api;

import com.covidinformation.models.EditProfilePojo;
import com.covidinformation.models.ResponseData;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

public interface ApiService {

    @GET("covid/user_registration.php")
    Call<ResponseData> userRegistration(
            @Query("name") String name,
            @Query("email") String email,
            @Query("phone") String phone,
            @Query("password") String password,
            @Query("age") String age,
            @Query("country") String country,
            @Query("province") String province);

    @GET("/covid/user_login.php?")
    Call<ResponseData> userLogin(
            @Query("email") String email,
            @Query("password") String password);


    @GET("/covid/admin_login.php?")
    Call<ResponseData> adminlogin(
            @Query("uname") String uname,
            @Query("password") String password);


    @GET("/covid/user_profile.php?")
    Call<List<EditProfilePojo>> editProfile(
            @Query("email") String email);

    @GET("covid/update_profile.php")
    Call<ResponseData> updateProfile(
            @Query("name") String name,
            @Query("email") String email,
            @Query("phone") String phone,
            @Query("password") String password);

    @GET("rideshare/forgotPassword.php")
    Call<ResponseData> forgotPassword
            (@Query("emailid") String emailid);






}
