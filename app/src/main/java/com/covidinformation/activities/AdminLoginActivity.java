package com.covidinformation.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.covidinformation.R;
import com.covidinformation.Utils;
import com.covidinformation.api.ApiService;
import com.covidinformation.api.RetroClient;
import com.covidinformation.models.ResponseData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminLoginActivity extends AppCompatActivity {
    Button cirLoginButton;
    EditText editTextEmail,editTextPassword;
    ProgressDialog  pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);


        getSupportActionBar().setTitle("Admin Login");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cirLoginButton=(Button)findViewById(R.id.cirLoginButton);
        editTextEmail=(EditText)findViewById(R.id.editTextEmail);
        editTextPassword=(EditText)findViewById(R.id.editTextPassword);


        cirLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(editTextEmail.getText().toString().isEmpty()){
                    Toast.makeText(AdminLoginActivity.this, "Please Enter Valid Username", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(editTextPassword.getText().toString().isEmpty()){
                    Toast.makeText(AdminLoginActivity.this, "Please Enter Valid Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                loginFunction();

            }
        });
    }
    public  void loginFunction() {
        pd= new ProgressDialog(AdminLoginActivity.this);
        pd.setTitle("Please wait,Data is being submit...");
        pd.show();
        ApiService apiService = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = apiService.adminlogin(editTextEmail.getText().toString(),editTextPassword.getText().toString());

        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                pd.dismiss();
                if (response.body().status.equals("true")) {
                    SharedPreferences sharedPreferences = getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
                    SharedPreferences.Editor et=sharedPreferences.edit();
                    et.putString("user_name",editTextEmail.getText().toString());
                    et.commit();
                    startActivity(new Intent(AdminLoginActivity.this, AdminDashBoardActivity.class));
                    finish();
                } else {
                    Toast.makeText(AdminLoginActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                pd.dismiss();
                Toast.makeText(AdminLoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}