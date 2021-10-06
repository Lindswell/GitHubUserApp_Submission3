package com.example.githubuserapp.ui.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubuserapp.data.User
import com.example.githubuserapp.databinding.UserDataLayoutBinding
import com.example.githubuserapp.ui.DetailActivity
import com.example.githubuserapp.ui.DetailActivity.Companion.EXTRA_USER


class UserAdapter(private val listUser: ArrayList<User>, private var activity: Activity): RecyclerView.Adapter<UserAdapter.ListViewHolder>(){


        override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ListViewHolder {
            val mView = UserDataLayoutBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
            return ListViewHolder(mView)
        }

        override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
            holder.bind(listUser[position])
        }

        override fun getItemCount(): Int = listUser.size

        inner class ListViewHolder(private val binding: UserDataLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
            fun bind(user: User) {
                Glide.with(itemView.context)
                    .load(user.photo)
                    .into(binding.userPhoto)
                binding.userName.text = user.username.toString()
                binding.userHtml.text = user.html.toString()

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(EXTRA_USER, user.username.toString())
                    activity.startActivity(intent)
                }

            }
        }

}