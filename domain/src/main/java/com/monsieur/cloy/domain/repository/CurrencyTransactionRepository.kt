package com.monsieur.cloy.domain.repository

import com.monsieur.cloy.domain.models.CurrencyTransaction
import com.monsieur.cloy.domain.models.common.UpdateCurrencyTransactionDataResult
import kotlinx.coroutines.flow.Flow

interface CurrencyTransactionRepository {

    fun getCurrencyTransactionsFlow(): Flow<List<CurrencyTransaction>>

    suspend fun deleteAllData()

    suspend fun updateCurrencyTransactionData(accessToken: String): UpdateCurrencyTransactionDataResult

    suspend fun insertCurrencyTransactions(currencyTransaction: List<CurrencyTransaction>)

}