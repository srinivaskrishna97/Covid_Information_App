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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.covidinformation.R;
import com.covidinformation.activities.AddQuarantineGuidelinesActivity;
import com.covidinformation.activities.EditTravellActivity;
import com.covidinformation.activities.EditVaccineActivity;
import com.covidinformation.activities.VaccineInfoActivity;
import com.covidinformation.api.ApiService;
import com.covidinformation.api.RetroClient;
import com.covidinformation.models.GetAllNewsPojo;
import com.covidinformation.models.ResponseData;
import com.covidinformation.models.VaccinePojo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VaccineInfoAdapter extends BaseAdapter {
    List<VaccinePojo> vaccinePojos;
    Context context;

    public VaccineInfoAdapter(Context context, List<VaccinePojo> vaccinePojos) {
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
        View obj2 = obj1.inflate(R.layout.adapter_vaccine_details, null);

        TextView tvTitle=(TextView)obj2.findViewById(R.id.tvTitle);
        tvTitle.setText("Title: "+vaccinePojos.get(position).getTitle());

        TextView tvDescription=(TextView)obj2.findViewById(R.id.tvDescription);
        tvDescription.setText("Description: "+vaccinePojos.get(position).getDes());

        TextView tvDate=(TextView)obj2.findViewById(R.id.tvDate);
        tvDate.setText("Date: "+vaccinePojos.get(position).getDat());


        Button btnEdit=(Button)obj2.findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(context, EditVaccineActivity.class);
                intent.putExtra("desc",vaccinePojos.get(position).getDes());
                intent.putExtra("title",vaccinePojos.get(position).getTitle());
                intent.putExtra("vid",vaccinePojos.get(position).getVid());
                context.startActivity(intent);


            }
        });


        Button btnDelete=(Button)obj2.findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                deleteVaccine(vaccinePojos.get(position).getVid());
            }
        });

        return obj2;
    }
    ProgressDialog progressDialog;
    public void deleteVaccine(String id){
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = service.deletevaccine(id);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                progressDialog.dismiss();
                if(response.body()==null){
                    Toast.makeText(context,"Server issue",Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent=new Intent(context, VaccineInfoActivity.class);
                    context.startActivity(intent);
                    ((Activity)context).finish();
                    Toast.makeText(context,"Deleted successfully",Toast.LENGTH_SHORT).show();
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
