package com.example.codingchallengenycschools.model.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class School(
    val dbn: String,
    val school_name : String,
    var location : String,
    val latitude : String,
    val longitude : String,
    val overview_paragraph : String,
    val phone_number : String,
    val fax_number : String,
    val school_email : String?, // not every school has an email
    var website : String,
    val borough : String
): Parcelable

@Parcelize
data class Scores(
    val dbn : String ,
    val num_of_sat_test_takers : String,
    val sat_critical_reading_avg_score : String,
    val sat_math_avg_score : String,
    val  sat_writing_avg_score : String
): Parcelable