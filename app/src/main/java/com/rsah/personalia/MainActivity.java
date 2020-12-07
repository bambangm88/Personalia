package com.rsah.personalia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.rsah.personalia.Menu.Absent.FaceRecog.GpsUtils;
import com.rsah.personalia.Menu.Absent.FaceRecog.RecognizeActivity;
import com.rsah.personalia.Helper.Helper;
import com.rsah.personalia.Menu.Absent.FaceRecog.RegFaceActivity;
import com.rsah.personalia.Menu.DataPribadi.Data_Pribadi;
import com.rsah.personalia.Menu.Pengaturan.Pengaturan;
import com.rsah.personalia.Menu.Performance.PilihPeriodePerformance;
import com.rsah.personalia.Menu.SlipGaji.ChooseJenisSlipGaji;
import com.rsah.personalia.Menu.TeamSaya.Team;
import com.rsah.personalia.Model.ResponseData;
import com.rsah.personalia.Model.ResponseEntityLogout;
import com.rsah.personalia.Model.ResponseEntityProfile;

import com.rsah.personalia.api.ApiService;
import com.rsah.personalia.api.Server;
import com.rsah.personalia.sessionManager.SessionManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.rsah.personalia.Menu.Absent.FaceRecog.GpsUtils.GPS_REQUEST;
import static com.rsah.personalia.Menu.Absent.FaceRecog.GpsUtils.LOCATION_REQUEST;

public class MainActivity extends AppCompatActivity    {

    private LinearLayout btn_pengaturan , btn_team , btn_slip_gaji , dataPribadi , btn_performance , btn_absent;

    private Context mContext;
    private ApiService API;
    private SessionManager session;
    private TextView id , nama , jabatan;
    private CircularImageView circularImageView;


    private FusedLocationProviderClient mFusedLocationClient;

    private double wayLatitude = 0.0, wayLongitude = 0.0;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;
    private android.widget.Button btnLocation;
   //private TextView txtLocation;
    private android.widget.Button btnContinueLocation;
    private StringBuilder stringBuilder;

    private boolean isContinue = false;
    private boolean isGPS = false;


    private List<ResponseEntityProfile> AllEntityProfile = new ArrayList<>();
    public static List<ResponseEntityLogout> AllEntityLogout = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10 * 1000); // 10 seconds
        locationRequest.setFastestInterval(5 * 1000); // 5 seconds

        new GpsUtils(this).turnGPSOn(new GpsUtils.onGpsListener() {
            @Override
            public void gpsStatus(boolean isGPSEnable) {
                // turn on GPS
                isGPS = isGPSEnable;
            }
        });




        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    if (location != null) {
                        wayLatitude = location.getLatitude();
                        wayLongitude = location.getLongitude();
                        if (!isContinue) {
                            //txtLocation.setText(String.format(Locale.US, "%s - %s", wayLatitude, wayLongitude));
                        } else {
                            stringBuilder.append(wayLatitude);
                            stringBuilder.append("-");
                            stringBuilder.append(wayLongitude);
                            stringBuilder.append("\n\n");
                            //txtContinueLocation.setText(stringBuilder.toString());
                        }
                        if (!isContinue && mFusedLocationClient != null) {
                            mFusedLocationClient.removeLocationUpdates(locationCallback);
                        }
                    }
                }
            }
        };



        mContext = this ;
        session = new SessionManager(getApplicationContext());
        API = Server.getAPIService();
        id = findViewById(R.id.id);
        nama = findViewById(R.id.nama);
        jabatan = findViewById(R.id.jabatan);
        circularImageView = findViewById(R.id.cirCularImage);



       btn_pengaturan = findViewById(R.id.pengaturan);
       btn_team = findViewById(R.id.btn_team);
       btn_slip_gaji = findViewById(R.id.btn_slip_gaji);
       dataPribadi = findViewById(R.id.btnProfile);
       btn_performance = findViewById(R.id.performance);
       btn_absent = findViewById(R.id.btnAbsent);




       btn_pengaturan.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               startActivity(new Intent(mContext , Pengaturan.class));

           }
       });

        btn_team.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(mContext , Team.class));

            }
        });

        btn_slip_gaji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //startActivity(new Intent(mContext , Team.class));

                //confirm_password() ;

                startActivity(new Intent(mContext , ChooseJenisSlipGaji.class));
            }
        });

        dataPribadi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //startActivity(new Intent(mContext , Team.class));

                startActivity(new Intent(mContext , Data_Pribadi.class));

            }
        });

        btn_performance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //startActivity(new Intent(mContext , Team.class));

                startActivity(new Intent(mContext , PilihPeriodePerformance.class));

            }
        });

        btn_absent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //startActivity(new Intent(mContext , Team.class));

                if (session.getIsRegFace().equals("Y")){
                    startActivity(new Intent(mContext , RecognizeActivity.class));

                }else{
                    Toast.makeText(mContext, "Wajah Anda Belum Terdaftar, Silahkan Registrasi", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(mContext , RegFaceActivity.class));
                }



            }
        });


        profile(session.getUsername());

        UpdateIsLogin(session.getUsername());

        Helper.checkVersionUpdate(mContext);


        Helper.countDown(mContext);



        if (!isGPS) {
            Toast.makeText(this, "Please turn on GPS", Toast.LENGTH_SHORT).show();
            return;
        }

        deteksiGPS();


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkPermission();
        }
        getLocation();



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
                            String sex = response.body().getDataProfile().get(0).getSex() ;
                            String pwd = response.body().getDataProfile().get(0).getPwd() ;
                            String form = response.body().getDataProfile().get(0).getForm() ;
                            String isRegistrasiFace = response.body().getDataProfile().get(0).getIsRegistrationFace() ;

                            //String ttl = response.body().getDataProfile().get(0).getsPlaceOfBirthDay() + ", " +response.body().getDataId().get(0).getdDateOfBirthDay() ;
                            //String alamat = response.body().getDataProfile().get(0).getsAddress() ;

                            String image_ = response.body().getDataProfile().get(0).getFoto() ;

                            session.createProfileSession(jabatan_,IDjabatan_,pwd,form,isRegistrasiFace,nama_);



                            String id_ = session.getUsername();

                            id.setText(id_);
                            nama.setText(nama_);
                            jabatan.setText(jabatan_);

                            if (sex.equals('M')){
                                circularImageView.setBackgroundResource(R.mipmap.profile);
                            }else{
                                circularImageView.setBackgroundResource(R.mipmap.female);
                            }


                            //setImage
                            if (!image_.equals("")) {

                                String url = "http://45.112.125.33/WebApiPersonaliaApp/foto_profile/" + image_;

                                Glide.with(mContext).load(url).into(circularImageView);

                            }





                            //Toast.makeText(mContext, "DATA DITEMUKAN", Toast.LENGTH_LONG).show();


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


    void confirm_password(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Konfirmasi Password");

// Set up the input
        final EditText input = new EditText(this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        builder.setView(input);

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String m_Text = input.getText().toString();
                String pwd = session.getKeyPwd();

                if (Helper.convert_to_sha256(m_Text).equals(pwd)){



                }else{

                    Toast.makeText(mContext, "Password Salah", Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                }





            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();

    }




    private void UpdateIsLogin(String username){


        Call<ResponseData> call = API.Logout(username , "LOGIN");
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if(response.isSuccessful()) {

                }else{

                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {

            }
        });
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        Helper.Logout(mContext);

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



    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    LOCATION_REQUEST);

        } else {
            if (isContinue) {
                mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
            } else {
                mFusedLocationClient.getLastLocation().addOnSuccessListener(MainActivity.this, location -> {
                    if (location != null) {
                        wayLatitude = location.getLatitude();
                        wayLongitude = location.getLongitude();
                       // txtLocation.setText(String.format(Locale.US, "%s - %s", wayLatitude, wayLongitude));
                        //Toast.makeText(mContext, String.format(Locale.US, "%s - %s", wayLatitude, wayLongitude), Toast.LENGTH_LONG).show();
                    } else {
                        mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
                    }
                });
            }
        }
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1000: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    if (isContinue) {
                        mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
                    } else {
                        mFusedLocationClient.getLastLocation().addOnSuccessListener(MainActivity.this, location -> {
                            if (location != null) {
                                wayLatitude = location.getLatitude();
                                wayLongitude = location.getLongitude();
                               // txtLocation.setText(String.format(Locale.US, "%s - %s", wayLatitude, wayLongitude));

                                Toast.makeText(mContext, String.format(Locale.US, "%s - %s", wayLatitude, wayLongitude), Toast.LENGTH_LONG).show();
                            } else {
                                mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
                            }
                        });
                    }
                } else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == GPS_REQUEST) {
                isGPS = true; // flag maintain before get location
            }
        }
    }


}


