package com.neuronride.match.cleanarch.core.domain.interactor

interface BaseInteractor<T> {

    suspend operator fun invoke(): T
}