package com.monsieur.cloy.domain.models

class OrderStatus(val id: Int, val name: String) {
    companion object{
        val InWork = OrderStatus(0, "В работе")
        val WaitingNewSupply = OrderStatus(1, "Ожидание новой поставки")
        val Canceled = OrderStatus(2, "Отменено")
        val Ready = OrderStatus(3, "Готово к выдаче")
        val Complete = OrderStatus(4, "Получено")

        fun getStatusById(id: Int): OrderStatus{
            return when(id){
                0 -> InWork
                1 -> WaitingNewSupply
                2 -> Canceled
                3 -> Ready
                4 -> Complete
                else -> InWork
            }
        }
    }
}