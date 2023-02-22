package com.example.bnet.data.net

import com.example.bnet.data.model.DataCloud
import javax.inject.Inject

interface CloudData {

    suspend fun fetchCloud(): List<DataCloud>

    class Base @Inject constructor(private val myService: MyService) : CloudData {
        override suspend fun fetchCloud(): List<DataCloud> = myService.fetchCloud()
    }
}