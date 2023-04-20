package com.monsieur.cloy.data.api

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TestClass {
    fun start(): String{
        try {
            val response = MerchShopApi().login("student@student.student", "pass")
            if(response.isSuccessful && response.body() != null){
                return response.body()!!.token
            }else{
                return "not successful " + response.raw()
            }
        }catch (e: Exception){
            return e.message!!
        }
    }
}