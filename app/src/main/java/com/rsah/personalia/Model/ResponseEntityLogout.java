package com.rsah.personalia.Model;

import com.google.gson.annotations.SerializedName;

public class ResponseEntityLogout {


    @SerializedName("FisrtName")
    private String FisrtName;




    @SerializedName("sEmpID")
    private String sEmpID;


    public String getsEmpID() {
        return sEmpID;
    }

    public void setsEmpID(String sEmpID) {
        this.sEmpID = sEmpID;
    }



    public String getFisrtName() {
        return FisrtName;
    }

    public void setFisrtName(String fisrtName) {
        FisrtName = fisrtName;
    }


















}
