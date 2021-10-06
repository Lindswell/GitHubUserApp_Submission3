package com.example.githubuserapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.githubuserapp.R
import com.example.githubuserapp.data.User
import com.example.githubuserapp.database.FavDao
import com.example.githubuserapp.database.FavDatabase
import com.example.githubuserapp.database.RoomData
import com.example.githubuserapp.databinding.ActivityDetailBinding
import com.example.githubuserapp.ui.adapter.SectionPagerAdapter
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailViewModel: DetailViewModel
    private lateinit var database: FavDao

    private var username = ""
    private var photo = ""
    private var html = ""

    companion object{
        const val EXTRA_USER = "extra_user"

        @StringRes
        private val TAB_TITLE = intArrayOf(
            R.string.follower,
            R.string.following
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        username = intent.getStringExtra(EXTRA_USER).toString()

        database = FavDatabase.getDatabase(applicationContext).favDao()
        val exist = database.checkFavUser(username)
        setIcon(exist)

        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        detailViewModel.getUserDetail(username, this).observe(this, {
            showData(it)
        })

        val sectionPagerAdapter = SectionPagerAdapter(this, username)
        binding.viewPager.adapter = sectionPagerAdapter
        TabLayoutMediator(binding.tab, binding.viewPager) { tabs, position ->
            tabs.text = resources.getString(TAB_TITLE[position])
        }.attach()

        binding.btnAdd.setOnClickListener(this)
        binding.back.setOnClickListener(this)

    }

    private fun showData(it: User) {

        photo = it.photo.toString()
        html = it.html.toString()

        binding.apply {
            Glide.with(this@DetailActivity)
                .load(it.photo.toString())
                .into(imgProfile)

            title.text = it.username.toString()
            tvNameProfile.text = it.name.toString()
            tvEmailProfile.text = it.html.toString()
            tvCompanyProfile.text = it.company.toString()
            tvLocationProfile.text = it.location.toString()
            tvRepos.text = it.repository.toString()
            tvFollower.text = it.followers_num.toString()
            tvFollowing.text = it.following_num.toString()
        }
        showLoading(false)
    }

    override fun onClick(v: View) {
        when(v) {
            binding.btnAdd -> {
                addToFavorite(v)
            }

            binding.back -> {
                back()
            }
        }
    }


    private fun addToFavorite(v: View) {
        val exist = database.checkFavUser(username)
        setData(exist, v)
    }

    private fun setData(exist: Boolean, v: View) {
        if (!exist) {
            setIcon(true)
            Snackbar.make(v, getString(R.string.add_success), Snackbar.LENGTH_SHORT).show()
            val inputFavData = RoomData (
                username = username,
                photo = photo,
                html = html
            )
            database.insert(inputFavData)
        } else {
            setIcon(false)
            Snackbar.make(v, getString(R.string.delete_success), Snackbar.LENGTH_SHORT).show()
            database.delete(username)
        }
    }

    private fun setIcon(exist: Boolean) {
        if (exist) {
            binding.btnAdd.setImageDrawable(
                ContextCompat.getDrawable(
                    applicationContext, R.drawable.ic_favorite
                )
            )
        } else {
            binding.btnAdd.setImageDrawable(
                ContextCompat.getDrawable(
                    applicationContext,R.drawable.ic_favorite_border
                )
            )
        }
    }

    private fun back() {
        onBackPressed()
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}