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
import com.covidinformation.activities.CovidLatestUpdates;
import com.covidinformation.activities.VaccineDetailsPojo;
import java.util.List;

public class CovidLatestUpdateAdapter extends BaseAdapter {
    List<VaccineDetailsPojo> vaccineDetailsPojo;
    Context context;

    public CovidLatestUpdateAdapter(Context context, List<VaccineDetailsPojo> vaccinede) {
        this.context=context;
        this.vaccineDetailsPojo=vaccinede;
    }

    @Override
    public int getCount() {
        return vaccineDetailsPojo.size();
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
        View obj2 = obj1.inflate(R.layout.adapter_covid_latestupdate, null);

        TextView tvCovidLatestUpdate=(TextView)obj2.findViewById(R.id.tvCovidLatestUpdate);
        tvCovidLatestUpdate.setText(vaccineDetailsPojo.get(position).getTitle());

        TextView tvCovidDesc=(TextView)obj2.findViewById(R.id.tvCovidDesc);
        tvCovidDesc.setText(vaccineDetailsPojo.get(position).getDescription()+"....");


        ImageView imageView=(ImageView)obj2.findViewById(R.id.imageView);
        Glide.with(context).load(vaccineDetailsPojo.get(position).getImage()).into(imageView);



        Button btn_detailsews=(Button)obj2.findViewById(R.id.btn_detailsews);
        btn_detailsews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, DetailedNewsActivity.class);
                intent.putExtra("title",vaccineDetailsPojo.get(position).getTitle());
                intent.putExtra("desc",vaccineDetailsPojo.get(position).getDescription());
                intent.putExtra("image",vaccineDetailsPojo.get(position).getImage());
                context.startActivity(intent);

            }
        });

        return obj2;
    }
}
