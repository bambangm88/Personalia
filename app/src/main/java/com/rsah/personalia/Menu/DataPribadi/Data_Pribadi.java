package com.rsah.personalia.Menu.DataPribadi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.rsah.personalia.Helper.Helper;
import com.rsah.personalia.Model.ResponseData;
import com.rsah.personalia.Model.ResponseEntityProfile;
import com.rsah.personalia.R;
import com.rsah.personalia.api.ApiService;
import com.rsah.personalia.api.Server;
import com.rsah.personalia.sessionManager.SessionManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Data_Pribadi extends AppCompatActivity {

    private Context mContext;
    private ApiService API;
    private SessionManager session;
    private CircularImageView circularImageView;
    private List<ResponseEntityProfile> AllEntityProfile = new ArrayList<>();
    private EditText nama , notelp , email , jabatan , bagian, alamat , unit;
    private CircularImageView img_profile ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pribadi);


        mContext = this ;
        session = new SessionManager(getApplicationContext());
        API = Server.getAPIService();

        img_profile = findViewById(R.id.cirCularImage);

        nama = findViewById(R.id.nama) ;
        email = findViewById(R.id.email) ;
        notelp = findViewById(R.id.notelp) ;
        jabatan = findViewById(R.id.jabatan) ;
        bagian = findViewById(R.id.bagian) ;
        alamat = findViewById(R.id.alamat) ;
        unit = findViewById(R.id.unit) ;



        Helper.countDown(this);


        profile(session.getUsername());


    }



    private void profile(String user){


        Call<ResponseData> call = API.getProfile(user);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if(response.isSuccessful()) {
                    if (response.body().getDataProfile() != null) {


                        if(response.body().getDataProfile().isEmpty()){

                            // Toast.makeText(mContext, "ID Tidak Ditemukan", Toast.LENGTH_LONG).show();

                        }else{

                            AllEntityProfile.addAll(response.body().getDataProfile()) ;

                            String nama_ = response.body().getDataProfile().get(0).getsFisrtName() ;
                            String jabatan_ = response.body().getDataProfile().get(0).getJabatan() ;
                            String IDjabatan_ = response.body().getDataProfile().get(0).getsDeptID() ;
                            String email_ = response.body().getDataProfile().get(0).getEmail() ;
                            String noTelp_ = response.body().getDataProfile().get(0).getsMobilePhone() ;
                            //String ttl = response.body().getDataProfile().get(0).getsPlaceOfBirthDay() + ", " +response.body().getDataId().get(0).getdDateOfBirthDay() ;
                            String alamat_ = response.body().getDataProfile().get(0).getsAddress() ;
                            String bagian_ = response.body().getDataProfile().get(0).getBidang() ;
                            String unit_ = response.body().getDataProfile().get(0).getUnit() ;
                            String image_ = response.body().getDataProfile().get(0).getFoto() ;
                            //Toast.makeText(mContext, "DATA DITEMUKAN", Toast.LENGTH_LONG).show();


                            nama.setText(nama_);
                            email.setText(email_);
                            notelp.setText(noTelp_);
                            jabatan.setText(jabatan_);
                            bagian.setText(bagian_);
                            alamat.setText(alamat_);
                            unit.setText(unit_);



                            //setImage
                            if (!image_.equals("")) {

                                String url = "http://45.112.125.33/WebApiPersonaliaApp/foto_profile/" + image_;

                                Glide.with(mContext).load(url).into(img_profile);

                            }





                        }



                    }else{

                        Toast.makeText(mContext, "Error Response Data", Toast.LENGTH_LONG).show();
                    }

                }else{
                    Toast.makeText(mContext, "Error Response Data", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                Toast.makeText(mContext, "Internal server error / check your connection", Toast.LENGTH_SHORT).show();
                Log.e("Error", "onFailure: "+t.getMessage() );
            }
        });
    }





}
