package com.covidinformation.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.covidinformation.R;
import com.covidinformation.adapters.GetFaqsAdapter;
import com.covidinformation.adapters.GetVaccineInfoAdapter;
import com.covidinformation.api.ApiService;
import com.covidinformation.api.RetroClient;
import com.covidinformation.models.Faqs;
import com.covidinformation.models.VaccinePojo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FAQsActivity extends AppCompatActivity {


    Button btnAddVaccine;
    List<Faqs> faqsPojos;
    ListView list_view;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f_a_qs);

        getSupportActionBar().setTitle(" ");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        list_view = (ListView) findViewById(R.id.list_view);
        faqsPojos = new ArrayList<>();
        GetFaqs();

    }

    public void GetFaqs() {
        progressDialog = new ProgressDialog(FAQsActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<List<Faqs>> call = service.getfaqs();
        call.enqueue(new Callback<List<Faqs>>() {
            @Override
            public void onResponse(Call<List<Faqs>> call, Response<List<Faqs>> response) {
                progressDialog.dismiss();
                if (response.body() == null) {
                    Toast.makeText(FAQsActivity.this, "No data found", Toast.LENGTH_SHORT).show();
                } else {
                    faqsPojos = response.body();
                    list_view.setAdapter(new GetFaqsAdapter(FAQsActivity.this, faqsPojos));
                }
            }

            @Override
            public void onFailure(Call<List<Faqs>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(FAQsActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
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