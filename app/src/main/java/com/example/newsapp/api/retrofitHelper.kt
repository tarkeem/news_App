package com.example.newsapp.api

import android.widget.Toast
import com.example.newsapp.model.news
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class retrofitHelper {
    companion object{
        val uri="https://newsapi.org/"
        private val retrofit by lazy {
            var logging=HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client=OkHttpClient.Builder().addInterceptor(logging).build()
            Retrofit.Builder().
            baseUrl(uri).
            addConverterFactory(GsonConverterFactory.create()).client(client).build()

        }


            val Api by lazy {
                retrofit.create(newsapi::class.java)
            }





        }
    }
