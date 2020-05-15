package com.example.codingchallengenycschools.model

import com.example.codingchallengenycschools.model.data.School
import com.example.codingchallengenycschools.model.network.SchoolApiService
import com.example.codingchallengenycschools.model.network.Result

import java.io.IOException

class SchoolRepo {
    private val apiService = SchoolApiService.create()

    suspend fun fetchSchools(
    ): Result<Any> {
        try {
            val response = apiService.fetchSchools()
            if (response.isSuccessful) {
                response.body()?.let { editSchoolAddr(it) }
                return Result.SuccessForSchool(response.body() ?: emptyList())
            }
            return Result.Error(IOException("Error fetching schools "))
        } catch (e: Exception) {
            return Result.Error(IOException("Unable to fetch schools " + e.message))
        }
    }

    suspend fun fetchScores(
    ): Result<Any> {
        try {
            val response = apiService.fetchScores()
            if (response.isSuccessful) {
                return Result.SuccessForScores(response.body() ?: emptyList())
            }
            return Result.Error(IOException("Error fetching schools' SAT Scores "))
        } catch (e: Exception) {
            return Result.Error(IOException("Unable to fetch schools' SAT Scores " + e.message))
        }
    }

    /*
    Edits the location field in School Class
    Removes all the information in the string after the zip code
    */
    private fun editSchoolAddr(schoolList: List<School>) {
        for (school in schoolList) {
            school.location = school.location.takeWhile { it != '(' }
        }
    }
}