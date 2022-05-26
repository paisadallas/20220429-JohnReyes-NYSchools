package com.john.a20220526_johnreyes_nyschools.res

import com.john.a20220526_johnreyes_nyschools.model.School
import retrofit2.Response
import retrofit2.http.GET

interface API {

    @GET(SCHOOL_LIST)
    suspend fun getSchool():Response<School>

    @GET(SAT_SCORE)
    suspend fun getScore():Response<School>

    companion object{
        const val BASE_URL = "https://data.cityofnewyork.us/"
        private const val SCHOOL_LIST = "resource/s3k6-pzi2.json"
        private const val SAT_SCORE = "resource/f9bf-2cp4.json"
    }

}