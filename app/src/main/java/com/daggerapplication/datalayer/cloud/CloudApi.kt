package com.daggerapplication.datalayer.cloud

import com.daggerapplication.bean.AddConnectionRequest
import com.daggerapplication.bean.AddEditConnectionResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface CloudApi {

//    @POST(UrlConstants.LOGIN)
//    fun loginUser(@Body request: LoginRequestBean): Observable<LoginResponse>

    @POST(UrlConstants.ADD_MY_CONNECTIONS)
    fun addConnection(@Body request: AddConnectionRequest): Observable<AddEditConnectionResponse>

}