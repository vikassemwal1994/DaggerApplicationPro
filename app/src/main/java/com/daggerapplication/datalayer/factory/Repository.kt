package com.daggerapplication.datalayer.factory

import com.daggerapplication.datalayer.cloud.CloudRepository
import com.daggerapplication.datalayer.preference.PreferenceRepository

interface Repository : CloudRepository, PreferenceRepository {

}