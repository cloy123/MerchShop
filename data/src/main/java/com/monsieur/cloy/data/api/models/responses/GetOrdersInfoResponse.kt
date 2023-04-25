package com.monsieur.cloy.data.api.models.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.monsieur.cloy.data.api.models.EventDto
import com.monsieur.cloy.data.api.models.OrderDto

class GetOrdersInfoResponse(
    @SerializedName("orders")
    @Expose
    var orders: List<OrderDto>
) {
}