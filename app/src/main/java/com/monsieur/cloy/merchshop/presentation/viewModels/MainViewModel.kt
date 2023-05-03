package com.monsieur.cloy.merchshop.presentation.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.monsieur.cloy.domain.usecase.GetProductsUseCase
import com.monsieur.cloy.domain.usecase.GetUserUseCase
import com.monsieur.cloy.domain.usecase.LoginUseCase
import com.monsieur.cloy.domain.usecase.UpdateProductsDataUseCase

class MainViewModel(
    application: Application,
    private val getProductsUseCase: GetProductsUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val loginUseCase: LoginUseCase,
    private val updateProductsDataUseCase: UpdateProductsDataUseCase
) : AndroidViewModel(application)  {
}