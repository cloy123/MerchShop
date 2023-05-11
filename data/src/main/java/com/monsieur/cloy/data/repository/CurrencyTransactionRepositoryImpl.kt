package com.monsieur.cloy.data.repository

import com.monsieur.cloy.data.api.MerchShopApi
import com.monsieur.cloy.data.mappers.CurrencyTransactionMapper
import com.monsieur.cloy.data.storage.CurrencyTransactionStorage
import com.monsieur.cloy.domain.models.CurrencyTransaction
import com.monsieur.cloy.domain.models.common.UpdateCurrencyTransactionDataResult
import com.monsieur.cloy.domain.repository.CurrencyTransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CurrencyTransactionRepositoryImpl(private val currencyTransactionStorage: CurrencyTransactionStorage, private val merchShopApi: MerchShopApi): CurrencyTransactionRepository {

    private val currencyTransactionMapper = CurrencyTransactionMapper()

    override fun getCurrencyTransactionsFlow(): Flow<List<CurrencyTransaction>> {
        return currencyTransactionStorage.getAllCurrencyTransactions().map { l -> l.map { currencyTransactionMapper.toDomainModel(it) } }
    }

    override suspend fun deleteAllData() {
        currencyTransactionStorage.deleteAllCurrencyTransactions()
    }

    override suspend fun updateCurrencyTransactionData(accessToken: String): UpdateCurrencyTransactionDataResult {
        var isSuccessful: Boolean
        var currencyTransactions: List<CurrencyTransaction>?
        var code: Int
        try {
            val response = merchShopApi.getCurrencyTransactionsInfo(accessToken)
            isSuccessful = response.isSuccessful
            code = response.code()
            if (response.isSuccessful && response.body() != null) {
                currencyTransactions = response.body()!!.currencyTransactions.map { currencyTransactionMapper.currencyTransactionDtoToCurrencyTransaction(it) }
            } else {
                currencyTransactions = null
            }
        } catch (e: Exception) {
            code = -1
            isSuccessful = false
            currencyTransactions = null
        }
        return UpdateCurrencyTransactionDataResult(currencyTransactions, isSuccessful, code)
    }

    override suspend fun insertCurrencyTransactions(currencyTransaction: List<CurrencyTransaction>) {
        currencyTransactionStorage.insertCurrencyTransactions(currencyTransaction.map { currencyTransactionMapper.toDataModel(it) })
    }
}