package com.elliottco.susamongus.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.elliottco.susamongus.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer)

        if(currentFragment == null) {
            val suspiciousBehaviorFragment = ReportListFragment.newInstance()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainer, suspiciousBehaviorFragment)
                .commit()
        }
    }
}