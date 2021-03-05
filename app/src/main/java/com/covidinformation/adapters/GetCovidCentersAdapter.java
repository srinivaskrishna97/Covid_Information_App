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
import com.covidinformation.activities.CovidCentersInfoActivity;
import com.covidinformation.activities.EditCovidInfoActivity;
import com.covidinformation.activities.EditTravellActivity;
import com.covidinformation.api.ApiService;
import com.covidinformation.api.RetroClient;
import com.covidinformation.models.GetCovidCentersPojo;
import com.covidinformation.models.GetQGPojo;
import com.covidinformation.models.ResponseData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetCovidCentersAdapter extends BaseAdapter {
    List<GetCovidCentersPojo> getCovidCenters;
    Context context;
    String URL = "http://covidinformation.live/covid/";

    public GetCovidCentersAdapter(Context context, List<GetCovidCentersPojo> getCovidCenters) {
        this.context = context;
        this.getCovidCenters = getCovidCenters;
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
        View obj2 = obj1.inflate(R.layout.adapter_covid_latestupdate, null);

        TextView tvName = (TextView) obj2.findViewById(R.id.tvName);
        tvName.setText("Center Name: " + getCovidCenters.get(position).getName());

        TextView tvLocation = (TextView) obj2.findViewById(R.id.tvLocation);
        tvLocation.setText("Location: " + getCovidCenters.get(position).getLocation());

        TextView tvAdddressone = (TextView) obj2.findViewById(R.id.tvAdddressone);
        tvAdddressone.setText("Address_1: " + getCovidCenters.get(position).getAddress1());

        TextView tvAdddresstwo = (TextView) obj2.findViewById(R.id.tvAdddresstwo);
        tvAdddresstwo.setText("Address_2: " + getCovidCenters.get(position).getAddress2());

        TextView tvPhone = (TextView) obj2.findViewById(R.id.tvPhone);
        tvPhone.setText("Phone No: " + getCovidCenters.get(position).getPhone());

        TextView tvLat = (TextView) obj2.findViewById(R.id.tvLat);
        tvLat.setText("Latitude: " + getCovidCenters.get(position).getLat());

        TextView tvLang = (TextView) obj2.findViewById(R.id.tvLang);
        tvLang.setText("Langitude: " + getCovidCenters.get(position).getLg());

        Button btnEdit = (Button) obj2.findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, EditCovidInfoActivity.class);
                intent.putExtra("centername", getCovidCenters.get(position).getName());
                intent.putExtra("location", getCovidCenters.get(position).getLocation());
                intent.putExtra("address1", getCovidCenters.get(position).getAddress1());
                intent.putExtra("address2", getCovidCenters.get(position).getAddress2());
                intent.putExtra("phone", getCovidCenters.get(position).getPhone());
                intent.putExtra("cid", getCovidCenters.get(position).getCid());
                context.startActivity(intent);


            }
        });

        Button btnDelete = (Button) obj2.findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                deleteCovidCenter(getCovidCenters.get(position).getCid());
            }
        });

        return obj2;
    }

    ProgressDialog progressDialog;

    public void deleteCovidCenter(String CenterId) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = service.deletecenter(CenterId);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                progressDialog.dismiss();
                if (response.body() == null) {
                    Toast.makeText(context, "Server issue", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(context, CovidCentersInfoActivity.class);
                    context.startActivity(intent);
                    ((Activity) context).finish();
                    Toast.makeText(context, " Center Deleted successfully", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(context, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
