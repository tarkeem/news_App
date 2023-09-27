package com.example.newsapp.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newsapp.model.Article
import com.example.newsapp.model.news
import retrofit2.Response

interface newsRepo {
suspend fun getBreakingNews(conterCode:String,page:Int,): Response<news>
suspend fun SearchBreakingNews(query:String): Response<news>
suspend fun insertTodb(article: Article)
suspend fun getFromDb():LiveData<List<Article>>


}