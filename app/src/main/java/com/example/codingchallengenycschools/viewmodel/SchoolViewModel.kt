package com.example.codingchallengenycschools.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codingchallengenycschools.model.SchoolRepo
import com.example.codingchallengenycschools.model.data.School
import com.example.codingchallengenycschools.model.data.Scores
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.codingchallengenycschools.model.network.Result

class SchoolViewModel : ViewModel() {

    private val _schoolList = MutableLiveData<List<School>>()
    val schoolList: LiveData<List<School>> = _schoolList

    private val _scoresList = MutableLiveData<List<Scores>>()
    val scoresList: LiveData<List<Scores>> = _scoresList

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val schoolRepo = SchoolRepo()

    fun fetchSchools() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = schoolRepo.fetchSchools()

            when (response) {
                is Result.SuccessForSchool -> {
                    _schoolList.postValue(response.data)
                }
                is Result.Error -> {
                    _error.postValue(response.exception.message)
                }
            }
        }
    }

    fun fetchScores() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = schoolRepo.fetchScores()

            when (response) {
                is Result.SuccessForScores -> {
                    _scoresList.postValue(response.data)
                }
                is Result.Error -> {
                    _error.postValue(response.exception.message)
                }
            }
        }
    }
}


