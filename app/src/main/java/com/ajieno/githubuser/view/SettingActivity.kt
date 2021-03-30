package com.ajieno.githubuser.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ajieno.githubuser.R

class SettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        supportActionBar?.hide()
    }
}