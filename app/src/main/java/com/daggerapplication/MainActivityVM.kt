package com.daggerapplication

import com.daggerapplication.base.BaseViewModel
import com.daggerapplication.datalayer.factory.DataSourceFactory
import javax.inject.Inject

class MainActivityVM @Inject constructor(private val mDataSource: DataSourceFactory) :
    BaseViewModel() {




}