package com.monsieur.cloy.data.api.interfaces

import com.monsieur.cloy.data.api.Login
import com.monsieur.cloy.data.api.models.LoginResponse
import retrofit2.Call
import retrofit2.http.*

interface MerchShopApiRequests {
    @POST("auth/login")
    fun login(@Body loginBody: Login): Call<LoginResponse>
//    fun login(@Query("email") email: String, @Query("password") password: String): Call<LoginResponse>
}