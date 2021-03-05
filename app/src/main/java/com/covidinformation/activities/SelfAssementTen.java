package com.covidinformation.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.covidinformation.R;

public class SelfAssementTen extends AppCompatActivity {
    Button btnYes, btnNo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_assement_ten);

        getSupportActionBar().setTitle("Self Assessment");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnYes = (Button) findViewById(R.id.btnYes);
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelfAssementTen.this, UserDashBoardActivity.class));
                Toast.makeText(SelfAssementTen.this, "Details Succussfully Recorded", Toast.LENGTH_SHORT).show();
                finish();


            }
        });
        btnNo = (Button) findViewById(R.id.btnNo);
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelfAssementTen.this, UserDashBoardActivity.class));
                Toast.makeText(SelfAssementTen.this, "Details Succussfully Recorded", Toast.LENGTH_SHORT).show();
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