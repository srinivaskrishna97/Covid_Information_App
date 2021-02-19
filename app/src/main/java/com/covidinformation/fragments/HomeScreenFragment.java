package com.covidinformation.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.covidinformation.R;
import com.covidinformation.activities.FAQsActivity;
import com.covidinformation.activities.LoginActivity;
import com.covidinformation.activities.MainActivity;
import com.covidinformation.activities.QuarantineGuideLinesActivity;
import com.covidinformation.activities.SafetyMeasuresActivity;
import com.covidinformation.activities.TravelGuidenceActivity;
import com.covidinformation.activities.UserDashBoardActivity;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;


public class HomeScreenFragment extends Fragment {
    TextView tvTotalCases, tvRecovered, tvDeaths, tvActive;
    PieChart pieChart;
    CardView cdSafetyMeasures, cdFAQs, cdTravellGuidence, cdQuarentineGuidelines;

    View view;

    public static HomeScreenFragment homeScreenFragment() {
        HomeScreenFragment fragment = new HomeScreenFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setHasOptionsMenu(true);
        view = inflater.inflate(R.layout.fragment_home_screen, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Home");

        tvTotalCases = (TextView) view.findViewById(R.id.tvTotalCases);
        tvRecovered = view.findViewById(R.id.tvRecovered);
        tvDeaths = view.findViewById(R.id.tvDeaths);
        tvActive = view.findViewById(R.id.tvActive);
        pieChart = view.findViewById(R.id.piechart);


        setData();
        userSafetyGuidence();
        return view;
    }

    private void setData() {
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

    public void userSafetyGuidence() {

        cdSafetyMeasures = (CardView) view.findViewById(R.id.cdSafetyMeasures);
        cdSafetyMeasures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), SafetyMeasuresActivity.class));

            }
        });
        cdFAQs = (CardView) view.findViewById(R.id.cdFAQs);
        cdFAQs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), FAQsActivity.class));

            }
        });
        cdTravellGuidence = (CardView) view.findViewById(R.id.cdTravellGuidence);
        cdTravellGuidence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), TravelGuidenceActivity.class));

            }
        });
        cdQuarentineGuidelines = (CardView) view.findViewById(R.id.cdQuarentineGuidelines);
        cdQuarentineGuidelines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), QuarantineGuideLinesActivity.class));

            }
        });

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.admin_navigation, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_logout:
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();

                return true;
        }

        return false;

    }
}
