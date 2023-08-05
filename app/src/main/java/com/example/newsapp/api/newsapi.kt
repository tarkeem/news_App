package com.example.newsapp.api

import com.example.newsapp.model.news
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.newsapp.utilits.constants
import com.example.newsapp.utilits.constants.Companion.api_key
import retrofit2.Call
import retrofit2.Response

interface newsapi {
    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country")
        code:String="us",
        @Query("page")
        pa:Int=1,
        @Query("apiKey")
        mykey:String= api_key,

        ):Response<news>

    @GET("v2/top-headlines")
    suspend fun getSearchedNews(
        @Query("q") querySh:String,
        @Query("page") page:Int=1,
        @Query("apiKey") apikey:String=api_key,
    ):Response<news>
}