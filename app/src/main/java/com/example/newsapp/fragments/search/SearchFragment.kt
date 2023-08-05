package com.example.newsapp.fragments.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.databinding.FragmentSearchBinding
import com.example.newsapp.viewmodels.newsViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import myCustomAdapter

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        var myadabter=myCustomAdapter(this.context!!)
        var viewModel: newsViewModel = ViewModelProvider(this).get(newsViewModel::class.java)


        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root

        var job:Job?=null
        binding.etSearch.addTextChangedListener {
            job?.cancel()
           job= MainScope().launch {
               delay(3000)
               it.let {
                   viewModel.getSearchNews(it.toString())
               }
            }


        }

        viewModel.searchData.observe(this) {
            if(it.isLoading)
            {
                binding.paginationProgressBar.visibility=View.VISIBLE
            }
            else
            {
                binding.paginationProgressBar.visibility=View.GONE
                myadabter.differ.submitList(it.data.body()?.articles)
                binding.rvSearchNews.adapter=myadabter
                //without layout manager recycle view will not wor
                binding.rvSearchNews.layoutManager= LinearLayoutManager(activity)
            }

        }





        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}