package com.monsieur.cloy.data.api.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class OrderDto(
    @SerializedName("id")
    @Expose
    var id: String,
    @SerializedName("userId")
    @Expose
    var userId: String,
    @SerializedName("dateCreation")
    @Expose
    var dateCreation: String,
    @SerializedName("dateCompletion")
    @Expose
    var dateCompletion: String?,
    @SerializedName("statusId")
    @Expose
    var statusId: Int,
    @SerializedName("orderSum")
    @Expose
    var orderSum: Int,
    @SerializedName("orderItems")
    @Expose
    var orderItems: List<OrderItemDto>
) {
}