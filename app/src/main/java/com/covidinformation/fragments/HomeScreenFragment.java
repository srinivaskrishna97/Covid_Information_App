package com.covidinformation.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.covidinformation.R;
import com.covidinformation.activities.FAQsActivity;
import com.covidinformation.activities.GetNotificationActivity;
import com.covidinformation.activities.GetVaccineDetailsActivity;
import com.covidinformation.activities.MainActivity;
import com.covidinformation.activities.NewsActivity;
import com.covidinformation.activities.NewsInfoActivity;
import com.covidinformation.activities.QuarantineGuideLinesActivity;
import com.covidinformation.activities.SafetyMeasuresActivity;
import com.covidinformation.activities.SelfAssementOneActivity;
import com.covidinformation.activities.TravelGuidenceActivity;
import com.covidinformation.activities.TravelGuidenceActivity1;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;


public class HomeScreenFragment extends Fragment {
    TextView tvTotalCases, tvDeaths,tv1,tv2,tv3,tvtr;
    PieChart pieChart;
    CardView cdSafetyMeasures, cdFAQs, cdTravellGuidence, cdQuarentineGuidelines,cdSelfAssement,cdNewsFeed,cdVaccineDetails,cdNotification;

    View view;

    TextView tvCases,tvRecovered,tvCritical,tvActive,tvTodayCases,tvTotalDeaths,tvTodayDeaths,tvAffectedCountries;
    SimpleArcLoader simpleArcLoader;
    ScrollView scrollView;


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


        tvCases = view.findViewById(R.id.tvCases);
        tvRecovered = view.findViewById(R.id.tvRecovered);
        tvCritical = view.findViewById(R.id.tvCritical);
        tvActive = view.findViewById(R.id.tvActive);
        tvTodayCases = view.findViewById(R.id.tvTodayCases);
        tvTotalDeaths = view.findViewById(R.id.tvTotalDeaths);
        tvTodayDeaths = view.findViewById(R.id.tvTodayDeaths);
        tvAffectedCountries = view.findViewById(R.id.tvAffectedCountries);

        simpleArcLoader = view.findViewById(R.id.loader);
        scrollView = view.findViewById(R.id.scrollStats);
        pieChart = view.findViewById(R.id.piechart);


        tv1 = view.findViewById(R.id.tv1);
        tv2 = view.findViewById(R.id.tv2);
        tv3 = view.findViewById(R.id.tv3);
      //  tvtr= view.findViewById(R.id.tvtr);
        pieChart = view.findViewById(R.id.piechart);


        simpleArcLoader = view.findViewById(R.id.loader);
        scrollView = view.findViewById(R.id.scrollStats);
      //  setData();
        userSafetyGuidence();
        fetchData();
        return view;
    }

    private void fetchData() {

        String url  = "https://disease.sh/v3/covid-19/all";

//        String url  = "https://corona.lmao.ninja/v2/all/";

        simpleArcLoader.start();

        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response.toString());

                            tv1.setText(jsonObject.getString("deaths"));
                            tv2.setText(jsonObject.getString("recovered"));
                            tv3.setText(jsonObject.getString("active"));

                            tvCases.setText(jsonObject.getString("cases"));
                            tvRecovered.setText(jsonObject.getString("recovered"));
                            tvCritical.setText(jsonObject.getString("critical"));
                            tvActive.setText(jsonObject.getString("active"));
                            tvTodayCases.setText(jsonObject.getString("todayCases"));
                            tvTotalDeaths.setText(jsonObject.getString("deaths"));
                            tvTodayDeaths.setText(jsonObject.getString("todayDeaths"));
                            tvAffectedCountries.setText(jsonObject.getString("affectedCountries"));

//
//                            pieChart.addPieSlice(new PieModel("Cases",Integer.parseInt(tvCases.getText().toString()), Color.parseColor("#FFA726")));
//                            pieChart.addPieSlice(new PieModel("Recoverd",Integer.parseInt(tvRecovered.getText().toString()), Color.parseColor("#66BB6A")));
//                            pieChart.addPieSlice(new PieModel("Deaths",Integer.parseInt(tvTotalDeaths.getText().toString()), Color.parseColor("#EF5350")));
//                            pieChart.addPieSlice(new PieModel("Active",Integer.parseInt(tvActive.getText().toString()), Color.parseColor("#29B6F6")));

                            pieChart.addPieSlice(new PieModel("Cases",Integer.parseInt(tvCases.getText().toString()), Color.parseColor("#8A56AC")));
                            pieChart.addPieSlice(new PieModel("Recoverd",Integer.parseInt(tvRecovered.getText().toString()), Color.parseColor("#FFCDD2")));
                            pieChart.addPieSlice(new PieModel("Deaths",Integer.parseInt(tvTotalDeaths.getText().toString()), Color.parseColor("#B2DFDB")));
                            pieChart.addPieSlice(new PieModel("Active",Integer.parseInt(tvActive.getText().toString()), Color.parseColor("#D47FA6")));
                            pieChart.startAnimation();

                            simpleArcLoader.stop();
                            simpleArcLoader.setVisibility(View.GONE);
                        //    scrollView.setVisibility(View.VISIBLE);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            simpleArcLoader.stop();
                            simpleArcLoader.setVisibility(View.GONE);
                            scrollView.setVisibility(View.VISIBLE);
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                simpleArcLoader.stop();
                simpleArcLoader.setVisibility(View.GONE);
                scrollView.setVisibility(View.VISIBLE);
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);


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

        cdSelfAssement = (CardView) view.findViewById(R.id.cdSelfAssement);
        cdSelfAssement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), SelfAssementOneActivity.class));

            }
        });


        cdVaccineDetails = (CardView) view.findViewById(R.id.cdVaccineDetails);
        cdVaccineDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), GetVaccineDetailsActivity.class));

            }
        });

        cdNewsFeed = (CardView) view.findViewById(R.id.cdNewsFeed);
        cdNewsFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), NewsActivity.class));

            }
        });

        cdNotification = (CardView) view.findViewById(R.id.cdNotification);
        cdNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), GetNotificationActivity.class));

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


                case R.id.menu_vaccine:
                Intent vaccine = new Intent(getContext(), GetVaccineDetailsActivity.class);
                startActivity(vaccine);
                return true;
        }

        return false;

    }


}
