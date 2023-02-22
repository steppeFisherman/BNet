package com.example.bnet.ui.di

import com.example.bnet.domain.FetchUseCase
import com.example.bnet.domain.Repository
import com.example.bnet.ui.model.MapDomainToUi
import com.example.bnet.utils.ToDispatch
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideFetchUseCase(repository: Repository): FetchUseCase =
        FetchUseCase.Base(repository = repository)

    @Provides
    fun provideMapDomainToUi(): MapDomainToUi = MapDomainToUi.Base()

    @Provides
    fun provideDispatchers(): ToDispatch = ToDispatch.Base()
}