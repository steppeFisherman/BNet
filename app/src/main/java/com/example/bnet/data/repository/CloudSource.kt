package com.example.bnet.data.repository

import com.example.bnet.data.model.MapCloudToDomain
import com.example.bnet.data.net.CloudData
import com.example.bnet.domain.model.MyResult
import javax.inject.Inject

interface CloudSource {

    suspend fun fetchCloud(): MyResult

    class Base @Inject constructor(
        private val cloudData: CloudData,
        private val mapper: MapCloudToDomain
    ) : CloudSource {

        override suspend fun fetchCloud(): MyResult {
            val cloud = cloudData.fetchCloud()
            val domain = cloud.map { mapper.transform(it) }
            return MyResult.Success(domain)
        }
    }
}