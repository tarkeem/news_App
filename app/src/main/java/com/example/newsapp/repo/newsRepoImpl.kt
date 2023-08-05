package com.example.newsapp.repo

import com.example.newsapp.api.retrofitHelper
import com.example.newsapp.model.news
import retrofit2.Response

class newsRepoImpl():newsRepo {
    override suspend fun getBreakingNews(conterCode: String, page: Int): Response<news> {
        var res =retrofitHelper.Api.getBreakingNews()
        return  res
    }

    override suspend fun SearchBreakingNews(q: String): Response<news> {
        var res =retrofitHelper.Api.getSearchedNews(querySh = q)
        return  res
    }
}