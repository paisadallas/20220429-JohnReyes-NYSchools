package com.john.a20220429_johnreyes_nyschools.res

import com.john.a20220429_johnreyes_nyschools.model.School
import com.john.a20220429_johnreyes_nyschools.res.API.Companion.SAT_SCORE
import com.john.a20220429_johnreyes_nyschools.res.API.Companion.SCHOOL_LIST
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
    //https://data.cityofnewyork.us/resource/s3k6-pzi2.json
}