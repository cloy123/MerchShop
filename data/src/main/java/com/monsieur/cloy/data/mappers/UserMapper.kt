package com.monsieur.cloy.data.mappers

import com.google.gson.Gson
import com.monsieur.cloy.data.api.models.responses.GetUserInfoResponse
import com.monsieur.cloy.data.api.models.responses.LoginResponse
import com.monsieur.cloy.data.storage.models.ProductEntity
import com.monsieur.cloy.data.storage.models.UserEntity
import com.monsieur.cloy.domain.models.Product
import com.monsieur.cloy.domain.models.User
import com.monsieur.cloy.domain.models.common.UserInfo
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class UserMapper {

    fun toDomainModel(userEntity: UserEntity): User {
        return User(
            userEntity.id,
            userEntity.userTypeId,
            userEntity.firstName,
            userEntity.lastName,
            userEntity.birthday,
            userEntity.email,
            userEntity.pointBalance,
            userEntity.className,
            userEntity.genderId,
            userEntity.accessToken,
            userEntity.refreshToken
        )
    }

    fun toDataModel(user: User): UserEntity {
        val entity = UserEntity()
        entity.id = user.id
        entity.userTypeId = user.userTypeId
        entity.firstName = user.firstName
        entity.lastName = user.lastName
        entity.birthday = user.birthday
        entity.email = user.email
        entity.pointBalance = user.pointBalance
        entity.className = user.className
        entity.genderId = user.genderId
        entity.accessToken = user.accessToken
        entity.refreshToken = user.refreshToken
        return entity
    }

    fun loginResponseToDomainModel(loginResponse: LoginResponse): User?{
        if(loginResponse.userData == null){
            return null
        }
        var className: String? = ""
        if(loginResponse.userData!!.classNumber != null){
            className += loginResponse.userData!!.classNumber.toString()
        }
        if(loginResponse.userData!!.classLetter != null){
            className += loginResponse.userData!!.classLetter
        }
        if(className == ""){
            className = null
        }

        return User(
            loginResponse.userData!!.id,
            loginResponse.userData!!.userTypeId,
            loginResponse.userData!!.firstName,
            loginResponse.userData!!.lastName,
            LocalDateTime.parse(loginResponse.userData!!.birthday, DateTimeFormatter.ISO_DATE_TIME),
            loginResponse.userData!!.email,
            loginResponse.userData!!.pointBalance,
            className,
            loginResponse.userData!!.genderId,
            loginResponse.accessToken!!,
            loginResponse.refreshToken!!
        )
    }

    fun getUserInfoResponseToUserInfo(getUserInfoResponse: GetUserInfoResponse): UserInfo?{
        if(getUserInfoResponse.user == null){
            return null
        }
        var className: String? = ""
        if(getUserInfoResponse.user!!.classNumber != null){
            className += getUserInfoResponse.user!!.classNumber.toString()
        }
        if(getUserInfoResponse.user!!.classLetter != null){
            className += getUserInfoResponse.user!!.classLetter
        }
        if(className == ""){
            className = null
        }
        return UserInfo(
            getUserInfoResponse.user!!.id,
            getUserInfoResponse.user!!.userTypeId,
            getUserInfoResponse.user!!.firstName,
            getUserInfoResponse.user!!.lastName,
            LocalDateTime.parse(getUserInfoResponse.user!!.birthday, DateTimeFormatter.ISO_DATE_TIME),
            getUserInfoResponse.user!!.email,
            getUserInfoResponse.user!!.pointBalance,
            className,
            getUserInfoResponse.user!!.genderId
        )
    }
}