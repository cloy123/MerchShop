package com.monsieur.cloy.data.api

import com.monsieur.cloy.data.api.models.requests.RefreshTokenRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TestClass {
    fun start(): String{
//        try {
            val response = MerchShopApi().getNotificationsInfo("eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJJZCI6IjhiNDQ0MzBiLTNjM2ItNGIxNS05NDQ2LTQ3MDQ5MzExMDMxNyIsIkVtYWlsIjoic3R1ZGVudEBzdHVkZW50LnN0dWRlbnQiLCJSb2xlTmFtZSI6InN0dWRlbnQiLCJuYmYiOjE2ODI2MjkyMjIsImV4cCI6MTY4MjYzMjgyMiwiaWF0IjoxNjgyNjI5MjIyLCJpc3MiOiJNZXJjaFNob3BBcGkifQ.6vWaEqq7r2pw4usxPVjpOF1cvjofxsIAmJ9GxpHHTCj6v9glNrC-9mUVkQoo4mif98RcYr4nKs0wJtwKKkhbQw")
            if(response.isSuccessful && response.body() != null){
                return response.body()!!.notifications[0].message
            }else{
                return "not successful " + response.raw()
            }
//        }catch (e: Exception){
//            return e.message!!
//        }
    }
}