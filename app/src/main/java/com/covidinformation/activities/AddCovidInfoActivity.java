package com.covidinformation.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.covidinformation.R;
import com.covidinformation.adapters.GetCovidCentersAdapter;
import com.covidinformation.adapters.GetTravelGuidelinesAdapter;
import com.covidinformation.api.ApiService;
import com.covidinformation.api.RetroClient;
import com.covidinformation.models.GetCovidCentersPojo;
import com.covidinformation.models.GetQGPojo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCovidInfoActivity extends AppCompatActivity {
    ProgressDialog pd;
    ListView list_view;
    List<GetCovidCentersPojo> getCovidCentersPojo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_covid_info);


        getSupportActionBar().setTitle("Covid centers");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getCovidCenters();

    }

    public void getCovidCenters(){
        pd = new ProgressDialog(AddCovidInfoActivity.this);
        pd.setMessage("Loading....");
        pd.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<List<GetCovidCentersPojo>> call = service.getcovidcenters();
        call.enqueue(new Callback<List<GetCovidCentersPojo>>() {
            @Override
            public void onResponse(Call<List<GetCovidCentersPojo>> call, Response<List<GetCovidCentersPojo>> response) {
                pd.dismiss();
                if(response.body()==null){
                    Toast.makeText(AddCovidInfoActivity.this,"No data found",Toast.LENGTH_SHORT).show();
                }else {
                    getCovidCentersPojo = response.body();
                    list_view.setAdapter(new GetCovidCentersAdapter(AddCovidInfoActivity.this,getCovidCentersPojo));


                }
            }

            @Override
            public void onFailure(Call<List<GetCovidCentersPojo>> call, Throwable t) {
                pd.dismiss();
                Toast.makeText(AddCovidInfoActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
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