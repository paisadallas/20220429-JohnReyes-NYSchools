package com.john.a20220526_johnreyes_nyschools.utils

sealed class SchoolState {
    object LOADING:SchoolState()
    class SUCCESS<T> (val school:T):SchoolState()
    class ERROR(val error : Throwable):SchoolState()
}