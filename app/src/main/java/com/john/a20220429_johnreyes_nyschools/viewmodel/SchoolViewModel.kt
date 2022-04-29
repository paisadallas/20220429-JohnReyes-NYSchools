package com.john.a20220429_johnreyes_nyschools.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.john.a20220429_johnreyes_nyschools.res.API
import com.john.a20220429_johnreyes_nyschools.res.RepositoryAPI
import com.john.a20220429_johnreyes_nyschools.utils.SchoolState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SchoolViewModel(
    private val apiRepositoryAPI: RepositoryAPI,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) :ViewModel(){

    private val _sortedSchool : MutableLiveData<SchoolState> = MutableLiveData(SchoolState.LOADING)
    val school: LiveData<SchoolState> get() = _sortedSchool

    private val _sortedSchoolItem : MutableLiveData<SchoolState> = MutableLiveData(SchoolState.LOADING)
    val scoreItem: LiveData<SchoolState> get() = _sortedSchoolItem


    /**
     * Creating coroutine with viewModelScope and launch
     *
     * launch - Launches a new coroutine without blocking the current thread and returns a
     * reference to the coroutine as a Job. The coroutine is cancelled when the resulting
     * job is cancelled.
     *
     * @param [ioDipspatcher] - Dispatcher used as a fallback for
     * time-related operations (delay, withTimeout) and to handle
     * rejected tasks from other dispatchers is also shut down.

     */

    fun getSchool(){
        viewModelScope.launch (ioDispatcher) {
            try {
                val respond = apiRepositoryAPI.getAllData()
                if (respond.isSuccessful){
                    respond.body()?.let {
                        _sortedSchool.postValue(SchoolState.SUCCESS(it))
                        getScore()
                    }
                }else{
                    throw Exception(respond.message())
                }
            }
            catch (e:java.lang.Exception){
                _sortedSchool.postValue(SchoolState.ERROR(e))
            }
        }
    }

     fun getScore(){
        viewModelScope.launch (ioDispatcher) {
            try {
                val respond = apiRepositoryAPI.getAllScore()
                if (respond.isSuccessful){
                    respond.body()?.let {
                        _sortedSchoolItem.postValue(SchoolState.SUCCESS(it))
                    }
                }else{
                    throw Exception(respond.message())
                }
            }
            catch (e:java.lang.Exception){
                _sortedSchoolItem.postValue(SchoolState.ERROR(e))
            }
        }
    }
}