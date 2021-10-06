package com.example.githubuserapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuserapp.data.User
import com.example.githubuserapp.databinding.FragmentFollowersBinding
import com.example.githubuserapp.ui.DetailViewModel
import com.example.githubuserapp.ui.adapter.UserAdapter

class FollowersFragment(private val username: String) : Fragment() {

    private lateinit var binding: FragmentFollowersBinding
    private lateinit var followerViewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFollowersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        followerViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        followerViewModel.getFollower(username, requireContext()). observe(viewLifecycleOwner, {
            setFollowersList(it)
            showLoading(false)
        })
    }

    private fun setFollowersList(user: ArrayList<User>) {
        binding.apply {
            listFollowers.layoutManager = LinearLayoutManager(requireContext())
            listFollowers.setHasFixedSize(true)
            listFollowers.adapter = UserAdapter(user, requireActivity())
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