package com.rsah.personalia.Menu.SlipGaji;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rsah.personalia.Helper.Helper;
import com.rsah.personalia.R;
import com.rsah.personalia.sessionManager.SessionManager;


public class ChooseJenisSlipGaji extends AppCompatActivity {

    private Context mContext ;
    private SessionManager session ;
    private Button btn_slipGaji , btnTHR , btn_Lembur ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_jenis_slip_gaji);


        mContext = this ;
        session = new SessionManager(getApplicationContext());

        btn_slipGaji = findViewById(R.id.slipgaji);
        btnTHR = findViewById(R.id.tunjangan);
        btn_Lembur = findViewById(R.id.lemburan);

        Helper.countDown(mContext);


        btn_slipGaji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirm_password("1");
            }
        });

        btnTHR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirm_password("2");
            }
        });

        btn_Lembur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirm_password("3");
            }
        });



    }


    void confirm_password(String num){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Konfirmasi Password");

// Set up the input
        final EditText input = new EditText(this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        builder.setView(input);

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String m_Text = input.getText().toString();
                String pwd = session.getKeyPwd();
                String pwdHash = Helper.convert_to_sha256(m_Text) ;


                if (pwd.replace(" ","").equals(pwdHash)){

                    if (num.equals("1")){

                        startActivity(new Intent(mContext , PilihPeriodeGaji.class));

                    }

                    if (num.equals("2")){

                        startActivity(new Intent(mContext , PilihPeriodeInsentifTHR.class));

                    }

                    if (num.equals("3")){

                        startActivity(new Intent(mContext , PilihPeriodeInsentifLembur.class));

                    }


                }else{

                    Toast.makeText(mContext, "Password Salah", Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                }


            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();

    }


}
