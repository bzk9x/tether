package com.bittscafe.tether

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.content.edit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val prefs = this.getSharedPreferences("prefs", MODE_PRIVATE)
        val isFirstTime = prefs.getBoolean("is_first_time", true)

        if (isFirstTime) {
            prefs.edit { putBoolean("is_first_time", false) }
            val onboardingActivity = Intent(this, OnboardingActivity::class.java)
            startActivity(onboardingActivity)
            finish()
        } else {
            Handler(Looper.getMainLooper()).postDelayed({
                val homeActivity = Intent(this, HomeActivity::class.java)
                startActivity(homeActivity)
                finish()
            }, 2000)
        }
    }
}