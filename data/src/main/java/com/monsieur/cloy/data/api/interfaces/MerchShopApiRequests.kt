package com.monsieur.cloy.data.api.interfaces

import com.monsieur.cloy.data.api.models.requests.*
import com.monsieur.cloy.data.api.models.responses.*
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

    @GET("events/getEventsInfo")
    fun getEventsInfo(@Header("Authorization") authorization: String): Call<GetEventsInfoResponse>

    @GET("users/getUserInfo")
    fun getUserInfo(@Header("Authorization") authorization: String): Call<GetUserInfoResponse>

    @GET("orders/getOrdersInfo")
    fun getOrdersInfo(@Header("Authorization") authorization: String): Call<GetOrdersInfoResponse>

    @GET("notifications/getNotificationsInfo")
    fun getNotificationsInfo(@Header("Authorization") authorization: String): Call<GetNotificationsInfoResponse>

    @GET("currencyTransactions/getCurrencyTransactionsInfo")
    fun getCurrencyTransactionsInfo(@Header("Authorization") authorization: String): Call<GetCurrencyTransactionsInfoResponse>

    @POST("orders/create")
    fun createOrder(@Header("Authorization") authorization: String, @Body createOrderRequest: CreateOrderRequest): Call<CreateOrderResponse>

    @POST("orders/cancelOrder")
    fun cancelOrder(@Header("Authorization") authorization: String, @Body cancelOrderRequest: CancelOrderRequest): Call<CancelOrderResponse>

    @POST("events/noteEventVisit")
    fun noteVisit(@Header("Authorization") authorization: String, @Body noteVisitRequest: NoteVisitRequest): Call<NoteVisitResponse>

    @POST("events/signupEvent")
    fun signupEvent(@Header("Authorization") authorization: String, @Body signupEventRequest: SignupEventRequest): Call<SignupEventResponse>
}