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
import com.rsah.personalia.Model.ResponseEntityGaji;
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

import static com.rsah.personalia.Menu.SlipGaji.slipGaji.TAG_PERIODE;

public class Performance extends AppCompatActivity {


    private Context mContext;
    private ApiService API;
    private SessionManager session;

    private TextView periode ;

    private List<ResponseEntityPenilaian> AllEntityPenilaian = new ArrayList<>();

    EditText skill_1 , skill_2 , skill_3 , skill_4 , skill_5 ;

    EditText kualitas_1 , kualitas_2 , kualitas_3 , kualitas_4 , kualitas_5 ;

    EditText kemampuan_1 , kemampuan_2 , kemampuan_3 , kemampuan_4 , kemampuan_5 ;

    EditText kerja_sama_1 , kerja_sama_2 , kerja_sama_3 , kerja_sama_4 , kerja_sama_5 ;

    EditText tanggung_jawab_1 , tanggung_jawab_2 , tanggung_jawab_3 , tanggung_jawab_4 , tanggung_jawab_5 ;

    EditText kerajinan_1 , kerajinan_2 , kerajinan_3 , kerajinan_4 , kerajinan_5 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_performance);

        mContext = this ;
        session = new SessionManager(getApplicationContext());
        API = Server.getAPIService();

        skill_1 = findViewById(R.id.skill_1) ;
        skill_2 = findViewById(R.id.skill_2) ;
        skill_3 = findViewById(R.id.skill_3) ;
        skill_4 = findViewById(R.id.skill_4) ;
        skill_5 = findViewById(R.id.skill_5) ;

        kualitas_1 = findViewById(R.id.kualitas_1) ;
        kualitas_2 = findViewById(R.id.kualitas_2) ;
        kualitas_3 = findViewById(R.id.kualitas_3) ;
        kualitas_4 = findViewById(R.id.kualitas_4) ;
        kualitas_5 = findViewById(R.id.kualitas_5) ;

        kemampuan_1 = findViewById(R.id.kemampuan_1) ;
        kemampuan_2 = findViewById(R.id.kemampuan_2) ;
        kemampuan_3 = findViewById(R.id.kemampuan_3) ;
        kemampuan_4 = findViewById(R.id.kemampuan_4) ;
        kemampuan_5 = findViewById(R.id.kemampuan_5) ;

        kerja_sama_1 = findViewById(R.id.kerja_sama_1) ;
        kerja_sama_2 = findViewById(R.id.kerja_sama_2) ;
        kerja_sama_3 = findViewById(R.id.kerja_sama_3) ;
        kerja_sama_4 = findViewById(R.id.kerja_sama_4) ;
        kerja_sama_5 = findViewById(R.id.kerja_sama_5) ;

        tanggung_jawab_1 = findViewById(R.id.tanggung_jawab_1) ;
        tanggung_jawab_2 = findViewById(R.id.tanggung_jawab_2) ;
        tanggung_jawab_3 = findViewById(R.id.tanggung_jawab_3) ;
        tanggung_jawab_4 = findViewById(R.id.tanggung_jawab_4) ;
        tanggung_jawab_5 = findViewById(R.id.tanggung_jawab_5) ;

        kerajinan_1 = findViewById(R.id.kerajinan_1) ;
        kerajinan_2 = findViewById(R.id.kerajinan_2) ;
        kerajinan_3 = findViewById(R.id.kerajinan_3) ;
        kerajinan_4 = findViewById(R.id.kerajinan_4) ;
        kerajinan_5 = findViewById(R.id.kerajinan_5) ;

        periode = findViewById(R.id.periode) ;



        periode.setText(TAG_PERIODE);

        displayPerformance("4",TAG_PERIODE,session.getUsername());

        Helper.countDown(this);





    }



    private void displayPerformance(String request , String periode, String empID) {


        Call<ResponseData> call = API.getpenilaian(request ,periode, empID);
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
                            String skill_4_ = response.body().getDataPenilaian().get(0).getSkill_4();
                            String skill_5_ = response.body().getDataPenilaian().get(0).getSkill_5();

                            String kualitas_1_ = response.body().getDataPenilaian().get(0).getKualitas_1();
                            String kualitas_2_ = response.body().getDataPenilaian().get(0).getKualitas_2();
                            String kualitas_3_ = response.body().getDataPenilaian().get(0).getKualitas_3();
                            String kualitas_4_ = response.body().getDataPenilaian().get(0).getKualitas_4();
                            String kualitas_5_ = response.body().getDataPenilaian().get(0).getKualitas_5();

                            String kemampuan_1_ = response.body().getDataPenilaian().get(0).getKemampuan_1();
                            String Kemampuan_2_ = response.body().getDataPenilaian().get(0).getKemampuan_2();
                            String Kemampuan_3_ = response.body().getDataPenilaian().get(0).getKemampuan_3();
                            String Kemampuan_4_ = response.body().getDataPenilaian().get(0).getKemampuan_4();
                            String Kemampuan_5_ = response.body().getDataPenilaian().get(0).getKemampuan_5();

                            String kerja_sama_1_ = response.body().getDataPenilaian().get(0).getKerja_sama_1();
                            String kerja_sama_2_ = response.body().getDataPenilaian().get(0).getKerja_sama_2();
                            String kerja_sama_3_ = response.body().getDataPenilaian().get(0).getKerja_sama_3();
                            String kerja_sama_4_ = response.body().getDataPenilaian().get(0).getKerja_sama_4();
                            String kerja_sama_5_ = response.body().getDataPenilaian().get(0).getKerja_sama_5();

                            String tanggung_jawab_1_ = response.body().getDataPenilaian().get(0).getTanggung_Jawab_1();
                            String tanggung_jawab_2_ = response.body().getDataPenilaian().get(0).getTanggung_Jawab_2();
                            String tanggung_jawab_3_ = response.body().getDataPenilaian().get(0).getTanggung_Jawab_3();
                            String tanggung_jawab_4_ = response.body().getDataPenilaian().get(0).getTanggung_Jawab_4();
                            String tanggung_jawab_5_ = response.body().getDataPenilaian().get(0).getTanggung_Jawab_5();


                            String kerajinan_1_ = response.body().getDataPenilaian().get(0).getKerajinan_1();
                            String kerajinan_2_ = response.body().getDataPenilaian().get(0).getKerajinan_2();
                            String kerajinan_3_ = response.body().getDataPenilaian().get(0).getKerajinan_3();
                            String kerajinan_4_ = response.body().getDataPenilaian().get(0).getKerajinan_4();
                            String kerajinan_5_ = response.body().getDataPenilaian().get(0).getKerajinan_5();


                            skill_1.setText(skill_1_);
                            skill_2.setText(skill_2_);
                            skill_3.setText(skill_3_);
                            skill_4.setText(skill_4_);
                            skill_5.setText(skill_5_);



                            kualitas_1.setText(kualitas_1_);
                            kualitas_2.setText(kualitas_2_);
                            kualitas_3.setText(kualitas_3_);
                            kualitas_4.setText(kualitas_4_);
                            kualitas_5.setText(kualitas_5_);


                            kemampuan_1.setText(kemampuan_1_);
                            kemampuan_2.setText(Kemampuan_2_);
                            kemampuan_3.setText(Kemampuan_3_);
                            kemampuan_4.setText(Kemampuan_4_);
                            kemampuan_5.setText(Kemampuan_5_);


                            kerja_sama_1.setText(kerja_sama_1_);
                            kerja_sama_2.setText(kerja_sama_2_);
                            kerja_sama_3.setText(kerja_sama_3_);
                            kerja_sama_4.setText(kerja_sama_4_);
                            kerja_sama_5.setText(kerja_sama_5_);

                            tanggung_jawab_1.setText(tanggung_jawab_1_);
                            tanggung_jawab_2.setText(tanggung_jawab_2_);
                            tanggung_jawab_3.setText(tanggung_jawab_3_);
                            tanggung_jawab_4.setText(tanggung_jawab_4_);
                            tanggung_jawab_5.setText(tanggung_jawab_5_);


                            kerajinan_1.setText(kerajinan_1_);
                            kerajinan_2.setText(kerajinan_2_);
                            kerajinan_3.setText(kerajinan_3_);
                            kerajinan_4.setText(kerajinan_4_);
                            kerajinan_5.setText(kerajinan_5_);

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
