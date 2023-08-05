package com.example.newsapp.repo

import com.example.newsapp.model.news
import retrofit2.Response

interface newsRepo {
suspend fun getBreakingNews(conterCode:String,page:Int,): Response<news>
suspend fun SearchBreakingNews(query:String): Response<news>
}