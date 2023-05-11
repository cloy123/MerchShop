package com.monsieur.cloy.data.repository

import com.monsieur.cloy.data.api.MerchShopApi
import com.monsieur.cloy.data.api.models.requests.CancelOrderRequest
import com.monsieur.cloy.data.mappers.OrderItemMapper
import com.monsieur.cloy.data.mappers.OrderMapper
import com.monsieur.cloy.data.mappers.ProductMapper
import com.monsieur.cloy.data.storage.OrderStorage
import com.monsieur.cloy.data.storage.ProductStorage
import com.monsieur.cloy.domain.models.*
import com.monsieur.cloy.domain.models.common.CancelOrderResult
import com.monsieur.cloy.domain.models.common.UpdateOrderDataResult
import com.monsieur.cloy.domain.repository.OrderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class OrderRepositoryImpl(
    private val orderStorage: OrderStorage,
    private val productStorage: ProductStorage,
    private val merchShopApi: MerchShopApi
    ) : OrderRepository {

    private val orderMapper = OrderMapper()
    private val orderItemMapper = OrderItemMapper()
    private val productMapper = ProductMapper()

    override fun getOrdersFlow(): Flow<List<Order>> {
        return orderStorage.getOrdersFlow().map { l -> l.map { orderMapper.toDomainModel(it) } }
    }

    override suspend fun deleteAllData() {
        orderStorage.deleteAllOrders()
    }

    override suspend fun updateOrderData(accessToken: String): UpdateOrderDataResult {
        var isSuccessful: Boolean
        var orders: List<Order>?
        val orderItems = ArrayList<OrderItem>()
        var code: Int
        try {
            val response = merchShopApi.getOrdersInfo(accessToken)
            isSuccessful = response.isSuccessful
            code = response.code()
            if (response.isSuccessful && response.body() != null) {
                orders = response.body()!!.orders.map { orderMapper.orderDtoToOrder(it) }
                val products = productStorage.getProducts().map { productMapper.toDomainModel(it) }
                for(order in response.body()!!.orders){
                    orderItems.addAll(order.orderItems.map { orderItemMapper.orderItemDtoToOrderItem(it, products.find { p -> p.id == it.productId }!!, orders!!.find { o -> o.id == it.orderId }!!) })
                }
            } else {
                orders = null
            }
        } catch (e: Exception) {
            code = -1
            isSuccessful = false
            orders = null
        }
        return UpdateOrderDataResult(orders, orderItems, isSuccessful, code)
    }

    override suspend fun insertOrders(orders: List<Order>) {
        orderStorage.insertOrders(orders.map { orderMapper.toDataModel(it) })
    }

    override suspend fun cancelOrder(accessToken: String, orderId: String): CancelOrderResult {
        var isSuccessful: Boolean
        var code: Int

        var isCanceled = false
        var errorMessage = ""

        val request = CancelOrderRequest(orderId)

        try {
            val response = merchShopApi.cancelOrder(accessToken, request)
            isSuccessful = response.isSuccessful
            code = response.code()
            if (response.isSuccessful && response.body() != null) {
                isCanceled = response.body()!!.isCanceled
                errorMessage = response.body()!!.errorMessage
            }
        } catch (e: Exception) {
            code = -1
            isSuccessful = false
        }
        return CancelOrderResult(isSuccessful, isCanceled, errorMessage, code)
    }
}