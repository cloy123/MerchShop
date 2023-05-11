package com.monsieur.cloy.merchshop.di

import com.monsieur.cloy.merchshop.presentation.viewModels.MainViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel<MainViewModel> {
        MainViewModel(
            androidApplication(),
            getProductsUseCase = get(),
            getUserUseCase = get(),
            loginUseCase = get(),
            updateProductsDataUseCase = get(),
            cancelOrderUseCase = get(),
            createBasketItemUseCase = get(),
            createOrderUseCase = get(),
            deleteBasketItemUseCase = get(),
            finishEventUseCase = get(),
            getBasketItemsUseCase = get(),
            getCurrencyTransactionsUseCase = get(),
            getEventParticipantsUseCase = get(),
            getEventResponsibleUseCase = get(),
            getEventRolesUseCase = get(),
            getEventsUseCase = get(),
            getNotificationsUseCase = get(),
            getOrderItemsUseCase = get(),
            logoutUseCase = get(),
            signupEventUseCase = get(),
            updateCurrencyTransactionDataUseCase = get(),
            updateEventsDataUseCase = get(),
            updateNotificationDataUseCase = get(),
            updateOrdersDataUseCase = get(),
            updateUserDataUseCase = get(),
            getOrdersUseCase = get()
        )
    }
}