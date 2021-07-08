package com.example.dynamicui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.dynamicui.databinding.ActivityMainBinding
import com.example.dynamicui.fragments.CategoriesFragment
import com.example.dynamicui.fragments.EventFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        supportFragmentManager.beginTransaction().replace(R.id.event_container, EventFragment())
            .commit()

        supportFragmentManager.beginTransaction().replace(R.id.categories_container, CategoriesFragment())
            .commit()
    }
}