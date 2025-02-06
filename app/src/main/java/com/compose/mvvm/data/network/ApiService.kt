package com.compose.mvvm.data.network


import com.compose.mvvm.data.model.AppDataModel
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("top-headlines")
    suspend fun getNewsBySources(@Query("sources") sources: String): AppDataModel

}