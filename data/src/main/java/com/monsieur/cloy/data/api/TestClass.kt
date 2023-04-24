package com.monsieur.cloy.data.api

import com.monsieur.cloy.data.api.models.requests.RefreshTokenRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TestClass {
    fun start(): String{
//        try {
            val response = MerchShopApi().getCatalogInfo("eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJJZCI6IjhiNDQ0MzBiLTNjM2ItNGIxNS05NDQ2LTQ3MDQ5MzExMDMxNyIsIkVtYWlsIjoic3R1ZGVudEBzdHVkZW50LnN0dWRlbnQiLCJSb2xlTmFtZSI6InN0dWRlbnQiLCJuYmYiOjE2ODIzNjk2MTgsImV4cCI6MTY4MjM3MzIxOCwiaWF0IjoxNjgyMzY5NjE4LCJpc3MiOiJNZXJjaFNob3BBcGkifQ.cbnOMNYag3FhyaCcGcxBR8V0Z9q-TSYloTwEbEIznGxPqg-4S6nJfrX1qzKqAAnBgUPRF8ZeirykR3keZa_upA")
            if(response.isSuccessful && response.body() != null){
                return response.body()!!.products[0].productType.name
            }else{
                return "not successful " + response.raw()
            }
//        }catch (e: Exception){
//            return e.message!!
//        }
    }
}