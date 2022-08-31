package com.neuronride.match.cleanarch.core.domain.repository.currency

import com.neuronride.match.cleanarch.core.domain.entity.Currency
import com.neuronride.match.cleanarch.core.domain.exception.Failure

interface CurrencyRepository {

    suspend fun fetchAll(): List<Currency>

    sealed class CurrencyFailure : Failure() {

        object FetchCurrencyListFailure : CurrencyFailure()
    }
}