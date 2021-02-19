package com.covidinformation.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.covidinformation.R;
import com.covidinformation.models.QGuideLinesPojo;
import com.covidinformation.models.SafetyGuidencePojo;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TravelGuidenceAdapter extends BaseAdapter {
    List<QGuideLinesPojo> safetyGuidencePojo,searchride;
    Context context;

    public TravelGuidenceAdapter(Context context, List<QGuideLinesPojo> safetypojo) {
        this.searchride=safetypojo;
        this.context = context;
        this.safetyGuidencePojo = new ArrayList<QGuideLinesPojo>();
        this.safetyGuidencePojo.addAll(safetyGuidencePojo);
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
        View obj2 = obj1.inflate(R.layout.adapter_travel_guidence, null);

        TextView tvGuidence=(TextView)obj2.findViewById(R.id.tvGuidence);
        tvGuidence.setText(safetyGuidencePojo.get(position).getDescription());

        return obj2;
    }
    public void guidenceFilter(String charText)
    {
        charText = charText.toLowerCase(Locale.getDefault());
        safetyGuidencePojo.clear();
        if (charText.length() == 0) {
            safetyGuidencePojo.addAll(searchride);
        } else {
            for (QGuideLinesPojo wp : searchride) {
                if (wp.getProvince().toLowerCase(Locale.getDefault()).contains(charText)) {
                    safetyGuidencePojo.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
}
