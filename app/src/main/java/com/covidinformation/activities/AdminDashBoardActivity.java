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

        
    }


}