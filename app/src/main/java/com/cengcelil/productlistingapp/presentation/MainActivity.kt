package com.cengcelil.productlistingapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cengcelil.productlistingapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var b: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityMainBinding.inflate(layoutInflater).also { b = it }.root)
    }
}