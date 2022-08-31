package com.neuronride.match.cleanarch.core.domain.interactor.currency

import com.neuronride.match.cleanarch.core.domain.entity.Currency
import com.neuronride.match.cleanarch.core.domain.interactor.BaseInteractor
import com.neuronride.match.cleanarch.core.domain.repository.currency.CurrencyRepository
import com.neuronride.match.cleanarch.core.domain.repository.currency.CurrencyRepository.CurrencyFailure
import javax.inject.Inject

class FetchAllCurrenciesInteractor @Inject constructor(
    private val repository: CurrencyRepository
) : BaseInteractor<FetchAllCurrenciesInteractor.Status> {

    override suspend fun invoke(): Status {
        runCatching {
            val items = repository.fetchAll()
            return Status.Success(items)
        }.onFailure { throwable ->
            when (throwable) {
                is CurrencyFailure.FetchCurrencyListFailure ->
                    return Status.UnableToFetchCurrencies

                else -> {}
            }
        }

        return Status.UnknownFailure
    }

    sealed class Status {

        data class Success(
            val currencyItems: List<Currency>
        ) : Status()

        object UnableToFetchCurrencies : Status()

        object UnknownFailure : Status()
    }
}