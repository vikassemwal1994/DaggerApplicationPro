package com.daggerapplication

import androidx.lifecycle.ViewModel
import com.daggerapplication.di.scope.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainActivityVM::class)
    abstract fun bindMainViewModel(viewModel: MainActivityVM): ViewModel


}