package com.example.newsapp.fragments.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.databinding.FragmentDashboardBinding
import com.example.newsapp.viewmodels.newsViewModel
import com.example.newsapp.viewmodels.newsViewModelFactory

class FavouriteFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModelfactory= newsViewModelFactory(this.context!!)
        var viewModel: newsViewModel = ViewModelProvider(this,viewModelfactory).get(newsViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDashboard




        viewModel.getNewsFromDb()
        viewModel.savedNews.observe(this) {
            if (it!=null)
            {
                println("saved news........................")
                println(it.toString())
            }

        }






        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}