package com.rsah.personalia.Menu.TeamSaya;


import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.rsah.personalia.Helper.Helper;
import com.rsah.personalia.Menu.Performance.PilihPeriodePerformance;
import com.rsah.personalia.Menu.TeamSaya.Penilaian.PilihPeriodePenilaian;
import com.rsah.personalia.R;
import com.rsah.personalia.api.ApiService;
import com.rsah.personalia.api.Server;
import com.rsah.personalia.sessionManager.SessionManager;

import java.text.SimpleDateFormat;

import static com.rsah.personalia.Menu.Performance.PilihPeriodePerformance.PREVIEW_BY_KEPALA_UNIT;

public class PreviewTeam extends AppCompatActivity {

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private TimePickerDialog mTimePicker;


    ProgressDialog pDialog;
    ApiService API;
    private Spinner spinnerMonitor;
    private SessionManager sessionManager ;

    private Context mContext;
    private ImageView imageView;

    private TextView tv_nama , tv_jabatan , tv_unit , tv_bidang ;

    public static String TAG_NAMA_ = "" ;
    public static String TAG_JABATAN = "" ;
    public static String TAG_IMAGE_STRING = "" ;
    public static String TAG_UNIT = "" ;
    public static String TAG_BIDANG = "" ;
    public static String TAG_EMP_ID_TEAM = "" ;
    public static String TAG_DEPTID_TEAM = "" ;


    private Button btn_buat_penilaian , btn_lihat_penilaian ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_team);

        mContext = this ;
        API = Server.getAPIService();

        tv_nama = findViewById(R.id.tvNama);
        tv_jabatan = findViewById(R.id.tvJabatan);
        tv_unit= findViewById(R.id.tvUnit);
        tv_bidang = findViewById(R.id.tvBidang);
        imageView = findViewById(R.id.profileTeam);
        btn_buat_penilaian= findViewById(R.id.btn_beri_penilaian);
        btn_lihat_penilaian= findViewById(R.id.btn_lihat_penilaian);

        sessionManager = new SessionManager(this);


        pDialog = new ProgressDialog(PreviewTeam.this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Memuat...");





        tv_nama.setText(TAG_NAMA_);
        tv_bidang.setText(TAG_BIDANG);
        tv_jabatan.setText(TAG_JABATAN);
        tv_unit.setText(TAG_UNIT);


        String url = mContext.getString(R.string.hostImage) + TAG_IMAGE_STRING;

        Glide.with(mContext).load(url).into(imageView);


        btn_buat_penilaian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //it
                //if (TAG_DEPTID_TEAM.equals("18")){
                   // startActivity(new Intent(mContext, Penilaian.class));
                //}

                //kebidanan //keperawatan
               //if (TAG_DEPTID_TEAM.equals("20") || TAG_DEPTID_TEAM.equals("21")){
                   // startActivity(new Intent(mContext, nilai_1.class));
                //}

                startActivity(new Intent(mContext, PilihPeriodePenilaian.class));


            }
        });


        btn_lihat_penilaian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //it
                //if (TAG_DEPTID_TEAM.equals("18")){
                // startActivity(new Intent(mContext, Penilaian.class));
                //}

                //kebidanan //keperawatan
                //if (TAG_DEPTID_TEAM.equals("20") || TAG_DEPTID_TEAM.equals("21")){
                // startActivity(new Intent(mContext, nilai_1.class));
                //}

                PREVIEW_BY_KEPALA_UNIT = "KEPUNIT" ;
                startActivity(new Intent(mContext, PilihPeriodePerformance.class));


            }
        });





    }


    @Override
    public void onBackPressed() {
        PREVIEW_BY_KEPALA_UNIT = "" ;
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();

        Helper.setTeamData(sessionManager);

    }
}
