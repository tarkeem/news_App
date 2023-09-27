package com.example.newsapp.fragments.webArticle

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.newsapp.databinding.FragmentWebArticleBinding
import com.example.newsapp.model.Article
import com.example.newsapp.viewmodels.newsViewModel
import com.example.newsapp.viewmodels.newsViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class webArticleFragment:Fragment() {
    private var _binding:FragmentWebArticleBinding?=null
    val binding get() = _binding!!
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModelfactory= newsViewModelFactory(this.context!!)
        var viewModel: newsViewModel = ViewModelProvider(this,viewModelfactory).get(newsViewModel::class.java)
        _binding= FragmentWebArticleBinding.inflate(inflater,container,false)
        var arg=this.arguments
        println("web fragment........................")
        var article:Article?=arg!!.getSerializable("article") as Article
        println(article.toString())
         binding.webView.webViewClient=WebViewClient()
            binding.webView.loadUrl(article!!.url)
        binding.fab.setOnClickListener {

            viewModel.inserToDb(article)



        }
        val root: View = binding.root
        return root
    }
}