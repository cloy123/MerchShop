package com.monsieur.cloy.merchshop.presentation.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.monsieur.cloy.domain.models.User
import com.monsieur.cloy.domain.models.common.LoginParam
import com.monsieur.cloy.domain.models.common.LoginResult
import com.monsieur.cloy.domain.usecase.GetProductsUseCase
import com.monsieur.cloy.domain.usecase.GetUserUseCase
import com.monsieur.cloy.domain.usecase.LoginUseCase
import com.monsieur.cloy.domain.usecase.UpdateProductsDataUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainViewModel(
    application: Application,
    private val getProductsUseCase: GetProductsUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val loginUseCase: LoginUseCase,
    private val updateProductsDataUseCase: UpdateProductsDataUseCase
) : AndroidViewModel(application)  {

    val loginResult = MutableLiveData<LoginResult?>()

    val user = MutableLiveData<User?>()

    init {
        viewModelScope.launch(Dispatchers.Default) {
            getUserUseCase.execute().collect{
                if(it.isEmpty()){
                    user.postValue(null)
                }else{
                    user.postValue(it[0])
                }
            }
        }
    }

    fun login(email: String, password: String){
        viewModelScope.launch(Dispatchers.Default){
            loginUseCase.execute(LoginParam(email, password)).first {
                loginResult.postValue(it)
                true
            }
        }
    }
}