package com.example.bnet.domain.model

sealed class MyResult {
    data class Success(val data: List<DataDomain>) : MyResult()
    data class Fail(val errorType: ErrorType) : MyResult()
}
