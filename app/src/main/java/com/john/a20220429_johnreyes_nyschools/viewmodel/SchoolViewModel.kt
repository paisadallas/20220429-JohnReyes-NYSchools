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
    val schoolItem: LiveData<SchoolState> get() = _sortedSchoolItem

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
                    throw Exception("response null")
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
                    throw Exception("response null")
                }
            }
            catch (e:java.lang.Exception){
                _sortedSchoolItem.postValue(SchoolState.ERROR(e))
            }
        }
    }
}