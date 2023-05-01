package com.monsieur.cloy.data.mappers

import com.monsieur.cloy.data.storage.models.BasketItemEntity
import com.monsieur.cloy.data.storage.models.BasketItemWithProduct
import com.monsieur.cloy.data.storage.models.CurrencyTransactionEntity
import com.monsieur.cloy.domain.models.BasketItem
import com.monsieur.cloy.domain.models.CurrencyTransaction
import com.monsieur.cloy.domain.models.Product

class CurrencyTransactionMapper {
    fun toDomainModel(currencyTransactionEntity: CurrencyTransactionEntity): CurrencyTransaction {
        return CurrencyTransaction(
            currencyTransactionEntity.id,
            currencyTransactionEntity.date,
            currencyTransactionEntity.currencyTransactionTypeId,
            currencyTransactionEntity.points
        )
    }

    fun toDataModel(currencyTransaction: CurrencyTransaction): CurrencyTransactionEntity {
        val entity = CurrencyTransactionEntity()
        entity.id = currencyTransaction.id
        entity.date = currencyTransaction.date
        entity.currencyTransactionTypeId = currencyTransaction.currencyTransactionTypeId
        entity.points = currencyTransaction.points
        return entity
    }
}