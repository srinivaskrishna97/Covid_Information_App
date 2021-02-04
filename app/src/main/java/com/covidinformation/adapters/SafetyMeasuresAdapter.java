package com.covidinformation.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.covidinformation.R;
import com.covidinformation.activities.DetailedNewsActivity;
import com.covidinformation.activities.VaccineDetailsPojo;
import com.covidinformation.models.SafetyGuidencePojo;

import java.util.List;

public class SafetyMeasuresAdapter extends BaseAdapter {
    List<SafetyGuidencePojo> safetyGuidencePojo;
    Context context;

    public SafetyMeasuresAdapter(Context context, List<SafetyGuidencePojo> safetypojo) {
        this.context=context;
        this.safetyGuidencePojo=safetypojo;
    }

    @Override
    public int getCount() {
        return safetyGuidencePojo.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater obj1 = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View obj2 = obj1.inflate(R.layout.adapter_safety_measures, null);

        TextView tvSafetyMeasures=(TextView)obj2.findViewById(R.id.tvSafetyMeasures);
        tvSafetyMeasures.setText(safetyGuidencePojo.get(position).getMessage());

        return obj2;
    }
}
