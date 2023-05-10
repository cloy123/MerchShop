package com.monsieur.cloy.domain.usecase

import com.monsieur.cloy.domain.models.common.UpdateEventDataResult
import com.monsieur.cloy.domain.models.common.UpdateOrderDataResult
import com.monsieur.cloy.domain.repository.OrderItemRepository
import com.monsieur.cloy.domain.repository.OrderRepository
import com.monsieur.cloy.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UpdateOrdersDataUseCase(
    private val userRepository: UserRepository,
    private val orderRepository: OrderRepository,
    private val orderItemRepository: OrderItemRepository
) {
    suspend fun execute(): Flow<UpdateOrderDataResult> {
        return flow {

            val userList = userRepository.getUser()
            if (userList.isEmpty()) {
                emit(UpdateOrderDataResult(null, null, false, -1))
            }
            val user = userList[0]

            var result = orderRepository.updateOrderData(user.accessToken)
            if (result.code == 401) {
                var refreshTokenResult =
                    userRepository.refreshToken(user.accessToken, user.refreshToken)
                if (refreshTokenResult.isSuccessful) {
                    user.accessToken = refreshTokenResult.accessToken!!
                    user.refreshToken = refreshTokenResult.refreshToken!!
                    userRepository.updateUser(user)
                } else if (refreshTokenResult.code == 401) {
                    userRepository.deleteUser()
                    emit(UpdateOrderDataResult(null, null, false, 401))
                } else {
                    emit(UpdateOrderDataResult(null, null, false, -1))
                }
            }
            result = orderRepository.updateOrderData(user.accessToken)
            if (result.orders != null) {
                orderRepository.deleteAllData()
                orderItemRepository.deleteAllData()
                orderRepository.insertOrders(result.orders!!)
                orderItemRepository.insertOrderItems(result.orderItems!!)
            }
            emit(result)
        }
    }
}