package com.rsah.personalia.Menu.SlipGaji;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.rsah.personalia.Helper.Helper;
import com.rsah.personalia.MainActivity;
import com.rsah.personalia.Menu.TeamSaya.Team;
import com.rsah.personalia.Menu.TeamSaya.TeamAdapter;
import com.rsah.personalia.Model.ResponseData;
import com.rsah.personalia.Model.ResponseEntityPeriode;
import com.rsah.personalia.Model.ResponseEntityTeam;
import com.rsah.personalia.R;
import com.rsah.personalia.api.ApiService;
import com.rsah.personalia.api.Server;
import com.rsah.personalia.sessionManager.SessionManager;

import static android.R.layout.simple_spinner_item;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PilihPeriodeGaji extends AppCompatActivity {


    private Spinner spinnerPeriode;
    private Context mContext ;

    ProgressDialog pDialog;
    ApiService API;

    private SessionManager sessionManager ;

    private RecyclerView rvPeriode;
    private List<ResponseEntityPeriode> AllPeriodeList = new ArrayList<>();
    private PeriodeGajiAdapter Adapter;

    private ArrayList<String> arrayPeriode = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_periode_gaji);

        mContext = this ;


        spinnerPeriode = findViewById(R.id.spPeriode);





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


        mContext = this ;
        API = Server.getAPIService();

        sessionManager = new SessionManager(this);

        rvPeriode = findViewById(R.id.rvPeriode);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        rvPeriode.setLayoutManager(mLayoutManager);
        rvPeriode.setItemAnimator(new DefaultItemAnimator());

        Adapter = new PeriodeGajiAdapter(this, AllPeriodeList);

        pDialog = new ProgressDialog(mContext);
        pDialog.setCancelable(false);
        pDialog.setMessage("Memuat...");

        Helper.countDown(this);


        spinnerPeriode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                String tahun = spinnerPeriode.getSelectedItem().toString();

                if(tahun.equals("-- Tahun --") ) {
                    Toast.makeText(mContext, "Silahkan Pilih Tahun", Toast.LENGTH_SHORT).show();

                }
                else {


                    getPeriode(tahun) ;

                }

            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });





    }


    private void getPeriode(String Tahun){

        AllPeriodeList.clear();

        pDialog.show();


        Call<ResponseData> call = API.getperiode(Tahun);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if(response.isSuccessful()) {
                    if (response.body().getDataPeriode()!= null) {


                        if(!response.body().getDataPeriode().isEmpty()){

                            pDialog.cancel();
                            AllPeriodeList.addAll(response.body().getDataPeriode());
                            rvPeriode.setAdapter(new PeriodeGajiAdapter(mContext, AllPeriodeList));
                            Adapter.notifyDataSetChanged();
                            Toast.makeText(mContext, "Periode Ditemukan", Toast.LENGTH_LONG).show();


                        }else{

                            pDialog.cancel();
                            Toast.makeText(mContext, "Periode Tidak Ditemukan", Toast.LENGTH_LONG).show();

                        }


                    }else{
                        pDialog.cancel();
                        finish();
                        Toast.makeText(mContext, "Error Response Data", Toast.LENGTH_LONG).show();
                    }

                }else{
                    pDialog.cancel();
                    finish();
                    Toast.makeText(mContext, "Error Response Data", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {

                pDialog.cancel();

                Toast.makeText(mContext, "Internal server error / check your connection", Toast.LENGTH_SHORT).show();
                finish();
                Log.e("Error", "onFailure: "+t.getMessage() );
            }
        });
    }









}
