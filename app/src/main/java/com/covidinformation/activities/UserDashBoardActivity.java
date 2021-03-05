package com.covidinformation.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.covidinformation.R;
import com.covidinformation.fragments.CovidCentersFragmnet;
import com.covidinformation.fragments.GetAllNewsFragment;
import com.covidinformation.fragments.HomeScreenFragment;
import com.covidinformation.fragments.MapFragment;
import com.covidinformation.fragments.MyProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class UserDashBoardActivity extends AppCompatActivity {
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);
        builder = new AlertDialog.Builder(this);
        getSupportActionBar().setTitle("Dashboard");
        bottomNavigation();
    }



    BottomNavigationView bottomNavigationView;
    private void bottomNavigation() {
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);

        bottomNavigationView.setSelectedItemId(R.id.item1);
        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.item1:
                                selectedFragment = GetAllNewsFragment.getAllNewsFragment();
                                break;
                            case R.id.item2:
                                selectedFragment = CovidCentersFragmnet.covidCentersFragmnet();
                                break;
                            case R.id.home:
                                selectedFragment = HomeScreenFragment.homeScreenFragment();
                                break;
                            case R.id.map:
                                selectedFragment = MapFragment.mapFragment();
                                break;
                            case R.id.myprofile:
                                selectedFragment = MyProfileFragment.myProfileFragment();
                                break;
                        }
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, HomeScreenFragment.homeScreenFragment());
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        //Uncomment the below code to Set the message and title from the strings.xml file


        //Setting message manually and performing action on button click
        builder.setMessage("Do you want to close this application ?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //  Action for 'NO' Button
                        dialog.cancel();

                    }
                });
        //Creating dialog box
        AlertDialog alert = builder.create();
        //Setting the title manually
        alert.setTitle("CovidInfo");
        alert.show();
    }
}