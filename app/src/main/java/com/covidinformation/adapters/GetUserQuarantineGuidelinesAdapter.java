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
import com.covidinformation.activities.AddQuarantineGuidelinesActivity;
import com.covidinformation.api.ApiService;
import com.covidinformation.api.RetroClient;
import com.covidinformation.models.GetQGPojo;
import com.covidinformation.models.QGuideLinesPojo;
import com.covidinformation.models.ResponseData;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetUserQuarantineGuidelinesAdapter extends BaseAdapter {
    List<QGuideLinesPojo> getQGPojos, searchGuidens;
    Context context;
    String URL = "http://covidinformation.live/covid/";

    public GetUserQuarantineGuidelinesAdapter(Context context, List<QGuideLinesPojo> getQGPojo1) {


        this.searchGuidens = getQGPojo1;
        this.context = context;
        this.getQGPojos = new ArrayList<QGuideLinesPojo>();
        this.getQGPojos.addAll(searchGuidens);
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
        View obj2 = obj1.inflate(R.layout.adapter_get_user_querentine_guidence, null);


        Button btngetcountry = (Button) obj2.findViewById(R.id.btngetcountry);
        btngetcountry.setText(getQGPojos.get(position).getProvince());


        TextView tvGuidence = (TextView) obj2.findViewById(R.id.tvGuidence);
        tvGuidence.setText(getQGPojos.get(position).getDescription());

        return obj2;
    }

    public void getQGuideLines(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        getQGPojos.clear();
        if (charText.length() == 0) {
            getQGPojos.addAll(searchGuidens);
        } else {
            for (QGuideLinesPojo wp : searchGuidens) {
                if (wp.getProvince().toLowerCase(Locale.getDefault()).contains(charText)) {
                    getQGPojos.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
}
