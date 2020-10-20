package com.rsah.personalia.Model;

import com.google.gson.annotations.SerializedName;

public class ResponseEntityTeam {



    @SerializedName("sIdCard")
    private String sIdCard;


    @SerializedName("sEmpID")
    private String sEmpID ;

    @SerializedName("sDeptID")
    private String sDeptID ;

    public String getsDeptID() {
        return sDeptID;
    }

    public void setsDeptID(String sDeptID) {
        this.sDeptID = sDeptID;
    }



    public String getsEmpID() {
        return sEmpID;
    }

    public void setsEmpID(String sEmpID) {
        this.sEmpID = sEmpID;
    }


    public String getsIdCard() {
        return sIdCard;
    }

    public void setsIdCard(String sIdCard) {
        this.sIdCard = sIdCard;
    }


    @SerializedName("sFisrtName")
    private String sFisrtName;

    @SerializedName("sPlaceOfBirthDay")
    private String sPlaceOfBirthDay;


    @SerializedName("dDateOfBirthDay")
    private String dDateOfBirthDay ;


    @SerializedName("sAddress")
    private String sAddress;

    @SerializedName("jabatan")
    private String jabatan;

    @SerializedName("Bidang")
    private String Bidang;

    @SerializedName("Unit")
    private String Unit;

    @SerializedName("foto")
    private String foto;

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }



    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }




    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getBidang() {
        return Bidang;
    }

    public void setBidang(String bidang) {
        this.Bidang = bidang;
    }





    @SerializedName("sex")
    private String sex;
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }



    public String getsFisrtName() {
        return sFisrtName;
    }

    public void setsFisrtName(String sFisrtName) {
        this.sFisrtName = sFisrtName;
    }

    public String getsPlaceOfBirthDay() {
        return sPlaceOfBirthDay;
    }

    public void setsPlaceOfBirthDay(String sPlaceOfBirthDay) {
        this.sPlaceOfBirthDay = sPlaceOfBirthDay;
    }

    public String getdDateOfBirthDay() {
        return dDateOfBirthDay;
    }

    public void setdDateOfBirthDay(String dDateOfBirthDay) {
        this.dDateOfBirthDay = dDateOfBirthDay;
    }

    public String getsAddress() {
        return sAddress;
    }

    public void setsAddress(String sAddress) {
        this.sAddress = sAddress;
    }
























}
