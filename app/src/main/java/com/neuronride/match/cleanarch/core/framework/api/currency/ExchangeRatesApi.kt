package com.neuronride.match.cleanarch.core.framework.api.currency

import retrofit2.Response
import retrofit2.http.GET

private const val EXCHANGE_RATES_GET = "exchange_rates"

interface ExchangeRatesApi {

    @GET(EXCHANGE_RATES_GET)
    suspend fun getExchangeRates(): Response<CurrencyResponse>
}