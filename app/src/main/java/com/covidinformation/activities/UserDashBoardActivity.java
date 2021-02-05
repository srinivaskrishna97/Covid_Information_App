package com.covidinformation.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.covidinformation.R;
import com.google.android.material.navigation.NavigationView;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class UserDashBoardActivity extends AppCompatActivity {
    TextView tvTotalCases, tvRecovered, tvDeaths, tvActive;
    PieChart pieChart;
    private ActionBarDrawerToggle t;
    private NavigationView navigation_view;
    private DrawerLayout layout_drawer;
    CardView cdSafetyMeasures,cdfaqs,cdTravellGuidence,cdQuarentineGuidelines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);

        getSupportActionBar().setTitle("Dashboard");



        // Link those objects with their
        // respective id's that
        // we have given in .XML file
        tvTotalCases = (TextView)findViewById(R.id.tvTotalCases);
        tvRecovered = findViewById(R.id.tvRecovered);
        tvDeaths = findViewById(R.id.tvDeaths);
        tvActive = findViewById(R.id.tvActive);
        pieChart = findViewById(R.id.piechart);

        // Creating a method setData()
        // to set the text in text view and pie chart
        setData();
        navigationView();
        userSafetyGuidence();
    }

    private void setData()
    {
        // Set the percentage of language used
        tvTotalCases.setText(Integer.toString(40));
        tvRecovered.setText(Integer.toString(30));
        tvDeaths.setText(Integer.toString(5));
        tvActive.setText(Integer.toString(25));

        // Set the data and color to the pie chart
        pieChart.addPieSlice(
                new PieModel(
                        "Total Cases",
                        Integer.parseInt(tvTotalCases.getText().toString()),
                        Color.parseColor("#FFA726")));
        pieChart.addPieSlice(
                new PieModel(
                        "Recovered",
                        Integer.parseInt(tvRecovered.getText().toString()),
                        Color.parseColor("#66BB6A")));
        pieChart.addPieSlice(
                new PieModel(
                        "Deaths",
                        Integer.parseInt(tvDeaths.getText().toString()),
                        Color.parseColor("#EF5350")));
        pieChart.addPieSlice(
                new PieModel(
                        "Active",
                        Integer.parseInt(tvActive.getText().toString()),
                        Color.parseColor("#29B6F6")));

        // To animate the pie chart
        pieChart.startAnimation();
    }
    private void navigationView(){
        layout_drawer = (DrawerLayout)findViewById(R.id.layout_drawer);
        t = new ActionBarDrawerToggle(this, layout_drawer,R.string.Open, R.string.Close);
        layout_drawer.addDrawerListener(t);
        t.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigation_view = (NavigationView)findViewById(R.id.navigation_view);
        navigation_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id)
                {
                    case R.id.myprofile:
                        Intent intent=new Intent(getApplicationContext(), EditProfileActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.map:
                        Intent i=new Intent(getApplicationContext(), MapActivity.class);
                        startActivity(i);
                        break;


                    case R.id.logout:
                        Intent logout=new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(logout);
                        finish();
                        break;

                    default:
                        return true;
                }
                layout_drawer.closeDrawer(GravityCompat.START);
                return true;

            }
        });
    }
    @Override
    public void onBackPressed() {
        if (layout_drawer.isDrawerOpen(GravityCompat.START)) {
            layout_drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (layout_drawer.isDrawerOpen(GravityCompat.START)) {
            layout_drawer.closeDrawer(GravityCompat.START);
        } else {
            layout_drawer.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
    public void userSafetyGuidence(){

        cdSafetyMeasures = (CardView) findViewById(R.id.cdSafetyMeasures);
        cdSafetyMeasures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserDashBoardActivity.this, SafetyMeasuresActivity.class));

            }
        });
        cdfaqs = (CardView) findViewById(R.id.cdfaqs);
        cdfaqs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserDashBoardActivity.this, FAQsActivity.class));

            }
        });
        cdTravellGuidence = (CardView) findViewById(R.id.cdTravellGuidence);
        cdTravellGuidence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserDashBoardActivity.this, TravelGuidenceActivity.class));

            }
        });
        cdQuarentineGuidelines = (CardView) findViewById(R.id.cdQuarentineGuidelines);
        cdQuarentineGuidelines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserDashBoardActivity.this, QuarantineGuideLinesActivity.class));

            }
        });

    }
}