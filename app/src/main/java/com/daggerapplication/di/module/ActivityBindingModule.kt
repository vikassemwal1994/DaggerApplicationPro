package com.daggerapplication.di.module

import com.daggerapplication.MainActivity
import com.daggerapplication.MainModule
import com.daggerapplication.di.scope.ActivityScoped
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [MainModule::class])
    internal abstract fun contributeMainActivity(): MainActivity



}