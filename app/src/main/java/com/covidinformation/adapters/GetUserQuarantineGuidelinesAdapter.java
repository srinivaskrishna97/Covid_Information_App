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
import com.covidinformation.activities.CovidCentersInfoActivity;
import com.covidinformation.activities.DetailedNewsActivity;
import com.covidinformation.activities.EditQuarantineActivity;
import com.covidinformation.activities.EditTravellActivity;
import com.covidinformation.api.ApiService;
import com.covidinformation.api.RetroClient;
import com.covidinformation.models.GetAllNewsPojo;
import com.covidinformation.models.GetQGPojo;
import com.covidinformation.models.ResponseData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetQuarantineGuidelinesAdapter extends BaseAdapter {
    List<GetQGPojo> getQGPojos;
    Context context;
    String URL="http://covidinformation.live/covid/";

    public GetQuarantineGuidelinesAdapter(Context context, List<GetQGPojo> getQGPojo) {
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
        View obj2 = obj1.inflate(R.layout.adapter_quarantine_guidelines, null);

        TextView tvDescription=(TextView)obj2.findViewById(R.id.tvDescription);
        tvDescription.setText(getQGPojos.get(position).getDescription());

        Button btnEdit=(Button)obj2.findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(context, EditQuarantineActivity.class);
                intent.putExtra("qid",getQGPojos.get(position).getQid());
                intent.putExtra("provience",getQGPojos.get(position).getProvince());
                intent.putExtra("country",getQGPojos.get(position).getCountry());
                intent.putExtra("desc",getQGPojos.get(position).getDescription());
                context.startActivity(intent);


            }
        });


        Button btnDelete=(Button)obj2.findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                deleteQGuidelines(getQGPojos.get(position).getQid());

                //Toast.makeText(context,getQGPojos.get(position).getQid().toString(),Toast.LENGTH_LONG).show();

            }
        });

        return obj2;
    }
    ProgressDialog progressDialog;
    public void deleteQGuidelines(String CenterId){
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = service.deletequarantine(CenterId);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                progressDialog.dismiss();
                if(response.body()==null){
                    Toast.makeText(context,"Server issue",Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent=new Intent(context, AddQuarantineGuidelinesActivity.class);
                    context.startActivity(intent);
                    ((Activity)context).finish();
                    Toast.makeText(context,"Deleted successfully",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                progressDialog.dismiss();

                Toast.makeText(context, "Something went wrong...Please try later!"+t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
