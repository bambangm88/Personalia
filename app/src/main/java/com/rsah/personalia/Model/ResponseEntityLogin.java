package com.rsah.personalia.Model;

import com.google.gson.annotations.SerializedName;

public class ResponseEntityLogin {


    @SerializedName("FisrtName")
    private String FisrtName;

    @SerializedName("callback")
    private String callback;

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }


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
