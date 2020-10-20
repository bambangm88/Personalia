package com.rsah.personalia.sessionManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;


import com.rsah.personalia.MainActivity;

import java.util.EnumMap;
import java.util.HashMap;

public class SessionManager {

    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "Sisimangi";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    // User name (make variable public to access from outside)

    public static final String KEY_USERNAME = "username";

    public static final String KEY_JABATAN = "jabatan";

    public static final String KEY_JABATAN_ID = "jabatan_id";

    public static final String KEY_PWD= "pwd_";
    public static final String KEY_FORM= "form_";

    public static final String KEY_NAME_= "name_";
    public static final String KEY_BIDANG_= "bidang_";
    public static final String KEY_JABATAN_= "jabatan_";
    public static final String KEY_UNIT_= "unit_";
    public static final String KEY_EMPID_= "empid_";
    public static final String KEY_IMAGE_STRING= "img_string_";

    public static final String KEY_DEPT_ID_STRING= "deptid_team_";


    public static final String KEY_SKILL_1 = "skill_1";
    public static final String KEY_SKILL_2 = "skill_2";
    public static final String KEY_SKILL_3 = "skill_3";
    public static final String KEY_SKILL_4 = "skill_4";
    public static final String KEY_SKILL_5 = "skill_5";

    public static final String KEY_KUALITAS_1 = "KUALITAS_1";
    public static final String KEY_KUALITAS_2 = "KUALITAS_2";
    public static final String KEY_KUALITAS_3 = "KUALITAS_3";
    public static final String KEY_KUALITAS_4 = "KUALITAS_4";
    public static final String KEY_KUALITAS_5 = "KUALITAS_5";

    public static final String KEY_KEMAMPUAN_1 = "KEMAMPUAN_1";
    public static final String KEY_KEMAMPUAN_2 = "KEMAMPUAN_2";
    public static final String KEY_KEMAMPUAN_3 = "KEMAMPUAN_3";
    public static final String KEY_KEMAMPUAN_4 = "KEMAMPUAN_4";
    public static final String KEY_KEMAMPUAN_5 = "KEMAMPUAN_5";

    public static final String KEY_KERJA_SAMA_1 = "KERJA_SAMA_1";
    public static final String KEY_KERJA_SAMA_2 = "KERJA_SAMA_2";
    public static final String KEY_KERJA_SAMA_3 = "KERJA_SAMA_3";
    public static final String KEY_KERJA_SAMA_4 = "KERJA_SAMA_4";
    public static final String KEY_KERJA_SAMA_5 = "KERJA_SAMA_5";

    public static final String KEY_TANGGUNG_JAWAB_1 = "TANGGUNG_JAWAB_1";
    public static final String KEY_TANGGUNG_JAWAB_2 = "TANGGUNG_JAWAB_2";
    public static final String KEY_TANGGUNG_JAWAB_3 = "TANGGUNG_JAWAB_3";
    public static final String KEY_TANGGUNG_JAWAB_4 = "TANGGUNG_JAWAB_4";
    public static final String KEY_TANGGUNG_JAWAB_5 = "TANGGUNG_JAWAB_5";
    public static final String KEY_TANGGUNG_JAWAB_6 = "TANGGUNG_JAWAB_6";

    public static final String KEY_KERAJINAN_1 = "KERAJINAN_1";
    public static final String KEY_KERAJINAN_2 = "KERAJINAN_2";
    public static final String KEY_KERAJINAN_3 = "KERAJINAN_3";
    public static final String KEY_KERAJINAN_4 = "KERAJINAN_4";
    public static final String KEY_KERAJINAN_5 = "KERAJINAN_5";

    public static final String KEY_PERIODE = "PERIODE_";




    // Constructor
    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


    /**
     * Create login session
     * */
    public void createLoginSession( String username){ //storelogin
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        editor.putString(KEY_USERNAME, username);
        //  editor.putString(KEY_PASSWORD, password);


        editor.commit();
    }


    public void createTeamSession( String nama,String bidang, String jabatan , String unit , String empID , String imageString , String deptIDTeam){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        editor.putString(KEY_NAME_, nama);
        editor.putString(KEY_BIDANG_, bidang);
        editor.putString(KEY_JABATAN_, jabatan);
        editor.putString(KEY_UNIT_, unit);
        editor.putString(KEY_EMPID_, empID);
        editor.putString(KEY_IMAGE_STRING, imageString);
        editor.putString(KEY_DEPT_ID_STRING, deptIDTeam);

        //  editor.putString(KEY_PASSWORD, password);


        editor.commit();
    }

    public void createProfileSession( String jabatan, String idJabatan , String Pwd , String Form){ //storelogin

        editor.putString(KEY_JABATAN,jabatan);
        editor.putString(KEY_JABATAN_ID,idJabatan);
        editor.putString(KEY_PWD,Pwd);
        editor.putString(KEY_FORM,Form);
        //  editor.putString(KEY_PASSWORD, password);


        editor.commit();
    }

    public void createPeriodeSession( String periode){ //storelogin

        editor.putString(KEY_PERIODE,periode);

        //  editor.putString(KEY_PASSWORD, password);


        editor.commit();
    }




    public void createSkill( String nilai_1, String nilai_2 , String nilai_3 , String nilai_4 , String nilai_5){ 

        editor.putString(KEY_SKILL_1,nilai_1);
        editor.putString(KEY_SKILL_2,nilai_2);
        editor.putString(KEY_SKILL_3,nilai_3);
        editor.putString(KEY_SKILL_4,nilai_4);
        editor.putString(KEY_SKILL_5,nilai_5);

        editor.commit();
    }

    public void createKualitas( String nilai_1, String nilai_2 , String nilai_3 , String nilai_4 , String nilai_5){

        editor.putString(KEY_KUALITAS_1,nilai_1);
        editor.putString(KEY_KUALITAS_2,nilai_2);
        editor.putString(KEY_KUALITAS_3,nilai_3);
        editor.putString(KEY_KUALITAS_4,nilai_4);
        editor.putString(KEY_KUALITAS_5,nilai_5);

        editor.commit();
    }


    public void createKemampuan( String nilai_1, String nilai_2 , String nilai_3 , String nilai_4 , String nilai_5){

        editor.putString(KEY_KEMAMPUAN_1,nilai_1);
        editor.putString(KEY_KEMAMPUAN_2,nilai_2);
        editor.putString(KEY_KEMAMPUAN_3,nilai_3);
        editor.putString(KEY_KEMAMPUAN_4,nilai_4);
        editor.putString(KEY_KEMAMPUAN_5,nilai_5);

        editor.commit();
    }


    public void createKerja_sama( String nilai_1, String nilai_2 , String nilai_3 , String nilai_4 , String nilai_5){

        editor.putString(KEY_KERJA_SAMA_1,nilai_1);
        editor.putString(KEY_KERJA_SAMA_2,nilai_2);
        editor.putString(KEY_KERJA_SAMA_3,nilai_3);
        editor.putString(KEY_KERJA_SAMA_4,nilai_4);
        editor.putString(KEY_KERJA_SAMA_5,nilai_5);

        editor.commit();
    }


    public void createTanggung_jawab( String nilai_1, String nilai_2 , String nilai_3 , String nilai_4 , String nilai_5 , String nilai_6){

        editor.putString(KEY_TANGGUNG_JAWAB_1,nilai_1);
        editor.putString(KEY_TANGGUNG_JAWAB_2,nilai_2);
        editor.putString(KEY_TANGGUNG_JAWAB_3,nilai_3);
        editor.putString(KEY_TANGGUNG_JAWAB_4,nilai_4);
        editor.putString(KEY_TANGGUNG_JAWAB_5,nilai_5);
        editor.putString(KEY_TANGGUNG_JAWAB_6,nilai_6);

        editor.commit();
    }


    public void createKerajinan( String nilai_1, String nilai_2 , String nilai_3 , String nilai_4 , String nilai_5){

        editor.putString(KEY_KERAJINAN_1,nilai_1);
        editor.putString(KEY_KERAJINAN_2,nilai_2);
        editor.putString(KEY_KERAJINAN_3,nilai_3);
        editor.putString(KEY_KERAJINAN_4,nilai_4);
        editor.putString(KEY_KERAJINAN_5,nilai_5);

        editor.commit();
    }




    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     * */
    public void checkLogin(){
        // Check login status
        if(!this.isLoggedIn()){
            // user is not logged in redirect to Login Activity

            Intent i = new Intent(_context, MainActivity.class);

            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);
        }

    }
    
    
    
    
    


    public String getUsername() {
        return pref.getString(KEY_USERNAME, "");
    }

    public String getJabatan() {
        return pref.getString(KEY_JABATAN, "");
    }

    public String getKeyJabatanId() {
        return pref.getString(KEY_JABATAN_ID, "");
    }

    public String getKeyPwd() {
        return pref.getString(KEY_PWD, "");
    }

    public String getKeyForm() {
        return pref.getString(KEY_FORM, "");
    }


    public String getKeyName() {
        return pref.getString(KEY_NAME_, "");
    }

    public String getKeyBidang() {
        return pref.getString(KEY_BIDANG_, "");
    }

    public String getKeyJabatan() {
        return pref.getString(KEY_JABATAN_ID, "");
    }

    public String getKeyUnit() {
        return pref.getString(KEY_UNIT_, "");
    }

    public String getKeyEmpid() {
        return pref.getString(KEY_EMPID_, "");
    }

    public String getKeyImageString() {
        return pref.getString(KEY_IMAGE_STRING, "");
    }

    public String getKeyDeptIdString() {
        return pref.getString(KEY_DEPT_ID_STRING, "");
    }



    public String getKeySkill1() {
        return pref.getString(KEY_SKILL_1, "");
    }
    public String getKeySkill2() {
        return pref.getString(KEY_SKILL_2, "");
    }
    public String getKeySkill3() {
        return pref.getString(KEY_SKILL_3, "");
    }
    public String getKeySkill4() {
        return pref.getString(KEY_SKILL_4, "");
    }
    public String getKeySkill5() {
        return pref.getString(KEY_SKILL_5, "");
    }



    public String getKeyKualitas1() {
        return pref.getString(KEY_KUALITAS_1, "");
    }

    public String getKeyKualitas2() {
        return pref.getString(KEY_KUALITAS_2, "");
    }
    public String getKeyKualitas3() {
        return pref.getString(KEY_KUALITAS_4, "");
    }
    public String getKeyKualitas4() {
        return pref.getString(KEY_KUALITAS_4, "");
    }
    public String getKeyKualitas5() {
        return pref.getString(KEY_KUALITAS_5, "");
    }


    public String getKeyKemampuan1() {
        return pref.getString(KEY_KEMAMPUAN_1, "");
    }
    public String getKeyKemampuan2() {
        return pref.getString(KEY_KEMAMPUAN_2, "");
    }
    public String getKeyKemampuan3() {
        return pref.getString(KEY_KEMAMPUAN_3, "");
    }
    public String getKeyKemampuan4() {
        return pref.getString(KEY_KEMAMPUAN_4, "");
    }
    public String getKeyKemampuan5() {
        return pref.getString(KEY_KEMAMPUAN_5, "");
    }

    public String getKeyKerjaSama1() {
        return pref.getString(KEY_KERJA_SAMA_1, "");
    }
    public String getKeyKerjaSama2() {
        return pref.getString(KEY_KERJA_SAMA_2, "");
    }
    public String getKeyKerjaSama3() {
        return pref.getString(KEY_KERJA_SAMA_3, "");
    }
    public String getKeyKerjaSama4() {
        return pref.getString(KEY_KERJA_SAMA_4, "");
    }
    public String getKeyKerjaSama5() {
        return pref.getString(KEY_KERJA_SAMA_5, "");
    }

    public String getKeyTanggungJawab1() {
        return pref.getString(KEY_TANGGUNG_JAWAB_1, "");
    }
    public String getKeyTanggungJawab2() {
        return pref.getString(KEY_TANGGUNG_JAWAB_2, "");
    }
    public String getKeyTanggungJawab3() {
        return pref.getString(KEY_TANGGUNG_JAWAB_3, "");
    }
    public String getKeyTanggungJawab4() {
        return pref.getString(KEY_TANGGUNG_JAWAB_4, "");
    }
    public String getKeyTanggungJawab5() {
        return pref.getString(KEY_TANGGUNG_JAWAB_5, "");
    }
    public String getKeyTanggungJawab6() {
        return pref.getString(KEY_TANGGUNG_JAWAB_6, "");
    }

    public String getKeyKerajinan1() {
        return pref.getString(KEY_KERAJINAN_1, "");
    }

    public String getKeyKerajinan2() {
        return pref.getString(KEY_KERAJINAN_2, "");
    }

    public String getKeyKerajinan3() {
        return pref.getString(KEY_KERAJINAN_3, "");
    }

    public String getKeyKerajinan4() {
        return pref.getString(KEY_KERAJINAN_4, "");
    }

    public String getKeyKerajinan5() {
        return pref.getString(KEY_KERAJINAN_5, "");
    }


    public String getKeyPeriode() {
        return pref.getString(KEY_PERIODE, "");
    }
    

    
    
    
    
    
    
    
    



    /**
     * Get stored session data
     * */
    public HashMap getUserDetails(){

        HashMap<String, String> user = new HashMap<>();
        // user name
        user.put(KEY_USERNAME, pref.getString(KEY_USERNAME, ""));

        // return user
        return user;
    }

    /**
     * Hapus Data Session
     * */
    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

    }

    /**
     * Quick check for login
     * **/
    // Get Login State
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }
}