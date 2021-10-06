package com.example.githubuserapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuserapp.data.User
import com.example.githubuserapp.databinding.FragmentHomeBinding
import com.example.githubuserapp.ui.adapter.UserAdapter

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    lateinit var homeViewModel: HomeFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel = ViewModelProvider(this).get(HomeFragmentViewModel::class.java)

        showLoading(false)

        binding.searchView.setOnQueryTextListener(object :
        androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                setData(query.toString())
                showLoading(false)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                setData(newText.toString())
                showLoading(true)
                return true
            }
        })
    }

    private fun setData(query: String) {
        if (query == "") {
            homeViewModel.getUserList(requireContext()).observe(viewLifecycleOwner, {
                setRecyclerview(it)
                showLoading(false)
            })
        } else {
            homeViewModel.getSearchList(query, requireContext()).observe(viewLifecycleOwner, {
                val data = it.items as ArrayList<User>
                setRecyclerview(data)
                showLoading(false)
            })
        }
    }

    private fun setRecyclerview(user: ArrayList<User>) {
        binding.listLayout.layoutManager = LinearLayoutManager(requireContext().applicationContext,
            LinearLayoutManager.VERTICAL, false)
        binding.listLayout.setHasFixedSize(true)
        binding.listLayout.adapter = UserAdapter(user, requireActivity())

        showLoading(false)

    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

}