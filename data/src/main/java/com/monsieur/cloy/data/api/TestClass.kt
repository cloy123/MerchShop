package com.monsieur.cloy.data.api

import com.monsieur.cloy.data.api.models.requests.RefreshTokenRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TestClass {
    fun start(): String{
//        try {
            val response = MerchShopApi().getEventsInfo("eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJJZCI6IjhiNDQ0MzBiLTNjM2ItNGIxNS05NDQ2LTQ3MDQ5MzExMDMxNyIsIkVtYWlsIjoic3R1ZGVudEBzdHVkZW50LnN0dWRlbnQiLCJSb2xlTmFtZSI6InN0dWRlbnQiLCJuYmYiOjE2ODI0NTQyNzUsImV4cCI6MTY4MjQ1Nzg3NSwiaWF0IjoxNjgyNDU0Mjc1LCJpc3MiOiJNZXJjaFNob3BBcGkifQ.Xh4ys0rd8JGiX2gVUrTlVvha0eRWPeD58ELLtUszlN1pA1gtfipOuAJ-2Lvsh9sv7QWWxY4VpYyiWVaPiTE4yg")
            if(response.isSuccessful && response.body() != null){
                return response.body()!!.events[0].eventResponsibles[0].lastName
            }else{
                return "not successful " + response.raw()
            }
//        }catch (e: Exception){
//            return e.message!!
//        }
    }
}