package com.monsieur.cloy.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.monsieur.cloy.data.storage.models.CurrencyTransactionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyTransactionDao {
    @Insert
    suspend fun insertCurrencyTransactions(currencyTransactions: List<CurrencyTransactionEntity>)

    @Query("SELECT * FROM currencyTransactions")
    fun getAllCurrencyTransactions(): Flow<List<CurrencyTransactionEntity>>

    @Query("DELETE FROM currencyTransactions")
    fun deleteAllCurrencyTransactions()
}