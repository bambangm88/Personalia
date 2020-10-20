package com.rsah.personalia.Model;

import com.google.gson.annotations.SerializedName;

public class ResponseEntityCheckID {



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
