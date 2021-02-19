package com.covidinformation.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.covidinformation.R;
import com.covidinformation.Utils;
import com.covidinformation.activities.EditProfileActivity;
import com.covidinformation.activities.UserDashBoardActivity;
import com.covidinformation.api.ApiService;
import com.covidinformation.api.RetroClient;
import com.covidinformation.models.EditProfilePojo;
import com.covidinformation.models.ResponseData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyProfileFragment extends Fragment {
    EditText etName,etMobilenumber,etEmail,etPassword;
    Button btn_updateProfile;
    SharedPreferences sharedPreferences;
    ProgressDialog progressDialog;
    List<EditProfilePojo> a1;
    ResponseData a2;
    View view;

    public static MyProfileFragment myProfileFragment() {
        MyProfileFragment fragment = new MyProfileFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_my_profile, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("My Profile");


        etName=(EditText)view.findViewById(R.id.etName);
        etMobilenumber=(EditText)view.findViewById(R.id.etMobilenumber);
        etEmail=(EditText)view.findViewById(R.id.etEmail);
        etPassword=(EditText)view.findViewById(R.id.etPassword);

        getMyProfile();
        btn_updateProfile=(Button)view.findViewById(R.id.btn_updateProfile);
        btn_updateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateProfile();
            }
        });
        return view;
    }

    public void getMyProfile(){
        sharedPreferences = getActivity().getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
        String session = sharedPreferences.getString("user_name", "def-val");
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<List<EditProfilePojo>> call = service.editProfile(session);
        call.enqueue(new Callback<List<EditProfilePojo>>() {
            @Override
            public void onResponse(Call<List<EditProfilePojo>> call, Response<List<EditProfilePojo>> response) {
                progressDialog.dismiss();
                a1 = response.body();
                EditProfilePojo user1 = a1.get(0);
                etName.setText(user1.getName());
                etMobilenumber.setText(user1.getPhone());
                etEmail.setText(user1.getEmail());
                etPassword.setText(user1.getPassword());
            }

            @Override
            public void onFailure(Call<List<EditProfilePojo>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
    private void updateProfile() {
        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String phone = etMobilenumber.getText().toString();
        String password = etPassword.getText().toString();

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = service.updateProfile(name,email,phone,password);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {

                progressDialog.dismiss();
                a2 = response.body();

                if (response.body().status.equals("true")) {
                    Toast.makeText(getContext(), response.body().message, Toast.LENGTH_LONG).show();
                    //Intent intent=new Intent(getContext(), UserDashBoardActivity.class);
                    //getActivity().finish();
                } else {
                    Toast.makeText(getContext(), response.body().message, Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}