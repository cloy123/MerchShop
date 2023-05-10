package com.monsieur.cloy.domain.usecase

import com.monsieur.cloy.domain.models.common.CreateOrderResult
import com.monsieur.cloy.domain.models.common.UpdateProductDataResult
import com.monsieur.cloy.domain.repository.BasketItemRepository
import com.monsieur.cloy.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CreateOrderUseCase(private val userRepository: UserRepository,
                         private val basketItemRepository: BasketItemRepository
) {
    suspend fun execute(): Flow<CreateOrderResult> {
        return flow {

            val userList = userRepository.getUser()
            if(userList.isEmpty()){
                emit(CreateOrderResult(false, false, "", -1))
            }
            val user = userList[0]

            val items = basketItemRepository.getBasketItems()
            if(items.isEmpty()){
                emit(CreateOrderResult(false, false, "", -1))
            }

            var result = basketItemRepository.createOrder(user.accessToken, items)
            if(result.code == 401){
                var refreshTokenResult = userRepository.refreshToken(user.accessToken, user.refreshToken)
                if(refreshTokenResult.isSuccessful){
                    user.accessToken = refreshTokenResult.accessToken!!
                    user.refreshToken = refreshTokenResult.refreshToken!!
                    userRepository.updateUser(user)
                }else if(refreshTokenResult.code == 401){
                    userRepository.deleteUser()
                    emit(CreateOrderResult(false, false, "", 401))
                }else{
                    emit(CreateOrderResult(false, false, "", -1))
                }
            }
            result = basketItemRepository.createOrder(user.accessToken, items)

            if(result.isCreated){
                basketItemRepository.deleteAllBasketItems()
            }

            emit(result)
        }
    }
}