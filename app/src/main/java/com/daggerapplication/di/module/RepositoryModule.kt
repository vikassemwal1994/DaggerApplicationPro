package com.daggerapplication.di.module

import android.content.Context
import com.daggerapplication.datalayer.cloud.CloudApi
import com.daggerapplication.datalayer.cloud.CloudDataSource
import com.daggerapplication.datalayer.factory.DataSourceFactory
import com.daggerapplication.datalayer.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    internal fun provideCloudDataSource(@Named("intercepted") apiService: CloudApi): CloudDataSource {
        return CloudDataSource(apiService)
    }

    @Provides
    @Singleton
    fun providesPreferenceStorage(context: Context): PreferenceManager =
        PreferenceManager(context)

    @Provides
    @Singleton
    internal fun provideDataSourcefactory(mCloudSource: CloudDataSource,
                                          mPreferenceSource: PreferenceManager): DataSourceFactory {
        return DataSourceFactory(mCloudSource,mPreferenceSource)
    }

}