package com.rsah.personalia.Menu.Performance;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
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

import static android.R.layout.simple_spinner_item;
import static com.rsah.personalia.Menu.Performance.PilihPeriodePerformance.PREVIEW_BY_KEPALA_UNIT;
import static com.rsah.personalia.Menu.SlipGaji.slipGaji.TAG_PERIODE;
import static com.rsah.personalia.Menu.TeamSaya.PreviewTeam.TAG_EMP_ID_TEAM;

public class Performance_IT extends AppCompatActivity {

    private Context mContext;
    private ApiService API;
    private SessionManager session;

    private List<ResponseEntityPenilaian> AllEntityPenilaian = new ArrayList<>();

    private EditText skill_1 ;

    private EditText kualitas_1 ;

    private ProgressDialog pDialog;

    private EditText kemampuan_1;

    private EditText kerja_sama_1 ;

    private EditText tanggung_jawab_1 ;

    private EditText kerajinan_1;

    private Spinner sp_skill_1 , sp_kualitas_1 , sp_kemampuan_1 , sp_kerja_sama_1 , sp_tanggung_jawab_1 , sp_kerajinan_1;

    private TextView periode ;

    private Button btn_kesimpulan , btn_ttd , btn_edit ;

    private ArrayList<String> arraySpinner = new ArrayList<String>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_performance_it);


        mContext = this ;
        session = new SessionManager(getApplicationContext());
        API = Server.getAPIService();

        skill_1 = findViewById(R.id.skill_1) ;
        kualitas_1 = findViewById(R.id.kualitas_1) ;
        kemampuan_1 = findViewById(R.id.kemampuan_1) ;
        kerja_sama_1 = findViewById(R.id.kerja_sama_1) ;
        tanggung_jawab_1 = findViewById(R.id.tanggung_jawab_1) ;
        kerajinan_1 = findViewById(R.id.kerajinan_1) ;



        sp_skill_1 = findViewById(R.id.sp_skill_1) ;
        sp_kualitas_1 = findViewById(R.id.sp_kualitas_1) ;
        sp_kemampuan_1 = findViewById(R.id.sp_kemampuan_1) ;
        sp_kerja_sama_1 = findViewById(R.id.sp_kerja_sama_1) ;
        sp_tanggung_jawab_1 = findViewById(R.id.sp_tanggung_jawab_1) ;
        sp_kerajinan_1 = findViewById(R.id.sp_kerajinan_1) ;


        arraySpinner.add("-- Nilai --") ;
        arraySpinner.add("A") ;
        arraySpinner.add("B+") ;
        arraySpinner.add("B") ;
        arraySpinner.add("B-") ;
        arraySpinner.add("C+") ;
        arraySpinner.add("C") ;
        arraySpinner.add("C-") ;
        arraySpinner.add("D") ;
        arraySpinner.add("E") ;

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(mContext, simple_spinner_item,arraySpinner );
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        sp_skill_1.setAdapter(spinnerArrayAdapter);


        spinnerArrayAdapter = new ArrayAdapter<String>(mContext, simple_spinner_item,arraySpinner );
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        sp_kualitas_1.setAdapter(spinnerArrayAdapter);

        spinnerArrayAdapter = new ArrayAdapter<String>(mContext, simple_spinner_item,arraySpinner );
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        sp_kemampuan_1.setAdapter(spinnerArrayAdapter);

        spinnerArrayAdapter = new ArrayAdapter<String>(mContext, simple_spinner_item,arraySpinner );
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        sp_kerja_sama_1.setAdapter(spinnerArrayAdapter);

        spinnerArrayAdapter = new ArrayAdapter<String>(mContext, simple_spinner_item,arraySpinner );
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        sp_tanggung_jawab_1.setAdapter(spinnerArrayAdapter);


        spinnerArrayAdapter = new ArrayAdapter<String>(mContext, simple_spinner_item,arraySpinner );
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        sp_kerajinan_1.setAdapter(spinnerArrayAdapter);



        periode = findViewById(R.id.periode) ;

        btn_kesimpulan = findViewById(R.id.btn_kesimpulan);
        btn_ttd = findViewById(R.id.btn_ttd);
        btn_edit = findViewById(R.id.btn_edit);

        btn_kesimpulan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext,Kesimpulan.class));
            }
        });


        btn_ttd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkSIgnature(TAG_PERIODE , session.getUsername());
            }
        });


        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String skill_1  = sp_skill_1.getSelectedItem().toString();
                String kualitas_1  = sp_kualitas_1.getSelectedItem().toString();
                String kemampuan_1  = sp_kemampuan_1.getSelectedItem().toString();
                String kerja_sama_1  = sp_kerja_sama_1.getSelectedItem().toString();
                String tanggung_jawab_1  = sp_tanggung_jawab_1.getSelectedItem().toString();
                String kerajinan_1  = sp_kerajinan_1.getSelectedItem().toString();


                if (skill_1.equals("-- Nilai --") || kualitas_1.equals("-- Nilai --" ) || kemampuan_1.equals("-- Nilai --" )  || kerja_sama_1.equals("-- Nilai --" )  || tanggung_jawab_1.equals("-- Nilai --" )  || kerajinan_1.equals("-- Nilai --" )          ){

                    Toast.makeText(mContext,"Pilih Nilai",Toast.LENGTH_SHORT).show();
                    return;
                }

                ResponseEntityPenilaian nilai = new ResponseEntityPenilaian();

                nilai.setSkill_1(Helper.nilaiHurufToNumber(skill_1));
                nilai.setSkill_2("");
                nilai.setSkill_3("");
                nilai.setSkill_4("");
                nilai.setSkill_5("");

                nilai.setKualitas_1(Helper.nilaiHurufToNumber(kualitas_1));
                nilai.setKualitas_2("");
                nilai.setKualitas_3("");
                nilai.setKualitas_4("");
                nilai.setKualitas_5("");

                nilai.setKemampuan_1(Helper.nilaiHurufToNumber(kemampuan_1));
                nilai.setKemampuan_2("");
                nilai.setKemampuan_3("");
                nilai.setKemampuan_4("");
                nilai.setKemampuan_5("");

                nilai.setKerja_sama_1(Helper.nilaiHurufToNumber(kerja_sama_1));
                nilai.setKerja_sama_2("");
                nilai.setKerja_sama_3("");
                nilai.setKerja_sama_4("");
                nilai.setKerja_sama_5("");

                nilai.setTanggung_Jawab_1(Helper.nilaiHurufToNumber(tanggung_jawab_1));
                nilai.setTanggung_Jawab_2("");
                nilai.setTanggung_Jawab_3("");
                nilai.setTanggung_Jawab_4("");
                nilai.setTanggung_Jawab_5("");
                nilai.setTanggung_jawab_6("");

                nilai.setKerajinan_1(Helper.nilaiHurufToNumber(kerajinan_1));
                nilai.setKerajinan_2("");
                nilai.setKerajinan_3("");
                nilai.setKerajinan_4("");
                nilai.setKerajinan_5("");

                String empID = TAG_EMP_ID_TEAM;
                String periode = TAG_PERIODE ;


                show_dialog(periode,empID,nilai,mContext);



            }
        });


        periode.setText(TAG_PERIODE);



        String formIT = "1" ;


        String empID = "" ;

        if (PREVIEW_BY_KEPALA_UNIT.equals("KEPUNIT")) {
            btn_ttd.setVisibility(View.GONE);
            btn_edit.setVisibility(View.VISIBLE);

            setEnabledSpinner();

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






                            //Toast.makeText(mContext, "DATA DITEMUKAN", Toast.LENGTH_LONG).show();




                            if (PREVIEW_BY_KEPALA_UNIT.equals("KEPUNIT")) {

                                sp_skill_1.setSelection(arraySpinner.indexOf(Helper.kalkulasi_Nilai(skill_1_)));
                                sp_kualitas_1.setSelection(arraySpinner.indexOf(Helper.kalkulasi_Nilai(kualitas_1_)));
                                sp_kemampuan_1.setSelection(arraySpinner.indexOf(Helper.kalkulasi_Nilai(kemampuan_1_)));
                                sp_kerja_sama_1.setSelection(arraySpinner.indexOf(Helper.kalkulasi_Nilai(kerja_sama_1_)));
                                sp_tanggung_jawab_1.setSelection(arraySpinner.indexOf(Helper.kalkulasi_Nilai(tanggung_jawab_1_)));
                                sp_kerajinan_1.setSelection(arraySpinner.indexOf(Helper.kalkulasi_Nilai(kerajinan_1_)));



                            }else{

                                skill_1.setText(Helper.kalkulasi_Nilai(skill_1_));

                                kualitas_1.setText(Helper.kalkulasi_Nilai(kualitas_1_));

                                kemampuan_1.setText(Helper.kalkulasi_Nilai(kemampuan_1_));

                                kerja_sama_1.setText(Helper.kalkulasi_Nilai(kerja_sama_1_));

                                tanggung_jawab_1.setText(Helper.kalkulasi_Nilai(tanggung_jawab_1_));

                                kerajinan_1.setText(Helper.kalkulasi_Nilai(kerajinan_1_));


                            }


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



    public void editNilai(String periode , String empID, ResponseEntityPenilaian nilai ,  Context context){

        pDialog = new ProgressDialog(context);
        pDialog.setCancelable(false);
        pDialog.setMessage("Memuat...");
        pDialog.show();


        Call<ResponseData> call = API.editPenilaian(periode,empID,
                nilai.getSkill_1(),nilai.getSkill_2(),
                nilai.getSkill_3(),nilai.getSkill_4(),
                nilai.getSkill_5(), nilai.getKualitas_1(),
                nilai.getKualitas_2(),nilai.getKualitas_3(),
                nilai.getKualitas_4(),nilai.getKualitas_5(),
                nilai.getKemampuan_1(),nilai.getKemampuan_2(),
                nilai.getKemampuan_3(),nilai.getKemampuan_4(),
                nilai.getKemampuan_5(),nilai.getKerja_sama_1(),
                nilai.getKerja_sama_2(),nilai.getKerja_sama_3(),
                nilai.getKerja_sama_4(),nilai.getKerja_sama_5(),
                nilai.getTanggung_Jawab_1(),nilai.getTanggung_Jawab_2(),
                nilai.getTanggung_Jawab_3(),nilai.getTanggung_Jawab_4(),
                nilai.getTanggung_Jawab_5(),nilai.getTanggung_jawab_6(),
                nilai.getKerajinan_1(),
                nilai.getKerajinan_2(),nilai.getKerajinan_3(),
                nilai.getKerajinan_4(),nilai.getKerajinan_5()
                 ); //abaikan status
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if(response.isSuccessful()) {

                    pDialog.cancel();
                    Toast.makeText(context, "Success", Toast.LENGTH_LONG).show();

                }else{
                    pDialog.cancel();
                    Toast.makeText(context, "Error Response Data", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {

                pDialog.cancel();

                Toast.makeText(context, "Internal server error / check your connection", Toast.LENGTH_SHORT).show();
                Log.e("Error", "onFailure: "+t.getMessage() );
            }
        });
    }



    private void checkSIgnature (String periode, String empID) {
        Call<ResponseData> call = API.checkSignature(periode, empID);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if (response.isSuccessful()) {
                    if (response.body().getDataSignature() != null) {


                        if (response.body().getDataSignature().isEmpty()) {

                            Toast.makeText(mContext, "Data Tidak Ditemukan", Toast.LENGTH_LONG).show();
                            finish();
                        } else {

                                if (response.body().getDataSignature().get(0).getCallback().equals("F")){

                                    startActivity(new Intent(mContext,signature_employee.class));

                                }else{

                                    Toast.makeText(mContext, "Sudah di Tanda Tangan", Toast.LENGTH_LONG).show();

                                }


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


    public void show_dialog(String periode , String empID, ResponseEntityPenilaian nilai ,  Context context){

        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setMessage("Edit Nilai ?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        editNilai(periode,empID,nilai,mContext);

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



    private void setEnabledSpinner(){

        skill_1.setVisibility(View.GONE);
        kemampuan_1.setVisibility(View.GONE);
        kualitas_1.setVisibility(View.GONE);
        kerajinan_1.setVisibility(View.GONE);
        kerja_sama_1.setVisibility(View.GONE);
        tanggung_jawab_1.setVisibility(View.GONE);

        sp_skill_1.setVisibility(View.VISIBLE);
        sp_kemampuan_1.setVisibility(View.VISIBLE);
        sp_kualitas_1.setVisibility(View.VISIBLE);
        sp_kerajinan_1.setVisibility(View.VISIBLE);
        sp_kerja_sama_1.setVisibility(View.VISIBLE);
        sp_tanggung_jawab_1.setVisibility(View.VISIBLE);

    }


}
