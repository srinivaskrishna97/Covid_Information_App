package com.covidinformation.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.covidinformation.R;
import com.covidinformation.activities.VaccineDetailsPojo;
import com.covidinformation.adapters.GetAllNewsAdapter;
import com.covidinformation.api.ApiService;
import com.covidinformation.api.RetroClient;
import com.covidinformation.models.GetAllNewsPojo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GetAllNewsFragment extends Fragment {
    List<GetAllNewsPojo> getAllNewsPojo;
    ListView list_view;
    ProgressDialog progressDialog;
    View view;

    public static GetAllNewsFragment getAllNewsFragment() {
        GetAllNewsFragment fragment = new GetAllNewsFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_get_all_news, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("News");
        list_view=(ListView)view.findViewById(R.id.list_view);

        getAllNewsPojo=new ArrayList<>();
        GetAllNews();


        return view;

    }

    public void GetAllNews(){
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<List<GetAllNewsPojo>> call = service.getAllNews();
        call.enqueue(new Callback<List<GetAllNewsPojo>>() {
            @Override
            public void onResponse(Call<List<GetAllNewsPojo>> call, Response<List<GetAllNewsPojo>> response) {
                //Toast.makeText(AvalableRoomsActivity.this,"ddddd   "+response.body().size(),Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                if(response.body()==null){
                    Toast.makeText(getContext(),"No data found",Toast.LENGTH_SHORT).show();
                }else {
                    getAllNewsPojo = response.body();
                    // Toast.makeText(MyAddsActivity.this,"ddddd   "+response.body(),Toast.LENGTH_SHORT).show();
                    list_view.setAdapter(new GetAllNewsAdapter(getContext(),getAllNewsPojo));


                }
            }

            @Override
            public void onFailure(Call<List<GetAllNewsPojo>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}