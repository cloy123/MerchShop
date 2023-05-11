package com.monsieur.cloy.domain.usecase

import com.monsieur.cloy.domain.models.common.UpdateCurrencyTransactionDataResult
import com.monsieur.cloy.domain.repository.CurrencyTransactionRepository
import com.monsieur.cloy.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UpdateCurrencyTransactionDataUseCase(private val currencyTransactionRepository: CurrencyTransactionRepository, private val userRepository: UserRepository
) {
    suspend fun execute(): Flow<UpdateCurrencyTransactionDataResult> {
        return flow {

            val userList = userRepository.getUser()
            if (userList.isEmpty()) {
                emit(UpdateCurrencyTransactionDataResult(null, false, -1))
            }
            val user = userList[0]

            var result = currencyTransactionRepository.updateCurrencyTransactionData(user.accessToken)
            if (result.code == 401) {
                val refreshTokenResult =
                    userRepository.refreshToken(user.accessToken, user.refreshToken)
                if (refreshTokenResult.isSuccessful) {
                    user.accessToken = refreshTokenResult.accessToken!!
                    user.refreshToken = refreshTokenResult.refreshToken!!
                    userRepository.updateUser(user)
                } else if (refreshTokenResult.code == 401) {
                    userRepository.deleteUser()
                    emit(UpdateCurrencyTransactionDataResult(null, false, 401))
                } else {
                    emit(UpdateCurrencyTransactionDataResult(null, false, -1))
                }
            }
            result = currencyTransactionRepository.updateCurrencyTransactionData(user.accessToken)
            if (result.currencyTransactions != null) {
                currencyTransactionRepository.deleteAllData()
                currencyTransactionRepository.insertCurrencyTransactions(result.currencyTransactions!!)
            }
            emit(result)
        }
    }
}