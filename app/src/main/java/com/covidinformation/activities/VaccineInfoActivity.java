package com.covidinformation.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.covidinformation.R;
import com.covidinformation.adapters.NewsInfoAdapter;
import com.covidinformation.adapters.VaccineInfoAdapter;
import com.covidinformation.api.ApiService;
import com.covidinformation.api.RetroClient;
import com.covidinformation.models.GetAllNewsPojo;
import com.covidinformation.models.VaccinePojo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VaccineInfoActivity extends AppCompatActivity {
    Button btnAddVaccine;
    List<VaccinePojo> vaccinePojos;
    ListView list_view;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vaccine_details);

        getSupportActionBar().setTitle("Vaccine Details");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

}

