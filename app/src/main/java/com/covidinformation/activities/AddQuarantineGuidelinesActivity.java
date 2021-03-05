package com.covidinformation.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.covidinformation.R;
import com.covidinformation.Utils;
import com.covidinformation.adapters.GetAllNewsAdapter;
import com.covidinformation.adapters.GetQuarantineGuidelinesAdapter;
import com.covidinformation.api.ApiService;
import com.covidinformation.api.RetroClient;
import com.covidinformation.models.GetAllNewsPojo;
import com.covidinformation.models.GetQGPojo;
import com.covidinformation.models.ResponseData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddQuarantineGuidelinesActivity extends AppCompatActivity {
    Spinner spinSelectCountry,spinSelectProvince;
    EditText etDescription;
    Button btnAdd;
    ProgressDialog pd;
    ListView list_view;
    List<GetQGPojo> getQGPojo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quarantine_guidelines);

        getSupportActionBar().setTitle("Quarantine Guidelines");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        list_view=(ListView)findViewById(R.id.list_view);
        getQGPojo=new ArrayList<>();
        getQuarantineGuidelines();

        spinSelectCountry=(Spinner)findViewById(R.id.spinSelectCountry);
        spinSelectProvince=(Spinner)findViewById(R.id.spinSelectProvince);

        etDescription=(EditText)findViewById(R.id.etDescription);
        btnAdd=(Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addQuarantine();
            }
        });
    }
    public  void addQuarantine() {
        pd= new ProgressDialog(AddQuarantineGuidelinesActivity.this);
        pd.setTitle("Please wait,Data is being submit...");
        pd.show();
        ApiService apiService = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = apiService.addquarantine(spinSelectProvince.getSelectedItem().toString(),spinSelectProvince.getSelectedItem().toString(),etDescription.getText().toString());

        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                pd.dismiss();
                if (response.body().status.equals("true")) {
                    startActivity(new Intent(AddQuarantineGuidelinesActivity.this, AddQuarantineGuidelinesActivity.class));
                    finish();
                } else {
                    Toast.makeText(AddQuarantineGuidelinesActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                pd.dismiss();
                Toast.makeText(AddQuarantineGuidelinesActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    public void getQuarantineGuidelines(){
        pd = new ProgressDialog(AddQuarantineGuidelinesActivity.this);
        pd.setMessage("Loading....");
        pd.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<List<GetQGPojo>> call = service.getquarantine();
        call.enqueue(new Callback<List<GetQGPojo>>() {
            @Override
            public void onResponse(Call<List<GetQGPojo>> call, Response<List<GetQGPojo>> response) {
                pd.dismiss();
                if(response.body()==null){
                    Toast.makeText(AddQuarantineGuidelinesActivity.this,"No data found",Toast.LENGTH_SHORT).show();
                }else {
                    getQGPojo = response.body();
                    list_view.setAdapter(new GetQuarantineGuidelinesAdapter(AddQuarantineGuidelinesActivity.this,getQGPojo));


                }
            }

            @Override
            public void onFailure(Call<List<GetQGPojo>> call, Throwable t) {
                pd.dismiss();
                Toast.makeText(AddQuarantineGuidelinesActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
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