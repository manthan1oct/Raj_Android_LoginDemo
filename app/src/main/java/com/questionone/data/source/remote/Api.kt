package com.questionone.data.source.remote

import com.questionone.data.ResponseModel
import io.reactivex.Observable
import retrofit2.adapter.rxjava2.Result
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    @POST("api/login")
    @FormUrlEncoded
    fun login(@Header("IMSI") IMSI: String,
              @Header("IMEI") IMEI: String,
              @Field("email") email: String,
              @Field("password") password: String): Observable<Result<ResponseModel>>

}