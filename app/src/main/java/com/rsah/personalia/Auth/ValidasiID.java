package com.rsah.personalia.Auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rsah.personalia.Helper.Helper;
import com.rsah.personalia.MainActivity;
import com.rsah.personalia.Model.ResponseData;
import com.rsah.personalia.Model.ResponseEntityCheckID;
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

import static com.rsah.personalia.Auth.Daftar.TAG_ID;

public class ValidasiID extends AppCompatActivity {


    private ProgressDialog pDialog;

    private Button btn_cari, btn_daftar;
    private EditText username , password ;

    private Context mContext;
    private ApiService API;

    private EditText et_nama , et_ttl , et_alamat ;

    private SessionManager session;

    private CardView cardSelanjutnya;

    public static List<ResponseEntityCheckID> AllEntityCheck = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validasi_id);

        session = new SessionManager(getApplicationContext());

        username = findViewById(R.id.editTextID) ;
        et_nama = findViewById(R.id.editTextNama) ;
        et_ttl = findViewById(R.id.editTextTanggalLahir) ;
        et_alamat = findViewById(R.id.editTextAlamat) ;

        btn_cari = findViewById(R.id.btn_cari);
        btn_daftar = findViewById(R.id.btn_daftar);

        cardSelanjutnya = findViewById(R.id.cvSelanjutnya);

        mContext = this ;
        API = Server.getAPIService();

        pDialog = new ProgressDialog(mContext);
        pDialog.setCancelable(false);
        pDialog.setMessage("Check...");




        btn_cari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = username.getText().toString();
                if (Helper.checkNullInputString(user)){
                    Check(user);
                }else{

                    Toast.makeText(mContext, "Field tidak boleh kosong", Toast.LENGTH_LONG).show();

                }

            }
        });

        btn_daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 Intent intent = new Intent(mContext, Daftar.class);
                 startActivity(intent);
                 Toast.makeText(mContext, "Silahkan Register", Toast.LENGTH_LONG).show();
                 finish();

            }
        });




    }

    private void Check(String username){

        pDialog.show();
        Call<ResponseData> call = API.CheckId(username);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if(response.isSuccessful()) {
                    if (response.body().getDataId() != null) {


                        if(response.body().getDataId().isEmpty()){

                            pDialog.cancel();

                            cardSelanjutnya.setVisibility(View.GONE);
                            Toast.makeText(mContext, "ID Tidak Ditemukan", Toast.LENGTH_LONG).show();

                        }else{
                            pDialog.cancel();

                            AllEntityCheck.addAll(response.body().getDataId()) ;

                            TAG_ID = username ;



                            cardSelanjutnya.setVisibility(View.VISIBLE);

                            String nama = response.body().getDataId().get(0).getsFisrtName() ;
                            String ttl = response.body().getDataId().get(0).getsPlaceOfBirthDay() + ", " +response.body().getDataId().get(0).getdDateOfBirthDay() ;
                            String alamat = response.body().getDataId().get(0).getsAddress() ;


                            et_nama.setText(nama);
                            et_ttl.setText(ttl);
                            et_alamat.setText(alamat);


                            Toast.makeText(mContext, "DATA DITEMUKAN", Toast.LENGTH_LONG).show();


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
