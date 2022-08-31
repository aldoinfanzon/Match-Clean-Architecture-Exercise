package com.neuronride.match.cleanarch.application.framework.di.module

import com.neuronride.match.cleanarch.core.domain.interactor.BaseInteractor
import com.neuronride.match.cleanarch.core.domain.interactor.currency.FetchAllCurrenciesInteractor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class InteractorProvideModule {

    @Binds
    abstract fun bindFetchAllCurrenciesInteractor(
        interactor: FetchAllCurrenciesInteractor
    ): BaseInteractor<FetchAllCurrenciesInteractor.Status>
}