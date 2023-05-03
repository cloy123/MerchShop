package com.monsieur.cloy.merchshop.di

import androidx.room.Room
import com.monsieur.cloy.data.api.MerchShopApi
import com.monsieur.cloy.data.db.MerchShopDatabase
import com.monsieur.cloy.data.db.dao.*
import com.monsieur.cloy.data.repository.ProductRepositoryImpl
import com.monsieur.cloy.data.repository.UserRepositoryImpl
import com.monsieur.cloy.data.storage.BasketItemStorage
import com.monsieur.cloy.data.storage.ProductStorage
import com.monsieur.cloy.data.storage.UserStorage
import com.monsieur.cloy.domain.repository.BasketItemRepository
import com.monsieur.cloy.domain.repository.ProductRepository
import com.monsieur.cloy.domain.repository.UserRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
    single<ProductStorage> {
        ProductStorage(productDao = get())
    }
    single<UserStorage> {
        UserStorage(userDao = get())
    }
    single<MerchShopDatabase> {
        Room.databaseBuilder(
            androidContext(),
            MerchShopDatabase::class.java,
            "merch_shop_database"
        ).build()
    }
    single<BasketItemDao> {
        get<MerchShopDatabase>().basketItemDao()
    }
    single<CurrencyTransactionDao> {
        get<MerchShopDatabase>().currencyTransactionDao()
    }
    single<EventDao> {
        get<MerchShopDatabase>().eventDao()
    }
    single<EventParticipantDao> {
        get<MerchShopDatabase>().eventParticipantDao()
    }
    single<EventResponsibleDao> {
        get<MerchShopDatabase>().eventResponsibleDao()
    }
    single<EventRoleDao> {
        get<MerchShopDatabase>().eventRoleDao()
    }
    single<NotificationDao> {
        get<MerchShopDatabase>().notificationDao()
    }
    single<OrderDao> {
        get<MerchShopDatabase>().orderDao()
    }
    single<OrderItemDao> {
        get<MerchShopDatabase>().orderItemDao()
    }
    single<ProductDao> {
        get<MerchShopDatabase>().productDao()
    }
    single<UserDao> {
        get<MerchShopDatabase>().userDao()
    }
    single<MerchShopApi> {
        MerchShopApi()
    }
    single<ProductRepository> {
        ProductRepositoryImpl(merchShopApi = get(), productStorage = get())
    }
    single<UserRepository> {
        UserRepositoryImpl(userStorage = get(), merchShopApi = get())
    }
}