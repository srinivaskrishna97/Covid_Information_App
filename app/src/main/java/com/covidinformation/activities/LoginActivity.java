package com.covidinformation.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.covidinformation.R;
import com.covidinformation.Utils;
import com.covidinformation.api.ApiService;
import com.covidinformation.api.RetroClient;
import com.covidinformation.models.ResponseData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    TextView tv_signup,tvForgetPassword;
    Button cirLoginButton;
    ProgressDialog pd;
    EditText editTextEmail,editTextPassword;
    Spinner spinRole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_login);

        getSupportActionBar().setTitle("User Login");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editTextEmail=(EditText)findViewById(R.id.editTextEmail);
        editTextPassword=(EditText)findViewById(R.id.editTextPassword);

        tv_signup=(TextView)findViewById(R.id.tv_signup);
        tvForgetPassword=(TextView)findViewById(R.id.tvForgetPassword);
        cirLoginButton=(Button)findViewById(R.id.cirLoginButton);
        spinRole=(Spinner)findViewById(R.id.spinRole);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.role, R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_drop_down_list);
        spinRole.setAdapter(adapter);

        tvForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,ForgotPasswordActivity.class));

            }
        });


        tv_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegistrationActivity.class));

            }
        });
        cirLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (spinRole.getSelectedItem().toString().equals("User")){
                    if(editTextEmail.getText().toString().isEmpty()){
                        Toast.makeText(LoginActivity.this, "Please Enter Valid Username", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(editTextPassword.getText().toString().isEmpty()){
                        Toast.makeText(LoginActivity.this, "Please Enter Valid Password", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    loginFunction();

                }

                else if (spinRole.getSelectedItem().toString().equals("Admin")){
                    if(editTextEmail.getText().toString().isEmpty()){
                        Toast.makeText(LoginActivity.this, "Please Enter Valid Username", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(editTextPassword.getText().toString().isEmpty()){
                        Toast.makeText(LoginActivity.this, "Please Enter Valid Password", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    adminLoginFunction();

                }
                else {
                    Toast.makeText(LoginActivity.this, "Please Choose role", Toast.LENGTH_SHORT).show();
                }



               // loginFunction();
                //startActivity(new Intent(LoginActivity.this, UserDashBoardActivity.class));

            }
        });
    }
    public  void loginFunction() {
        pd= new ProgressDialog(LoginActivity.this);
        pd.setTitle("Please wait,Data is being submit...");
        pd.show();
        ApiService apiService = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = apiService.userLogin(editTextEmail.getText().toString(),editTextPassword.getText().toString());

        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                pd.dismiss();
                if (response.body().status.equals("true")) {
                    SharedPreferences sharedPreferences = getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
                    SharedPreferences.Editor et=sharedPreferences.edit();
                    et.putString("user_name",editTextEmail.getText().toString());
                    et.commit();
                    startActivity(new Intent(LoginActivity.this, UserDashBoardActivity.class));
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                pd.dismiss();
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public  void adminLoginFunction() {
        pd= new ProgressDialog(LoginActivity.this);
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
                    startActivity(new Intent(LoginActivity.this, AdminDashBoardActivity.class));
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                pd.dismiss();
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
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