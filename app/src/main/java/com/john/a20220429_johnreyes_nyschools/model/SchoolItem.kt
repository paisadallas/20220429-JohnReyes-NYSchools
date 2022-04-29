package com.john.a20220429_johnreyes_nyschools.model


import com.google.gson.annotations.SerializedName

data class SchoolItem(
    @SerializedName("dbn")
    val dbn:String,
    @SerializedName("school_name")
    val school_name: String,
    @SerializedName("phone_number")
    val phone_number:String,
    @SerializedName("city")
    val city:String,
    @SerializedName("zip")
    val zip:String,
    @SerializedName("overview_paragraph")
    val overview:String,
    @SerializedName("num_of_sat_test_takers")
    val sat_takers:String,
    @SerializedName("sat_critical_reading_avg_score")
    val sat_critical:String,
    @SerializedName("sat_math_avg_score")
    val sat_math:String,
    @SerializedName("sat_writing_avg_score")
    val sat_writing:String
)