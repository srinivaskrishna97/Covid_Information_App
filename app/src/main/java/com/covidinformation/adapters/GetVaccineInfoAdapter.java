package com.covidinformation.adapters;

import android.app.Activity;
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
import com.covidinformation.activities.DetailedNewsActivity;
import com.covidinformation.activities.DetailsVaccineActivity;
import com.covidinformation.activities.EditVaccineActivity;
import com.covidinformation.activities.VaccineInfoActivity;
import com.covidinformation.api.ApiService;
import com.covidinformation.api.RetroClient;
import com.covidinformation.models.ResponseData;
import com.covidinformation.models.VaccinePojo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetVaccineInfoAdapter extends BaseAdapter {
    List<VaccinePojo> vaccinePojos;
    Context context;

    public GetVaccineInfoAdapter(Context context, List<VaccinePojo> vaccinePojos) {
        this.context=context;
        this.vaccinePojos=vaccinePojos;
    }

    @Override
    public int getCount() {
        return vaccinePojos.size();
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
        View obj2 = obj1.inflate(R.layout.adapter_get_vaccine_details, null);

        TextView tvTitle=(TextView)obj2.findViewById(R.id.tvTitle);
        tvTitle.setText("Title: "+vaccinePojos.get(position).getTitle());

        TextView tvDescription=(TextView)obj2.findViewById(R.id.tvDescription);
        tvDescription.setText("Description: "+vaccinePojos.get(position).getDes());

        TextView tvDate=(TextView)obj2.findViewById(R.id.tvDate);
        tvDate.setText("Date: "+vaccinePojos.get(position).getDat());


        Button btn_detailsews=(Button)obj2.findViewById(R.id.btndetails);
        btn_detailsews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, DetailsVaccineActivity.class);
                intent.putExtra("title",vaccinePojos.get(position).getTitle());
                intent.putExtra("desc",vaccinePojos.get(position).getDes());
                intent.putExtra("date",vaccinePojos.get(position).getDat());
                context.startActivity(intent);

            }
        });

        return obj2;
    }

}
