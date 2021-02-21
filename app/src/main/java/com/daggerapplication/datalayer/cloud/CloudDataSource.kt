package com.daggerapplication.datalayer.cloud

import com.daggerapplication.bean.AddConnectionRequest
import com.daggerapplication.bean.AddEditConnectionResponse
import io.reactivex.Observable
import javax.inject.Inject

class CloudDataSource @Inject constructor(private val cloudApi: CloudApi) : CloudRepository  {

    override fun addConnection(request: AddConnectionRequest): Observable<AddEditConnectionResponse> {
        TODO("Not yet implemented")
    }


}