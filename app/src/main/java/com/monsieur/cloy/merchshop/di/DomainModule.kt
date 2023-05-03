package com.monsieur.cloy.merchshop.di

import com.monsieur.cloy.domain.usecase.GetProductsUseCase
import com.monsieur.cloy.domain.usecase.GetUserUseCase
import com.monsieur.cloy.domain.usecase.LoginUseCase
import com.monsieur.cloy.domain.usecase.UpdateProductsDataUseCase
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
}