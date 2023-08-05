package com.example.newsapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.model.news
import com.example.newsapp.repo.newsRepo
import com.example.newsapp.repo.newsRepoImpl
import kotlinx.coroutines.launch
import retrofit2.Response

class newsViewModel:ViewModel(){
    var newsRepoImpl: newsRepoImpl=newsRepoImpl()

    //tag:
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











}