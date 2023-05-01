package com.monsieur.cloy.data.mappers

import com.monsieur.cloy.data.storage.models.BasketItemEntity
import com.monsieur.cloy.data.storage.models.BasketItemWithProduct
import com.monsieur.cloy.domain.models.BasketItem
import com.monsieur.cloy.domain.models.Product

class BasketItemMapper {
    fun toDomainModel(basketItem: BasketItemWithProduct): BasketItem {

        val product = Product(
            basketItem.productId,
            basketItem.typeName,
            basketItem.sizeName,
            basketItem.colorName,
            basketItem.colorValue,
            basketItem.showInCatalog,
            basketItem.freeQuantity,
            basketItem.price,
            basketItem.discount,
            basketItem.imageFileName
        )

        return BasketItem(
            basketItem.id,
            product,
            basketItem.quantity
        )
    }

    fun toDataModel(basketItem: BasketItem): BasketItemEntity {
        val basketItemEntity = BasketItemEntity()
        basketItemEntity.id = basketItem.id
        basketItemEntity.productId = basketItem.product.id
        basketItemEntity.quantity = basketItem.quantity
        return basketItemEntity
    }
}