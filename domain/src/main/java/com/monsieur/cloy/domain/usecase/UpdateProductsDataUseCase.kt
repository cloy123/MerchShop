package com.monsieur.cloy.domain.usecase

import com.monsieur.cloy.domain.models.common.UpdateProductDataResult
import com.monsieur.cloy.domain.repository.ProductRepository
import com.monsieur.cloy.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UpdateProductsDataUseCase(
    private val productRepository: ProductRepository,
    private val userRepository: UserRepository
) {
    suspend fun execute(): Flow<UpdateProductDataResult> {
        return flow {

            val userList = userRepository.getUser()
            if(userList.isEmpty()){
                emit(UpdateProductDataResult(null, false, -1))
            }
            val user = userList[0]

            var result = productRepository.updateProductData(user.accessToken)
            if(result.code == 401){
                var refreshTokenResult = userRepository.refreshToken(user.accessToken, user.refreshToken)
                if(refreshTokenResult.isSuccessful){
                    user.accessToken = refreshTokenResult.accessToken!!
                    user.refreshToken = refreshTokenResult.refreshToken!!
                    userRepository.updateUser(user)
                }else if(refreshTokenResult.code == 401){
                    userRepository.deleteUser()
                    emit(UpdateProductDataResult(null, false, 401))
                }else{
                    emit(UpdateProductDataResult(null, false, -1))
                }
            }
            result = productRepository.updateProductData(user.accessToken)
            if(result.products != null){
                productRepository.deleteAllProducts()
                productRepository.insertProducts(result.products!!)
            }
            emit(result)
        }
    }
}