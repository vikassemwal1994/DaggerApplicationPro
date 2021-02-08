package com.daggerapplication.di.module

import android.content.ClipboardManager
import android.content.Context
import android.net.wifi.WifiManager
import com.daggerapplication.base.BaseApplication
import dagger.Module
import dagger.Provides

@Module
class AppModule {


    @Provides
    fun provideContext(application: BaseApplication): Context {
        return application.applicationContext
    }


    @Provides
    fun providesWifiManager(context: Context): WifiManager =
        context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

    @Provides
    fun providesClipboardManager(context: Context): ClipboardManager =
        context.applicationContext.getSystemService(Context.CLIPBOARD_SERVICE)
                as ClipboardManager

/*@Provides
    fun provideGlide(context: Context): RequestManager {
        return Glide.with(context)
    }*/


}