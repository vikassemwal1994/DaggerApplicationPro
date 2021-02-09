package com.daggerapplication.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel()
{
    private val disposables = CompositeDisposable()

    override fun onCleared() {
        disposables.clear()
        super.onCleared()

    }

    fun getDisposables(): CompositeDisposable
    {
        return disposables
    }
}