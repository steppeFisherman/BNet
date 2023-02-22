package com.example.bnet.domain

import com.example.bnet.domain.model.MyResult

interface Repository {
    suspend fun fetchCloud(): MyResult
}