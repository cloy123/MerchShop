package com.monsieur.cloy.merchshop.di

import com.monsieur.cloy.domain.usecase.*
import org.koin.dsl.module

val domainModule = module {
    factory<GetProductsUseCase> {
        GetProductsUseCase(productRepository = get())
    }
    factory<GetUserUseCase> {
        GetUserUseCase(userRepository = get())
    }
    factory<LoginUseCase> {
        LoginUseCase(userRepository = get())
    }
    factory<UpdateProductsDataUseCase> {
        UpdateProductsDataUseCase(productRepository = get(), userRepository = get())
    }
    factory<CancelOrderUseCase>{
        CancelOrderUseCase(userRepository = get(), orderRepository = get())
    }
    factory<CreateBasketItemUseCase>{
        CreateBasketItemUseCase(basketItemRepository = get())
    }
    factory<CreateOrderUseCase>{
        CreateOrderUseCase(userRepository = get(), basketItemRepository = get())
    }
    factory<DeleteBasketItemUseCase>{
        DeleteBasketItemUseCase(basketItemRepository = get())
    }
    factory<NoteVisitUseCase>{
        NoteVisitUseCase(userRepository = get(), eventRepository = get())
    }
    factory<GetBasketItemsUseCase>{
        GetBasketItemsUseCase(basketItemRepository = get())
    }
    factory<GetCurrencyTransactionsUseCase>{
        GetCurrencyTransactionsUseCase(currencyTransactionRepository = get())
    }
    factory<GetEventParticipantsUseCase>{
        GetEventParticipantsUseCase(eventParticipantRepository = get())
    }
    factory<GetEventResponsibleUseCase>{
        GetEventResponsibleUseCase(eventResponsibleRepository = get())
    }
    factory<GetEventRolesUseCase>{
        GetEventRolesUseCase(eventRoleRepository = get())
    }
    factory<GetEventsUseCase>{
        GetEventsUseCase(eventRepository = get())
    }
    factory<GetNotificationsUseCase>{
        GetNotificationsUseCase(notificationRepository = get())
    }
    factory<GetOrderItemsUseCase>{
        GetOrderItemsUseCase(orderItemRepository = get())
    }
    factory<LogoutUseCase>{
        LogoutUseCase(userRepository = get())
    }
    factory<SignupEventUseCase>{
        SignupEventUseCase(userRepository = get(), eventRepository = get())
    }
    factory<UpdateCurrencyTransactionDataUseCase>{
        UpdateCurrencyTransactionDataUseCase(userRepository = get(), currencyTransactionRepository = get())
    }
    factory<UpdateEventsDataUseCase>{
        UpdateEventsDataUseCase(userRepository = get(), eventRepository = get(), eventRoleRepository = get(), eventResponsibleRepository = get(), eventParticipantRepository = get())
    }
    factory<UpdateNotificationDataUseCase>{
        UpdateNotificationDataUseCase(userRepository = get(), notificationRepository = get())
    }
    factory<UpdateOrdersDataUseCase>{
        UpdateOrdersDataUseCase(userRepository = get(), orderItemRepository = get(), orderRepository = get())
    }
    factory<UpdateUserDataUseCase>{
        UpdateUserDataUseCase(userRepository = get())
    }
}