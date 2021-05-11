package com.heliushouse.numbertrivia.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.heliushouse.numbertrivia.R
import com.heliushouse.numbertrivia.databinding.ActivityMainBinding
import com.heliushouse.numbertrivia.ui.number.NumberFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var fragment: NumberFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        fragment = NumberFragment.newInstance()
        supportFragmentManager.beginTransaction().replace(binding.hostFragment.id, fragment).commit()
    }
}