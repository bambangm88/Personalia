package com.rsah.personalia.Menu.TeamSaya.Penilaian;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.rsah.personalia.Helper.Helper;
import com.rsah.personalia.Menu.SlipGaji.PeriodeGajiAdapter;
import com.rsah.personalia.Menu.TeamSaya.Penilaian.IT.Penilaian;
import com.rsah.personalia.Menu.TeamSaya.Penilaian.General.nilai_1;
import com.rsah.personalia.Menu.TeamSaya.Penilaian.KEBIDANAN.nilai_1_bid;
import com.rsah.personalia.Menu.TeamSaya.Penilaian.KEPERAWATAN.nilai_1_pwt;
import com.rsah.personalia.Model.ResponseData;
import com.rsah.personalia.Model.ResponseEntityPeriode;
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
import static com.rsah.personalia.Menu.TeamSaya.Penilaian.signature.TAG_PERIODE;
import static com.rsah.personalia.Menu.TeamSaya.PreviewTeam.TAG_DEPTID_TEAM;
import static com.rsah.personalia.Menu.TeamSaya.PreviewTeam.TAG_EMP_ID_TEAM;

public class PilihPeriodePenilaian extends AppCompatActivity {


    private Spinner spinnerPeriode , spinnerBulan;
    private Context mContext ;

    ProgressDialog pDialog;
    ApiService API;

    private SessionManager sessionManager ;

    private RecyclerView rvPeriode;
    private List<ResponseEntityPeriode> AllPeriodeList = new ArrayList<>();
    private PeriodeGajiAdapter Adapter;

    private ArrayList<String> arrayPeriode = new ArrayList<String>();
    private ArrayList<String> arrayBulan = new ArrayList<String>();
    private Button btnApply ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_periode_penilaian);

        mContext = this ;


        spinnerPeriode = findViewById(R.id.spPeriode);
        spinnerBulan = findViewById(R.id.spBulan);
        btnApply = findViewById(R.id.btn_apply);


        arrayPeriode.add("-- Tahun --") ;
        arrayPeriode.add("2020") ;
        arrayPeriode.add("2021") ;
        arrayPeriode.add("2022") ;
        arrayPeriode.add("2023") ;
        arrayPeriode.add("2024") ;
        arrayPeriode.add("2025") ;
        arrayPeriode.add("2026") ;
        arrayPeriode.add("2027") ;
        arrayPeriode.add("2028") ;
        arrayPeriode.add("2029") ;
        arrayPeriode.add("2030") ;
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(mContext, simple_spinner_item,arrayPeriode );
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spinnerPeriode.setAdapter(spinnerArrayAdapter);



        arrayBulan.add("-- Bulan --") ;
        arrayBulan.add("Januari") ;
        arrayBulan.add("Februari") ;
        arrayBulan.add("Maret") ;
        arrayBulan.add("April") ;
        arrayBulan.add("Mei") ;
        arrayBulan.add("Juni") ;
        arrayBulan.add("Juli") ;
        arrayBulan.add("Agustus") ;
        arrayBulan.add("September") ;
        arrayBulan.add("Oktober") ;
        arrayBulan.add("November") ;
        arrayBulan.add("Desember") ;

        ArrayAdapter<String> spinnerArrayAdapterBulan = new ArrayAdapter<String>(mContext, simple_spinner_item,arrayBulan );
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spinnerBulan.setAdapter(spinnerArrayAdapterBulan);


        mContext = this ;
        API = Server.getAPIService();

        sessionManager = new SessionManager(this);

        pDialog = new ProgressDialog(mContext);
        pDialog.setCancelable(false);
        pDialog.setMessage("Memuat...");




        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tahun = spinnerPeriode.getSelectedItem().toString();
                String bulan = spinnerBulan.getSelectedItem().toString();

                if (tahun.equals("-- Tahun --") || bulan.equals("-- Bulan --")){

                    Toast.makeText(mContext,"Silahkan Pilih",Toast.LENGTH_LONG).show();

                }else{

                    checkPeriodeIsExist(tahun+Helper.convertBulanToNumber(bulan),TAG_EMP_ID_TEAM);

                }


            }
        });



    }


            private void checkPeriodeIsExist(String Periode , String empID){

                pDialog = new ProgressDialog(mContext);
                pDialog.setCancelable(false);
                pDialog.setMessage("Memuat...");
                pDialog.show();


                Call<ResponseData> call = API.checkPenilaian(Periode,
                        empID);
                call.enqueue(new Callback<ResponseData>() {
                    @Override
                    public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                        if(response.isSuccessful()) {

                            if (response.body().getDataPenilaian() != null){


                                if (response.body().getDataPenilaian().get(0).getCallback().equals("0")){
                                    pDialog.cancel();
                                    //it

                                    String jenisForm = sessionManager.getKeyForm();

                                    Log.e("TAG", "jenisForm: "+jenisForm );

                                     // form it
                                    if (jenisForm.equals("1")){
                                            startActivity(new Intent(mContext, Penilaian.class));
                                    } //form perawat
                                    if (jenisForm.equals("2")){
                                        startActivity(new Intent(mContext, nilai_1_pwt.class));
                                    } //form bidan
                                    if (jenisForm.equals("3")){
                                        startActivity(new Intent(mContext, nilai_1_bid.class));
                                    } //form general
                                    if (jenisForm.equals("4")){ //general
                                        startActivity(new Intent(mContext, nilai_1.class));
                                    }

                                    TAG_PERIODE = Periode ;

                                    sessionManager.createPeriodeSession(Periode);

                                }else{

                                    pDialog.cancel();
                                    Toast.makeText(mContext, "Periode Penilaian Telah Dibuat", Toast.LENGTH_LONG).show();

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




    @Override
    protected void onResume() {
        super.onResume();

        Helper.setTeamData(sessionManager);

    }





}
