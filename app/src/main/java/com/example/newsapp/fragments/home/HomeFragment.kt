package com.example.newsapp.fragments.home

import android.os.Bundle
import android.system.ErrnoException
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.api.newsapi
import com.example.newsapp.api.retrofitHelper
import com.example.newsapp.databinding.FragmentHomeBinding
import com.example.newsapp.model.news
import com.example.newsapp.viewmodels.newsViewModel
import myCustomAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        var muadabter=myCustomAdapter(this.context!!)
        var viewModel: newsViewModel = ViewModelProvider(this).get(newsViewModel::class.java)
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

viewModel.getNews()
        viewModel.newsData.observe(this) {
            if(it.isLoading)
            {
                binding.progressBar.visibility=View.VISIBLE
            }
            else
            {
                binding.progressBar.visibility=View.GONE
                muadabter.differ.submitList(it.data.body()?.articles)
                binding.recyclerView.adapter=muadabter
                //without layout manager recycle view will not wor
                binding.recyclerView.layoutManager=LinearLayoutManager(activity)
            }

        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}