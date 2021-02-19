package com.covidinformation.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.covidinformation.R;
import com.covidinformation.adapters.GetUserQuarantineGuidelinesAdapter;
import com.covidinformation.adapters.NewsInfoAdapter;
import com.covidinformation.adapters.SafetyMeasuresAdapter;
import com.covidinformation.api.ApiService;
import com.covidinformation.api.RetroClient;
import com.covidinformation.models.GetAllNewsPojo;
import com.covidinformation.models.QGuideLinesPojo;
import com.covidinformation.models.SafetyGuidencePojo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class QuarantineGuideLinesActivity extends AppCompatActivity {
    ListView list_view;
    List<QGuideLinesPojo> guidencePojoList;
    Spinner spinProveience;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quarantine_guide_lines);

        getSupportActionBar().setTitle("Quarantine GuideLines");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spinProveience=(Spinner)findViewById(R.id.spinProveience);
        btnSubmit=(Button)findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetQuerentineGuidence(spinProveience.getSelectedItem().toString());
            }
        });
        list_view=(ListView)findViewById(R.id.list_view);
        guidencePojoList=new ArrayList<>();


    }
    ProgressDialog progressDialog;
    public void GetQuerentineGuidence(String province) {
        progressDialog = new ProgressDialog(QuarantineGuideLinesActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();
        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<List<QGuideLinesPojo>> call = service.searchquarantine(province);
        call.enqueue(new Callback<List<QGuideLinesPojo>>() {
            @Override
            public void onResponse(Call<List<QGuideLinesPojo>> call, Response<List<QGuideLinesPojo>> response) {
                progressDialog.dismiss();
                if (response.body() == null) {
                    Toast.makeText(QuarantineGuideLinesActivity.this, "No data found", Toast.LENGTH_SHORT).show();
                }
                if (response.body().size() == 0) {
                    Toast.makeText(QuarantineGuideLinesActivity.this, "No data found", Toast.LENGTH_SHORT).show();
                }else {
                    guidencePojoList = response.body();
                    list_view.setAdapter(new GetUserQuarantineGuidelinesAdapter(QuarantineGuideLinesActivity.this, guidencePojoList));
                }
            }

            @Override
            public void onFailure(Call<List<QGuideLinesPojo>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(QuarantineGuideLinesActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
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