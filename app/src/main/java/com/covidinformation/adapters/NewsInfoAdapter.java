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
import com.covidinformation.activities.DetailedNewsActivity;
import com.covidinformation.activities.EditNewsActivity;
import com.covidinformation.api.ApiService;
import com.covidinformation.api.RetroClient;
import com.covidinformation.models.GetAllNewsPojo;
import com.covidinformation.models.ResponseData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsInfoAdapter extends BaseAdapter {
    List<GetAllNewsPojo> allNewsPojos;
    Context context;
    String URL="http://covidinformation.live/covid/";

    public NewsInfoAdapter(Context context, List<GetAllNewsPojo> news) {
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
        View obj2 = obj1.inflate(R.layout.adapter_news_info, null);

        TextView tvCovidLatestUpdate=(TextView)obj2.findViewById(R.id.tvCovidLatestUpdate);
        tvCovidLatestUpdate.setText(allNewsPojos.get(position).getTitle());

        TextView tvCovidDesc=(TextView)obj2.findViewById(R.id.tvCovidDesc);
        tvCovidDesc.setText(allNewsPojos.get(position).getDes()+"....");


        ImageView imageView=(ImageView)obj2.findViewById(R.id.imageView);
        Glide.with(context).load(URL+allNewsPojos.get(position).getImage()).into(imageView);

        Button btnEdit=(Button)obj2.findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, EditNewsActivity.class);
                intent.putExtra("nid",allNewsPojos.get(position).getNid());
                intent.putExtra("title",allNewsPojos.get(position).getTitle());
                intent.putExtra("desc",allNewsPojos.get(position).getDes());
                context.startActivity(intent);
                //((Activity)context).finish();

            }
        });


        Button btnDelete=(Button)obj2.findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                deleteNews(allNewsPojos.get(position).getNid());
            }
        });

        return obj2;
    }
    ProgressDialog progressDialog;
    public void deleteNews(String id){
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = service.deletenews(id);
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
                Toast.makeText(context, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
