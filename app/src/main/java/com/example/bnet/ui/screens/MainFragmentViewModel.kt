package com.example.bnet.ui.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bnet.domain.FetchUseCase
import com.example.bnet.domain.model.ErrorType
import com.example.bnet.domain.model.MyResult
import com.example.bnet.ui.model.DataUi
import com.example.bnet.ui.model.MapDomainToUi
import com.example.bnet.utils.ToDispatch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    private val fetchUseCase: FetchUseCase,
    private val mapper: MapDomainToUi,
    private val dispatchers: ToDispatch
) : ViewModel() {

    private var mData = MutableLiveData<List<DataUi>>()
    private var mError = MutableLiveData<ErrorType>()
    private var mLoading = MutableLiveData(false)

    val data: LiveData<List<DataUi>>
        get() = mData
    val error: LiveData<ErrorType>
        get() = mError
    val loading: LiveData<Boolean>
        get() = mLoading

    private val exceptionHandler = CoroutineExceptionHandler { _, _ -> }
    private val scope = CoroutineScope(Job() + exceptionHandler)

    private fun fetch() {
        mLoading.value = true
        dispatchers.launchUI(scope = scope) {
            this.coroutineContext
            when (val result = fetchUseCase.fetchCloud()) {
                is MyResult.Success -> {
                    mLoading.value = false
                    mData.value = result.data.map { mapper.transform(it) }
                }
                is MyResult.Fail -> {
                    mLoading.value = false
                    mError.value = result.errorType
                }
            }
        }
    }

    init {
        fetch()
    }
}