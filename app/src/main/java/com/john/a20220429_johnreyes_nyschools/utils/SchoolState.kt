package com.john.a20220429_johnreyes_nyschools.utils

sealed class SchoolState {
    object LOADING:SchoolState()
    class SUCCESS<T> (val school:T):SchoolState()
    class ERROR(val error : Throwable):SchoolState()
}