package com.example.githubuserapp.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.example.githubuserapp.databinding.ActivitySplashScreenBinding
import com.example.githubuserapp.ui.fragment.SettingPreferences
import com.example.githubuserapp.ui.fragment.SettingViewModel


private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "setting")

class SplashScreen : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

            override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val pref = SettingPreferences.getInstance(dataStore)
        val viewModel = ViewModelProvider(this).get(
        SettingViewModel::class.java
        )

       viewModel.setPreferences(pref)

       viewModel.getThemeSettings().observe(this,
             {isDarkModeActive: Boolean ->
                 if (isDarkModeActive) {
                     AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                 } else {
                     AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
       })

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        Handler(mainLooper).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}