package com.rsah.personalia.Menu.Pengaturan;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.rsah.personalia.Auth.Login;
import com.rsah.personalia.Helper.Helper;
import com.rsah.personalia.MainActivity;
import com.rsah.personalia.Model.ResponseData;
import com.rsah.personalia.Model.ResponseEntityLogin;
import com.rsah.personalia.Model.ResponseEntityLogout;
import com.rsah.personalia.R;
import com.rsah.personalia.api.ApiService;
import com.rsah.personalia.api.Server;
import com.rsah.personalia.sessionManager.SessionManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Pengaturan extends AppCompatActivity {


    private  Button ubahPwd , keluar;
    private SessionManager sessionManager ;
    private Context mContext ;

    private ApiService API;

    private ProgressDialog pDialog;

    public static List<ResponseEntityLogout> AllEntityLogout = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengaturan);

        mContext = this ;
        API = Server.getAPIService();

        pDialog = new ProgressDialog(mContext);
        pDialog.setCancelable(false);
        pDialog.setMessage("Memuat...");


        ubahPwd = findViewById(R.id.ubahPwd) ;
        keluar = findViewById(R.id.keluar);
        sessionManager = new SessionManager(mContext) ;

        Helper.countDown(this);

        ubahPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Pengaturan.this, ChangePassword.class));

            }
        });

        keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                show_dialog(mContext);

            }
        });



    }

    public void show_dialog(Context context){

        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setMessage("Keluar ?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        Logout(sessionManager.getUsername());

                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }





    private void Logout(String username){


        pDialog.show();
        Call<ResponseData> call = API.Logout(username , "LOGOUT");
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if(response.isSuccessful()) {

                            AllEntityLogout.addAll(response.body().getDataLogout()) ;

                            Toast.makeText(mContext, "Logout Berhasil", Toast.LENGTH_LONG).show();

                            Intent i = new Intent(mContext, Login.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                    Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                    Intent.FLAG_ACTIVITY_NEW_TASK);
                            ((Activity)mContext).startActivity(i);



                }else{
                    pDialog.cancel();
                    Toast.makeText(mContext, "Error Response Data", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {

                pDialog.cancel();

                Toast.makeText(mContext, "Internal server error / check your connection", Toast.LENGTH_SHORT).show();
                Log.e("Error", "onFailure: "+t.getMessage() );
            }
        });
    }




}
