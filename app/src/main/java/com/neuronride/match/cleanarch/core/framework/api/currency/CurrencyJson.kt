package com.neuronride.match.cleanarch.core.framework.api.currency

data class CurrencyJson(
    val id: String,
    val name: String,
    val unit: String,
    val value: Double,
    val type: String
)