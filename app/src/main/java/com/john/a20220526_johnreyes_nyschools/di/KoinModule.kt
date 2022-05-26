package com.john.a20220526_johnreyes_nyschools.di

import com.john.a20220526_johnreyes_nyschools.res.API
import com.john.a20220526_johnreyes_nyschools.res.RepositoryAPI
import com.john.a20220526_johnreyes_nyschools.res.RepositoryImplement
import com.john.a20220526_johnreyes_nyschools.viewmodel.SchoolViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Dependency inject Modules for be inject with koin:
 * Network
 * ViewModel
 *
 */
val networkModule = module {

    /**
     * The format of the logs created by this class
     * should not be considered stable and may change slightly between releases
     *
     */

    fun providesLoggingInterceptors():HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    /**
     * This builds a client that shares the same connection pool, thread pools, and configuration.
     */
    fun providesHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor):OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

    /**
     * This is providing the Service API interface for the restful service call
     *
     * @return [API] -> Interface for the service network call
     *
     * @param okHttpClient - This is the client object to add interceptors and timeouts
     */
    fun providesNetworkServices(okHttpClient: OkHttpClient): API =
        Retrofit.Builder()
            .baseUrl(API.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(API::class.java)

    /**
     * This is providing the API repository for the API
     *
     * @return [RepositoryAPI] - implementing interface of the repository
     *
     * @param networkServices - this is the network Services of the API
     */
    fun providesApiRepo(networkServices:API):RepositoryAPI =
        RepositoryImplement(networkServices)

    single { providesLoggingInterceptors() }
    single { providesHttpClient(get()) }
    single { providesNetworkServices(get()) }
    single { providesApiRepo(get()) }
}

/**
 * This is providing the View Model for be inject
 */

val viewModelModule = module {
    viewModel {SchoolViewModel(get())}
}