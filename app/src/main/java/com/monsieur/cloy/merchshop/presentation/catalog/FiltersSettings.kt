package com.monsieur.cloy.merchshop.presentation.catalog

class FiltersSettings(var sortBy: Sort,
                      var priceFrom: Int,
                      var priceTo: Int,
                      var listColors: List<String>,
                      var listTypes: List<String>) {
}

enum class Sort {
    ByPriceDescending,
    ByPriceAscending,
    ByName
}