package com.covidinformation.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.covidinformation.R;
import com.covidinformation.adapters.GetCovidCentersAdapter;
import com.covidinformation.api.ApiService;
import com.covidinformation.api.RetroClient;
import com.covidinformation.models.GetCovidCentersPojo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CovidCentersInfoActivity extends AppCompatActivity {
    ProgressDialog pd;
    ListView list_view;
    List<GetCovidCentersPojo> getCovidCentersPojo;
    Button btnAddCoviecenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_centers_info);


        getSupportActionBar().setTitle("COVID CENTRES");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnAddCoviecenter=(Button)findViewById(R.id.btnAddCoviecenter);
        btnAddCoviecenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CovidCentersInfoActivity.this,AddCovidInfoActivity.class));
                finish();
            }
        });
        list_view=(ListView)findViewById(R.id.list_view);
        getCovidCentersPojo=new ArrayList<>();
        getCovidCenters();
    }
    public void getCovidCenters(){
        pd = new ProgressDialog(CovidCentersInfoActivity.this);
        pd.setMessage("Loading.....");
        pd.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<List<GetCovidCentersPojo>> call = service.getcovidcenters();
        call.enqueue(new Callback<List<GetCovidCentersPojo>>() {
            @Override
            public void onResponse(Call<List<GetCovidCentersPojo>> call, Response<List<GetCovidCentersPojo>> response) {
                pd.dismiss();
                if(response.body()==null){
                    Toast.makeText(CovidCentersInfoActivity.this,"No data found",Toast.LENGTH_SHORT).show();
                }else {
                    getCovidCentersPojo = response.body();
                    list_view.setAdapter(new GetCovidCentersAdapter(CovidCentersInfoActivity.this,getCovidCentersPojo));


                }
            }

            @Override
            public void onFailure(Call<List<GetCovidCentersPojo>> call, Throwable t) {
                pd.dismiss();
                Toast.makeText(CovidCentersInfoActivity.this, "Something went wrong....Please try later!", Toast.LENGTH_SHORT).show();
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