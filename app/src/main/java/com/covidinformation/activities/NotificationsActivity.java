package com.covidinformation.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.covidinformation.R;
import com.covidinformation.api.ApiService;
import com.covidinformation.api.RetroClient;
import com.covidinformation.models.ResponseData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationsActivity extends AppCompatActivity {
    EditText etName, etMessage;
    Spinner spinCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        getSupportActionBar().setTitle("Notifications");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etName = (EditText) findViewById(R.id.etName);
        etMessage = (EditText) findViewById(R.id.etMessage);
        spinCategory=(Spinner)findViewById(R.id.spinCategory);

        Button btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(spinCategory.getSelectedItem().toString().equals("Please Select Type")){
                    Toast.makeText(NotificationsActivity.this, "Please Select Type", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(etName.getText().toString().isEmpty()){
                    Toast.makeText(NotificationsActivity.this, "Please  write name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(etMessage.getText().toString().isEmpty()){
                    Toast.makeText(NotificationsActivity.this, "Please  write message", Toast.LENGTH_SHORT).show();
                    return;
                }
                addNotification();
            }
        });

    }

    ProgressDialog progressDialog;

    private void addNotification() {
        String name = etName.getText().toString();
        String messsage = etMessage.getText().toString();

        progressDialog = new ProgressDialog(NotificationsActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = service.addnotification(spinCategory.getSelectedItem().toString(),name,messsage);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                progressDialog.dismiss();
                if (response.body().status.equals("true")) {
                    Toast.makeText(NotificationsActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                    startActivity(new Intent(NotificationsActivity.this, AdminDashBoardActivity.class));
                    finish();


                } else {
                    Toast.makeText(NotificationsActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(NotificationsActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
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