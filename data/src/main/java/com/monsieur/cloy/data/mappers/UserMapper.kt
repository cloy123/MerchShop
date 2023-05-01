package com.monsieur.cloy.data.mappers

import com.monsieur.cloy.data.storage.models.ProductEntity
import com.monsieur.cloy.data.storage.models.UserEntity
import com.monsieur.cloy.domain.models.Product
import com.monsieur.cloy.domain.models.User

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
}