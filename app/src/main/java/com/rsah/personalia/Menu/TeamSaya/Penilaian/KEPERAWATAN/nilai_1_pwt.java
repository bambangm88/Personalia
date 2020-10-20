package com.rsah.personalia.Menu.TeamSaya.Penilaian.KEPERAWATAN;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.rsah.personalia.Helper.Helper;
import com.rsah.personalia.R;
import com.rsah.personalia.sessionManager.SessionManager;

import java.util.ArrayList;

import static android.R.layout.simple_spinner_item;
import static com.rsah.personalia.Menu.TeamSaya.Penilaian.signature.TAG_SELESAI_NILAI;
import static com.rsah.personalia.Menu.TeamSaya.Penilaian.signature.TAG_SKILL_1;
import static com.rsah.personalia.Menu.TeamSaya.Penilaian.signature.TAG_SKILL_2;
import static com.rsah.personalia.Menu.TeamSaya.Penilaian.signature.TAG_SKILL_3;
import static com.rsah.personalia.Menu.TeamSaya.Penilaian.signature.TAG_SKILL_4;
import static com.rsah.personalia.Menu.TeamSaya.Penilaian.signature.TAG_SKILL_5;
import static com.rsah.personalia.Menu.TeamSaya.PreviewTeam.TAG_NAMA_;

public class nilai_1_pwt extends AppCompatActivity {





    private Spinner nilai_1 , nilai_2 , nilai_3 ;

    private Button btn_selanjutnya ;

    private Context mContext ;

    private TextView nama ;

    private SessionManager sessionManager ;

    private ArrayList<String> arraySpinner = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nilai_1_pwt);

        nilai_1 = findViewById(R.id.nilai_1);
        nilai_2 = findViewById(R.id.nilai_2);
        nilai_3 = findViewById(R.id.nilai_3);


        nama = findViewById(R.id.nama);

        nama.setText(TAG_NAMA_);




        mContext = this ;

        sessionManager = new SessionManager(mContext) ;

        btn_selanjutnya = findViewById(R.id.btn_selanjutnya);


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
        nilai_1.setAdapter(spinnerArrayAdapter);


        ArrayAdapter<String> spinnerArrayAdapter_2 = new ArrayAdapter<String>(mContext, simple_spinner_item,arraySpinner );
        spinnerArrayAdapter_2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        nilai_2.setAdapter(spinnerArrayAdapter_2);


        ArrayAdapter<String> spinnerArrayAdapter_3 = new ArrayAdapter<String>(mContext, simple_spinner_item,arraySpinner );
        spinnerArrayAdapter_3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        nilai_3.setAdapter(spinnerArrayAdapter_3);


        btn_selanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nilai_1_ = nilai_1.getSelectedItem().toString();
                String nilai_2_ = nilai_2.getSelectedItem().toString();
                String nilai_3_ = nilai_3.getSelectedItem().toString();



                if (nilai_1_.equals("-- Nilai --")){
                    Toast.makeText(mContext,"Pilih Nilai",Toast.LENGTH_SHORT).show();
                }else if(nilai_2_.equals("-- Nilai --")){
                    Toast.makeText(mContext,"Pilih Nilai",Toast.LENGTH_SHORT).show();
                }else if(nilai_3_.equals("-- Nilai --")){
                    Toast.makeText(mContext,"Pilih Nilai",Toast.LENGTH_SHORT).show();
                }else{

                    TAG_SKILL_1 = Helper.nilaiHurufToNumber_Bidan(nilai_1_) ;
                    TAG_SKILL_2 = Helper.nilaiHurufToNumber_Bidan(nilai_2_) ;
                    TAG_SKILL_3 = Helper.nilaiHurufToNumber_Bidan(nilai_3_);

                    sessionManager.createSkill(Helper.nilaiHurufToNumber_Bidan(nilai_1_),Helper.nilaiHurufToNumber_Bidan(nilai_2_),Helper.nilaiHurufToNumber_Bidan(nilai_3_),"","");

                    startActivity(new Intent(mContext, nilai_2_pwt.class));


                }



            }


        });







    }


    @Override
    protected void onResume() {

        if (!TAG_SELESAI_NILAI.equals("")){
            finish();
            TAG_SELESAI_NILAI = "" ;
        }

        Helper.setTeamData(sessionManager);

        super.onResume();
    }

}
