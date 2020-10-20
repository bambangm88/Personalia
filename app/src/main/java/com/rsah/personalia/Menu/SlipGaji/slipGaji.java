package com.rsah.personalia.Menu.SlipGaji;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.rsah.personalia.Auth.Login;
import com.rsah.personalia.Helper.Helper;
import com.rsah.personalia.Model.ResponseData;
import com.rsah.personalia.Model.ResponseEntityGaji;
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

public class slipGaji extends AppCompatActivity {


    private Context mContext;
    private ApiService API;
    private SessionManager session;
    private TextView  nama , nik , jabatan , bagian , tmk , jml_upah;
    private TextView  direktur , manajer , kepala_smf, kabag , spj  ;
    private TextView  tetap , unit , uang_makan , transport , overtime , lain_lain , koperasi ;
    private TextView  bank_bjb , bpjstk , bpjskes , perawatan , obat , potongan_lain_lain , pph21 , bankTrf ;
    private Button btnSlipGaji ;
    public static String TAG_PERIODE = "" ;


    private TextView  total_a , total_b, total_all ;

    private List<ResponseEntityGaji> AllEntityGaji = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slip_gaji);

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

        direktur = findViewById(R.id.direktur);
        manajer = findViewById(R.id.manajer);
        kepala_smf = findViewById(R.id.kepala_smf);
        kabag = findViewById(R.id.kabag);
        spj = findViewById(R.id.spj);
        bankTrf = findViewById(R.id.trf);
        koperasi = findViewById(R.id.koperasi);


        tetap = findViewById(R.id.tetap);
        unit = findViewById(R.id.unit);
        uang_makan = findViewById(R.id.uang_makan);
        transport = findViewById(R.id.transport);
        overtime = findViewById(R.id.overtime);
        lain_lain = findViewById(R.id.lain_lain);

        bank_bjb= findViewById(R.id.bank_bjb);
        bpjstk= findViewById(R.id.bpjstk);
        bpjskes = findViewById(R.id.bpjskes);
        perawatan = findViewById(R.id.perawatan);
        obat = findViewById(R.id.obat);
        potongan_lain_lain = findViewById(R.id.potongan_lain_lain);
        pph21 = findViewById(R.id.pph21);

        total_a = findViewById(R.id.total_a);
        total_b = findViewById(R.id.total_b);
        total_all = findViewById(R.id.totalAll);

        Helper.countDown(this);

        slipGaji(session.getUsername(),TAG_PERIODE) ;



        btnSlipGaji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                download_slip_gaji(session.getUsername(),TAG_PERIODE);
            }
        });


    }

    private void slipGaji(String user, String tahun){


        Call<ResponseData> call = API.getGaji(user, tahun);
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
                            String jml_upah_ = Helper.checkNull(response.body().getDataGaji().get(0).getGaji_Pokok());

                            String direktur_ = Helper.checkNull(response.body().getDataGaji().get(0).getDirektur());
                            String manajer_ = Helper.checkNull(response.body().getDataGaji().get(0).getManajer());
                            String kepala_smf_ = Helper.checkNull(response.body().getDataGaji().get(0).getKepala_smf());
                            String kabag_ = Helper.checkNull(response.body().getDataGaji().get(0).getKabag());
                            String spj_ = Helper.checkNull(response.body().getDataGaji().get(0).getSpj());

                            String tetap_ = Helper.checkNull(response.body().getDataGaji().get(0).getTetap());
                            String unit_ = Helper.checkNull(response.body().getDataGaji().get(0).getUnit());
                            String uang_makan_ = Helper.checkNull(response.body().getDataGaji().get(0).getUang_makan());
                            String transport_ = Helper.checkNull(response.body().getDataGaji().get(0).getTransport());
                            String overtime_ = Helper.checkNull(response.body().getDataGaji().get(0).getOvertime());
                            String lain_lain_ = Helper.checkNull(response.body().getDataGaji().get(0).getLain_lain());

                            String bank_bjb_ = Helper.checkNull(response.body().getDataGaji().get(0).getBank_bjb());
                            String bpjstk_ = Helper.checkNull(response.body().getDataGaji().get(0).getBpjstk());
                            String bpjskes_ = Helper.checkNull(response.body().getDataGaji().get(0).getBpjs_kes());
                            String perawatan_ = Helper.checkNull(response.body().getDataGaji().get(0).getPerawatan());
                            String obat_ = Helper.checkNull(response.body().getDataGaji().get(0).getObat());
                            String pot_lain_lain_ = Helper.checkNull(response.body().getDataGaji().get(0).getPotongan_lain_lain());
                            String trf_ = "MASPION "+response.body().getDataGaji().get(0).getTransfer_maspion();
                            String koperasi_ = Helper.checkNull(response.body().getDataGaji().get(0).getKoperasi());


                            String pph21_ = Helper.checkNull(response.body().getDataGaji().get(0).getPph21());


                            String total_a_ = Helper.checkNull(response.body().getTotal_a());

                            String total_b_ = Helper.checkNull(response.body().getTotal_b());

                            String totalAll_ = Helper.checkNull(response.body().getTotalAll());


                            nama.setText(nama_);
                            nik.setText(nik_);
                            jabatan.setText(jabatan_);
                            bagian.setText(bagian_);
                            tmk.setText(tmk_);
                            jml_upah.setText(jml_upah_);
                            bankTrf.setText(trf_);
                            koperasi.setText(koperasi_);

                            direktur.setText(direktur_);
                            manajer.setText(manajer_);
                            kepala_smf.setText(kepala_smf_);
                            kabag.setText(kabag_);
                            spj.setText(spj_);

                            tetap.setText(tetap_);
                            unit.setText(unit_);
                            uang_makan.setText(uang_makan_);
                            transport.setText(transport_);
                            overtime.setText(overtime_);
                            lain_lain.setText(lain_lain_);

                            bank_bjb.setText(bank_bjb_);
                            bpjstk.setText(bpjstk_);
                            bpjskes.setText(bpjskes_);
                            perawatan.setText(perawatan_);
                            obat.setText(obat_);
                            potongan_lain_lain.setText(pot_lain_lain_);
                            pph21.setText(pph21_);

                            total_a.setText(total_a_);
                            total_b.setText(total_b_);
                            total_all.setText(totalAll_);








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

        Call<ResponseData> call = API.downloadGaji(user,tahun);
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
