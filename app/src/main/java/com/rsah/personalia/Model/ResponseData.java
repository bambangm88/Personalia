package com.rsah.personalia.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ResponseData {


    @SerializedName("dataLogin")
    @Expose
    private List<ResponseEntityLogin> dataLogin = null;

    public List<ResponseEntityLogin> getDataLogin() {
        return dataLogin;
    }

    public void setDataLogin(List<ResponseEntityLogin> dataLogin) {
        this.dataLogin = dataLogin;
    }



    @SerializedName("dataVersion")
    @Expose
    private List<ResponseEntityVersion> dataVersion = null;

    public List<ResponseEntityVersion> getDataVersion() {
        return dataVersion;
    }

    public void setDataVersion(List<ResponseEntityVersion> dataVersion) {
        this.dataVersion = dataVersion;
    }

    @SerializedName("dataLogout")
    @Expose
    private List<ResponseEntityLogout> dataLogout = null;

    public List<ResponseEntityLogout> getDataLogout() {
        return dataLogout;
    }

    public void setDataLogout(List<ResponseEntityLogout> dataLogout) {
        this.dataLogout = dataLogout;
    }




    @SerializedName("dataId")
    @Expose
    private List<ResponseEntityCheckID> dataId= null;


    public List<ResponseEntityCheckID> getDataId() {
        return dataId;
    }

    public void setDataId(List<ResponseEntityCheckID> dataId) {
        this.dataId = dataId;
    }


    @SerializedName("dataDaftar")
    @Expose
    private List<ResponseEntityLogin> dataDaftar = null;


    public List<ResponseEntityLogin> getDataDaftar() {
        return dataDaftar;
    }

    public void setDataDaftar(List<ResponseEntityLogin> dataDaftar) {
        this.dataDaftar = dataDaftar;
    }


    @SerializedName("dataProfile")
    @Expose
    private List<ResponseEntityProfile> dataProfile= null;

    public List<ResponseEntityProfile> getDataProfile() {
        return dataProfile;
    }

    public void setDataProfile(List<ResponseEntityProfile> dataProfile) {
        this.dataProfile = dataProfile;
    }


    @SerializedName("dataTeam")
    @Expose
    private List<ResponseEntityTeam> dataTeam= null;

    public List<ResponseEntityTeam> getDataTeam() {
        return dataTeam;
    }

    public void setDataTeam(List<ResponseEntityTeam> dataTeam) {
        this.dataTeam = dataTeam;
    }



    @SerializedName("dataGaji")
    @Expose
    private List<ResponseEntityGaji> dataGaji= null;

    public List<ResponseEntityGaji> getDataGaji() {
        return dataGaji;
    }

    public void setDataGaji(List<ResponseEntityGaji> dataGaji) {
        this.dataGaji = dataGaji;
    }

    @SerializedName("dataPeriode")
    @Expose
    private List<ResponseEntityPeriode> dataPeriode= null;


    public List<ResponseEntityPeriode> getDataPeriode() {
        return dataPeriode;
    }

    public void setDataPeriode(List<ResponseEntityPeriode> dataPeriode) {
        this.dataPeriode = dataPeriode;
    }





    @SerializedName("Success")
    @Expose
    private String Success ;

    public String getSuccess() {
        return Success;
    }

    public void setSuccess(String success) {
        Success = success;
    }


    @SerializedName("total_a")
    @Expose
    private String total_a ;

    @SerializedName("total_b")
    @Expose
    private String total_b ;


    @SerializedName("totalAll")
    @Expose
    private String totalAll ;

    @SerializedName("tmk")
    @Expose
    private String tmk ;


    public String getTmk() {
        return tmk;
    }

    public void setTmk(String tmk) {
        this.tmk = tmk;
    }



    public String getTotal_a() {
        return total_a;
    }

    public void setTotal_a(String total_a) {
        this.total_a = total_a;
    }

    public String getTotal_b() {
        return total_b;
    }

    public void setTotal_b(String total_b) {
        this.total_b = total_b;
    }

    public String getTotalAll() {
        return totalAll;
    }

    public void setTotalAll(String totalAll) {
        this.totalAll = totalAll;
    }





    @SerializedName("dataPenilaian")
    @Expose
    private List<ResponseEntityPenilaian> dataPenilaian= null;


    public List<ResponseEntityPenilaian> getDataPenilaian() {
        return dataPenilaian;
    }

    public void setDataPenilaian(List<ResponseEntityPenilaian> dataPenilaian) {
        this.dataPenilaian = dataPenilaian;
    }


    @SerializedName("dataSignature")
    @Expose
    private List<ResponseEntitySignature> dataSignature= null;


    public List<ResponseEntitySignature> getDataSignature() {
        return dataSignature;
    }

    public void setDataSignature(List<ResponseEntitySignature> dataSignature) {
        this.dataSignature = dataSignature;
    }





}







