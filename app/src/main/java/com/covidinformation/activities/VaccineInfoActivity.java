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

        btnAddVaccine = (Button) findViewById(R.id.btnAddVaccine);
        btnAddVaccine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VaccineInfoActivity.this, AddVaccineDetailsActivity.class));
                finish();

            }
        });
        list_view = (ListView) findViewById(R.id.list_view);
        vaccinePojos = new ArrayList<>();
        GetAllNews();

    }

    public void GetAllNews() {
        progressDialog = new ProgressDialog(VaccineInfoActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<List<VaccinePojo>> call = service.getvaccine();
        call.enqueue(new Callback<List<VaccinePojo>>() {
            @Override
            public void onResponse(Call<List<VaccinePojo>> call, Response<List<VaccinePojo>> response) {
                progressDialog.dismiss();
                if (response.body() == null) {
                    Toast.makeText(VaccineInfoActivity.this, "No data found", Toast.LENGTH_SHORT).show();
                } else {
                    vaccinePojos = response.body();
                    list_view.setAdapter(new VaccineInfoAdapter(VaccineInfoActivity.this, vaccinePojos));
                }
            }

            @Override
            public void onFailure(Call<List<VaccinePojo>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(VaccineInfoActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}


