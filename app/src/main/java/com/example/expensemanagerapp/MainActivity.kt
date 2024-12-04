package com.example.expensemanagerapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.expensemanagerapp.databinding.ActivityMainBinding
import com.example.expensemanagerapp.fragments.HomeFragment
import com.example.expensemanagerapp.fragments.SettingsFragment
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        openFragment(HomeFragment())

        binding.bottomNavigationView.setOnItemSelectedListener {item->
            when(item.itemId){
                R.id.dashboard -> {
                    openFragment(HomeFragment())
                    true
                }
                R.id.setting ->{
                    openFragment(SettingsFragment())
                    true
                }
                else->false
            }

        }






    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}