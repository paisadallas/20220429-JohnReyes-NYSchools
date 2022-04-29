package com.john.a20220429_johnreyes_nyschools.res

import com.john.a20220429_johnreyes_nyschools.model.School
import retrofit2.Response



class RepositoryImplement(
    private val api: API
):RepositoryAPI{
    override suspend fun getAllData(): Response<School> =
        api.getSchool()

    override suspend fun getAllScore(): Response<School> =api.getScore()



}

interface RepositoryAPI {
    suspend fun getAllData():Response<School>

    suspend fun getAllScore():Response<School>
}