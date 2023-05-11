package com.monsieur.cloy.merchshop.presentation.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.monsieur.cloy.domain.models.*
import com.monsieur.cloy.domain.models.common.*
import com.monsieur.cloy.domain.usecase.*
import com.monsieur.cloy.merchshop.presentation.catalog.Color
import com.monsieur.cloy.merchshop.presentation.catalog.FiltersSettings
import com.monsieur.cloy.merchshop.presentation.catalog.Sort
import com.monsieur.cloy.merchshop.utilits.calculatePrice
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainViewModel(
    application: Application,
    private val getProductsUseCase: GetProductsUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val loginUseCase: LoginUseCase,
    private val updateProductsDataUseCase: UpdateProductsDataUseCase,
    private val cancelOrderUseCase: CancelOrderUseCase,
    private val createBasketItemUseCase: CreateBasketItemUseCase,
    private val createOrderUseCase: CreateOrderUseCase,
    private val deleteBasketItemUseCase: DeleteBasketItemUseCase,
    private val finishEventUseCase: FinishEventUseCase,
    private val getBasketItemsUseCase: GetBasketItemsUseCase,
    private val getCurrencyTransactionsUseCase: GetCurrencyTransactionsUseCase,
    private val getEventParticipantsUseCase: GetEventParticipantsUseCase,
    private val getEventResponsibleUseCase: GetEventResponsibleUseCase,
    private val getEventRolesUseCase: GetEventRolesUseCase,
    private val getEventsUseCase: GetEventsUseCase,
    private val getNotificationsUseCase: GetNotificationsUseCase,
    private val getOrderItemsUseCase: GetOrderItemsUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val signupEventUseCase: SignupEventUseCase,
    private val updateCurrencyTransactionDataUseCase: UpdateCurrencyTransactionDataUseCase,
    private val updateEventsDataUseCase: UpdateEventsDataUseCase,
    private val updateNotificationDataUseCase: UpdateNotificationDataUseCase,
    private val updateOrdersDataUseCase: UpdateOrdersDataUseCase,
    private val updateUserDataUseCase: UpdateUserDataUseCase,
    private val getOrdersUseCase: GetOrdersUseCase

    ) : AndroidViewModel(application)  {

    val loginResult = MutableLiveData<LoginResult?>()

    val updateProductDataResult = MutableLiveData<UpdateProductDataResult?>()

    var products = ArrayList<Product>()

    var types = MutableLiveData<ArrayList<String>>()

    var colors = MutableLiveData<ArrayList<Color>>()

    val filteredProducts = MutableLiveData<List<List<Product>>>()

    val events = MutableLiveData<List<Event>>()

    val eventRoles = MutableLiveData<List<EventRole>>()

    val eventResponsibles = MutableLiveData<List<EventResponsible>>()

    val eventParticipants = MutableLiveData<List<EventParticipant>>()

    var filtersSettings: FiltersSettings =
        FiltersSettings(Sort.ByName, 0, 99999999, ArrayList(), ArrayList())
        private set

    val user = MutableLiveData<User?>()

    var userData: User? = null

    val signupResult = MutableLiveData<SignupEventResult?>()
    val finishEventResult = MutableLiveData<FinishEventResult?>()

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
                    userData = null
                }else{
                    user.postValue(it[0])
                    userData = it[0]
                }
            }
        }
        viewModelScope.launch(Dispatchers.Default) {
            getEventsUseCase.execute().collect{
                events.postValue(it as ArrayList)
            }
        }
        viewModelScope.launch(Dispatchers.Default) {
            getEventParticipantsUseCase.execute().collect{
                eventParticipants.postValue(it as ArrayList)
            }
        }
        viewModelScope.launch(Dispatchers.Default) {
            getEventRolesUseCase.execute().collect{
                eventRoles.postValue(it as ArrayList)
            }
        }
        viewModelScope.launch(Dispatchers.Default) {
            getEventResponsibleUseCase.execute().collect{
                eventResponsibles.postValue(it as ArrayList)
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

    fun signupEvent(event: Event, role: EventRole){
        viewModelScope.launch(Dispatchers.Default) {
            signupEventUseCase.execute(event.id, role.id).first {
                signupResult.postValue(it)
                true
            }
        }
    }

    fun finishEvent(event: Event, eventParticipants: List<EventParticipant>){
        viewModelScope.launch(Dispatchers.Default) {
            finishEventUseCase.execute(event.id, eventParticipants).first {
                finishEventResult.postValue(it)
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