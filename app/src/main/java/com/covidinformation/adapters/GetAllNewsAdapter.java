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
import com.covidinformation.models.GetAllNewsPojo;

import java.util.List;

public class GetAllNewsAdapter extends BaseAdapter {
    List<GetAllNewsPojo> allNewsPojos;
    Context context;
    String URL="http://covidinformation.live/covid/";

    public GetAllNewsAdapter(Context context, List<GetAllNewsPojo> news) {
        this.context=context;
        this.allNewsPojos=news;
    }

    @Override
    public int getCount() {
        return allNewsPojos.size();
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
        View obj2 = obj1.inflate(R.layout.adapter_get_all_news, null);

        TextView tvCovidLatestUpdate=(TextView)obj2.findViewById(R.id.tvCovidLatestUpdate);
        tvCovidLatestUpdate.setText(allNewsPojos.get(position).getTitle());

        TextView tvCovidDesc=(TextView)obj2.findViewById(R.id.tvCovidDesc);
        tvCovidDesc.setText(allNewsPojos.get(position).getDes()+"....");


        ImageView imageView=(ImageView)obj2.findViewById(R.id.imageView);
        Glide.with(context).load(URL+allNewsPojos.get(position).getImage()).into(imageView);



        Button btn_detailsews=(Button)obj2.findViewById(R.id.btn_detailsews);
        btn_detailsews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, DetailedNewsActivity.class);
                intent.putExtra("title",allNewsPojos.get(position).getTitle());
                intent.putExtra("desc",allNewsPojos.get(position).getDes());
                intent.putExtra("image",URL+allNewsPojos.get(position).getImage());
                context.startActivity(intent);

            }
        });

        return obj2;
    }
}
