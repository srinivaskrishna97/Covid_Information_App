package com.covidinformation.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.covidinformation.R;
import com.covidinformation.adapters.GetCovidCentersAdapter;
import com.covidinformation.adapters.GetTravelGuidelinesAdapter;
import com.covidinformation.api.ApiService;
import com.covidinformation.api.RetroClient;
import com.covidinformation.models.GetCovidCentersPojo;
import com.covidinformation.models.GetQGPojo;
import com.covidinformation.models.ResponseData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCovidInfoActivity extends AppCompatActivity {
    ProgressDialog pd;
    ListView list_view;
    Button btnAddCenter;
    List<GetCovidCentersPojo> getCovidCentersPojo;
    EditText etCenterName,etLocation,etAddressOne,etAddressTwo,etPhone,etLatitude,etLang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_covid_info);


        getSupportActionBar().setTitle("Add Covid Centers");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etCenterName=(EditText)findViewById(R.id.etCenterName);
        etLocation=(EditText)findViewById(R.id.etLocation);
        etAddressOne=(EditText)findViewById(R.id.etAddressOne);
        etAddressTwo=(EditText)findViewById(R.id.etAddressTwo);
        etPhone=(EditText)findViewById(R.id.etPhone);
        etLatitude=(EditText)findViewById(R.id.etLatitude);
        etLang=(EditText)findViewById(R.id.etLang);

        btnAddCenter=(Button)findViewById(R.id.btnAddCenter);
        btnAddCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCovidCenters();
            }
        });
    }
    public  void addCovidCenters() {
        pd = new ProgressDialog(AddCovidInfoActivity.this);
        pd.setTitle("Please wait,Data is being submit...");
        pd.show();
        ApiService apiService = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = apiService.addcenters(etCenterName.getText().toString(), etLocation.getText().toString(),
                etAddressOne.getText().toString(), etAddressTwo.getText().toString(), etPhone.getText().toString(), etLang.getText().toString(),
                etLang.getText().toString());

        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                pd.dismiss();
                if (response.body().status.equals("true")) {
                    startActivity(new Intent(AddCovidInfoActivity.this, CovidCentersInfoActivity.class));
                    finish();
                } else {
                    Toast.makeText(AddCovidInfoActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                pd.dismiss();
                Toast.makeText(AddCovidInfoActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
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