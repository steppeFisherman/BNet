package com.example.bnet.data.net

import com.example.bnet.data.model.DataCloud
import retrofit2.http.GET
import retrofit2.http.Headers

interface MyService {

    companion object {
        const val BASE_URL = "http://shans.d2.i-partner.ru/"
        private const val HEADER = "accept: application/json"
    }

    @Headers(HEADER)
    @GET("api/ppp/index/")
    suspend fun fetchCloud(): List<DataCloud>
}