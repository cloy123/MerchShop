package com.monsieur.cloy.domain.usecase

import com.monsieur.cloy.domain.models.CurrencyTransaction
import com.monsieur.cloy.domain.models.Product
import com.monsieur.cloy.domain.repository.CurrencyTransactionRepository
import com.monsieur.cloy.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class GetCurrencyTransactionsUseCase(private val currencyTransactionRepository: CurrencyTransactionRepository) {
    fun execute(): Flow<List<CurrencyTransaction>> {
        return currencyTransactionRepository.getCurrencyTransactionsFlow()
    }
}