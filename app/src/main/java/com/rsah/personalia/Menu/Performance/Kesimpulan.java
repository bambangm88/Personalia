package com.rsah.personalia.Menu.Performance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

public class Kesimpulan extends AppCompatActivity {


    private Context mContext;
    private ApiService API;
    private SessionManager session;

    private List<ResponseEntityPenilaian> AllEntityPenilaian = new ArrayList<>();

    TextView nilai_1, nilai_2 , nilai_3 , nilai_4 , nilai_5 , nilai_6 , kesimpulan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kesimpulan);


        mContext = this ;
        session = new SessionManager(getApplicationContext());
        API = Server.getAPIService();

        nilai_1 = findViewById(R.id.nilai_1) ;
        nilai_2 = findViewById(R.id.nilai_2) ;
        nilai_3 = findViewById(R.id.nilai_3) ;
        nilai_4 = findViewById(R.id.nilai_4) ;
        nilai_5 = findViewById(R.id.nilai_5) ;
        nilai_6 = findViewById(R.id.nilai_6) ;

        kesimpulan = findViewById(R.id.kesimpulan) ;




        String formIT = "1" ;

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


                            String kualitas_1_ = response.body().getDataPenilaian().get(0).getKualitas_1();


                            String kemampuan_1_ = response.body().getDataPenilaian().get(0).getKemampuan_1();


                            String kerja_sama_1_ = response.body().getDataPenilaian().get(0).getKerja_sama_1();


                            String tanggung_jawab_1_ = response.body().getDataPenilaian().get(0).getTanggung_Jawab_1();



                            String kerajinan_1_ = response.body().getDataPenilaian().get(0).getKerajinan_1();

                            String kesimpulan_ = response.body().getDataPenilaian().get(0).getKesimpulan();



                            nilai_1.setText(Helper.kalkulasi_Nilai(skill_1_));


                            nilai_2.setText(Helper.kalkulasi_Nilai(kualitas_1_));



                            nilai_3.setText(Helper.kalkulasi_Nilai(kemampuan_1_));


                            nilai_4.setText(Helper.kalkulasi_Nilai(kerja_sama_1_));


                            nilai_5.setText(Helper.kalkulasi_Nilai(tanggung_jawab_1_));


                            nilai_6.setText(Helper.kalkulasi_Nilai(kerajinan_1_));

                            kesimpulan.setText(Helper.kalkulasi_Nilai_it(kesimpulan_));


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
