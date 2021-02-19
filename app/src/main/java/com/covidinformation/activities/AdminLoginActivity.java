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


        getSupportActionBar().setTitle("Dashboard");
        /*getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/

        cdQuarantineGuideLines=(CardView)findViewById(R.id.cdQuarantineGuideLines);
        cdQuarantineGuideLines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminDashBoardActivity.this,AddQuarantineGuidelinesActivity.class));

            }
        });

        cdAddNews=(CardView)findViewById(R.id.cdAddNews);
        cdAddNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminDashBoardActivity.this,NewsInfoActivity.class));

            }
        });
        cdAddCovidCenters=(CardView)findViewById(R.id.cdAddCovidCenters);
        cdAddCovidCenters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminDashBoardActivity.this,CovidCentersInfoActivity.class));

            }
        });

        cdCouuntryReports=(CardView)findViewById(R.id.cdCouuntryReports);
        cdCouuntryReports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminDashBoardActivity.this,CountryReportsActivity.class));

            }
        });

        cdTravellGuidence=(CardView)findViewById(R.id.cdTravellGuidence);
        cdTravellGuidence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminDashBoardActivity.this,AddTravellGuidelinesActivity.class));

            }
        });

        cdNotifications=(CardView)findViewById(R.id.cdNotifications);
        cdNotifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminDashBoardActivity.this,NotificationsActivity.class));

            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.admin_navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.menu_logout){
            Intent intent=new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();


        }
        return super.onOptionsItemSelected(item);
    }


}