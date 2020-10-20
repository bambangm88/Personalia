package com.rsah.personalia.Menu.SlipGaji;

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

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rsah.personalia.Helper.Helper;
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
import static com.rsah.personalia.Menu.SlipGaji.slipGaji.TAG_PERIODE;
import static com.rsah.personalia.Menu.SlipGaji.slipGajiTHR.TAG_PERIODE_THR;

public class PilihPeriodeInsentifTHR extends AppCompatActivity {


    private Spinner spinnerPeriode;
    private Context mContext ;


    ApiService API;

    private SessionManager sessionManager ;


    private ArrayList<String> arrayPeriode = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_periode_thr);




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


        mContext = this ;
        API = Server.getAPIService();

        sessionManager = new SessionManager(this);

        Helper.countDown(mContext);


        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(mContext, simple_spinner_item,arrayPeriode );
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spinnerPeriode.setAdapter(spinnerArrayAdapter);



        spinnerPeriode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                String tahun = spinnerPeriode.getSelectedItem().toString();

                if(tahun.equals("-- Tahun --") ) {
                    Toast.makeText(mContext, "Silahkan Pilih Tahun", Toast.LENGTH_SHORT).show();

                }
                else {

                    TAG_PERIODE_THR  = spinnerPeriode.getSelectedItem().toString() ;
                    mContext.startActivity(new Intent(mContext,slipGajiTHR.class));

                }

            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });





    }












}
