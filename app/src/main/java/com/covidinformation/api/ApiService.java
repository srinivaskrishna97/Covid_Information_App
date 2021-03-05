package com.covidinformation.api;

import com.covidinformation.models.EditProfilePojo;
import com.covidinformation.models.Faqs;
import com.covidinformation.models.GetAllNewsPojo;
import com.covidinformation.models.GetCovidCentersPojo;
import com.covidinformation.models.GetQGPojo;
import com.covidinformation.models.NotificationPojo;
import com.covidinformation.models.QGuideLinesPojo;
import com.covidinformation.models.ResponseData;
import com.covidinformation.models.SafetyGuidencePojo;
import com.covidinformation.models.VaccinePojo;

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


    @GET("/covid/addnotification.php")
    Call<ResponseData> addnotification(
            @Query("type") String type,
            @Query("name") String name,
            @Query("msg") String msg);

    @Multipart
    @POST("covid/user_registration.php")
    Call<ResponseData> user_registration(
            @Part MultipartBody.Part file,
            @PartMap Map<String, String> partMap
    );

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

    @GET("covid/forgotpassword.php")
    Call<ResponseData> forgotPassword
            (@Query("email") String email);

    @Multipart
    @POST("covid/addnews.php")
    Call<ResponseData> addnews(
            @Part MultipartBody.Part file,
            @PartMap Map<String, String> partMap
    );


    @GET("covid/getnews.php")
    Call<List<GetAllNewsPojo>> getAllNews();

    @GET("covid/getvaccine.php")
    Call<List<VaccinePojo>> getvaccine();

    @GET("covid/getfaqs.php")
    Call<List<Faqs>> getfaqs();

    @GET("covid/searchquarantine.php")
    Call<List<QGuideLinesPojo>> searchquarantine(@Query("province") String province);

    @GET("covid/getquarantine.php")
    Call<List<QGuideLinesPojo>> allsearchquarantine();

    @GET("covid/gettravel.php")
    Call<List<QGuideLinesPojo>> allsearchtravel();

    @GET("/covid/getnotification.php")
    Call<List<NotificationPojo>> getnotification();


    @GET("covid/searchtravel.php")
    Call<List<QGuideLinesPojo>> searchtravel(@Query("province") String province);


    @GET("covid/getquarantine.php")
    Call<List<GetQGPojo>> getquarantine();

    @GET("/covid/addquarantine.php?")
    Call<ResponseData> addquarantine(
            @Query("province") String email,
            @Query("country") String password,
            @Query("description") String description
    );




    @GET("/covid/editnews.php?")
    Call<ResponseData> editnews(
            @Query("title") String title,
            @Query("des") String des,
            @Query("nid") String nid
    );




    @GET("/covid/editvaccine.php?")
    Call<ResponseData> editvaccine(
            @Query("title") String title,
            @Query("des") String des,
            @Query("vid") String vid
    );

    @GET("/covid/edittravel.php?")
    Call<ResponseData> edittravel(
            @Query("province") String province,
            @Query("country") String country,
            @Query("description") String description,
            @Query("tid") String tid
    );


    @GET("/covid/editquarantine.php?")
    Call<ResponseData> editquarantine(
            @Query("province") String province,
            @Query("country") String country,
            @Query("description") String description,
            @Query("qid") String qid
    );


    @GET("/covid/addvaccine.php?")
    Call<ResponseData> addvaccine(
            @Query("title") String title,
            @Query("des") String des,
            @Query("dat") String dat
    );



    @GET("/covid/addcenters.php?")
    Call<ResponseData> addcenters(
            @Query("name") String name,
            @Query("location") String location,
            @Query("address1") String address1,
            @Query("address2") String address2,
            @Query("phone") String phone,
            @Query("lat") String lat,
            @Query("lg") String lg
    );




    @GET("/covid/editcerters.php?")
    Call<ResponseData> editcerters(
            @Query("name") String name,
            @Query("location") String location,
            @Query("address1") String address1,
            @Query("address2") String address2,
            @Query("phone") String phone,
            @Query("cid") String cid
    );


    @GET("/covid/addtravel.php?")
    Call<ResponseData> addtravel(
            @Query("province") String email,
            @Query("country") String password,
            @Query("description") String description
    );

    @GET("covid/gettravel.php")
    Call<List<GetQGPojo>> gettravel();


    @GET("covid/getcovidcenters.php")
    Call<List<GetCovidCentersPojo>> getcovidcenters();


    @GET("covid/safetyguidence.php")
    Call<List<SafetyGuidencePojo>> safetyguidence();




    @GET("/covid/deletecenter.php")
    Call<ResponseData> deletecenter(@Query("id") String id);


    @GET("/covid/deletequarantine.php")
    Call<ResponseData> deletequarantine(@Query("id") String id);


    @GET("/covid/deletenews.php")
    Call<ResponseData> deletenews(@Query("id") String id);


    @GET("/covid/deletevaccine.php")
    Call<ResponseData> deletevaccine(@Query("id") String id);


    @GET("/covid/deletetravel.php")
    Call<ResponseData> deletetravel(@Query("id") String id);




}
