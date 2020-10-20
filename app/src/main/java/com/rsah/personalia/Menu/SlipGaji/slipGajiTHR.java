package com.rsah.personalia.Menu.SlipGaji;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.rsah.personalia.Helper.Helper;
import com.rsah.personalia.Model.ResponseData;
import com.rsah.personalia.Model.ResponseEntityGaji;
import com.rsah.personalia.R;
import com.rsah.personalia.api.ApiService;
import com.rsah.personalia.api.Server;
import com.rsah.personalia.sessionManager.SessionManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class slipGajiTHR extends AppCompatActivity {


    private Context mContext;
    private ApiService API;
    private SessionManager session;
    private TextView  nama , nik , jabatan , bagian , tmk , jml_upah;
    private TextView  pph21 , maspion ;
    private Button btnSlipGaji ;
    public static String TAG_PERIODE_THR = "" ;


    private TextView  total_a , total_b, total_all ;

    private List<ResponseEntityGaji> AllEntityGaji = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slip_gaji_thr);

        mContext = this ;
        session = new SessionManager(getApplicationContext());
        API = Server.getAPIService();

        btnSlipGaji = findViewById(R.id.btn_slip_gaji);

        nama = findViewById(R.id.nama);
        nik = findViewById(R.id.nik);
        bagian = findViewById(R.id.bagian);
        tmk = findViewById(R.id.tmk);
        jabatan = findViewById(R.id.jabatan);
        jml_upah = findViewById(R.id.jml_upah);
        pph21 = findViewById(R.id.pph21);
        maspion = findViewById(R.id.trf);

        total_a = findViewById(R.id.total_a);
        total_b = findViewById(R.id.total_b);
        total_all = findViewById(R.id.totalAll);

        Helper.countDown(this);

        slipGaji(session.getUsername(),TAG_PERIODE_THR) ;



        btnSlipGaji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download_slip_gaji(session.getUsername(),TAG_PERIODE_THR);
            }
        });


    }

    private void slipGaji(String user, String tahun){


        Call<ResponseData> call = API.getGajiThr(user, tahun);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if(response.isSuccessful()) {
                    if (response.body().getDataGaji() != null) {


                        if(response.body().getDataGaji().isEmpty()){

                            Toast.makeText(mContext, "Data Tidak Ditemukan", Toast.LENGTH_LONG).show();
                            finish();
                        }else{

                            AllEntityGaji.addAll(response.body().getDataGaji()) ;

                            String nama_ = response.body().getDataGaji().get(0).getNama();
                            String nik_ = response.body().getDataGaji().get(0).getNik();
                            String jabatan_ = response.body().getDataGaji().get(0).getJabatan();
                            String bagian_ = response.body().getDataGaji().get(0).getBagian();
                            String tmk_ = response.body().getTmk();
                            String jml_upah_ = Helper.checkNull(response.body().getDataGaji().get(0).getJumlah_thr());
                            String maspion_ = response.body().getDataGaji().get(0).getTransfer_maspion();

                            String pph21_ = Helper.checkNull(response.body().getDataGaji().get(0).getPph21());

                            String totalAll_ = Helper.checkNull(response.body().getTotalAll());


                            nama.setText(nama_);
                            nik.setText(nik_);
                            jabatan.setText(jabatan_);
                            bagian.setText(bagian_);
                            tmk.setText(tmk_);
                            jml_upah.setText(jml_upah_);
                            maspion.setText(maspion_);

                            Log.v("TAG","message") ;


                            pph21.setText(pph21_);

                            total_all.setText(totalAll_);
                            total_b.setText(pph21_);
                            total_a.setText(jml_upah_);


                            //Toast.makeText(mContext, "DATA DITEMUKAN", Toast.LENGTH_LONG).show();


                        }



                    }else{

                        Toast.makeText(mContext, "Error Response Data", Toast.LENGTH_LONG).show();
                        finish();
                    }

                }else{
                    Toast.makeText(mContext, "Error Response Data", Toast.LENGTH_LONG).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                Toast.makeText(mContext, "Internal server error / check your connection", Toast.LENGTH_SHORT).show();
                Log.e("Error", "onFailure: "+t.getMessage() );
                finish();
            }
        });
    }





    private void download_slip_gaji(String user,String tahun){

        ProgressDialog pDialog;
        pDialog = new ProgressDialog(mContext);
        pDialog.setCancelable(false);
        pDialog.setMessage("Sending to mail...");
        pDialog.show();

        Call<ResponseData> call = API.downloadGajiThr(user,tahun);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if(response.isSuccessful()) {
                    if (response.body().getSuccess() != null) {


                        if(response.body().getSuccess().equals("1")){

                            pDialog.cancel();

                             Toast.makeText(mContext, "Berhasil, Silahkan check email anda", Toast.LENGTH_LONG).show();

                        }else{
                            pDialog.cancel();


                            Toast.makeText(mContext, "Gagal, Server Error", Toast.LENGTH_LONG).show();


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
