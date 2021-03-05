package com.covidinformation.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.covidinformation.R;

public class DetailsVaccineActivity extends AppCompatActivity {

    TextView tvCovidLatestUpdate,tvCovidDesc;
    ImageView imageView;
    Button btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_vaccine);
        getSupportActionBar().setTitle("Vaccine Details");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvCovidLatestUpdate=(TextView)findViewById(R.id.tvCovidLatestUpdate);
        tvCovidDesc=(TextView)findViewById(R.id.tvCovidDesc);
        imageView=(ImageView)findViewById(R.id.imageView);

        tvCovidLatestUpdate.setText(getIntent().getStringExtra("title"));
        tvCovidDesc.setText(getIntent().getStringExtra("desc"));
        // Glide.with(DetailsVaccineActivity.this).load(getIntent().getStringExtra("image")).into(imageView);

        btn_back=(Button)findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(DetailedNewsActivity.this, GetAllNewsFragment.class));
                finish();

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