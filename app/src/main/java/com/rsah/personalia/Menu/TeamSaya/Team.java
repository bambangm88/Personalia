package com.rsah.personalia.Menu.TeamSaya;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Spinner;
import android.widget.Toast;


import com.rsah.personalia.Helper.Helper;
import com.rsah.personalia.Model.ResponseData;
import com.rsah.personalia.Model.ResponseEntityTeam;
import com.rsah.personalia.R;
import com.rsah.personalia.api.ApiService;
import com.rsah.personalia.api.Server;
import com.rsah.personalia.sessionManager.SessionManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Team extends AppCompatActivity {



    ProgressDialog pDialog;
    ApiService API;
    private Spinner spinnerMonitor;
    private SessionManager sessionManager ;

    private RecyclerView rvTeam;
    private List<ResponseEntityTeam> AllTeamList = new ArrayList<>();
    private TeamAdapter Adapter;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

        mContext = this ;
        API = Server.getAPIService();

        sessionManager = new SessionManager(this);

        rvTeam = findViewById(R.id.rvRoom_search_doctor);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        rvTeam.setLayoutManager(mLayoutManager);
        rvTeam.setItemAnimator(new DefaultItemAnimator());

        Adapter = new TeamAdapter(this, AllTeamList);

        pDialog = new ProgressDialog(Team.this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Memuat...");



        getTeam(sessionManager.getKeyJabatanId());


    }



    private void getTeam(String Team){


        pDialog.show();


        Call<ResponseData> call = API.getTeam(Team);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if(response.isSuccessful()) {
                    if (response.body().getDataTeam() != null) {


                        if(!response.body().getDataTeam().isEmpty()){

                            pDialog.cancel();
                            AllTeamList.addAll(response.body().getDataTeam());
                            rvTeam.setAdapter(new TeamAdapter(mContext, AllTeamList));
                            Adapter.notifyDataSetChanged();

                        }else{

                            pDialog.cancel();
                            finish();
                            Toast.makeText(mContext, "Team Tidak Ditemukan", Toast.LENGTH_LONG).show();

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
                finish();
                Toast.makeText(mContext, "Internal server error / check your connection", Toast.LENGTH_SHORT).show();
                Log.e("Error", "onFailure: "+t.getMessage() );
            }
        });
    }







}
