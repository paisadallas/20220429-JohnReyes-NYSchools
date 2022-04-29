package com.john.a20220429_johnreyes_nyschools.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.john.a20220429_johnreyes_nyschools.res.RepositoryAPI
import com.john.a20220429_johnreyes_nyschools.utils.SchoolState
import io.mockk.clearAllMocks
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import com.google.common.truth.Truth.assertThat
import com.john.a20220429_johnreyes_nyschools.model.School
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SchoolViewModelTest{

    @get:Rule
    var rule = InstantTaskExecutorRule()

    private val testDispatcher = UnconfinedTestDispatcher()

    private val mockRepository = mockk<RepositoryAPI>(relaxed = true)

    lateinit var target: SchoolViewModel

    @Before
    fun setup(){
        Dispatchers.setMain(testDispatcher)
        target = SchoolViewModel(mockRepository,testDispatcher)
    }

    @After
    fun shutdown(){
        clearAllMocks()
        Dispatchers.resetMain()
    }

    @Test
    fun `get all school when trying to load from server returns loading state`() {
        val stateList = mutableListOf<SchoolState>()
        target.school.observeForever{
            stateList.add(it)
        }

        target.getSchool()

        assertThat(stateList).isNotEmpty()
        assertThat(stateList).hasSize(2)
        assertThat(stateList[0]).isInstanceOf(SchoolState.LOADING::class.java)


    }

    @Test
    fun `get all school when trying to load from server returns success state`() = runTest {
        coEvery { mockRepository.getAllData() } returns mockk{
            every { isSuccessful } returns true
            every { body() } returns mockk{ mockk<School>()}
        }

        val stateList = mutableListOf<SchoolState>()
        target.school.observeForever{
            stateList.add(it)
        }

        target.getSchool()

        assertThat(stateList).isNotEmpty()
        assertThat(stateList).hasSize(2)
        assertThat(stateList[1]).isInstanceOf(SchoolState.SUCCESS::class.java)
    }

    @Test
    fun `get all school when trying to load from server returns error state`() {
        val stateList = mutableListOf<SchoolState>()
        target.school.observeForever{
            stateList.add(it)
        }

        target.getSchool()

        assertThat(stateList).isNotEmpty()
        assertThat(stateList).hasSize(2)
        assertThat(stateList[1]).isInstanceOf(SchoolState.ERROR::class.java)

    }

    @Test
    fun `get all school item when trying to load from server laoding state`() {
        val stateList = mutableListOf<SchoolState>()
        target.schoolItem.observeForever{
            stateList.add(it)
        }

        target.getScore()

        assertThat(stateList).isNotEmpty()
        assertThat(stateList).hasSize(2)
        assertThat(stateList[0]).isInstanceOf(SchoolState.LOADING::class.java)

    }

    @Test
    fun `get all school item when trying to load from server returns success state`() = runTest {
        coEvery { mockRepository.getAllData() } returns mockk{
            every { isSuccessful } returns true
            every { body() } returns mockk{ mockk<School>()}
        }

        val stateList = mutableListOf<SchoolState>()
        target.schoolItem.observeForever{
            stateList.add(it)
        }

        target.getScore()

        assertThat(stateList).isNotEmpty()
        assertThat(stateList).hasSize(2)
        assertThat(stateList[1]).isInstanceOf(SchoolState.SUCCESS::class.java)
    }

    @Test
    fun `get all school item when trying to load from server returns error state`() {
        val stateList = mutableListOf<SchoolState>()
        target.schoolItem.observeForever{
            stateList.add(it)
        }

        target.getScore()

        assertThat(stateList).isNotEmpty()
        assertThat(stateList).hasSize(2)
        assertThat(stateList[1]).isInstanceOf(SchoolState.ERROR::class.java)

    }
}