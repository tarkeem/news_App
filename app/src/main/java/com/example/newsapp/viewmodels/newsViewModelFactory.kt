package com.example.newsapp.viewmodels


import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class newsViewModelFactory(private var cxt:Context):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(newsViewModel::class.java))
        {
            return newsViewModel(cxt) as T
        }
        throw error("wrong")
    }
}