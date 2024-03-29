package com.monsieur.cloy.data.mappers

import com.monsieur.cloy.data.api.models.ProductDto
import com.monsieur.cloy.data.storage.models.ProductEntity
import com.monsieur.cloy.domain.models.Product

class ProductMapper {

    fun toDomainModel(productEntity: ProductEntity): Product {
        return Product(
            productEntity.id,
            productEntity.typeName,
            productEntity.sizeName,
            productEntity.colorName,
            productEntity.colorValue,
            productEntity.showInCatalog,
            productEntity.freeQuantity,
            productEntity.price,
            productEntity.discount,
            productEntity.imageFileName
        )
    }

    fun toDataModel(product: Product): ProductEntity {
        val entity = ProductEntity()
        entity.id = product.id
        entity.typeName = product.typeName
        entity.sizeName = product.sizeName
        entity.colorName = product.colorName
        entity.colorValue = product.colorValue
        entity.showInCatalog = product.showInCatalog
        entity.freeQuantity = product.freeQuantity
        entity.price = product.price
        entity.discount = product.discount
        entity.imageFileName = product.imageFileName
        return entity
    }

    fun productDtoToProduct(productDto: ProductDto): Product{
        return Product(
            productDto.id,
            productDto.productType.name,
            productDto.productSize.name,
            productDto.productColor.name,
            productDto.productColor.value,
            productDto.showInCatalog,
            productDto.freeQuantity,
            productDto.price,
            productDto.discount,
            productDto.imageFileName
        )
    }
}