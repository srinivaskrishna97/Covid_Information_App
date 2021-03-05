package com.covidinformation.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.covidinformation.R;
import com.covidinformation.models.NotificationPojo;
import com.covidinformation.models.QGuideLinesPojo;

import java.util.List;

public class NotificationsAdapter extends BaseAdapter {
    List<NotificationPojo> notificationPojo;
    Context context;
    String URL="http://covidinformation.live/covid/";

    public NotificationsAdapter(Context context, List<NotificationPojo> notificationPojo) {
        this.context=context;
        this.notificationPojo=notificationPojo;
    }

    @Override
    public int getCount() {
        return notificationPojo.size();
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
        View obj2 = obj1.inflate(R.layout.adapter_notifications, null);


        ImageView img_fav=(ImageView)obj2.findViewById(R.id.imgnn);

        TextView tvMessage=(TextView)obj2.findViewById(R.id.tvMessage);
        tvMessage.setText(notificationPojo.get(position).getMsg());


        TextView tvName=(TextView)obj2.findViewById(R.id.tvName);
        tvName.setText(notificationPojo.get(position).getName());


        if(notificationPojo.get(position).getType().equals("Q"))
        {
            // Glide.with(context).load(notificationPojo.get(position).get()).into(imgnn);
            img_fav.setImageResource(R.drawable.q);
        }

        if(notificationPojo.get(position).getType().equals("T"))
        {
            // Glide.with(context).load(notificationPojo.get(position).get()).into(imgnn);
            img_fav.setImageResource(R.drawable.t);
        }

        if(notificationPojo.get(position).getType().equals("v"))
        {
            // Glide.with(context).load(notificationPojo.get(position).get()).into(imgnn);
            img_fav.setImageResource(R.drawable.v);
        }

        if(notificationPojo.get(position).getType().equals("N"))
        {
            // Glide.with(context).load(notificationPojo.get(position).get()).into(imgnn);
            img_fav.setImageResource(R.drawable.n);
        }





        return obj2;
    }
}
