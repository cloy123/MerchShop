package com.monsieur.cloy.data.api

import com.google.gson.GsonBuilder
import com.monsieur.cloy.data.api.interfaces.MerchShopApiRequests
import com.monsieur.cloy.data.api.models.requests.LoginRequest
import com.monsieur.cloy.data.api.models.requests.LogoutRequest
import com.monsieur.cloy.data.api.models.requests.RefreshTokenRequest
import com.monsieur.cloy.data.api.models.responses.*
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Modifier
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.*


class MerchShopApi {
    private val okHttpClient: OkHttpClient = getUnsafeOkHttpClient()
    private val retrofit: Retrofit = Retrofit.Builder().baseUrl("https://192.168.0.105:7245/api/")
        .client(okHttpClient)
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder()
                    .serializeNulls()
                    .excludeFieldsWithModifiers(
                        Modifier.FINAL,
                        Modifier.TRANSIENT, Modifier.STATIC
                    )
                    .create()
            )
        ).build()

    fun login(loginRequest: LoginRequest): Response<LoginResponse> {
        val merchShopApiRequests = retrofit.create(MerchShopApiRequests::class.java)
        val response = merchShopApiRequests.login(loginRequest)
        return response.execute()
    }

    fun refreshToken(refreshTokenRequest: RefreshTokenRequest): Response<RefreshTokenResponse>{
        val merchShopApiRequests = retrofit.create(MerchShopApiRequests::class.java)
        val response = merchShopApiRequests.refreshToken(refreshTokenRequest)
        return response.execute()
    }

    fun logout(logoutRequest: LogoutRequest): Response<LogoutResponse>{
        val merchShopApiRequests = retrofit.create(MerchShopApiRequests::class.java)
        val response = merchShopApiRequests.logout(logoutRequest)

        return response.execute()
    }

    fun getCatalogInfo(token: String): Response<GetCatalogInfoResponse>{
        val merchShopApiRequests = retrofit.create(MerchShopApiRequests::class.java)
        val response = merchShopApiRequests.getCatalogInfo("Bearer $token")
        return response.execute()
    }

    fun getEventsInfo(token: String): Response<GetEventsInfoResponse>{
        val merchShopApiRequests = retrofit.create(MerchShopApiRequests::class.java)
        val response = merchShopApiRequests.getEventsInfo("Bearer $token")
        return response.execute()
    }

    fun getUserInfo(token: String): Response<GetUserInfoResponse>{
        val merchShopApiRequests = retrofit.create(MerchShopApiRequests::class.java)
        val response = merchShopApiRequests.getUserInfo("Bearer $token")
        return response.execute()
    }

    fun getOrdersInfo(token: String): Response<GetOrdersInfoResponse>{
        val merchShopApiRequests = retrofit.create(MerchShopApiRequests::class.java)
        val response = merchShopApiRequests.getOrdersInfo("Bearer $token")
        return response.execute()
    }

    fun getNotificationsInfo(token: String): Response<GetNotificationsInfoResponse>{
        val merchShopApiRequests = retrofit.create(MerchShopApiRequests::class.java)
        val response = merchShopApiRequests.getNotificationsInfo("Bearer $token")
        return response.execute()
    }
}








private fun getUnsafeOkHttpClient(): OkHttpClient {
    return try {
        // Create a trust manager that does not validate certificate chains
        val trustAllCerts: Array<TrustManager> = arrayOf<TrustManager>(
            object : X509TrustManager {
                @Throws(CertificateException::class)
                override fun checkClientTrusted(
                    chain: Array<X509Certificate?>?,
                    authType: String?
                ) {
                }

                @Throws(CertificateException::class)
                override fun checkServerTrusted(
                    chain: Array<X509Certificate?>?,
                    authType: String?
                ) {
                }

                override fun getAcceptedIssuers(): Array<X509Certificate> {
                    return arrayOf()
                }

            }
        )

        // Install the all-trusting trust manager
        val sslContext: SSLContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, SecureRandom())
        // Create an ssl socket factory with our all-trusting manager
        val sslSocketFactory: SSLSocketFactory = sslContext.socketFactory
        val builder = OkHttpClient.Builder()
        builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
        builder.hostnameVerifier { hostname, session -> true }
        builder.build()
    } catch (e: Exception) {
        throw RuntimeException(e)
    }
}