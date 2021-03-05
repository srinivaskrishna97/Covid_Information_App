package com.covidinformation.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.covidinformation.R;
import com.covidinformation.api.ApiService;
import com.covidinformation.api.RetroClient;
import com.covidinformation.models.ResponseData;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistrationActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks{
    TextView tv_signin;
    Button btn_register,btnUploadImage;
    EditText etName,etMobilenumber,etEmail,etPassword;
    Spinner spinAge,spinSelectCountry,spinSelectProvince;
    private static final String TAG = RegistrationActivity.class.getSimpleName();
    private static final int REQUEST_GALLERY_CODE = 200;
    private static final int READ_REQUEST_CODE = 300;
    private static final String SERVER_PATH = "http://covidinformation.live/";
    private Uri uri;
    ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_registration);

        getSupportActionBar().setTitle("Registration");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        btnUploadImage=(Button)findViewById(R.id.btnUploadImage);
        btnUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK);
                openGalleryIntent.setType("image/*");
                startActivityForResult(openGalleryIntent, REQUEST_GALLERY_CODE);

            }
        });

        etName=(EditText)findViewById(R.id.etName);
        etMobilenumber=(EditText)findViewById(R.id.etphone);
        etEmail=(EditText)findViewById(R.id.etEmail);
        etPassword=(EditText)findViewById(R.id.etPassword);


        tv_signin=(TextView)findViewById(R.id.tv_signin);
        btn_register=(Button)findViewById(R.id.btn_register);

        spinAge=(Spinner)findViewById(R.id.spinAge);
        spinSelectCountry=(Spinner)findViewById(R.id.spinCountry);
        spinSelectProvince=(Spinner)findViewById(R.id.spinSelectProvince);

        ArrayAdapter adapter1 = ArrayAdapter.createFromResource(this, R.array.age, R.layout.spinner_item);
        adapter1.setDropDownViewResource(R.layout.spinner_drop_down_list);
        spinAge.setAdapter(adapter1);

        ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this, R.array.country, R.layout.spinner_item);
        adapter2.setDropDownViewResource(R.layout.spinner_drop_down_list);
        spinSelectCountry.setAdapter(adapter2);


        ArrayAdapter adapter3 = ArrayAdapter.createFromResource(this, R.array.province, R.layout.spinner_item);
        adapter3.setDropDownViewResource(R.layout.spinner_drop_down_list);
        spinSelectProvince.setAdapter(adapter3);

//Start
        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.no_img);
        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+"/LatestShare.jpg";
        OutputStream out = null;
        File file1=new File(path);
        try {
            out = new FileOutputStream(file1);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        path=file1.getPath();
        Uri bmpUri = Uri.parse("file://"+path);
        file = new File(bmpUri.getPath());
        //End
        tv_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this,LoginActivity.class));

            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etName.getText().toString().isEmpty()){
                    Toast.makeText(RegistrationActivity.this, "Please Enter Name..", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(etMobilenumber.getText().toString().isEmpty()){
                    Toast.makeText(RegistrationActivity.this, "Please Enter MObile number..", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(etEmail.getText().toString().isEmpty()){
                    Toast.makeText(RegistrationActivity.this, "Please Enter Email..", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(etPassword.getText().toString().isEmpty()){
                    Toast.makeText(RegistrationActivity.this, "Please Enter password..", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(spinAge.getSelectedItem().toString().equals("Age")){
                    Toast.makeText(RegistrationActivity.this, "Please Select Age..", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(spinSelectCountry.getSelectedItem().toString().equals("Country")){
                    Toast.makeText(RegistrationActivity.this, "Please Select Country..", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(spinSelectProvince.getSelectedItem().toString().equals("Province")){
                    Toast.makeText(RegistrationActivity.this, "Please Select Province..", Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                   // startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
                    registration();

                }

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, RegistrationActivity.this);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_GALLERY_CODE && resultCode == Activity.RESULT_OK){
            uri = data.getData();
            if(EasyPermissions.hasPermissions(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                String filePath = getRealPathFromURIPath(uri, RegistrationActivity.this);
                file = new File(filePath);

            }else{
                EasyPermissions.requestPermissions(this, getString(R.string.read_file), READ_REQUEST_CODE, Manifest.permission.READ_EXTERNAL_STORAGE);
            }
        }
    }
    private String getRealPathFromURIPath(Uri contentURI, Activity activity) {
        Cursor cursor = activity.getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }
    File file;
    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        if(uri != null){
            String filePath = getRealPathFromURIPath(uri, RegistrationActivity.this);
            file = new File(filePath);
            // uploadImageToServer();
        }
    }
    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Log.d(TAG, "Permission has been denied");

    }

    private void registration() {
        pd = new ProgressDialog(RegistrationActivity.this);
        pd.setTitle("Loading");
        pd.show();
        Map<String, String> map = new HashMap<>();
        map.put("name",  etName.getText().toString());
        map.put("email", etEmail.getText().toString());
        map.put("phone",  etMobilenumber.getText().toString());
        map.put("country", spinSelectCountry.getSelectedItem().toString());
        map.put("password",etPassword.getText().toString());
        map.put("age", spinAge.getSelectedItem().toString());
        map.put("province", spinSelectProvince.getSelectedItem().toString());


        //RequestBody mFile = RequestBody.create(MediaType.parse("image/*"), file);
        RequestBody mFile = RequestBody.create(MediaType.parse("image/*"), String.valueOf(Uri.parse("android.resource://com.covidinformation/drawable/dp_pic.png")));
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), mFile);
        RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_PATH)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService uploadImage = retrofit.create(ApiService.class);
        Call<ResponseData> fileUpload = uploadImage.user_registration(fileToUpload, map);
        fileUpload.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                pd.dismiss();
                Toast.makeText(RegistrationActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                Intent intent=new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                //finish();
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                pd.dismiss();
                Toast.makeText(RegistrationActivity.this, "Error" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

   /* ProgressDialog progressDialog;
    private void submitData() {
        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String mobileno = etMobilenumber.getText().toString();
        String age=spinAge.getSelectedItem().toString();
        String country=spinSelectCountry.getSelectedItem().toString();
        String provience=spinSelectProvince.getSelectedItem().toString();

        progressDialog = new ProgressDialog(RegistrationActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseData> call = service.userRegistration(name, email, mobileno,password,age,country,provience);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                progressDialog.dismiss();
                if (response.body().status.equals("true")) {
                    Toast.makeText(RegistrationActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(RegistrationActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(RegistrationActivity.this, response.body().message, Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(RegistrationActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}