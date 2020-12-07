package com.rsah.personalia.Helper;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.developer.kalert.KAlertDialog;
import com.google.android.gms.maps.model.LatLng;
import com.rsah.personalia.Auth.Login;
import com.rsah.personalia.BuildConfig;
import com.rsah.personalia.MainActivity;
import com.rsah.personalia.Model.ResponseData;
import com.rsah.personalia.Model.ResponseEntityPeriode;
import com.rsah.personalia.Model.ResponseEntityVersion;
import com.rsah.personalia.api.ApiService;
import com.rsah.personalia.api.Server;
import com.rsah.personalia.sessionManager.SessionManager;

import org.jsoup.Jsoup;
import org.w3c.dom.Document;

import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.pm.PackageManager.*;
import static androidx.constraintlayout.widget.Constraints.TAG;

import static com.rsah.personalia.Menu.TeamSaya.PreviewTeam.TAG_BIDANG;
import static com.rsah.personalia.Menu.TeamSaya.PreviewTeam.TAG_DEPTID_TEAM;
import static com.rsah.personalia.Menu.TeamSaya.PreviewTeam.TAG_EMP_ID_TEAM;
import static com.rsah.personalia.Menu.TeamSaya.PreviewTeam.TAG_IMAGE_STRING;
import static com.rsah.personalia.Menu.TeamSaya.PreviewTeam.TAG_JABATAN;
import static com.rsah.personalia.Menu.TeamSaya.PreviewTeam.TAG_NAMA_;
import static com.rsah.personalia.Menu.TeamSaya.PreviewTeam.TAG_UNIT;


import static com.rsah.personalia.Menu.TeamSaya.Penilaian.signature.TAG_SKILL_1;
import static com.rsah.personalia.Menu.TeamSaya.Penilaian.signature.TAG_SKILL_2;
import static com.rsah.personalia.Menu.TeamSaya.Penilaian.signature.TAG_SKILL_3;
import static com.rsah.personalia.Menu.TeamSaya.Penilaian.signature.TAG_SKILL_4;
import static com.rsah.personalia.Menu.TeamSaya.Penilaian.signature.TAG_SKILL_5;

import static com.rsah.personalia.Menu.TeamSaya.Penilaian.signature.TAG_KUALITAS_1;
import static com.rsah.personalia.Menu.TeamSaya.Penilaian.signature.TAG_KUALITAS_2;
import static com.rsah.personalia.Menu.TeamSaya.Penilaian.signature.TAG_KUALITAS_3;
import static com.rsah.personalia.Menu.TeamSaya.Penilaian.signature.TAG_KUALITAS_4;
import static com.rsah.personalia.Menu.TeamSaya.Penilaian.signature.TAG_KUALITAS_5;

import static com.rsah.personalia.Menu.TeamSaya.Penilaian.signature.TAG_KEMAMPUAN_1;
import static com.rsah.personalia.Menu.TeamSaya.Penilaian.signature.TAG_KEMAMPUAN_2;
import static com.rsah.personalia.Menu.TeamSaya.Penilaian.signature.TAG_KEMAMPUAN_3;
import static com.rsah.personalia.Menu.TeamSaya.Penilaian.signature.TAG_KEMAMPUAN_4;
import static com.rsah.personalia.Menu.TeamSaya.Penilaian.signature.TAG_KEMAMPUAN_5;


import static com.rsah.personalia.Menu.TeamSaya.Penilaian.signature.TAG_KERJA_SAMA_1;
import static com.rsah.personalia.Menu.TeamSaya.Penilaian.signature.TAG_KERJA_SAMA_2;
import static com.rsah.personalia.Menu.TeamSaya.Penilaian.signature.TAG_KERJA_SAMA_3;
import static com.rsah.personalia.Menu.TeamSaya.Penilaian.signature.TAG_KERJA_SAMA_4;
import static com.rsah.personalia.Menu.TeamSaya.Penilaian.signature.TAG_KERJA_SAMA_5;


import static com.rsah.personalia.Menu.TeamSaya.Penilaian.signature.TAG_TANGGUNG_JAWAB_1;
import static com.rsah.personalia.Menu.TeamSaya.Penilaian.signature.TAG_TANGGUNG_JAWAB_2;
import static com.rsah.personalia.Menu.TeamSaya.Penilaian.signature.TAG_TANGGUNG_JAWAB_3;
import static com.rsah.personalia.Menu.TeamSaya.Penilaian.signature.TAG_TANGGUNG_JAWAB_4;
import static com.rsah.personalia.Menu.TeamSaya.Penilaian.signature.TAG_TANGGUNG_JAWAB_5;
import static com.rsah.personalia.Menu.TeamSaya.Penilaian.signature.TAG_TANGGUNG_JAWAB_6;

import static com.rsah.personalia.Menu.TeamSaya.Penilaian.signature.TAG_KERAJINAN_1;
import static com.rsah.personalia.Menu.TeamSaya.Penilaian.signature.TAG_KERAJINAN_2;
import static com.rsah.personalia.Menu.TeamSaya.Penilaian.signature.TAG_KERAJINAN_3;
import static com.rsah.personalia.Menu.TeamSaya.Penilaian.signature.TAG_KERAJINAN_4;
import static com.rsah.personalia.Menu.TeamSaya.Penilaian.signature.TAG_KERAJINAN_5;




public class Helper {


    private static SessionManager sessionManager ;


    public static boolean checkNullInputLogin(String username , String Password){

        Boolean check = true ;

        if(username.equals("") || Password.equals("")){

            check = false ;

        }

        return check ;


    }

    public static boolean checkNullInputString(String username){

        Boolean check = true ;

        if(username.equals("") ){

            check = false ;

        }

        return check ;


    }


    public static String chekJenisKelamin(String JenisKelamin){

        String jk = "" ;

        if (JenisKelamin.equals("Perempuan")){
            jk = "P";
        }else{
            jk = "L";
        }

        return jk ;

    }


    public static String convertStatus(String code){

        String status = "" ;

        if (code.equals("01")){
            status = "Terdaftar";
        }else if (code.equals("00")){
            status = "Selesai";
        }else{
            status = "Expired";
        }

        return status ;

    }


    public static String convertHariToDaySession(String day){

        String daySession = "" ;

        if (day.contains("Minggu")){
            daySession = "1";
        }

        if (day.contains("Senin")){
            daySession = "2";
        }

        if (day.contains("Selasa")){
            daySession = "3";
        }

        if (day.contains("Rabu")){
            daySession = "4";
        }

        if (day.contains("Kamis")){
            daySession = "5";
        }

        if (day.contains("Jumat")){
            daySession = "6";
        }

        if (day.contains("Sabtu")){
            daySession = "7";
        }


        return daySession ;

    }


    public static String convert_to_sha256(String string) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(string.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }


    public static String checkNull(String string) {

        String num = "" ;

        if (string == null || string.isEmpty()|| string.equals(".0000")){

            num = "0" ;

        }
        else
        {

            String replace = string.replace(".0000","");
            num = replace ;
        }


        return convert_currency(num) ;

    }

    public static String convert_currency(String price){


        double d = Double.parseDouble(price);

        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(d);



    }




    public static void countDown(Context mContext){



        new CountDownTimer(150000, 150000) {

            public void onTick(long millisUntilFinished) {

                Log.e(TAG, "seconds remaining: "  + millisUntilFinished / 1000  );

            }

            public void onFinish() {


                try {

                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setMessage("Session Anda Telah Berakhir !")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //do things

                                    Intent i = new Intent(mContext, Login.class);
                                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                            Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                            Intent.FLAG_ACTIVITY_NEW_TASK);
                                    ((Activity)mContext).startActivity(i);


                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();


                }catch (Exception ex){


                }





            }

        }.start();
    }




    public static void Logout(Context mContext){

        ApiService API = Server.getAPIService();
        sessionManager = new SessionManager(mContext) ;


        String user = "";

        if (sessionManager.getUsername() != null){
            user = sessionManager.getUsername() ;
        }



        Call<ResponseData> call = API.Logout(user, "LOGOUT");
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if(response.isSuccessful()) {

                }else{
                    Toast.makeText(mContext, "Error Response Data", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {

               Toast.makeText(mContext, "Internal server error / check your connection", Toast.LENGTH_SHORT).show();
                Log.e("Error", "onFailure: "+t.getMessage() );
            }
        });
    }


    public static int checkVersion(){

        int versionCode = BuildConfig.VERSION_CODE;
        String versionName = BuildConfig.VERSION_NAME;

        return  versionCode ;

    }



    public static void checkVersionUpdate(Context mContext){

        ApiService API = Server.getAPIService();
        Call<ResponseData> call = API.CheckVersion( "PERSONALIA");
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {

                if(response.isSuccessful()) {


                    if (response.body().getDataVersion()!= null){

                        if(response.body().getDataVersion().isEmpty()){



                        }else{

                            String versionCodeUpdate = response.body().getDataVersion().get(0).getVersionCode() ;
                            int versionUpdate = Integer.parseInt(versionCodeUpdate);
                            int versionNow = checkVersion() ;


                            if (versionNow < versionUpdate){

                            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                            builder.setMessage("UPDATE APLIKASI VERSION v"+versionUpdate+" TERSEDIA")
                                    .setCancelable(false)
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            //do things

                                            final String appPackageName = mContext.getPackageName(); // getPackageName() from Context or Activity object
                                            try {
                                                mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                                            } catch (android.content.ActivityNotFoundException anfe) {
                                                mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                                            }


                                        }
                                    });
                            AlertDialog alert = builder.create();
                            alert.show();

                            }


                        }


                    }else{

                    }


                }else

                 {
                    Toast.makeText(mContext, "Error Response Data", Toast.LENGTH_LONG).show();
                 }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {

                Toast.makeText(mContext, "Internal server error / check your connection", Toast.LENGTH_SHORT).show();
                Log.e("Error", "onFailure: "+t.getMessage() );
            }
        });
    }


    public static String kalkulasi_Nilai(String s_nilai){

        String nilai = s_nilai;
        String hasil = "" ;

        if (nilai.equals("125")){
            hasil = "A" ;
        }

        else if (nilai.equals("123")){
            hasil = "B+" ;
        }

        else if (nilai.equals("116")){
            hasil = "B" ;
        }

        else if (nilai.equals("113")){
            hasil = "B-" ;
        }

        else if (nilai.equals("107")){
            hasil = "C+" ;
        }

        else if (nilai.equals("100")){
            hasil = "C" ;
        }

        else if (nilai.equals("93") ){
            hasil = "C-" ;
        }

        else if (nilai.equals("85") ){
            hasil = "D" ;
        }

        else if (nilai.equals("77") ){
            hasil = "E" ;
        }
        return hasil ;
    }


    public static String kalkulasi_Nilai_bidan(String s_nilai){

        String nilai = s_nilai;
        String hasil = "" ;

        if (nilai.equals("100")){
            hasil = "A" ;
        }

        else if (nilai.equals("90")){
            hasil = "B+" ;
        }

        else if (nilai.equals("80")){
            hasil = "B" ;
        }

        else if (nilai.equals("70")){
            hasil = "B-" ;
        }

        else if (nilai.equals("65")){
            hasil = "C+" ;
        }

        else if (nilai.equals("60")){
            hasil = "C" ;
        }

        else if (nilai.equals("50") ){
            hasil = "C-" ;
        }

        else if (nilai.equals("40") ){
            hasil = "D" ;
        }

        else if (nilai.equals("20") ){
            hasil = "E" ;
        }

        return hasil ;
    }



    public static String kalkulasi_kesimpulan_Nilai_bidan(String s_nilai){

        int nilai = Integer.parseInt(s_nilai);
        String hasil = "" ;

        if (nilai > 91){
            hasil = "A" ;
        }

        else if (nilai >= 80  && nilai <= 91  ){
            hasil = "B+" ;
        }

        else if (nilai >= 71  && nilai <= 79  ){
            hasil = "B" ;
        }

        else if (nilai >= 67  && nilai <= 70  ){
            hasil = "B-" ;
        }

        else if (nilai >= 65  && nilai <= 67  ){
            hasil = "C+" ;
        }

        else if (nilai >= 60  && nilai <= 64  ){
            hasil = "C" ;
        }

        else if (nilai >= 50  && nilai <= 59  ){
            hasil = "C-" ;
        }

        else if (nilai >= 21  && nilai <= 49  ){
            hasil = "D" ;
        }

        return hasil ;
    }


    public static String nilaiHurufToNumber(String s_nilai){

        String nilai = s_nilai;
        String hasil = "" ;

        if (nilai.equals("A")){
            hasil = "125" ;
        }

        else if (nilai.equals("B+")){
            hasil = "123" ;
        }

        else if (nilai.equals("B")){
            hasil = "116" ;
        }

        else if (nilai.equals("B-")){
            hasil = "113" ;
        }

        else if (nilai.equals("C+")){
            hasil = "107" ;
        }

        else if (nilai.equals("C")){
            hasil = "100" ;
        }

        else if (nilai.equals("C-")){
            hasil = "93" ;
        }

        else if (nilai.equals("D")){
            hasil = "85" ;
        }

        else if (nilai.equals("E")){
            hasil = "77" ;
        }

        return hasil ;
    }


    public static String nilaiHurufToNumber_Bidan(String s_nilai){

        String nilai = s_nilai;
        String hasil = "" ;

        if (nilai.equals("A")){
            hasil = "100" ;
        }

        else if (nilai.equals("B+")){
            hasil = "90" ;
        }

        else if (nilai.equals("B")){
            hasil = "80" ;
        }

        else if (nilai.equals("B-")){
            hasil = "70" ;
        }

        else if (nilai.equals("C+")){
            hasil = "65" ;
        }

        else if (nilai.equals("C")){
            hasil = "60" ;
        }

        else if (nilai.equals("C-")){
            hasil = "50" ;
        }

        else if (nilai.equals("D")){
            hasil = "40" ;
        }

        else if (nilai.equals("E")){
            hasil = "20" ;
        }

        return hasil ;
    }

    public static String convertBulanToNumber(String bulan){

        String num ="";

        if (bulan.equals("Januari")){
            num = "01" ;
        }

        if (bulan.equals("Februari")){
            num = "02" ;
        }

        if (bulan.equals("Maret")){
            num = "03" ;
        }

        if (bulan.equals("April")){
            num = "04" ;
        }

        if (bulan.equals("Mei")){
            num = "05" ;
        }

        if (bulan.equals("Juni")){
            num = "06" ;
        }

        if (bulan.equals("Juli")){
            num = "07" ;
        }

        if (bulan.equals("Agustus")){
            num = "08" ;
        }

        if (bulan.equals("September")){
            num = "09" ;
        }

        if (bulan.equals("Oktober")){
            num = "10" ;
        }

        if (bulan.equals("November")){
            num = "11" ;
        }

        if (bulan.equals("Desember")){
            num = "12" ;
        }



        return num ;
    }


    public static String kalkulasi_Nilai_it(String s_nilai){

        int nilai = Integer.parseInt(s_nilai);
        String hasil = "" ;

        if (nilai > 123){
            hasil = "A" ;
        }

        else if (nilai >= 120  && nilai <= 123  ){
            hasil = "B+" ;
        }

        else if (nilai >= 115  && nilai <= 119  ){
            hasil = "B" ;
        }

        else if (nilai >= 110  && nilai <= 114  ){
            hasil = "B-" ;
        }

        else if (nilai >= 105  && nilai <= 109  ){
            hasil = "C+" ;
        }

        else if (nilai >= 97  && nilai <= 104  ){
            hasil = "C" ;
        }

        else if (nilai >= 90  && nilai <= 96  ){
            hasil = "C-" ;
        }

        else if (nilai >= 80  && nilai <= 89  ){
            hasil = "D" ;
        }

        else {
            hasil = "E" ;
        }
        return hasil ;
    }




    public static void setTeamData(SessionManager sessionManager){

        TAG_NAMA_ = sessionManager.getKeyName();
        TAG_BIDANG = sessionManager.getKeyBidang();
        TAG_JABATAN =sessionManager.getJabatan();
        TAG_UNIT = sessionManager.getKeyUnit();
        TAG_EMP_ID_TEAM = sessionManager.getKeyEmpid();
        TAG_IMAGE_STRING = sessionManager.getKeyImageString();
        TAG_DEPTID_TEAM = sessionManager.getKeyDeptIdString();

    }



    public static void setTeamNilai(SessionManager sessionManager){

        TAG_NAMA_ = sessionManager.getKeyName();

        TAG_SKILL_1 = sessionManager.getKeySkill1() ;
        TAG_SKILL_2 = sessionManager.getKeySkill2() ;
        TAG_SKILL_3 = sessionManager.getKeySkill3() ;
        TAG_SKILL_4 = sessionManager.getKeySkill4() ;
        TAG_SKILL_5 = sessionManager.getKeySkill5() ;

        TAG_KUALITAS_1 = sessionManager.getKeyKualitas1() ;
        TAG_KUALITAS_2 = sessionManager.getKeyKualitas2() ;
        TAG_KUALITAS_3 = sessionManager.getKeyKualitas3() ;
        TAG_KUALITAS_4 = sessionManager.getKeyKualitas4() ;
        TAG_KUALITAS_5 = sessionManager.getKeyKualitas5() ;

        TAG_KEMAMPUAN_1 = sessionManager.getKeyKemampuan1() ;
        TAG_KEMAMPUAN_2 = sessionManager.getKeyKemampuan2() ;
        TAG_KEMAMPUAN_3 = sessionManager.getKeyKemampuan3() ;
        TAG_KEMAMPUAN_4 = sessionManager.getKeyKemampuan4() ;
        TAG_KEMAMPUAN_5 = sessionManager.getKeyKemampuan5() ;

        TAG_KERJA_SAMA_1 = sessionManager.getKeyKerjaSama1();
        TAG_KERJA_SAMA_2 = sessionManager.getKeyKerjaSama2();
        TAG_KERJA_SAMA_3 = sessionManager.getKeyKerjaSama3();
        TAG_KERJA_SAMA_4 = sessionManager.getKeyKerjaSama4();
        TAG_KERJA_SAMA_5 = sessionManager.getKeyKerjaSama5();

        TAG_TANGGUNG_JAWAB_1 = sessionManager.getKeyTanggungJawab1();
        TAG_TANGGUNG_JAWAB_2 = sessionManager.getKeyTanggungJawab2();
        TAG_TANGGUNG_JAWAB_3 = sessionManager.getKeyTanggungJawab3();
        TAG_TANGGUNG_JAWAB_4 = sessionManager.getKeyTanggungJawab4();
        TAG_TANGGUNG_JAWAB_5 = sessionManager.getKeyTanggungJawab5();
        TAG_TANGGUNG_JAWAB_6 = sessionManager.getKeyTanggungJawab6();

        TAG_KERAJINAN_1 = sessionManager.getKeyKerajinan1();
        TAG_KERAJINAN_2 = sessionManager.getKeyKerajinan2();
        TAG_KERAJINAN_3 = sessionManager.getKeyKerajinan3();
        TAG_KERAJINAN_4 = sessionManager.getKeyKerajinan4();
        TAG_KERAJINAN_5 = sessionManager.getKeyKerajinan5();

    }



    public static void notifAlert( Activity context, String title){
        new KAlertDialog(context, KAlertDialog.SUCCESS_TYPE)
                .setTitleText("Notification")
                .setContentText(title)
                .setConfirmText("OK")
                .setConfirmClickListener(new KAlertDialog.KAlertClickListener() {
                    @Override
                    public void onClick(KAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                        //getDataCustomerFromDBSetToTextview();

                        Intent intent = new Intent(context,MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        context.startActivity(intent);
                        context.finish();
                    }
                })
                .show();
    }



    public static double CalculationByDistance(LatLng StartP, LatLng EndP) {
        int Radius = 6371;// radius of earth in Km
        double lat1 = StartP.latitude;
        double lat2 = EndP.latitude;
        double lon1 = StartP.longitude;
        double lon2 = EndP.longitude;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double valueResult = Radius * c;
        double km = valueResult / 1;
        DecimalFormat newFormat = new DecimalFormat("####");
        int kmInDec = Integer.valueOf(newFormat.format(km));
        double meter = valueResult % 1000;
        int meterInDec = Integer.valueOf(newFormat.format(meter));
        Log.i("Radius Value", "" + valueResult + "   KM  " + kmInDec
                + " Meter   " + meterInDec);

        return Radius * c;
    }




}






