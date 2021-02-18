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

public class QuarantineGuideLinesActivity extends AppCompatActivity {
    ListView list_view;
    List<SafetyGuidencePojo> guidencePojoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quarantine_guide_lines);


       
    }
}