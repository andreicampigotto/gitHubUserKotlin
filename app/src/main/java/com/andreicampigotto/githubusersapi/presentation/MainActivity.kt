package com.andreicampigotto.githubusersapi.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.andreicampigotto.githubusersapi.R
import com.andreicampigotto.githubusersapi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(R.layout.activity_main) {

//    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

}