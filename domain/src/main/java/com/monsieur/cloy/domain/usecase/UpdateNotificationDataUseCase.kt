package com.monsieur.cloy.domain.usecase

import com.monsieur.cloy.domain.models.common.UpdateCurrencyTransactionDataResult
import com.monsieur.cloy.domain.models.common.UpdateNotificationDataResult
import com.monsieur.cloy.domain.repository.CurrencyTransactionRepository
import com.monsieur.cloy.domain.repository.NotificationRepository
import com.monsieur.cloy.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UpdateNotificationDataUseCase(private val notificationRepository: NotificationRepository, private val userRepository: UserRepository
) {
    suspend fun execute(): Flow<UpdateNotificationDataResult> {
        return flow {

            val userList = userRepository.getUser()
            if (userList.isEmpty()) {
                emit(UpdateNotificationDataResult(null, false, -1))
            }
            val user = userList[0]

            var result = notificationRepository.updateNotificationData(user.accessToken)
            if (result.code == 401) {
                var refreshTokenResult =
                    userRepository.refreshToken(user.accessToken, user.refreshToken)
                if (refreshTokenResult.isSuccessful) {
                    user.accessToken = refreshTokenResult.accessToken!!
                    user.refreshToken = refreshTokenResult.refreshToken!!
                    userRepository.updateUser(user)
                } else if (refreshTokenResult.code == 401) {
                    userRepository.deleteUser()
                    emit(UpdateNotificationDataResult(null, false, 401))
                } else {
                    emit(UpdateNotificationDataResult(null, false, -1))
                }
            }
            result = notificationRepository.updateNotificationData(user.accessToken)
            if (result.notifications != null) {
                notificationRepository.deleteAllData()
                notificationRepository.insertNotifications(result.notifications!!)
            }
            emit(result)
        }
    }
}