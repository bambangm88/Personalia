package com.rsah.personalia.Menu.TeamSaya.Penilaian.IT;

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
import static com.rsah.personalia.Menu.TeamSaya.Penilaian.signature.TAG_KEMAMPUAN_1;
import static com.rsah.personalia.Menu.TeamSaya.Penilaian.signature.TAG_SELESAI_NILAI;
import static com.rsah.personalia.Menu.TeamSaya.PreviewTeam.TAG_NAMA_;

public class Penilaian_3 extends AppCompatActivity {




    private Spinner nilai_5 ;

    private Button btn_selanjutnya ;

    private Context mContext ;

    private TextView nama ;

    private SessionManager sessionManager ;

    private ArrayList<String> arraySpinner = new ArrayList<String>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penilaian_3);

        nilai_5 = findViewById(R.id.nilai_5);

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
        nilai_5.setAdapter(spinnerArrayAdapter);

        btn_selanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String nilai_ = nilai_5.getSelectedItem().toString();

                if (nilai_.equals("-- Nilai --")){
                    Toast.makeText(mContext,"Pilih Nilai",Toast.LENGTH_SHORT).show();
                }else{


                    TAG_KEMAMPUAN_1 = Helper.nilaiHurufToNumber(nilai_) ;

                    sessionManager.createKemampuan(Helper.nilaiHurufToNumber(nilai_) ,"","","","");

                    startActivity(new Intent(mContext,Penilaian_4.class));


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
