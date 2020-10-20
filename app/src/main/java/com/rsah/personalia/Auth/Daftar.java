package com.rsah.personalia.Auth;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.rsah.personalia.Model.ResponseData;
import com.rsah.personalia.R;
import com.rsah.personalia.api.ApiService;
import com.rsah.personalia.api.Server;

import java.io.ByteArrayOutputStream;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Daftar extends AppCompatActivity {


    public static String TAG_ID = "" ;
    public static String chooseUpload_set = "";
    public static String chooseUpload = "";
    public static Bitmap bitmapUpload ;

    int bitmap_size = 60; // range 1 - 100
    private  Bitmap bitmap, decoded;
    private  ProgressDialog pDialog;
    private int GALLERY = 1, CAMERA = 2;

    private ApiService API;

    private  CircularImageView circularImageView ;

    private Context mContext ;

    private Button btn_daftar ;

    private EditText et_user , et_email , et_pwd ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        mContext = this ;

        API = Server.getAPIService();
        btn_daftar = findViewById(R.id.btn_daftar);
        et_user = findViewById(R.id.editTextUser);
        et_email = findViewById(R.id.editTextEmail);
        et_pwd = findViewById(R.id.editTextPassword);

        circularImageView = findViewById(R.id.cirCularImage);

        requestMultiplePermissions();


        circularImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseUpload = "1" ;
                startActivity(new Intent(mContext,UploadFotoProfile.class));
            }
        });


        btn_daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String id = TAG_ID ;
                String user = et_user.getText().toString();
                String email = et_email.getText().toString();
                String pwd = et_pwd.getText().toString();
                String foto = getStringImage(imageView2Bitmap(circularImageView));


                if (circularImageView.getTag() != "ada" ){

                    Toast.makeText(getApplicationContext(), "Silahkan Pilih Foto", Toast.LENGTH_SHORT).show();
                    return;
                }



                if ( user.isEmpty()  ){

                    Toast.makeText(getApplicationContext(), "Username Tidak Boleh Kosong ", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (email.isEmpty() ){

                    Toast.makeText(getApplicationContext(), "Email Tidak Boleh Kosong  ", Toast.LENGTH_SHORT).show();
                    return;
                }

                if ( pwd.isEmpty() ){

                    Toast.makeText(getApplicationContext(), "Password tidak boleh kosong ", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (pwd.length() < 8 ){

                    Toast.makeText(getApplicationContext(), "Password min 8 karakter ", Toast.LENGTH_SHORT).show();
                    return;
                }


                DoDaftar(id,user,email,pwd,foto);


            }
        });


    }

    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }


    private void  requestMultiplePermissions(){
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            Toast.makeText(getApplicationContext(), "Permission diizinkan!", Toast.LENGTH_SHORT).show();
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings
                            //openSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }


                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(getApplicationContext(), "Some Error! ", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();
    }

    private Bitmap imageView2Bitmap(ImageView view){
        Bitmap bitmap = ((BitmapDrawable)view.getDrawable()).getBitmap();
        return bitmap;
    }



    public void DoDaftar(String id,
                           String username ,
                           String email ,
                           String password ,
                           String foto
                         ){

        pDialog = new ProgressDialog(mContext);
        pDialog.setCancelable(false);
        pDialog.setMessage("Mendaftar...");
        pDialog.show();


        Call<ResponseData> call = API.Daftar(id,username,email,password,foto);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if(response.isSuccessful()) {
                    pDialog.cancel();

                    if(response.body().getDataDaftar().isEmpty()) {

                        pDialog.cancel();
                        Toast.makeText(mContext, "Register Success", Toast.LENGTH_LONG).show();

                        finish();

                        startActivity(new Intent(mContext,Login.class));

                    }else{

                        pDialog.cancel();
                        Toast.makeText(mContext, "Member Sudah Teregistrasi", Toast.LENGTH_LONG).show();

                    }


                }else{
                    pDialog.cancel();
                    Toast.makeText(mContext, "Error Response Data", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {

                pDialog.cancel();

                Toast.makeText(mContext, "Internal server error / check your connection", Toast.LENGTH_SHORT).show();
                Log.e("Error", "onFailure: "+t.getMessage() );
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        if(chooseUpload.equals("1")){
            if(chooseUpload_set.equals("1")) {
                circularImageView.setImageBitmap(bitmapUpload);
                chooseUpload = "";
                chooseUpload_set ="";
                circularImageView.setTag("ada");
            }
        }

    }

}
