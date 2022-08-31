package com.neuronride.match.cleanarch.application.ui.features.main.viewmodel

import com.neuronride.match.cleanarch.core.domain.entity.Currency

sealed class CurrencyState {

    object Loading : CurrencyState()

    object Error : CurrencyState()

    data class ShowList(
        val items: List<Currency>
    ) : CurrencyState()
}