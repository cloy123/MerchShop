package com.monsieur.cloy.domain.usecase

import com.monsieur.cloy.domain.models.common.CancelOrderResult
import com.monsieur.cloy.domain.repository.OrderRepository
import com.monsieur.cloy.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CancelOrderUseCase (private val userRepository: UserRepository,
                          private val orderRepository: OrderRepository
) {
    suspend fun execute(orderId: String): Flow<CancelOrderResult> {
        return flow {

            val userList = userRepository.getUser()
            if (userList.isEmpty()) {
                emit(CancelOrderResult(false, false, "", -1))
            }
            val user = userList[0]


            var result = orderRepository.cancelOrder(user.accessToken, orderId)
            if (result.code == 401) {
                val refreshTokenResult =
                    userRepository.refreshToken(user.accessToken, user.refreshToken)
                if (refreshTokenResult.isSuccessful) {
                    user.accessToken = refreshTokenResult.accessToken!!
                    user.refreshToken = refreshTokenResult.refreshToken!!
                    userRepository.updateUser(user)
                } else if (refreshTokenResult.code == 401) {
                    userRepository.deleteUser()
                    emit(CancelOrderResult(false, false, "", 401))
                } else {
                    emit(CancelOrderResult(false, false, "", -1))
                }
            }
            result = orderRepository.cancelOrder(user.accessToken, orderId)

            emit(result)
        }
    }
}