package com.john.a20220429_johnreyes_nyschools

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.john.a20220429_johnreyes_nyschools.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}