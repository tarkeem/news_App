package com.example.newsapp.model

data class news(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)