package com.rsah.personalia.Menu.TeamSaya.Penilaian.General;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.rsah.personalia.Helper.Helper;
import com.rsah.personalia.R;
import com.rsah.personalia.sessionManager.SessionManager;

import static com.rsah.personalia.Menu.TeamSaya.Penilaian.signature.TAG_KERJA_SAMA_1;

import static com.rsah.personalia.Menu.TeamSaya.Penilaian.signature.TAG_KERJA_SAMA_2;
import static com.rsah.personalia.Menu.TeamSaya.Penilaian.signature.TAG_KERJA_SAMA_3;
import static com.rsah.personalia.Menu.TeamSaya.Penilaian.signature.TAG_KERJA_SAMA_4;
import static com.rsah.personalia.Menu.TeamSaya.Penilaian.signature.TAG_KERJA_SAMA_5;
import static com.rsah.personalia.Menu.TeamSaya.Penilaian.signature.TAG_SELESAI_NILAI;
import static com.rsah.personalia.Menu.TeamSaya.PreviewTeam.TAG_NAMA_;

public class nilai_4 extends AppCompatActivity {




    private EditText nilai_1 , nilai_2 , nilai_3 , nilai_4 , nilai_5 ;

    private Button btn_selanjutnya ;

    private Context mContext ;

    private TextView nama ;

    private SessionManager sessionManager ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nilai_4);

        nilai_1 = findViewById(R.id.nilai_1);
        nilai_2 = findViewById(R.id.nilai_2);
        nilai_3 = findViewById(R.id.nilai_3);
        nilai_4 = findViewById(R.id.nilai_4);
        nilai_5 = findViewById(R.id.nilai_5);

        nama = findViewById(R.id.nama);

        nama.setText(TAG_NAMA_);




        mContext = this ;

        sessionManager = new SessionManager(mContext) ;

        btn_selanjutnya = findViewById(R.id.btn_selanjutnya);


        btn_selanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nilai_1_ = nilai_1.getText().toString();
                String nilai_2_ = nilai_2.getText().toString();
                String nilai_3_ = nilai_3.getText().toString();
                String nilai_4_ = nilai_4.getText().toString();
                String nilai_5_ = nilai_5.getText().toString();


                if (nilai_1_.equals("")){
                    nilai_1.setError("Silahkan Isi Nilai");
                }else if(nilai_2_.equals("")){
                    nilai_2.setError("Silahkan Isi Nilai");
                }else if(nilai_3_.equals("")){
                    nilai_3.setError("Silahkan Isi Nilai");
                }else if(nilai_4_.equals("")){
                    nilai_4.setError("Silahkan Isi Nilai");
                }else if(nilai_5_.equals("")){
                    nilai_5.setError("Silahkan Isi Nilai");
                } else{

                    TAG_KERJA_SAMA_1 = nilai_1_ ;
                    TAG_KERJA_SAMA_2 = nilai_2_ ;
                    TAG_KERJA_SAMA_3 = nilai_3_ ;
                    TAG_KERJA_SAMA_4 = nilai_4_ ;
                    TAG_KERJA_SAMA_5 = nilai_5_ ;


                    sessionManager.createKerja_sama(nilai_1_,nilai_2_,nilai_3_,nilai_4_,nilai_5_);


                    startActivity(new Intent(mContext,nilai_5.class));


                }



            }


        });







    }


    @Override
    protected void onResume() {

        if (!TAG_SELESAI_NILAI.equals("")){
            finish();

        }

        Helper.setTeamData(sessionManager);

        super.onResume();
    }

}
