package com.daggerapplication.datalayer.cloud

import com.daggerapplication.bean.AddConnectionRequest
import com.daggerapplication.bean.AddEditConnectionResponse
import io.reactivex.Observable


interface CloudRepository {

    fun addConnection(request: AddConnectionRequest): Observable<AddEditConnectionResponse>

}