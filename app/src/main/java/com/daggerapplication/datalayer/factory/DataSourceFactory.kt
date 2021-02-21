package com.daggerapplication.datalayer.factory

import com.daggerapplication.bean.AddConnectionRequest
import com.daggerapplication.bean.AddEditConnectionResponse
import com.daggerapplication.datalayer.cloud.CloudDataSource
import com.daggerapplication.datalayer.preference.PreferenceManager
import io.reactivex.Observable
import javax.inject.Inject

class DataSourceFactory @Inject constructor(
    private val mCloudSource: CloudDataSource,
    private val mPreferenceSource: PreferenceManager

) : Repository {

    override fun addConnection(request: AddConnectionRequest): Observable<AddEditConnectionResponse> {
        return mCloudSource.addConnection(request = request)
    }



    //preference
    override fun setUserId(id: String) {
        return mPreferenceSource.setUserId(id)
    }

    override fun getUserId(): String? {
        return mPreferenceSource.getUserId()
    }

    override fun getAuthCode(): String {
        return mPreferenceSource.getAuthCode()
    }

    override fun getToken(): String? {
        return mPreferenceSource.getToken()
    }

    override fun setToken(token: String) {
        return mPreferenceSource.setToken(token = token)
    }


}