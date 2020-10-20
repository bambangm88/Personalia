package com.rsah.personalia.Model;

import com.google.gson.annotations.SerializedName;

public class ResponseEntityPeriode {



    @SerializedName("sPeriodeID")
    private String sPeriodeID;



    @SerializedName("sPeriodeDesc")
    private String sPeriodeDesc;


    public String getsPeriodeDesc() {
        return sPeriodeDesc;
    }

    public void setsPeriodeDesc(String sPeriodeDesc) {
        this.sPeriodeDesc = sPeriodeDesc;
    }



    public String getsPeriodeID() {
        return sPeriodeID;
    }

    public void setsPeriodeID(String sPeriodeID) {
        this.sPeriodeID = sPeriodeID;
    }
























}
