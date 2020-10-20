package com.rsah.personalia.Menu.Performance;

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

import static com.rsah.personalia.Menu.SlipGaji.slipGaji.TAG_PERIODE;
import static com.rsah.personalia.Menu.TeamSaya.Penilaian.signature.TAG_TTD;
import static com.rsah.personalia.Menu.TeamSaya.PreviewTeam.TAG_EMP_ID_TEAM;


public class signature_employee extends AppCompatActivity {

    private PaintView paintView;
    private int defaultColor;
    private int STORAGE_PERMISSION_CODE = 1;

    public static String TAG_TTD_STRING = "";
    public static String TAG_SELESAI_NILAI = "";

    ProgressDialog pDialog;

    private FloatingActionButton fab_main,  fab_undo , fab_redo;
    private Animation fab_open, fab_close, fab_clock, fab_anticlock;
    TextView textview_save, textview_undo , textview_redo;


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

                String ttd = TAG_TTD_STRING;
                saveSignature(TAG_PERIODE,sessionManager.getUsername(),ttd, signature_employee.this);




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

                            ActivityCompat.requestPermissions(signature_employee.this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);

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



    public void saveSignature( String periode,  String empID ,  String ttd, Context context){

        pDialog = new ProgressDialog(context);
        pDialog.setCancelable(false);
        pDialog.setMessage("Memuat...");
        pDialog.show();


        Call<ResponseData> call = API.insertTtdEmployee(periode,empID,ttd);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if(response.isSuccessful()) {

                    pDialog.cancel();
                    Toast.makeText(context, "Success", Toast.LENGTH_LONG).show();
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
    public void onBackPressed() {

        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }



}