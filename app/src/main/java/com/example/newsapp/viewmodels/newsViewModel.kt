package com.example.newsapp.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.newsapp.db.newsDao


import com.example.newsapp.model.Article
import com.example.newsapp.model.news
import com.example.newsapp.repo.newsRepo
import com.example.newsapp.repo.newsRepoImpl
import kotlinx.coroutines.launch
import retrofit2.Response

class newsViewModel(var cxt:Context):ViewModel(){


    var newsRepoImpl: newsRepoImpl=newsRepoImpl(cxt)

    //region: api_part
    var newsData:MutableLiveData<newsState> = MutableLiveData()



    fun  getNews()
    {
        viewModelScope.launch {
            var  res = newsRepoImpl.getBreakingNews("us",1)
            emitNewsState(false,res)
        }

    }


    fun emitNewsState(isLoading:Boolean=true,data:Response<news>)
    {
        var state=newsState(isLoading,data)
        newsData.postValue(state)
    }


    data class newsState(var isLoading:Boolean=false,var data:Response<news>)
    //endtag:




    var searchData:MutableLiveData<searchState> = MutableLiveData()
    fun  getSearchNews(q:String)
    {
        viewModelScope.launch {
            var  res = newsRepoImpl.SearchBreakingNews(q = q)
            emitSearchNewsState(false,res)
        }

    }


    fun emitSearchNewsState(isLoading:Boolean=true,data:Response<news>)
    {
        var state=searchState(isLoading,data)
        searchData.postValue(state)
    }


    data class searchState(var isLoading:Boolean=false,var data:Response<news>)


//endregion

//region::data base part
    fun inserToDb(article: Article)
    {
        viewModelScope.launch {
            newsRepoImpl.insertTodb(article)
        }

    }
    var savedNews:MutableLiveData<List<Article>> = MutableLiveData()

    fun getNewsFromDb()
    {
        viewModelScope.launch {
          savedNews.postValue(newsRepoImpl.getFromDb().value)
        }

    }


    //endregion



}