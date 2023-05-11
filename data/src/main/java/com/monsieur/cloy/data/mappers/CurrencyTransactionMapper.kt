package com.monsieur.cloy.data.mappers

import com.monsieur.cloy.data.api.models.CurrencyTransactionDto
import com.monsieur.cloy.data.storage.models.CurrencyTransactionEntity
import com.monsieur.cloy.domain.models.CurrencyTransaction
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

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

    fun currencyTransactionDtoToCurrencyTransaction(currencyTransactionDto: CurrencyTransactionDto): CurrencyTransaction{
        return CurrencyTransaction(
            currencyTransactionDto.id,
            LocalDateTime.parse(currencyTransactionDto.date, DateTimeFormatter.ISO_DATE_TIME),
            currencyTransactionDto.currencyTransactionTypeId,
            currencyTransactionDto.points
        )
    }
}