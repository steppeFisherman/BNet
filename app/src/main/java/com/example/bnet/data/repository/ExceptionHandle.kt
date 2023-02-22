package com.example.bnet.data.repository

import android.database.sqlite.SQLiteException
import com.example.bnet.domain.model.ErrorType
import com.example.bnet.domain.model.MyResult
import retrofit2.HttpException
import java.net.UnknownHostException

interface ExceptionHandle {

    fun handle(exception: Exception?): MyResult

    class Base : ExceptionHandle {
        override fun handle(exception: Exception?): MyResult = MyResult.Fail(
            when (exception) {
                is UnknownHostException -> ErrorType.NO_CONNECTION
                is HttpException -> ErrorType.HTTP_EXCEPTION
                is SQLiteException -> ErrorType.SQL_EXCEPTION
                else -> ErrorType.GENERIC_ERROR
            }
        )
    }
}
