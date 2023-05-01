package com.monsieur.cloy.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.monsieur.cloy.data.storage.models.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Update
    suspend fun updateUser(user: UserEntity)

    @Insert
    suspend fun insertUser(user: UserEntity)

    @Query("SELECT * FROM user")
    fun getUser(): Flow<List<UserEntity>>

    @Query("DELETE FROM user")
    suspend fun deleteUser()
}