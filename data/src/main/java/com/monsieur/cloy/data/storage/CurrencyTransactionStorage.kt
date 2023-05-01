package com.monsieur.cloy.data.storage

import com.monsieur.cloy.data.db.dao.CurrencyTransactionDao
import com.monsieur.cloy.data.storage.models.CurrencyTransactionEntity
import kotlinx.coroutines.flow.Flow

class CurrencyTransactionStorage(private val currencyTransactionDao: CurrencyTransactionDao) {


    suspend fun insertCurrencyTransactions(currencyTransactions: List<CurrencyTransactionEntity>){
        currencyTransactionDao.insertCurrencyTransactions(currencyTransactions)
    }

    fun getAllCurrencyTransactions(): Flow<List<CurrencyTransactionEntity>>{
        return currencyTransactionDao.getAllCurrencyTransactions()
    }

    suspend fun deleteAllCurrencyTransactions(){
        currencyTransactionDao.deleteAllCurrencyTransactions()
    }
}