package com.rsah.personalia.Menu.Absent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.rsah.personalia.Auth.Login;
import com.rsah.personalia.Helper.Helper;
import com.rsah.personalia.MainActivity;
import com.rsah.personalia.Model.ResponseData;
import com.rsah.personalia.Model.ResponseEntityLogin;
import com.rsah.personalia.R;
import com.rsah.personalia.api.ApiService;
import com.rsah.personalia.api.Server;
import com.rsah.personalia.sessionManager.SessionManager;

import java.util.List;
import java.util.Locale;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailAbsen extends AppCompatActivity   {

    private TextView currentLocation , tvname , distanceLoc ;
    private Context mContext;
    private ApiService API;
    private SessionManager session;
    Intent intentThatCalled;
    public double latitude;
    public double longitude;
    public LocationManager locationManager;
    public Criteria criteria;
    public String bestProvider;
    private RelativeLayout rlProgress ;
    private Button CheckIn , CheckOut ;
    private static double currentLoc = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_absen);


        Bundle bundle = getIntent().getExtras();
        intentThatCalled = getIntent();
        rlProgress = findViewById(R.id.rlprogress);
        CheckIn = findViewById(R.id.btnCheckIn);
        CheckOut= findViewById(R.id.btnCheckOut);
        distanceLoc= findViewById(R.id.distancelocation);


        deteksiGPS();


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkPermission();
        }

        mContext = this ;
        session = new SessionManager(getApplicationContext());
        API = Server.getAPIService();
        currentLocation = findViewById(R.id.currentlocation);
        tvname = findViewById(R.id.welcome);






        profile(session.getUsername());

        FusedLocationProviderClient mFusedLocationClient =  LocationServices.getFusedLocationProviderClient(this);

        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.

                        try {

                            getCompleteAddressString(location.getLatitude(),location.getLongitude());

                            currentLoc = countDistanceLocation(location);
                            String format = String.format(Locale.US, "%.1f", countDistanceLocation(location));

                            distanceLoc.setText(format+" km dari office");
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e("TAG", "onSuccess: error "+e.getMessage() );

                        }

                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(DetailAbsen.this,
                                "error : " + e.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });







        CheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





                Absent(session.getUsername(),"IN");
            }
        });

        CheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Absent(session.getUsername(),"OUT");
            }
        });


    }


    public void deteksiGPS (){
        // Untuk Mendeteksi apakah GPS aktif atau tidak
        LocationManager lm = (LocationManager)getSystemService(LOCATION_SERVICE);
        if (!lm.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                !lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            // Build the alert dialog
            final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
            builder.setTitle("GPS Anda Belum Aktif");
            builder.setMessage("Untuk Melanjutkan Silahkan Aktifkan GPS Anda !");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    // Show location settings when the user acknowledges the alert dialog
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(intent);
                }
            });

            builder.setNegativeButton("Lain Kali", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    // Show location settings when the user acknowledges the alert dialog

                }
            });

            Dialog alertDialog = builder.create();
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.show();
        }

    }



    private String getCompleteAddressString(double LATITUDE, double LONGITUDE) {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");

                for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                }
                strAdd = strReturnedAddress.toString();

                Log.e("TAG", strReturnedAddress.toString());

                currentLocation.setText(strReturnedAddress.toString());

            } else {
                Log.e("TAG", "No Address returned!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.w("TAG", "Canont get Address!");
        }
        return strAdd;
    }

    public void checkPermission(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ){//Can add more as per requirement

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},
                    123);
        }
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

                            String nama_ = response.body().getDataProfile().get(0).getsFisrtName() ;
                            tvname.setText("Welcome, "+nama_);




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









    private void Absent(String empid, String request){

        showProgress(true);
        Call<ResponseData> call = API.Absent( request,empid);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if(response.isSuccessful()) {
                    if (response.body().getSuccess() != null) {


                        if(response.body().getSuccess().equals("00") ){

                            showProgress(false);

                            if (request.equals("IN")){

                                Helper.notifAlert(DetailAbsen.this,"Check In Berhasil");

                            }else{
                                Helper.notifAlert(DetailAbsen.this,"Check Out Berhasil");

                            }



                        }else if(response.body().getSuccess().equals("01") ){
                            showProgress(false);

                            if (request.equals("IN")){
                                Toast.makeText(mContext, "Check In Sudah dilakukan", Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(mContext, "Check Out Sudah dilakukan", Toast.LENGTH_LONG).show();
                            }

                        }else{
                            Toast.makeText(mContext, "Terjadi Kesalahan", Toast.LENGTH_LONG).show();
                        }


                    }else{
                        showProgress(false);
                        Toast.makeText(mContext, "Error Response Data", Toast.LENGTH_LONG).show();
                    }

                }else{
                    showProgress(false);
                    Toast.makeText(mContext, "Error Response Data", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {

                showProgress(false);
                Toast.makeText(mContext, "Internal server error"+t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("Error", "onFailure: "+t.getMessage() );
            }
        });
    }




    private void showProgress (boolean bool){

        if (bool){
            rlProgress.setVisibility(View.VISIBLE);
        }else{
            rlProgress.setVisibility(View.GONE);
        }

    }


    private  double countDistanceLocation(Location location){
        String srsaLat = "-6.499580" ;
        String srsaLong = "107.471244" ;
        double LatRsa = Double.parseDouble(srsaLat);
        double LongRsa = Double.parseDouble(srsaLong);

        LatLng latLng1 = new LatLng(LatRsa,LongRsa);
        LatLng latLng2 = new LatLng(location.getLatitude(),location.getLongitude());

        double jarak = Helper.CalculationByDistance(latLng1,latLng2);

        return jarak;
    }




}