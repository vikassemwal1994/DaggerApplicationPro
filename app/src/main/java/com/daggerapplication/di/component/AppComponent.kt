package com.daggerapplication.di.component

import com.daggerapplication.base.BaseApplication
import com.daggerapplication.di.module.*
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBindingModule::class,
        AppModule::class,
        ViewModelModule::class,
        CloudModule::class,
//        NetworkModule::class,
        RepositoryModule::class

    ]
)
public interface AppComponent : AndroidInjector<BaseApplication> {
    @Component.Factory
    abstract class Builder : AndroidInjector.Factory<BaseApplication>
}