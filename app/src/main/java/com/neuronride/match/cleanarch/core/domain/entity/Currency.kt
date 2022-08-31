package com.neuronride.match.cleanarch.core.domain.entity

data class Currency(
    val id: String,
    val name: String,
    val unit: String,
    val value: Double,
    val type: String
)