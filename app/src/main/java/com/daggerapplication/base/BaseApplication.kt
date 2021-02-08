package com.daggerapplication.base

import android.content.Context
import android.content.res.Configuration
import com.daggerapplication.di.component.DaggerAppComponent
import com.daggerapplication.utility.LocaleHelper
import com.daggerapplication.utility.SessionMgmt
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class BaseApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
    }

    //  private lateinit var pluginYM: YMBotPlugin

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)

    }

    override fun attachBaseContext(base: Context?) {
//        MultiDex.install(this)
        val session = SessionMgmt(base)
        if (session.keyLanguage != "") {
            if (session.keyLanguage == "2") {
                super.attachBaseContext(LocaleHelper.onAttach(base, "es"))
            } else if (session.keyLanguage == "1") {
                super.attachBaseContext(LocaleHelper.onAttach(base, "en"))
            }
        } else {
            super.attachBaseContext(LocaleHelper.onAttach(base, "en"))
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        languageChange()
    }

    private fun languageChange() {

        var session =  SessionMgmt(this)
        if (session.keyLanguage != "") {

            if (session.keyLanguage == "2") {
                LocaleHelper.setLocale(this, "es")
            } else if (session.keyLanguage == "1") {
                LocaleHelper.setLocale(this, "en")
            }
        } else {
            LocaleHelper.setLocale(this, "en")
        }
    }

}