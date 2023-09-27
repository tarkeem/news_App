package com.example.newsapp.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentHomeBinding
import com.example.newsapp.viewmodels.newsViewModel
import com.example.newsapp.viewmodels.newsViewModelFactory
import myCustomAdapter

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
        var myAdapter=myCustomAdapter(this.context!!)
        val viewModelfactory=newsViewModelFactory(this.context!!)
        var viewModel: newsViewModel = ViewModelProvider(this,viewModelfactory).get(newsViewModel::class.java)


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
                myAdapter.differ.submitList(it.data.body()?.articles)
                binding.recyclerView.adapter=myAdapter
                //without layout manager recycle view will not worK
                binding.recyclerView.layoutManager=LinearLayoutManager(activity)
                myAdapter.setOnItemClickListener { article ->
                    println("clicked..............")
                    println(article.toString())
                    var bundle = Bundle()
                    bundle.putSerializable("article",article)
                    findNavController().navigate(R.id.action_navigation_home_to_webArticleFragment,bundle)
                }

            }

        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}