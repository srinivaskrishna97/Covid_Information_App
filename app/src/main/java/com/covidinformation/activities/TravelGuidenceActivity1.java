package com.covidinformation.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.covidinformation.R;
import com.covidinformation.adapters.TravelGuidenceAdapter;
import com.covidinformation.api.ApiService;
import com.covidinformation.api.RetroClient;
import com.covidinformation.models.QGuideLinesPojo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TravelGuidenceActivity1 extends AppCompatActivity {
    ListView list_view;
    List<QGuideLinesPojo> guidencePojoList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_guidence1);

        getSupportActionBar().setTitle("Travel Guidencs");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        list_view = (ListView) findViewById(R.id.list_view);
        AllGetTravelGuidence();
    }
    ProgressDialog progressDialog;
    public void AllGetTravelGuidence() {
        progressDialog = new ProgressDialog(TravelGuidenceActivity1.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();
        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<List<QGuideLinesPojo>> call = service.allsearchtravel();
        call.enqueue(new Callback<List<QGuideLinesPojo>>() {
            @Override
            public void onResponse(Call<List<QGuideLinesPojo>> call, Response<List<QGuideLinesPojo>> response) {
                progressDialog.dismiss();
                if (response.body() == null) {
                    Toast.makeText(TravelGuidenceActivity1.this, "No data found", Toast.LENGTH_SHORT).show();
                }
                if (response.body().size() == 0) {
                    Toast.makeText(TravelGuidenceActivity1.this, "No data found", Toast.LENGTH_SHORT).show();
                } else {

                    guidencePojoList = response.body();
                    //Toast.makeText(getApplicationContext(),""+guidencePojoList.size(),Toast.LENGTH_SHORT).show();
                    //travelGuidenceAdapter=new TravelGuidenceAdapter(TravelGuidenceActivity1.this, guidencePojoList);
                    list_view.setAdapter(new TravelGuidenceAdapter(TravelGuidenceActivity1.this,  response.body()));
                }
            }

            @Override
            public void onFailure(Call<List<QGuideLinesPojo>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(TravelGuidenceActivity1.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
