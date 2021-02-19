package com.covidinformation.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.covidinformation.R;

public class AdminDashBoardActivity extends AppCompatActivity {
    CardView cdAddNews,cdAddCovidINfo,cdCouuntryReports,cdTravellGuidence,cdNotifications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dash_board);

        getSupportActionBar().setTitle("Dashboard");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
                startActivity(new Intent(AdminDashBoardActivity.this,AddNewsActivity.class));

            }
        });
        cdAddCovidINfo=(CardView)findViewById(R.id.cdAddCovidINfo);

        cdAddCovidINfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminDashBoardActivity.this,AddCovidInfoActivity.class));

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