package com.monsieur.cloy.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.monsieur.cloy.data.db.dao.*
import com.monsieur.cloy.data.storage.models.*

@Database(
    entities =
    [
        BasketItemEntity::class,
        CurrencyTransactionEntity::class,
        EventEntity::class,
        EventParticipantEntity::class,
        EventResponsibleEntity::class,
        EventRoleEntity::class,
        NotificationEntity::class,
        OrderEntity::class,
        OrderItemEntity::class,
        ProductEntity::class,
        UserEntity::class
    ], version = 1
)
@TypeConverters(Converters::class)
abstract class MerchShopDatabase : RoomDatabase() {
    abstract fun basketItemDao(): BasketItemDao
    abstract fun currencyTransactionDao(): CurrencyTransactionDao
    abstract fun eventDao(): EventDao
    abstract fun eventParticipantDao(): EventParticipantDao
    abstract fun eventResponsibleDao(): EventResponsibleDao
    abstract fun eventRoleDao(): EventRoleDao
    abstract fun notificationDao(): NotificationDao
    abstract fun orderDao(): OrderDao
    abstract fun orderItemDao(): OrderItemDao
    abstract fun productDao(): ProductDao
    abstract fun userDao(): UserDao
}