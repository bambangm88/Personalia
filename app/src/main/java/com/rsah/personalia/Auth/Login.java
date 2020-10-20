package com.rsah.personalia.Auth;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rsah.personalia.Helper.Helper;
import com.rsah.personalia.MainActivity;
import com.rsah.personalia.Model.ResponseData;
import com.rsah.personalia.Model.ResponseEntityLogin;
import com.rsah.personalia.R;
import com.rsah.personalia.api.ApiService;
import com.rsah.personalia.api.Server;
import com.rsah.personalia.sessionManager.SessionManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {



    private ProgressDialog pDialog;

    private Button btn_login ;
    private EditText username , password ;

    private Context mContext;
    private ApiService API;

    private SessionManager session;


    private TextView btn_daftar ;

    public static List<ResponseEntityLogin> AllEntityLogin = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        session = new SessionManager(getApplicationContext());

        username = findViewById(R.id.editTextID) ;
        password = findViewById(R.id.editTextPassword);


        btn_login = findViewById(R.id.btn_login);
        btn_daftar = findViewById(R.id.btn_daftar);

        mContext = this ;
        API = Server.getAPIService();

        pDialog = new ProgressDialog(Login.this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Memuat...");


        /*if (session.isLoggedIn()) {

            Intent i = new Intent(Login.this, MainActivity.class);

            startActivity(i);
            finish();
        }*/





        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = username.getText().toString();
                String pwd = password.getText().toString();

                if (Helper.checkNullInputLogin(user,pwd)){
                    Login(user,pwd);
                }else{

                    Toast.makeText(mContext, "Field tidak boleh kosong", Toast.LENGTH_LONG).show();

                }

            }
        });


        btn_daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, ValidasiID.class));
            }
        });


        Helper.checkVersionUpdate(mContext);

        Helper.Logout(mContext);


    }

    private void Login(String username, String password){


        pDialog.show();
        Call<ResponseData> call = API.Login(username,password);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if(response.isSuccessful()) {
                    if (response.body().getDataLogin() != null) {


                        if(response.body().getDataLogin().isEmpty()){

                            pDialog.cancel();
                            Toast.makeText(mContext, "Password salah", Toast.LENGTH_LONG).show();

                        }

                        else if(response.body().getDataLogin().get(0).getCallback() != null){


                            if(response.body().getDataLogin().get(0).getCallback().equals("1")){

                                pDialog.cancel();
                                Toast.makeText(mContext, "Akun sedang aktif", Toast.LENGTH_LONG).show();

                            }

                            if(response.body().getDataLogin().get(0).getCallback().equals("F")){

                                pDialog.cancel();
                                Toast.makeText(mContext, "Username salah", Toast.LENGTH_LONG).show();

                            }




                        }
                        else{
                            pDialog.cancel();

                            AllEntityLogin.addAll(response.body().getDataLogin()) ;

                            for(ResponseEntityLogin model : AllEntityLogin) {

                                session.createLoginSession(
                                        model.getsEmpID()
                                );

                            }

                            Intent intent = new Intent(Login.this, MainActivity.class);
                            startActivity(intent);
                            Toast.makeText(mContext, "Login Berhasil", Toast.LENGTH_LONG).show();
                            finish();


                        }



                    }else{
                        pDialog.cancel();

                        Toast.makeText(mContext, "Error Response Data", Toast.LENGTH_LONG).show();
                    }

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
