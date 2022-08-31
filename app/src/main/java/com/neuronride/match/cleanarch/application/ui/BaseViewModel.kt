package com.neuronride.match.cleanarch.application.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {

    protected fun <T> LiveData<T>.postValue(value: T) {
        when (this) {
            is MutableLiveData<T> -> postValue(value)
            else -> throw ExceptionInInitializerError("Not using mutablelivedata")
        }
    }
}