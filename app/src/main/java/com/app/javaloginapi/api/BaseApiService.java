package com.app.javaloginapi.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface BaseApiService {

    @FormUrlEncoded
    @POST("submitLogin")
    Call<ResponseBody> submitLogin(
            @Field("username") String username,
            @Field("password") String password
    );

    @POST("addDivisi")
    Call<ResponseBody> addDivisi(
            @Field("nama_divisi") String add_divisi
    );

}
