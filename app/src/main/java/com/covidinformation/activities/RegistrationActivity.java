package com.covidinformation.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.covidinformation.R;
import com.covidinformation.api.ApiService;
import com.covidinformation.api.RetroClient;
import com.covidinformation.models.ResponseData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class
RegistrationActivity extends AppCompatActivity {
    TextView tv_signin;
    Button btn_register;
    EditText etName,etMobilenumber,etEmail,etPassword;
    Spinner spinAge,spinSelectCountry,spinSelectProvince;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_registration);

        getSupportActionBar().setTitle("Registration"); //set title to the activity
        getSupportActionBar().setHomeButtonEnabled(true); //set homebutton enable on a activity.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //set up the UP arrow

        //take values from the layout files.
        etName=(EditText)findViewById(R.id.etName);
        etMobilenumber=(EditText)findViewById(R.id.etMobilenumber);
        etEmail=(EditText)findViewById(R.id.etEmail);
        etPassword=(EditText)findViewById(R.id.etPassword);
        tv_signin=(TextView)findViewById(R.id.tv_signin);
        btn_register=(Button)findViewById(R.id.btn_register);
        spinAge=(Spinner)findViewById(R.id.spinAge);
        spinSelectCountry=(Spinner)findViewById(R.id.spinSelectCountry);
        spinSelectProvince=(Spinner)findViewById(R.id.spinSelectProvince);

        //array for age
        ArrayAdapter adapter1 = ArrayAdapter.createFromResource(this, R.array.age, R.layout.spinner_item);
        adapter1.setDropDownViewResource(R.layout.spinner_drop_down_list);
        spinAge.setAdapter(adapter1);

        //array for country
        ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this, R.array.country, R.layout.spinner_item);
        adapter2.setDropDownViewResource(R.layout.spinner_drop_down_list);
        spinSelectCountry.setAdapter(adapter2);

        //array for province
        ArrayAdapter adapter3 = ArrayAdapter.createFromResource(this, R.array.province, R.layout.spinner_item);
        adapter3.setDropDownViewResource(R.layout.spinner_drop_down_list);
        spinSelectProvince.setAdapter(adapter3);

        //login redirection from registration code
        tv_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this,LoginActivity.class));

            }
        });

        //for registration and validation check.
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etName.getText().toString().isEmpty()){
                    Toast.makeText(RegistrationActivity.this, "Please Enter Name..", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(etMobilenumber.getText().toString().isEmpty()){
                    Toast.makeText(RegistrationActivity.this, "Please Enter MObile number..", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(etEmail.getText().toString().isEmpty()){
                    Toast.makeText(RegistrationActivity.this, "Please Enter Email..", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(etPassword.getText().toString().isEmpty()){
                    Toast.makeText(RegistrationActivity.this, "Please Enter password..", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(spinAge.getSelectedItem().toString().equals("Age")){
                    Toast.makeText(RegistrationActivity.this, "Please Select Age..", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(spinSelectCountry.getSelectedItem().toString().equals("Country")){
                    Toast.makeText(RegistrationActivity.this, "Please Select Country..", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(spinSelectProvince.getSelectedItem().toString().equals("Province")){
                    Toast.makeText(RegistrationActivity.this, "Please Select Province..", Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    //function calling for data saving to the DB.
                   // startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
                    submitData();

                }

            }
        });
    }

    ProgressDialog progressDialog;

    //transfering data to the function variable.
    private void submitData() {
        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String mobileno = etMobilenumber.getText().toString();
        String age=spinAge.getSelectedItem().toString();
        String country=spinSelectCountry.getSelectedItem().toString();
        String provience=spinSelectProvince.getSelectedItem().toString();

        //message will appear while processing of data transmission.
        progressDialog = new ProgressDialog(RegistrationActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        //service call for data transferring.
        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = service.userRegistration(name, email, mobileno,password,age,country,provience);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            //success of data transmission toast.and transfer to login activity.
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                progressDialog.dismiss();
                if (response.body().status.equals("true")) {
                    Toast.makeText(RegistrationActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(RegistrationActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(RegistrationActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            //if fails to data transmission toast.and transfer to login activity.
            public void onFailure(Call<ResponseData> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(RegistrationActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
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