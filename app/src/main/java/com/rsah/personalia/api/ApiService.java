package com.rsah.personalia.api;



import com.rsah.personalia.Model.ResponseData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;


public interface ApiService {

    @GET("listCompany")
    Call<ResponseData> fetchCompany();



    @FormUrlEncoded
    @POST("login")
    Call<ResponseData> Login(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("absent")
    Call<ResponseData> Absent(@Field("request") String request, @Field("employeeid") String employeeid);

    @FormUrlEncoded
    @POST("logout")
    Call<ResponseData> Logout(@Field("username") String username , @Field("param") String param);

    @FormUrlEncoded
    @POST("daftar")
    Call<ResponseData> Daftar(@Field("id") String id, @Field("username") String username,
                              @Field("email") String email, @Field("password") String password,
                              @Field("foto") String foto);


    @FormUrlEncoded
    @POST("checkId")
    Call<ResponseData> CheckId(@Field("username") String username);

    @FormUrlEncoded
    @POST("checkVersion")
    Call<ResponseData> CheckVersion(@Field("app") String app);




    @FormUrlEncoded
    @POST("ubahPwd")
    Call<ResponseData> ubahPwd(
                               @Field("user") String user,
                               @Field("pass_old") String pass_old,
                               @Field("pass_baru") String pass_baru);


    @FormUrlEncoded
    @POST("displayProfile")
    Call<ResponseData> getProfile(@Field("user") String user);

    @FormUrlEncoded
    @POST("displayTeam")
    Call<ResponseData> getTeam(@Field("team") String user);

    @FormUrlEncoded
    @POST("displayGaji")
    Call<ResponseData> getGaji(@Field("user") String user , @Field("periode") String periode);

    @FormUrlEncoded
    @POST("slipGaji/download")
    Call<ResponseData> downloadGaji(@Field("user") String user , @Field("periode") String periode);

    @FormUrlEncoded
    @POST("slipGaji_Thr/download")
    Call<ResponseData> downloadGajiThr(@Field("user") String user , @Field("periode") String periode);

    @FormUrlEncoded
    @POST("displayGajiThr")
    Call<ResponseData> getGajiThr(@Field("user") String user , @Field("periode") String periode);


    @FormUrlEncoded
    @POST("slipGaji_Lembur/download")
    Call<ResponseData> downloadGajiLembur(@Field("user") String user , @Field("periode") String periode);

    @FormUrlEncoded
    @POST("displayGajiLembur")
    Call<ResponseData> getGajiLembur(@Field("user") String user , @Field("periode") String periode);


    @FormUrlEncoded
    @POST("displayPeriodeList")
    Call<ResponseData> getperiode(@Field("tahun") String tahun);


    @FormUrlEncoded
    @POST("displayPeriodePerformance")
    Call<ResponseData> getperiodePerformance(@Field("periode") String periode ,@Field("empID") String empID);



    @FormUrlEncoded
    @POST("insertPenilaian")
    Call<ResponseData> insertPenilaian(
                                @Field("kepala") String kepala,
                                @Field("periode") String periode,

                                @Field("skill_1") String skill_1,
                                @Field("skill_2") String skill_2,
                                @Field("skill_3") String skill_3,
                                @Field("skill_4") String skill_4,
                                @Field("skill_5") String skill_5,

                                @Field("kualitas_1") String kualitas_1,
                                @Field("kualitas_2") String kualitas_2,
                                @Field("kualitas_3") String kualitas_3,
                                @Field("kualitas_4") String kualitas_4,
                                @Field("kualitas_5") String kualitas_5,

                                @Field("kemampuan_1") String kemampuan_1,
                                @Field("kemampuan_2") String kemampuan_2,
                                @Field("kemampuan_3") String kemampuan_3,
                                @Field("kemampuan_4") String kemampuan_4,
                                @Field("kemampuan_5") String kemampuan_5,

                                @Field("kerja_sama_1") String kerja_sama_1,
                                @Field("kerja_sama_2") String kerja_sama_2,
                                @Field("kerja_sama_3") String kerja_sama_3,
                                @Field("kerja_sama_4") String kerja_sama_4,
                                @Field("kerja_sama_5") String kerja_sama_5,

                                @Field("tanggung_jawab_1") String tanggung_jawab_1,
                                @Field("tanggung_jawab_2") String tanggung_jawab_2,
                                @Field("tanggung_jawab_3") String tanggung_jawab_3,
                                @Field("tanggung_jawab_4") String tanggung_jawab_4,
                                @Field("tanggung_jawab_5") String tanggung_jawab_5,
                                @Field("tanggung_jawab_6") String tanggung_jawab_6,

                                @Field("kerajinan_1") String kerajinan_1,
                                @Field("kerajinan_2") String kerajinan_2,
                                @Field("kerajinan_3") String kerajinan_3,
                                @Field("kerajinan_4") String kerajinan_4,
                                @Field("kerajinan_5") String kerajinan_5,

                                @Field("ttd") String ttd,
                                @Field("empID") String empID,
                                @Field("status") String status
                         );

    @FormUrlEncoded
    @POST("displayPenilaian")
    Call<ResponseData> getpenilaian(@Field("requestForm") String requestForm,@Field("periode") String periode,@Field("empID") String empID);

    @FormUrlEncoded
    @POST("displayCheckPenilaian")
    Call<ResponseData> checkPenilaian(@Field("periode") String periode,@Field("empID") String empID);


    @FormUrlEncoded
    @POST("checkSignature")
    Call<ResponseData> checkSignature(@Field("periode") String periode,@Field("empID") String empID);

    @FormUrlEncoded
    @POST("updateRegistrationFace")
    Call<ResponseData> registrationFace(@Field("status") String periode,@Field("employeeid") String empID);

    @FormUrlEncoded
    @POST("InsertTtdEmployee")
    Call<ResponseData> insertTtdEmployee(@Field("periode") String periode,@Field("empID") String empID,@Field("ttd") String ttd);


    @FormUrlEncoded
    @POST("editPenilaian")
    Call<ResponseData> editPenilaian(
            @Field("periode") String periode,
            @Field("empID") String empID,
            @Field("skill_1") String skill_1,
            @Field("skill_2") String skill_2,
            @Field("skill_3") String skill_3,
            @Field("skill_4") String skill_4,
            @Field("skill_5") String skill_5,

            @Field("kualitas_1") String kualitas_1,
            @Field("kualitas_2") String kualitas_2,
            @Field("kualitas_3") String kualitas_3,
            @Field("kualitas_4") String kualitas_4,
            @Field("kualitas_5") String kualitas_5,

            @Field("kemampuan_1") String kemampuan_1,
            @Field("kemampuan_2") String kemampuan_2,
            @Field("kemampuan_3") String kemampuan_3,
            @Field("kemampuan_4") String kemampuan_4,
            @Field("kemampuan_5") String kemampuan_5,

            @Field("kerja_sama_1") String kerja_sama_1,
            @Field("kerja_sama_2") String kerja_sama_2,
            @Field("kerja_sama_3") String kerja_sama_3,
            @Field("kerja_sama_4") String kerja_sama_4,
            @Field("kerja_sama_5") String kerja_sama_5,

            @Field("tanggung_jawab_1") String tanggung_jawab_1,
            @Field("tanggung_jawab_2") String tanggung_jawab_2,
            @Field("tanggung_jawab_3") String tanggung_jawab_3,
            @Field("tanggung_jawab_4") String tanggung_jawab_4,
            @Field("tanggung_jawab_5") String tanggung_jawab_5,
            @Field("tanggung_jawab_6") String tanggung_jawab_6,

            @Field("kerajinan_1") String kerajinan_1,
            @Field("kerajinan_2") String kerajinan_2,
            @Field("kerajinan_3") String kerajinan_3,
            @Field("kerajinan_4") String kerajinan_4,
            @Field("kerajinan_5") String kerajinan_5



    );


}
