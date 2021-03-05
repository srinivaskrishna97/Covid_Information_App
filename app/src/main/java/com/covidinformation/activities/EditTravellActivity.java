package com.covidinformation.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.covidinformation.R;
import com.covidinformation.api.ApiService;
import com.covidinformation.api.RetroClient;
import com.covidinformation.models.ResponseData;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditTravellActivity extends AppCompatActivity {
    Spinner spinSelectCountry,spinSelectProvince;
    EditText etDescription;
    Button btnAdd;
    ProgressDialog pd;
    String[] country,provience;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_travel);

        getSupportActionBar().setTitle("Edit Travel Guidence");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spinSelectCountry=(Spinner)findViewById(R.id.spinSelectCountry);
        spinSelectProvince=(Spinner)findViewById(R.id.spinSelectProvince);

        country=getResources().getStringArray(R.array.country);
        provience=getResources().getStringArray(R.array.province);


        int cnamebra = new ArrayList<String>(Arrays.asList(country)).indexOf(getIntent().getStringExtra("country"));
        spinSelectCountry.setSelection(cnamebra);

        int proviencee = new ArrayList<String>(Arrays.asList(country)).indexOf(getIntent().getStringExtra("provience"));
        spinSelectProvince.setSelection(proviencee);

        etDescription=(EditText)findViewById(R.id.etDescription);
        etDescription.setText(getIntent().getStringExtra("desc"));

        btnAdd=(Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateTravell();
            }
        });
    }
