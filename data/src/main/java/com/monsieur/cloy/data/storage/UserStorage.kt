package com.monsieur.cloy.data.storage

import com.monsieur.cloy.data.db.dao.UserDao
import com.monsieur.cloy.data.storage.models.UserEntity
import kotlinx.coroutines.flow.Flow

class UserStorage(private val userDao: UserDao) {

    suspend fun updateUser(user: UserEntity){
        userDao.updateUser(user)
    }

    suspend fun insertUser(user: UserEntity){
        userDao.insertUser(user)
    }

    fun getUser(): Flow<List<UserEntity>>{
        return userDao.getUser()
    }

    fun deleteUser(){
        userDao.deleteUser()
    }
}