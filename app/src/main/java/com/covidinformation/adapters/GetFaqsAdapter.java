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

import com.covidinformation.R;
import com.covidinformation.activities.DetailsVaccineActivity;
import com.covidinformation.models.Faqs;
import com.covidinformation.models.VaccinePojo;

import java.util.List;

//
public class GetFaqsAdapter extends BaseAdapter {
    List<Faqs> faqsPojos;
    Context context;

    public GetFaqsAdapter(Context context, List<Faqs> faqsPojos) {
        this.context=context;
        this.faqsPojos=faqsPojos;
    }

    @Override
    public int getCount() {
        return faqsPojos.size();
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
        View obj2 = obj1.inflate(R.layout.adapter_get_faqs, null);

        TextView tvTitle=(TextView)obj2.findViewById(R.id.tvTitle);
        tvTitle.setText("Title: "+faqsPojos.get(position).getFid());

        TextView tvDescription=(TextView)obj2.findViewById(R.id.tvDescription);
        tvDescription.setText(faqsPojos.get(position).getQuestion());

        TextView tvans=(TextView)obj2.findViewById(R.id.tvans);
        tvans.setText(faqsPojos.get(position).getAns());


        ImageView imageView=(ImageView) obj2.findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tvans.setVisibility(View.VISIBLE);
//                if()
//                {
//
//                }
//                else {
//
//                }

            }
        });

        return obj2;
    }

}
