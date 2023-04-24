package com.monsieur.cloy.data.api.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ProductDto(
    @SerializedName("id")
    @Expose
    var id: String,
    @SerializedName("productTypeId")
    @Expose
    var productTypeId: String,
    @SerializedName("productType")
    @Expose
    var productType: ProductTypeDto,
    @SerializedName("productSizeId")
    @Expose
    var productSizeId: String,
    @SerializedName("productSize")
    @Expose
    var productSize: ProductSizeDto,
    @SerializedName("productColorId")
    @Expose
    var productColorId: String,
    @SerializedName("productColor")
    @Expose
    var productColor: ProductColorDto,
    @SerializedName("showInCatalog")
    @Expose
    var showInCatalog: Boolean,
    @SerializedName("quantity")
    @Expose
    var quantity: Int,
    @SerializedName("freeQuantity")
    @Expose
    var freeQuantity: Int,
    @SerializedName("minQuantity")
    @Expose
    var minQuantity: Int,
    @SerializedName("price")
    @Expose
    var price: Int,
    @SerializedName("discount")
    @Expose
    var discount: Int,
    @SerializedName("imageFileName")
    @Expose
    var imageFileName: String,
) {
}