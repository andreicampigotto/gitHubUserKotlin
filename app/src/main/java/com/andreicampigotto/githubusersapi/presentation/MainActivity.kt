package com.andreicampigotto.githubusersapi.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.andreicampigotto.githubusersapi.R
import com.andreicampigotto.githubusersapi.databinding.ActivityMainBinding
import com.andreicampigotto.githubusersapi.presentation.user.UserFragment

class MainActivity : AppCompatActivity() {

//    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            // Evita criar fragments duplicados após uma recriação da Activity
            val fragmentUser = UserFragment()

            supportFragmentManager.beginTransaction()
                .add(R.id.fragmente_user, fragmentUser)
                .commit()
        }

    }

}