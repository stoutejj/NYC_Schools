package com.example.codingchallengenycschools.model.network

import com.example.codingchallengenycschools.model.data.School
import com.example.codingchallengenycschools.model.data.Scores
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface SchoolApiService {

    @GET("s3k6-pzi2.json")
    suspend fun fetchSchools() : Response<List<School>>

    @GET("f9bf-2cp4.json")
    suspend fun fetchScores() : Response<List<Scores>>

    companion object{
        fun create() : SchoolApiService {
            val interceptor =
                HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)

            val client =
                OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()

            val retrofit =
                Retrofit.Builder()
                    .baseUrl("https://data.cityofnewyork.us/resource/")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()

            return retrofit.create(SchoolApiService::class.java)
        }
    }
}