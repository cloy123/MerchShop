package com.monsieur.cloy.data.api.interfaces

import com.monsieur.cloy.data.api.models.requests.LoginRequest
import com.monsieur.cloy.data.api.models.requests.LogoutRequest
import com.monsieur.cloy.data.api.models.requests.RefreshTokenRequest
import com.monsieur.cloy.data.api.models.responses.GetCatalogInfoResponse
import com.monsieur.cloy.data.api.models.responses.LoginResponse
import com.monsieur.cloy.data.api.models.responses.LogoutResponse
import com.monsieur.cloy.data.api.models.responses.RefreshTokenResponse
import retrofit2.Call
import retrofit2.http.*

interface MerchShopApiRequests {
    @POST("auth/login")
    fun login(@Body loginBody: LoginRequest): Call<LoginResponse>
    @POST("auth/refreshToken")
    fun refreshToken(@Body refreshTokenBody: RefreshTokenRequest): Call<RefreshTokenResponse>
    @POST("auth/logout")
    fun logout(@Body logoutBody: LogoutRequest): Call<LogoutResponse>

    @GET("catalog/getCatalogInfo")
    fun getCatalogInfo(@Header("Authorization") authorization: String): Call<GetCatalogInfoResponse>
}