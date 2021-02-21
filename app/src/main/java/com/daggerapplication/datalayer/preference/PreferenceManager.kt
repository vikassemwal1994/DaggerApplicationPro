package com.daggerapplication.datalayer.preference

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class PreferenceManager  @Inject constructor(mContext: Context) : PreferenceRepository  {

    private val mSharedPreferences: SharedPreferences by lazy {
        mContext.getSharedPreferences(PreferenceConstant.APP_PREFERENCE, Context.MODE_PRIVATE)
    }
    private val mPersistSharedPreferences: SharedPreferences by lazy {
        mContext.getSharedPreferences(PreferenceConstant.PERSIST_PREFERENCE, Context.MODE_PRIVATE)
    }

    override fun setUserId(id: String) {
        TODO("Not yet implemented")
    }

    override fun getUserId(): String? {
        TODO("Not yet implemented")
    }

    override fun getAuthCode(): String {
        return getString(PreferenceConstant.AUTH_CODE, mPersistSharedPreferences) ?: ""
    }

    override fun getToken(): String {
        return getString(PreferenceConstant.ACCESS_TOKEN, mPersistSharedPreferences) ?: ""
    }

    override fun setToken(token: String) {
        saveStringValue(PreferenceConstant.ACCESS_TOKEN, token)
    }

    @Suppress("SameParameterValue")
    private fun getString(
        key: String,
        mSharedPreferences: SharedPreferences = this.mSharedPreferences
    ): String? {
        return mSharedPreferences.getString(key, null)
    }

    @Suppress("SameParameterValue")
    private fun saveStringValue(
        key: String,
        value: String,
        mSharedPreferences: SharedPreferences = this.mSharedPreferences
    ) {
        mSharedPreferences.edit()?.also { editor ->
            editor.putString(key, value).apply()
        }
    }

}