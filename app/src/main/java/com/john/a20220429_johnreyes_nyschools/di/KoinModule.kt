package com.john.a20220429_johnreyes_nyschools.di

import com.john.a20220429_johnreyes_nyschools.res.API
import com.john.a20220429_johnreyes_nyschools.res.RepositoryAPI
import com.john.a20220429_johnreyes_nyschools.res.RepositoryImplement
import com.john.a20220429_johnreyes_nyschools.viewmodel.SchoolViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    fun providesLoggingInterceptors():HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    fun providesHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor):OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

    fun providesNetworkServices(okHttpClient: OkHttpClient): API =
        Retrofit.Builder()
            .baseUrl(API.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(API::class.java)

    fun providesJokeRepo(networkServices:API):RepositoryAPI =
        RepositoryImplement(networkServices)

    single { providesLoggingInterceptors() }
    single { providesHttpClient(get()) }
    single { providesNetworkServices(get()) }
    single { providesJokeRepo(get()) }
}

val viewModelModule = module {
    viewModel {SchoolViewModel(get())}
}