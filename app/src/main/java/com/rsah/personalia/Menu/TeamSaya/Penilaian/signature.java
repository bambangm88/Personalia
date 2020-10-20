package com.rsah.personalia.Menu.TeamSaya.Penilaian;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rsah.personalia.Helper.Helper;
import com.rsah.personalia.Menu.TeamSaya.Penilaian.IT.paint.PaintView;
import com.rsah.personalia.Model.ResponseData;
import com.rsah.personalia.Model.ResponseEntityPenilaian;
import com.rsah.personalia.R;
import com.rsah.personalia.api.ApiService;
import com.rsah.personalia.api.Server;
import com.rsah.personalia.sessionManager.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.rsah.personalia.Menu.TeamSaya.PreviewTeam.TAG_EMP_ID_TEAM;


public class signature extends AppCompatActivity {

    private PaintView paintView;
    private int defaultColor;
    private int STORAGE_PERMISSION_CODE = 1;

    public static String TAG_TTD_STRING = "";
    public static String TAG_SELESAI_NILAI = "";

    ProgressDialog pDialog;

    private FloatingActionButton fab_main,  fab_undo , fab_redo;
    private Animation fab_open, fab_close, fab_clock, fab_anticlock;
    TextView textview_save, textview_undo , textview_redo;

    public static String TAG_SKILL_1 = "" ;
    public static String TAG_SKILL_2 = "" ;
    public static String TAG_SKILL_3 = "" ;
    public static String TAG_SKILL_4 = "" ;
    public static String TAG_SKILL_5 = "" ;

    public static String TAG_KUALITAS_1 = "" ;
    public static String TAG_KUALITAS_2 = "" ;
    public static String TAG_KUALITAS_3 = "" ;
    public static String TAG_KUALITAS_4 = "" ;
    public static String TAG_KUALITAS_5 = "" ;

    public static String TAG_KEMAMPUAN_1 = "" ;
    public static String TAG_KEMAMPUAN_2 = "" ;
    public static String TAG_KEMAMPUAN_3 = "" ;
    public static String TAG_KEMAMPUAN_4 = "" ;
    public static String TAG_KEMAMPUAN_5 = "" ;

    public static String TAG_KERJA_SAMA_1 = "" ;
    public static String TAG_KERJA_SAMA_2 = "" ;
    public static String TAG_KERJA_SAMA_3 = "" ;
    public static String TAG_KERJA_SAMA_4 = "" ;
    public static String TAG_KERJA_SAMA_5 = "" ;

    public static String TAG_TANGGUNG_JAWAB_1 = "" ;
    public static String TAG_TANGGUNG_JAWAB_2 = "" ;
    public static String TAG_TANGGUNG_JAWAB_3 = "" ;
    public static String TAG_TANGGUNG_JAWAB_4 = "" ;
    public static String TAG_TANGGUNG_JAWAB_5 = "" ;
    public static String TAG_TANGGUNG_JAWAB_6 = "" ;

    public static String TAG_KERAJINAN_1 = "" ;
    public static String TAG_KERAJINAN_2 = "" ;
    public static String TAG_KERAJINAN_3 = "" ;
    public static String TAG_KERAJINAN_4 = "" ;
    public static String TAG_KERAJINAN_5 = "" ;

    public static String TAG_PERIODE = "" ;

    public static String TAG_TTD = "" ;

    Button btn_simpan ;

    Boolean isOpen = false;

    ApiService API;

    private Context mContext;

    private SessionManager sessionManager ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Button button;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signature);


        paintView = findViewById(R.id.paintView);

        DisplayMetrics displayMetrics = new DisplayMetrics();

        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        paintView.initialise(displayMetrics);



        API = Server.getAPIService();
        mContext = this ;

        sessionManager = new SessionManager(mContext) ;

        fab_main = findViewById(R.id.fab);
        fab_undo = findViewById(R.id.fabUndo);
        fab_redo = findViewById(R.id.fabRedo);
        btn_simpan= findViewById(R.id.btn_simpan);

        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_clock = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_rotate_clock);
        fab_anticlock = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_rotate_anticlock);


        textview_undo = (TextView) findViewById(R.id.textview_undo);
        textview_redo = (TextView) findViewById(R.id.textview_redo);



        fab_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isOpen) {


                    textview_undo.setVisibility(View.INVISIBLE);
                    textview_redo.setVisibility(View.INVISIBLE);
                    fab_undo.startAnimation(fab_close);
                    fab_redo.startAnimation(fab_close);
                    fab_main.startAnimation(fab_anticlock);
                    fab_undo.setClickable(false);
                    fab_redo.setClickable(false);
                    isOpen = false;
                } else {

                    textview_undo.setVisibility(View.VISIBLE);
                    textview_redo.setVisibility(View.VISIBLE);
                    fab_undo.startAnimation(fab_open);
                    fab_redo.startAnimation(fab_open);
                    fab_main.startAnimation(fab_clock);
                    fab_undo.setClickable(true);
                    fab_redo.setClickable(true);
                    isOpen = true;
                }

            }
        });


        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            if (TAG_TTD.equals("")){

                Toast.makeText(mContext,"Silahkan Tanda Tangan",Toast.LENGTH_LONG).show();

                return;

            }

                paintView.saveImage();

                String kepala = sessionManager.getUsername() ;
                String periode = TAG_PERIODE ;


                ResponseEntityPenilaian nilai = new ResponseEntityPenilaian();

                nilai.setSkill_1(TAG_SKILL_1);
                nilai.setSkill_2(TAG_SKILL_2);
                nilai.setSkill_3(TAG_SKILL_3);
                nilai.setSkill_4(TAG_SKILL_4);
                nilai.setSkill_5(TAG_SKILL_5);

                nilai.setKualitas_1(TAG_KUALITAS_1);
                nilai.setKualitas_2(TAG_KUALITAS_2);
                nilai.setKualitas_3(TAG_KUALITAS_3);
                nilai.setKualitas_4(TAG_KUALITAS_4);
                nilai.setKualitas_5(TAG_KUALITAS_5);

                nilai.setKemampuan_1(TAG_KEMAMPUAN_1);
                nilai.setKemampuan_2(TAG_KEMAMPUAN_2);
                nilai.setKemampuan_3(TAG_KEMAMPUAN_3);
                nilai.setKemampuan_4(TAG_KEMAMPUAN_4);
                nilai.setKemampuan_5(TAG_KEMAMPUAN_5);

                nilai.setKerja_sama_1(TAG_KERJA_SAMA_1);
                nilai.setKerja_sama_2(TAG_KERJA_SAMA_2);
                nilai.setKerja_sama_3(TAG_KERJA_SAMA_3);
                nilai.setKerja_sama_4(TAG_KERJA_SAMA_4);
                nilai.setKerja_sama_5(TAG_KERJA_SAMA_5);

                nilai.setTanggung_Jawab_1(TAG_TANGGUNG_JAWAB_1);
                nilai.setTanggung_Jawab_2(TAG_TANGGUNG_JAWAB_2);
                nilai.setTanggung_Jawab_3(TAG_TANGGUNG_JAWAB_3);
                nilai.setTanggung_Jawab_4(TAG_TANGGUNG_JAWAB_4);
                nilai.setTanggung_Jawab_5(TAG_TANGGUNG_JAWAB_5);
                nilai.setTanggung_jawab_6(TAG_TANGGUNG_JAWAB_6);

                nilai.setKerajinan_1(TAG_KERAJINAN_1);
                nilai.setKerajinan_2(TAG_KERAJINAN_2);
                nilai.setKerajinan_3(TAG_KERAJINAN_3);
                nilai.setKerajinan_4(TAG_KERAJINAN_4);
                nilai.setKerajinan_5(TAG_KERAJINAN_5);


                String ttd = TAG_TTD_STRING;
                String empID = TAG_EMP_ID_TEAM ;

                saveNilai(kepala,periode,nilai,ttd,empID, signature.this);

            }
        });

        fab_undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paintView.undo();
                Toast.makeText(getApplicationContext(), "Undo", Toast.LENGTH_SHORT).show();

            }
        });

        fab_redo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paintView.redo();
                Toast.makeText(getApplicationContext(), "Redo", Toast.LENGTH_SHORT).show();

            }
        });








    }


    private void requestStoragePermission () {

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

            new AlertDialog.Builder(this)
                    .setTitle("Permission needed")
                    .setMessage("Needed to save image")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            ActivityCompat.requestPermissions(signature.this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);

                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.dismiss();

                        }

                    })
                    .create().show();

        } else {

            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == STORAGE_PERMISSION_CODE) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                Toast.makeText(this, "Access Diizinkan", Toast.LENGTH_LONG).show();

            } else {

                Toast.makeText(this, "Access Ditolak", Toast.LENGTH_LONG).show();

            }

        }

    }



    public void saveNilai(String kepala, String periode, ResponseEntityPenilaian nilai , String ttd, String empID , Context context){

        pDialog = new ProgressDialog(context);
        pDialog.setCancelable(false);
        pDialog.setMessage("Memuat...");
        pDialog.show();


        Call<ResponseData> call = API.insertPenilaian(kepala,periode,
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
                nilai.getKerajinan_4(),nilai.getKerajinan_5(),
                ttd, empID,"KEBIDANAN"); //abaikan status
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if(response.isSuccessful()) {

                    pDialog.cancel();
                    Toast.makeText(context, "Success", Toast.LENGTH_LONG).show();
                    TAG_SELESAI_NILAI = "selesai" ;
                    finish();


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





    @Override
    protected void onResume() {
        super.onResume();
        Helper.setTeamNilai(sessionManager);
        Helper.setTeamData(sessionManager);
        TAG_PERIODE = sessionManager.getKeyPeriode();

    }







}