package com.monsieur.cloy.data.api

import com.monsieur.cloy.data.api.models.requests.RefreshTokenRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TestClass {
    fun start(): String{
//        try {
            val response = MerchShopApi().getOrdersInfo("eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJJZCI6IjhiNDQ0MzBiLTNjM2ItNGIxNS05NDQ2LTQ3MDQ5MzExMDMxNyIsIkVtYWlsIjoic3R1ZGVudEBzdHVkZW50LnN0dWRlbnQiLCJSb2xlTmFtZSI6InN0dWRlbnQiLCJuYmYiOjE2ODI0NTc5MDYsImV4cCI6MTY4MjQ2MTUwNiwiaWF0IjoxNjgyNDU3OTA2LCJpc3MiOiJNZXJjaFNob3BBcGkifQ.0QJG4Bzy_XR81ur4BPFujxog9bfDQtwK038Ylyx7QMhyZ9B2lZCe7FmkcqvrU_naj200Abd9-AGpAk4AivUzGQ")
            if(response.isSuccessful && response.body() != null){
                return response.body()!!.orders[0].orderSum.toString()
            }else{
                return "not successful " + response.raw()
            }
//        }catch (e: Exception){
//            return e.message!!
//        }
    }
}