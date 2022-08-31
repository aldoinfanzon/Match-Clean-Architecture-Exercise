package com.neuronride.match.cleanarch.core.domain.interactor

interface BaseParamInteractor<in InputParams, out FinalResult>
        where InputParams : Any, FinalResult : Any {

    suspend operator fun invoke(params: InputParams): FinalResult
}