package com.daggerapplication.datalayer.preference

interface PreferenceRepository {

    fun setUserId(id: String)
    fun getUserId(): String?
    fun getAuthCode(): String
    fun getToken(): String?
    fun setToken(name: String)

}