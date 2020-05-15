package com.example.codingchallengenycschools.model.network

import com.example.codingchallengenycschools.model.data.School
import com.example.codingchallengenycschools.model.data.Scores

sealed class Result<out T: Any>{
    data class SuccessForSchool(val data: List<School>) : Result<Any>()
    data class SuccessForScores(val data: List<Scores>) : Result<Any>()
    data class Error(val exception: Exception) : Result<Nothing>()
}