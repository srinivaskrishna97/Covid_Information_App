package com.covidinformation.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.covidinformation.R;
import com.covidinformation.api.ApiService;
import com.covidinformation.api.RetroClient;
import com.covidinformation.models.GetCovidCentersPojo;
import com.covidinformation.models.ResponseData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditCovidInfoActivity extends AppCompatActivity {
    ProgressDialog pd;
    ListView list_view;
    Button btnAddCenter;
    List<GetCovidCentersPojo> getCovidCentersPojo;
    EditText etCenterName,etLocation,etAddressOne,etAddressTwo,etPhone,etLatitude,etLang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_covid_centers);


        getSupportActionBar().setTitle("Edit Covid Centers");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etCenterName=(EditText)findViewById(R.id.etCenterName);
        etCenterName.setText(getIntent().getStringExtra("centername"));

        etLocation=(EditText)findViewById(R.id.etLocation);
        etLocation.setText(getIntent().getStringExtra("location"));

        etAddressOne=(EditText)findViewById(R.id.etAddressOne);
        etAddressOne.setText(getIntent().getStringExtra("address1"));

        etAddressTwo=(EditText)findViewById(R.id.etAddressTwo);
        etAddressTwo.setText(getIntent().getStringExtra("address2"));

        etPhone=(EditText)findViewById(R.id.etPhone);
        etPhone.setText(getIntent().getStringExtra("phone"));


        btnAddCenter=(Button)findViewById(R.id.btnAddCenter);
        btnAddCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCovidCenters();
            }
        });
    }
    public  void addCovidCenters() {
        pd = new ProgressDialog(EditCovidInfoActivity.this);
        pd.setTitle("Please wait,Data is being submit...");
        pd.show();
        ApiService apiService = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = apiService.editcerters(etCenterName.getText().toString(), etLocation.getText().toString(),
                etAddressOne.getText().toString(), etAddressTwo.getText().toString(), etPhone.getText().toString(),getIntent().getStringExtra("cid"));

        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                pd.dismiss();
                if (response.body().status.equals("true")) {
                    startActivity(new Intent(EditCovidInfoActivity.this, CovidCentersInfoActivity.class));
                    finish();
                } else {
                    Toast.makeText(EditCovidInfoActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                pd.dismiss();
                Toast.makeText(EditCovidInfoActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
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