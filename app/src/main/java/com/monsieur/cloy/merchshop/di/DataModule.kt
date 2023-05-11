package com.monsieur.cloy.merchshop.di

import androidx.room.Room
import com.monsieur.cloy.data.api.MerchShopApi
import com.monsieur.cloy.data.db.MerchShopDatabase
import com.monsieur.cloy.data.db.dao.*
import com.monsieur.cloy.data.repository.*
import com.monsieur.cloy.data.storage.*
import com.monsieur.cloy.domain.repository.*
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
    single<BasketItemStorage> {
        BasketItemStorage(basketItemDao = get())
    }
    single<CurrencyTransactionStorage> {
        CurrencyTransactionStorage(currencyTransactionDao = get())
    }
    single<EventParticipantStorage> {
        EventParticipantStorage(eventParticipantDao = get())
    }
    single<EventResponsibleStorage> {
        EventResponsibleStorage(eventResponsibleDao = get())
    }
    single<EventRoleStorage> {
        EventRoleStorage(eventRoleDao = get())
    }
    single<EventStorage> {
        EventStorage(eventDao = get())
    }
    single<NotificationStorage> {
        NotificationStorage(notificationDao = get())
    }
    single<OrderItemStorage> {
        OrderItemStorage(orderItemDao = get())
    }
    single<OrderStorage> {
        OrderStorage(orderDao = get())
    }
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
    single<BasketItemRepository> {
        BasketItemRepositoryImpl(merchShopApi = get(), basketItemStorage = get())
    }
    single<CurrencyTransactionRepository> {
        CurrencyTransactionRepositoryImpl(merchShopApi = get(), currencyTransactionStorage = get())
    }
    single<EventParticipantRepository> {
        EventParticipantRepositoryImpl(eventParticipantStorage = get())
    }
    single<EventRepository> {
        EventRepositoryImpl(merchShopApi = get(), eventStorage = get())
    }
    single<EventResponsibleRepository> {
        EventResponsibleRepositoryImpl(eventResponsibleStorage = get())
    }
    single<EventRoleRepository> {
        EventRoleRepositoryImpl(eventRoleStorage = get())
    }
    single<NotificationRepository> {
        NotificationRepositoryImpl(merchShopApi = get(), notificationStorage = get())
    }
    single<OrderItemRepository> {
        OrderItemRepositoryImpl(orderItemStorage = get())
    }
    single<OrderRepository> {
        OrderRepositoryImpl(merchShopApi = get(), orderStorage = get(), productStorage = get())
    }
    single<ProductRepository> {
        ProductRepositoryImpl(merchShopApi = get(), productStorage = get())
    }
    single<UserRepository> {
        UserRepositoryImpl(userStorage = get(), merchShopApi = get())
    }
}