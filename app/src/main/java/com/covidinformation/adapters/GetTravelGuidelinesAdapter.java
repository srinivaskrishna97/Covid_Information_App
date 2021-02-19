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
import com.covidinformation.activities.AddTravellGuidelinesActivity;
import com.covidinformation.activities.CovidCentersInfoActivity;
import com.covidinformation.api.ApiService;
import com.covidinformation.api.RetroClient;
import com.covidinformation.models.GetQGPojo;
import com.covidinformation.models.ResponseData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetTravelGuidelinesAdapter extends BaseAdapter {
    List<GetQGPojo> getQGPojos;
    Context context;
    String URL="http://covidinformation.live/covid/";

    public GetTravelGuidelinesAdapter(Context context, List<GetQGPojo> getQGPojo) {
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
        View obj2 = obj1.inflate(R.layout.adapter_travel_guidelines, null);

        TextView tvDescription=(TextView)obj2.findViewById(R.id.tvDescription);
        tvDescription.setText(getQGPojos.get(position).getDescription());

        Button btnEdit=(Button)obj2.findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        Button btnDelete=(Button)obj2.findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                deleteTravellGuidelines(getQGPojos.get(position).getQid());
            }
        });

        return obj2;
    }
    ProgressDialog progressDialog;
    public void deleteTravellGuidelines(String CenterId){
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = service.deletetravel(CenterId);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                progressDialog.dismiss();
                if(response.body()==null){
                    Toast.makeText(context,"Server issue",Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent=new Intent(context, AddTravellGuidelinesActivity.class);
                    context.startActivity(intent);
                    Toast.makeText(context," Center Deleted successfully",Toast.LENGTH_SHORT).show();
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
