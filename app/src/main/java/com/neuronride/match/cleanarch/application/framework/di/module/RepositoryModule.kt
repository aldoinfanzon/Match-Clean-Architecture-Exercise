package com.neuronride.match.cleanarch.application.framework.di.module

import com.neuronride.match.cleanarch.core.data.repository.currency.CoingeckoCurrencyRepository
import com.neuronride.match.cleanarch.core.domain.repository.currency.CurrencyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindCurrencyRepository(
        repository: CoingeckoCurrencyRepository
    ): CurrencyRepository
}