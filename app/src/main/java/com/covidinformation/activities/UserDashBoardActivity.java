package com.covidinformation.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

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
                                selectedFragment = HomeScreenFragment.homeScreenFragment();
                                break;
                            case R.id.home:
                                selectedFragment = HomeScreenFragment.homeScreenFragment();
                                break;
                            case R.id.map:
                                selectedFragment = HomeScreenFragment.homeScreenFragment();
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


}