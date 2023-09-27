package com.example.newsapp.repo

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.example.newsapp.api.retrofitHelper
import com.example.newsapp.db.NewsDatabase

import com.example.newsapp.model.Article
import com.example.newsapp.model.news
import retrofit2.Response


class newsRepoImpl(var cxt :Context):newsRepo {

        var db=NewsDatabase.invoke(cxt)
    override suspend fun getBreakingNews(conterCode: String, page: Int): Response<news> {
        var res =retrofitHelper.Api.getBreakingNews()
        return  res
    }


    override suspend fun SearchBreakingNews(q: String): Response<news> {
        var res =retrofitHelper.Api.getSearchedNews(querySh = q)
        return  res
    }

    override suspend fun insertTodb(article: Article) {
        db.getArticleDao().insertToDb(article)
    }

    override suspend fun getFromDb(): LiveData<List<Article>> {
      return  db.getArticleDao().getAllArticle()
    }


}