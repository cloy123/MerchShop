package com.monsieur.cloy.data.api.models.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.monsieur.cloy.data.api.models.ProductColorDto
import com.monsieur.cloy.data.api.models.ProductDto
import com.monsieur.cloy.data.api.models.ProductSizeDto
import com.monsieur.cloy.data.api.models.ProductTypeDto

class GetCatalogInfoResponse(
    @SerializedName("products")
    @Expose
    var products: List<ProductDto>,

//    @SerializedName("productSizes")
//    @Expose
//    var productSizes: List<ProductSizeDto>,
//
//    @SerializedName("productColors")
//    @Expose
//    var productColors: List<ProductColorDto>,
//
//    @SerializedName("productTypes")
//    @Expose
//    var productTypes: List<ProductTypeDto>

) {
}