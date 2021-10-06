package com.example.githubuserapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuserapp.database.RoomData
import com.example.githubuserapp.databinding.FragmentFavoriteBinding
import com.example.githubuserapp.ui.adapter.FavoriteAdapter

class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var favViewModel: FavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favViewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)
        favViewModel.getFavData(requireContext()).observe(viewLifecycleOwner, {
            setData(it)
        })
    }

    private fun setData(list: ArrayList<RoomData>) {
        binding.apply {
            listLayout.layoutManager = LinearLayoutManager(requireContext())
            listLayout.adapter = FavoriteAdapter(list, requireActivity())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}