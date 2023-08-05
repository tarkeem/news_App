package com.example.newsapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapp.model.Article

@Dao
interface newsDao {
    @Insert(onConflict =OnConflictStrategy.REPLACE )
    suspend fun insertToDb(article: Article)

    @Query("SELECT* FROM ARTICLES")
    fun getAllArticle():LiveData<List<Article>>

    @Delete
    suspend fun deletFromDb(article: Article)
}