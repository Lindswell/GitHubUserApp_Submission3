package com.example.githubuserapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuserapp.data.User
import com.example.githubuserapp.databinding.FragmentFollowingBinding
import com.example.githubuserapp.ui.DetailViewModel
import com.example.githubuserapp.ui.adapter.UserAdapter

class FollowingFragment(private val username: String) : Fragment() {


    private lateinit var binding: FragmentFollowingBinding
    private lateinit var followingViewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFollowingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        followingViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        followingViewModel.getFollowing(username, requireContext()). observe(viewLifecycleOwner, {
            setFollowingList(it)
            showLoading(false)
        })
    }

    private fun setFollowingList(user: ArrayList<User>) {
        binding.apply {
            listFollowing.layoutManager = LinearLayoutManager(requireContext())
            listFollowing.setHasFixedSize(true)
            listFollowing.adapter = UserAdapter(user, requireActivity())
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}