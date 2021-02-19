package com.covidinformation.adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.covidinformation.R;
import com.covidinformation.activities.CovidCentersInfoActivity;
import com.covidinformation.api.ApiService;
import com.covidinformation.api.RetroClient;
import com.covidinformation.models.GetCovidCentersPojo;
import com.covidinformation.models.ResponseData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserCovidCentersAdapter extends BaseAdapter {
    List<GetCovidCentersPojo> getCovidCenters;
    Context context;
    String URL="http://covidinformation.live/covid/";

    public UserCovidCentersAdapter(Context context, List<GetCovidCentersPojo> getCovidCenters) {
        this.context=context;
        this.getCovidCenters=getCovidCenters;
    }

    @Override
    public int getCount() {
        return getCovidCenters.size();
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
        View obj2 = obj1.inflate(R.layout.adapter_user_covid_centers, null);

        TextView tvName=(TextView)obj2.findViewById(R.id.tvName);
        tvName.setText("Center Name: "+getCovidCenters.get(position).getName());

        TextView tvLocation=(TextView)obj2.findViewById(R.id.tvLocation);
        tvLocation.setText("Location: "+getCovidCenters.get(position).getLocation());

        TextView tvAdddressone=(TextView)obj2.findViewById(R.id.tvAdddressone);
        tvAdddressone.setText("Address_1: "+getCovidCenters.get(position).getAddress1());


        TextView tvAdddresstwo=(TextView)obj2.findViewById(R.id.tvAdddresstwo);
        tvAdddresstwo.setText("Address_2: "+getCovidCenters.get(position).getAddress2());

        TextView tvPhone=(TextView)obj2.findViewById(R.id.tvPhone);
        tvPhone.setText("Phone No: "+getCovidCenters.get(position).getPhone());

        TextView tvLat=(TextView)obj2.findViewById(R.id.tvLat);
        tvLat.setText("Latitude: "+getCovidCenters.get(position).getLat());

        TextView tvLang=(TextView)obj2.findViewById(R.id.tvLang);
        tvLang.setText("Langitude: "+getCovidCenters.get(position).getLg());

        return obj2;
    }

}
