package com.bashirli.bootcamp8iyun.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.bashirli.bootcamp8iyun.R
import com.bashirli.bootcamp8iyun.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setup()

    }


    private fun setup(){
        with(binding){

            val navHostFragment=supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
            val navController=navHostFragment.navController

            bottomNavigationView.setupWithNavController(navController)
            //  NavigationUI.setupWithNavController(bottomNavigationView,navController)  --> eynidir



        }
    }

}