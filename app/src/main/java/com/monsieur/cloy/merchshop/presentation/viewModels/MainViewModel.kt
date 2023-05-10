package com.monsieur.cloy.merchshop.presentation.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.monsieur.cloy.domain.models.Product
import com.monsieur.cloy.domain.models.User
import com.monsieur.cloy.domain.models.common.LoginParam
import com.monsieur.cloy.domain.models.common.LoginResult
import com.monsieur.cloy.domain.models.common.UpdateProductDataResult
import com.monsieur.cloy.domain.usecase.GetProductsUseCase
import com.monsieur.cloy.domain.usecase.GetUserUseCase
import com.monsieur.cloy.domain.usecase.LoginUseCase
import com.monsieur.cloy.domain.usecase.UpdateProductsDataUseCase
import com.monsieur.cloy.merchshop.presentation.catalog.Color
import com.monsieur.cloy.merchshop.presentation.catalog.FiltersSettings
import com.monsieur.cloy.merchshop.presentation.catalog.Sort
import com.monsieur.cloy.merchshop.utilits.calculatePrice
import com.monsieur.cloy.merchshop.utilits.showToast
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

    val updateProductDataResult = MutableLiveData<UpdateProductDataResult?>()

    var products = ArrayList<Product>()

    var types = MutableLiveData<ArrayList<String>>()

    var colors = MutableLiveData<ArrayList<Color>>()

    val filteredProducts = MutableLiveData<List<List<Product>>>()

    var filtersSettings: FiltersSettings =
        FiltersSettings(Sort.ByName, 0, 99999999, ArrayList(), ArrayList())
        private set

    val user = MutableLiveData<User?>()

    init {
        viewModelScope.launch(Dispatchers.Default) {
            getProductsUseCase.execute().collect {
                products = it as ArrayList
                filteredProducts.postValue(filterProducts(products))
            }
        }
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

    fun setFilters(filtersSettings: FiltersSettings) {
        this.filtersSettings = filtersSettings
        viewModelScope.launch(Dispatchers.IO) {
            if (filteredProducts.value != null) {
                filteredProducts.postValue(filterProducts(products))
            }
        }
    }

    private fun filterProducts(products: List<Product>): List<List<Product>> {
        if (products.isEmpty()) {
            return ArrayList()
        } else {
            val newTypes = ArrayList<String>()
            val newColors = ArrayList<Color>()

            var list = products.filter { product ->
                product.showInCatalog
            }

            var result = ArrayList<List<Product>>()

            val recurringProducts = ArrayList<Product>()
            for(product in list){
                if(!newTypes.contains(product.typeName)){
                    newTypes.add(product.typeName)
                }
                val color = Color(product.colorName, product.colorValue)
                if(!newColors.contains(color)){
                    newColors.add(color)
                }

                if(recurringProducts.find { it.colorName == product.colorName && it.typeName == product.typeName } == null){
                    recurringProducts.addAll(list.filter { p ->
                        p.colorName == product.colorName && p.typeName == product.typeName
                                && p != product
                    })
                }
            }

            list = list.filter { p ->
                !recurringProducts.contains(p)
            }

            list = list.filter { p ->
                p.price >= filtersSettings.priceFrom &&
                        p.price <= filtersSettings.priceTo
            }

            if (filtersSettings.listTypes.isNotEmpty()) {
                list = list.filter { p ->
                    filtersSettings.listTypes.contains(p.typeName)
                }
            }
            if (filtersSettings.listColors.isNotEmpty()) {
                list = list.filter { p ->
                    filtersSettings.listColors.contains(Color(p.colorName, p.colorValue))
                }
            }

            list = when (filtersSettings.sortBy) {
                Sort.ByName -> list.sortedBy { product -> product.typeName + product.colorName }
                Sort.ByPriceAscending -> list.sortedBy { product ->
                    calculatePrice(
                        product.price,
                        product.discount
                    )
                }
                Sort.ByPriceDescending -> list.sortedByDescending { product ->
                    calculatePrice(
                        product.price,
                        product.discount
                    )
                }
            }

            for(p in list){
                var r = ArrayList<Product>()
                r.add(p)
                r.addAll(recurringProducts.filter {
                    it.typeName == p.typeName && it.colorName == p.colorName
                })
                result.add(r)
            }

            return result
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

    fun updateProductData(){
        viewModelScope.launch(Dispatchers.Default) {
            updateProductsDataUseCase.execute().first {
                updateProductDataResult.postValue(it)
                true
            }
        }
    }
}