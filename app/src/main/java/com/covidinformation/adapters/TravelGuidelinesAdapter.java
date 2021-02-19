package com.covidinformation.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.covidinformation.R;
import com.covidinformation.models.QGuideLinesPojo;

import java.util.List;

public class TravelGuidelinesAdapter extends BaseAdapter {
    List<QGuideLinesPojo> getQGPojos;
    Context context;
    String URL="http://covidinformation.live/covid/";

    public TravelGuidelinesAdapter(Context context, List<QGuideLinesPojo> getQGPojo) {
        this.context=context;
        this.getQGPojos=getQGPojo;
    }

    @Override
    public int getCount() {
        return getQGPojos.size();
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
        tvGuidence.setText(getQGPojos.get(position).getDescription());

        return obj2;
    }
}
