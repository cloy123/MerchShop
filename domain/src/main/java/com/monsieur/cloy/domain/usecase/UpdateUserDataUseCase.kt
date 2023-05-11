package com.monsieur.cloy.domain.usecase

import com.monsieur.cloy.domain.models.common.GetUserInfoResult
import com.monsieur.cloy.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UpdateUserDataUseCase(
    private val userRepository: UserRepository
) {
    suspend fun execute(): Flow<GetUserInfoResult> {
        return flow {

            val userList = userRepository.getUser()
            if(userList.isEmpty()){
                emit(GetUserInfoResult(null, false, -1))
            }
            val user = userList[0]

            var result = userRepository.getUserInfo(user.accessToken)
            if(result.code == 401){
                val refreshTokenResult = userRepository.refreshToken(user.accessToken, user.refreshToken)
                if(refreshTokenResult.isSuccessful){
                    user.accessToken = refreshTokenResult.accessToken!!
                    user.refreshToken = refreshTokenResult.refreshToken!!
                    userRepository.updateUser(user)
                }else if(refreshTokenResult.code == 401){
                    userRepository.deleteUser()
                    emit(GetUserInfoResult(null, false, 401))
                }else{
                    emit(GetUserInfoResult(null, false, -1))
                }
            }
            result = userRepository.getUserInfo(user.accessToken)
            if(result.userInfo != null){
                user.userTypeId = result.userInfo!!.userTypeId
                user.firstName = result.userInfo!!.firstName
                user.lastName = result.userInfo!!.lastName
                user.birthday = result.userInfo!!.birthday
                user.email = result.userInfo!!.email
                user.pointBalance = result.userInfo!!.pointBalance
                user.className = result.userInfo!!.className
                user.genderId = result.userInfo!!.genderId
                userRepository.updateUser(user)
            }
            emit(result)
        }
    }
}