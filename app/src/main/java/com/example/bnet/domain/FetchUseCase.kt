package com.example.bnet.domain

import com.example.bnet.domain.model.MyResult
import javax.inject.Inject

interface FetchUseCase {

    suspend fun fetchCloud(): MyResult

    class Base @Inject constructor(private val repository: Repository) : FetchUseCase {
        override suspend fun fetchCloud() = repository.fetchCloud()
    }
}