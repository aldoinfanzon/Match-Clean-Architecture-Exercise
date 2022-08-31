package com.neuronride.match.cleanarch.application.ui.features.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.neuronride.match.cleanarch.application.ui.BaseViewModel
import com.neuronride.match.cleanarch.core.domain.interactor.currency.FetchAllCurrenciesInteractor
import com.neuronride.match.cleanarch.core.domain.interactor.currency.FetchAllCurrenciesInteractor.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(
    private val fetchCurrenciesInteractor: FetchAllCurrenciesInteractor
) : BaseViewModel() {

    val currenciesLiveData: LiveData<CurrencyState> = MutableLiveData()

    init {
        fetchAllCurrencies()
    }

    private fun fetchAllCurrencies() {
        currenciesLiveData.postValue(CurrencyState.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            fetchCurrenciesInteractor.invoke().let { state ->
                when (state) {
                    is Status.Success ->
                        currenciesLiveData.postValue(
                            CurrencyState.ShowList(state.currencyItems)
                        )

                    is Status.UnableToFetchCurrencies,
                    is Status.UnknownFailure ->
                        currenciesLiveData.postValue(CurrencyState.Error)
                }
            }
        }
    }
}