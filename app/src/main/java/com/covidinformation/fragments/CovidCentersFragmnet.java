package com.covidinformation.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.covidinformation.R;
import com.covidinformation.activities.CovidCentersInfoActivity;
import com.covidinformation.adapters.GetCovidCentersAdapter;
import com.covidinformation.adapters.UserCovidCentersAdapter;
import com.covidinformation.api.ApiService;
import com.covidinformation.api.RetroClient;
import com.covidinformation.models.GetCovidCentersPojo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CovidCentersFragmnet extends Fragment {
    View view;
    ProgressDialog pd;
    ListView list_view;
    List<GetCovidCentersPojo> getCovidCentersPojo;


    public static CovidCentersFragmnet covidCentersFragmnet() {
        CovidCentersFragmnet fragment = new CovidCentersFragmnet();
        return fragment;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_get_all_news, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Covid Centers");
        list_view=(ListView)view.findViewById(R.id.list_view);
        getCovidCentersPojo=new ArrayList<>();
        getCovidCenters();
        return view;

    }
    public void getCovidCenters(){
        pd = new ProgressDialog(getContext());
        pd.setMessage("Loading....");
        pd.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<List<GetCovidCentersPojo>> call = service.getcovidcenters();
        call.enqueue(new Callback<List<GetCovidCentersPojo>>() {
            @Override
            public void onResponse(Call<List<GetCovidCentersPojo>> call, Response<List<GetCovidCentersPojo>> response) {
                pd.dismiss();
                if(response.body()==null){
                    Toast.makeText(getContext(),"No data found",Toast.LENGTH_SHORT).show();
                }else {
                    getCovidCentersPojo = response.body();
                    list_view.setAdapter(new UserCovidCentersAdapter(getContext(),getCovidCentersPojo));
                }
            }

            @Override
            public void onFailure(Call<List<GetCovidCentersPojo>> call, Throwable t) {
                pd.dismiss();
                Toast.makeText(getContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}