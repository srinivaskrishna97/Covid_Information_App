package com.covidinformation.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.covidinformation.R;
import com.covidinformation.adapters.NotificationsAdapter;
import com.covidinformation.adapters.TravelGuidelinesAdapter;
import com.covidinformation.api.ApiService;
import com.covidinformation.api.RetroClient;
import com.covidinformation.models.NotificationPojo;
import com.covidinformation.models.QGuideLinesPojo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetNotificationActivity extends AppCompatActivity {
    ListView list_view;
    List<NotificationPojo> notificationPojo;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_notification);

        getSupportActionBar().setTitle(" ");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        list_view = (ListView) findViewById(R.id.list_view);
        notificationPojo = new ArrayList<>();
        AllGetTravelGuidence();
    }

    public void AllGetTravelGuidence() {
        progressDialog = new ProgressDialog(GetNotificationActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();
        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<List<NotificationPojo>> call = service.getnotification();
        call.enqueue(new Callback<List<NotificationPojo>>() {
            @Override
            public void onResponse(Call<List<NotificationPojo>> call, Response<List<NotificationPojo>> response) {
                progressDialog.dismiss();
                if (response.body() == null) {
                    Toast.makeText(GetNotificationActivity.this, "No data found", Toast.LENGTH_SHORT).show();
                }
                if (response.body().size() == 0) {
                    Toast.makeText(GetNotificationActivity.this, "No data found", Toast.LENGTH_SHORT).show();
                } else {
                    notificationPojo = response.body();
                    list_view.setAdapter(new NotificationsAdapter(GetNotificationActivity.this, notificationPojo));
                }
            }

            @Override
            public void onFailure(Call<List<NotificationPojo>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(GetNotificationActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
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