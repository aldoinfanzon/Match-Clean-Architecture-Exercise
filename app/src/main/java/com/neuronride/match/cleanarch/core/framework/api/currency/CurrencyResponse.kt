package com.neuronride.match.cleanarch.core.framework.api.currency

data class CurrencyResponse(
    val rates: Map<String, CurrencyJson>
)