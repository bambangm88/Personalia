package com.rsah.personalia.Menu.Performance;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.rsah.personalia.Helper.Helper;
import com.rsah.personalia.Model.ResponseData;
import com.rsah.personalia.Model.ResponseEntityPenilaian;
import com.rsah.personalia.R;
import com.rsah.personalia.api.ApiService;
import com.rsah.personalia.api.Server;
import com.rsah.personalia.sessionManager.SessionManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.rsah.personalia.Menu.Performance.PilihPeriodePerformance.PREVIEW_BY_KEPALA_UNIT;
import static com.rsah.personalia.Menu.SlipGaji.slipGaji.TAG_PERIODE;
import static com.rsah.personalia.Menu.TeamSaya.PreviewTeam.TAG_EMP_ID_TEAM;

public class KesimpulanBidanPerawat extends AppCompatActivity {


    private Context mContext;
    private ApiService API;
    private SessionManager session;

    private List<ResponseEntityPenilaian> AllEntityPenilaian = new ArrayList<>();

    TextView nilai_1_1, nilai_1_2 , nilai_1_3 ;
    TextView nilai_2_1, nilai_2_2 ;
    TextView nilai_3_1, nilai_3_2 ;
    TextView nilai_4_1, nilai_4_2 , nilai_4_3 ;
    TextView nilai_5_1, nilai_5_2 , nilai_5_3  , nilai_5_4, nilai_5_5 , nilai_5_6 ;
    TextView nilai_6_1, nilai_6_2 ;
    TextView kesimpulan ;

    public static String TAG_FORM ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kesimpulan_bidan);


        mContext = this ;
        session = new SessionManager(getApplicationContext());
        API = Server.getAPIService();

        nilai_1_1 = findViewById(R.id.nilai_1_1) ;
        nilai_1_2 = findViewById(R.id.nilai_1_2) ;
        nilai_1_3 = findViewById(R.id.nilai_1_3) ;
                
        nilai_2_1 = findViewById(R.id.nilai_2_1) ;
        nilai_2_2 = findViewById(R.id.nilai_2_2) ;
              
        nilai_3_1 = findViewById(R.id.nilai_3_1) ;
        nilai_3_2 = findViewById(R.id.nilai_3_2) ;
        
        nilai_4_1 = findViewById(R.id.nilai_4_1) ;
        nilai_4_2 = findViewById(R.id.nilai_4_2) ;
        nilai_4_3 = findViewById(R.id.nilai_4_3) ;
        
        nilai_5_1 = findViewById(R.id.nilai_5_1) ;
        nilai_5_2 = findViewById(R.id.nilai_5_2) ;
        nilai_5_3 = findViewById(R.id.nilai_5_3) ;
        nilai_5_4 = findViewById(R.id.nilai_5_4) ;
        nilai_5_5 = findViewById(R.id.nilai_5_5) ;
        nilai_5_6 = findViewById(R.id.nilai_5_6) ;
        
        nilai_6_1 = findViewById(R.id.nilai_6_1) ;
        nilai_6_2 = findViewById(R.id.nilai_6_2) ;

        kesimpulan = findViewById(R.id.kesimpulan) ;




        String formIT = TAG_FORM ;


        String empID = "" ;

        if (PREVIEW_BY_KEPALA_UNIT.equals("KEPUNIT")) {
            empID = TAG_EMP_ID_TEAM;

        }else {

            empID = session.getUsername();

        }





        displayPerformance(formIT,TAG_PERIODE,empID);


        Helper.countDown(this);


    }



    private void displayPerformance(String request,String periode, String empID) {


        Call<ResponseData> call = API.getpenilaian(request,periode, empID);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if (response.isSuccessful()) {
                    if (response.body().getDataPenilaian() != null) {


                        if (response.body().getDataPenilaian().isEmpty()) {

                            Toast.makeText(mContext, "Data Tidak Ditemukan", Toast.LENGTH_LONG).show();
                            finish();
                        } else {

                            AllEntityPenilaian.addAll(response.body().getDataPenilaian());

                            String skill_1_ = response.body().getDataPenilaian().get(0).getSkill_1();
                            String skill_2_ = response.body().getDataPenilaian().get(0).getSkill_2();
                            String skill_3_ = response.body().getDataPenilaian().get(0).getSkill_3();


                            String kualitas_1_ = response.body().getDataPenilaian().get(0).getKualitas_1();
                            String kualitas_2_ = response.body().getDataPenilaian().get(0).getKualitas_3();


                            String kemampuan_1_ = response.body().getDataPenilaian().get(0).getKemampuan_1();
                            String kemampuan_2_ = response.body().getDataPenilaian().get(0).getKemampuan_2();


                            String kerja_sama_1_ = response.body().getDataPenilaian().get(0).getKerja_sama_1();
                            String kerja_sama_2_ = response.body().getDataPenilaian().get(0).getKerja_sama_2();
                            String kerja_sama_3_ = response.body().getDataPenilaian().get(0).getKerja_sama_3();


                            String tanggung_jawab_1_ = response.body().getDataPenilaian().get(0).getTanggung_Jawab_1();
                            String tanggung_jawab_2_ = response.body().getDataPenilaian().get(0).getTanggung_Jawab_2();
                            String tanggung_jawab_3_ = response.body().getDataPenilaian().get(0).getTanggung_Jawab_3();
                            String tanggung_jawab_4_ = response.body().getDataPenilaian().get(0).getTanggung_Jawab_4();
                            String tanggung_jawab_5_ = response.body().getDataPenilaian().get(0).getTanggung_Jawab_5();
                            String tanggung_jawab_6_ = response.body().getDataPenilaian().get(0).getTanggung_jawab_6();


                            String kerajinan_1_ = response.body().getDataPenilaian().get(0).getKerajinan_1();
                            String kerajinan_2_ = response.body().getDataPenilaian().get(0).getKerajinan_2();

                            String kesimpulan_ = response.body().getDataPenilaian().get(0).getKesimpulan();



                            nilai_1_1.setText(Helper.kalkulasi_Nilai_bidan(skill_1_));
                            nilai_1_2.setText(Helper.kalkulasi_Nilai_bidan(skill_2_));
                            nilai_1_3.setText(Helper.kalkulasi_Nilai_bidan(skill_3_));


                            nilai_2_1.setText(Helper.kalkulasi_Nilai_bidan(kualitas_1_));
                            nilai_2_2.setText(Helper.kalkulasi_Nilai_bidan(kualitas_2_));



                            nilai_3_1.setText(Helper.kalkulasi_Nilai_bidan(kemampuan_1_));
                            nilai_3_2.setText(Helper.kalkulasi_Nilai_bidan(kemampuan_2_));


                            nilai_4_1.setText(Helper.kalkulasi_Nilai_bidan(kerja_sama_1_));
                            nilai_4_2.setText(Helper.kalkulasi_Nilai_bidan(kerja_sama_2_));
                            nilai_4_3.setText(Helper.kalkulasi_Nilai_bidan(kerja_sama_3_));


                            nilai_5_1.setText(Helper.kalkulasi_Nilai_bidan(tanggung_jawab_1_));
                            nilai_5_2.setText(Helper.kalkulasi_Nilai_bidan(tanggung_jawab_2_));
                            nilai_5_3.setText(Helper.kalkulasi_Nilai_bidan(tanggung_jawab_3_));
                            nilai_5_4.setText(Helper.kalkulasi_Nilai_bidan(tanggung_jawab_4_));
                            nilai_5_5.setText(Helper.kalkulasi_Nilai_bidan(tanggung_jawab_5_));
                            nilai_5_6.setText(Helper.kalkulasi_Nilai_bidan(tanggung_jawab_6_));


                            nilai_6_1.setText(Helper.kalkulasi_Nilai_bidan(kerajinan_1_));
                            nilai_6_2.setText(Helper.kalkulasi_Nilai_bidan(kerajinan_2_));

                            kesimpulan.setText(Helper.kalkulasi_kesimpulan_Nilai_bidan(kesimpulan_));


                            //Toast.makeText(mContext, "DATA DITEMUKAN", Toast.LENGTH_LONG).show();


                        }


                    } else {

                        Toast.makeText(mContext, "Error Response Data", Toast.LENGTH_LONG).show();
                        finish();
                    }

                } else {
                    Toast.makeText(mContext, "Error Response Data", Toast.LENGTH_LONG).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                Toast.makeText(mContext, "Internal server error / check your connection", Toast.LENGTH_SHORT).show();
                Log.e("Error", "onFailure: " + t.getMessage());
                finish();
            }
        });

    }



}
