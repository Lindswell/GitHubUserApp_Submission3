package com.example.githubuserapp.ui.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubuserapp.database.RoomData
import com.example.githubuserapp.databinding.UserDataLayoutBinding
import com.example.githubuserapp.ui.DetailActivity

class FavoriteAdapter (private val list: ArrayList<RoomData>, private val activity: Activity):
    RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: UserDataLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: RoomData) {
            Glide.with(itemView.context)
                .load(data.photo)
                .into(binding.userPhoto)
            binding.userName.text = data.username.toString()
            binding.userHtml.text = data.html.toString()

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_USER, data.username.toString())
                activity.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val mView = UserDataLayoutBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(mView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size


}