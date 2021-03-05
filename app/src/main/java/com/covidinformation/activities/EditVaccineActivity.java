package com.covidinformation.activities;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.covidinformation.R;
import com.covidinformation.api.ApiService;
import com.covidinformation.api.RetroClient;
import com.covidinformation.models.ResponseData;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditVaccineActivity extends AppCompatActivity {
    Button btnAdd;
    EditText etTitle,etDescription,etDate;
    ProgressDialog pd;
    int mYear, mMonth, mDay;
    String DAY, MONTH, YEAR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_vaccine);

        getSupportActionBar().setTitle("Edit Vaccine");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etDescription=(EditText)findViewById(R.id.etDescription);
        etDescription.setText(getIntent().getStringExtra("desc"));

        etTitle=(EditText)findViewById(R.id.etTitle);
        etTitle.setText(getIntent().getStringExtra("title"));

        btnAdd=(Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateVaccine();

            }
        });
    }
    public  void updateVaccine() {
        pd= new ProgressDialog(EditVaccineActivity.this);
        pd.setTitle("Please wait,Data is being submit...");
        pd.show();
        ApiService apiService = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = apiService.editvaccine(etTitle.getText().toString(),etDescription.getText().toString(),getIntent().getStringExtra("vid"));

        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                pd.dismiss();
                if (response.body().status.equals("true")) {
                    startActivity(new Intent(EditVaccineActivity.this, NewsInfoActivity.class));
                    finish();
                } else {
                    Toast.makeText(EditVaccineActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                pd.dismiss();
                Toast.makeText(EditVaccineActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
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