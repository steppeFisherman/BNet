package com.example.bnet.ui.di

import com.example.bnet.data.model.MapCloudToDomain
import com.example.bnet.data.net.CloudData
import com.example.bnet.data.net.MyService
import com.example.bnet.data.repository.CloudSource
import com.example.bnet.data.repository.ExceptionHandle
import com.example.bnet.data.repository.RepositoryImpl
import com.example.bnet.domain.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideClient(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.MINUTES)
        .writeTimeout(1, TimeUnit.MINUTES)
        .readTimeout(1, TimeUnit.MINUTES)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(MyService.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideService(retrofit: Retrofit): MyService = retrofit.create(MyService::class.java)

    @Provides
    @Singleton
    fun provideCloud(myService: MyService): CloudData = CloudData.Base(myService)

    @Provides
    fun provideExceptionHandle(): ExceptionHandle = ExceptionHandle.Base()

    @Provides
    @Singleton
    fun provideMapCloudToDomain(): MapCloudToDomain = MapCloudToDomain.Base()

    @Provides
    fun provideCloudSource(
        cloudData: CloudData,
        mapper: MapCloudToDomain
    ): CloudSource = CloudSource.Base(
        cloudData = cloudData,
        mapper = mapper
    )

    @Provides
    @Singleton
    fun provideRepository(
        cloudSource: CloudSource,
        exceptionHandle: ExceptionHandle,
    ): Repository = RepositoryImpl(
        cloudSource = cloudSource,
        exceptionHandle = exceptionHandle,
    )
}