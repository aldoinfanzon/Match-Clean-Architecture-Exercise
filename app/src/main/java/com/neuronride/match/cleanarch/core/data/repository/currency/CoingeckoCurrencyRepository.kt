package com.neuronride.match.cleanarch.core.data.repository.currency

import com.neuronride.match.cleanarch.core.domain.entity.Currency
import com.neuronride.match.cleanarch.core.domain.repository.currency.CurrencyRepository
import com.neuronride.match.cleanarch.core.domain.repository.currency.CurrencyRepository.CurrencyFailure
import com.neuronride.match.cleanarch.core.framework.api.currency.CurrencyJson
import com.neuronride.match.cleanarch.core.framework.api.currency.ExchangeRatesApi
import javax.inject.Inject
import timber.log.Timber

class CoingeckoCurrencyRepository @Inject constructor(
    private val api: ExchangeRatesApi
) : CurrencyRepository {

    override suspend fun fetchAll(): List<Currency> {
        runCatching {
            val call = api.getExchangeRates()
            val response = call.body()!!
            val list = ArrayList<Currency>(response.rates.size)
            response.rates.forEach { entry ->
                list.add(entry.value.toCurrency(entry.key))
            }
            return list
        }.onFailure { throwable ->
            Timber.e(throwable)
        }

        throw CurrencyFailure.FetchCurrencyListFailure
    }

    private fun CurrencyJson.toCurrency(id: String): Currency =
        Currency(
            id,
            name,
            unit,
            value,
            type
        )
}