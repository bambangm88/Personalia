package com.rsah.personalia.Model;

import com.google.gson.annotations.SerializedName;

public class ResponseEntityProfile {



    @SerializedName("sIdCard")
    private String sIdCard;

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


    @SerializedName("sDeptID")
    private String sDeptID;

    public String getsDeptID() {
        return sDeptID;
    }

    public void setsDeptID(String sDeptID) {
        this.sDeptID = sDeptID;
    }


    @SerializedName("jabatan")
    private String jabatan;


    @SerializedName("sex")
    private String sex;


    @SerializedName("Pwd")
    private String Pwd;


    @SerializedName("email")
    private String email;

    @SerializedName("sMobilePhone")
    private String sMobilePhone;

    @SerializedName("Bidang")
    private String Bidang;

    @SerializedName("Unit")
    private String Unit;

    @SerializedName("foto")
    private String foto;


    @SerializedName("Form")
    private String Form;

    @SerializedName("isRegistrationFace")
    private String isRegistrationFace;

    public String getIsRegistrationFace() {
        return isRegistrationFace;
    }

    public void setIsRegistrationFace(String isRegistrationFace) {
        this.isRegistrationFace = isRegistrationFace;
    }



    public String getForm() {
        return Form;
    }

    public void setForm(String form) {
        Form = form;
    }



    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }



    public String getBidang() {
        return Bidang;
    }

    public void setBidang(String bidang) {
        Bidang = bidang;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }





    public String getsMobilePhone() {
        return sMobilePhone;
    }

    public void setsMobilePhone(String sMobilePhone) {
        this.sMobilePhone = sMobilePhone;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }




    public String getPwd() {
        return Pwd;
    }

    public void setPwd(String pwd) {
        Pwd = pwd;
    }






    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }




    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
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
