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
import com.covidinformation.adapters.GetAllNewsAdapter;
import com.covidinformation.adapters.NewsInfoAdapter;
import com.covidinformation.api.ApiService;
import com.covidinformation.api.RetroClient;
import com.covidinformation.models.GetAllNewsPojo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsActivity extends AppCompatActivity {
    Button btnAddNews;
    List<GetAllNewsPojo> getAllNewsPojo;
    ListView list_view;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        getSupportActionBar().setTitle(" ");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        list_view = (ListView) findViewById(R.id.list_view);
        getAllNewsPojo = new ArrayList<>();
        GetAllNews();

    }

    public void GetAllNews() {
        progressDialog = new ProgressDialog(NewsActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<List<GetAllNewsPojo>> call = service.getAllNews();
        call.enqueue(new Callback<List<GetAllNewsPojo>>() {
            @Override
            public void onResponse(Call<List<GetAllNewsPojo>> call, Response<List<GetAllNewsPojo>> response) {
                progressDialog.dismiss();
                if (response.body() == null) {
                    Toast.makeText(NewsActivity.this, "No data found", Toast.LENGTH_SHORT).show();
                } else {
                    getAllNewsPojo = response.body();
                    list_view.setAdapter(new GetAllNewsAdapter(NewsActivity.this, getAllNewsPojo));
                }
            }

            @Override
            public void onFailure(Call<List<GetAllNewsPojo>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(NewsActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
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

