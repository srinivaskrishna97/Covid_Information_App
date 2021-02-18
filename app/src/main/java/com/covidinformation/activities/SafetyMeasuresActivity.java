package com.covidinformation.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.covidinformation.R;
import com.covidinformation.adapters.SafetyMeasuresAdapter;
import com.covidinformation.models.SafetyGuidencePojo;

import java.util.ArrayList;
import java.util.List;

public class SafetyMeasuresActivity extends AppCompatActivity {
   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safety_measures);

        getSupportActionBar().setTitle("Safety Measures");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //now we can create two types of slider first using viewpager
        //and another using third party library which is easy to use let's get started
        ImageSlider imageSlider=findViewById(R.id.slider);

        List<SlideModel> slideModels=new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.safety_1));
        slideModels.add(new SlideModel(R.drawable.safety_2));
        slideModels.add(new SlideModel(R.drawable.safety_3));
        slideModels.add(new SlideModel(R.drawable.safety_4));
        slideModels.add(new SlideModel(R.drawable.safety_5));
        slideModels.add(new SlideModel(R.drawable.safety_6));
        slideModels.add(new SlideModel(R.drawable.safety_7));
        slideModels.add(new SlideModel(R.drawable.safety_8));

        imageSlider.setImageList(slideModels,false);






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