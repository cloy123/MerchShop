package com.monsieur.cloy.domain.models

class CurrencyTransactionType (val id: Int, val name: String) {
    companion object{
        val OrderCreatedTransaction = CurrencyTransactionType(0, "Заказ")
        val OrderCancelledTransaction = CurrencyTransactionType(1, "Отмена заказа")
        val EventTransaction = CurrencyTransactionType(2, "Мероприятие")
        val EventResponsiblesTransaction = CurrencyTransactionType(3, "Ответственный за мероприятие")
        val HolidayTransaction = CurrencyTransactionType(4, "Событие")

        fun getTypeById(id: Int): CurrencyTransactionType{
            return when(id){
                0 -> OrderCreatedTransaction
                1 -> OrderCancelledTransaction
                2 -> EventTransaction
                3 -> EventResponsiblesTransaction
                4 -> HolidayTransaction
                else -> HolidayTransaction
            }
        }
    }
}