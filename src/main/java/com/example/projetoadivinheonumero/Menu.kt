package com.example.projetoadivinheonumero

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class Menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val prefs = getSharedPreferences("user_config", MODE_PRIVATE)
        when (prefs.getInt("theme", R.id.rbThemeLigth)) {
            R.id.rbThemeLigth -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            R.id.rbThemeDark -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }

        val btnPlay: Button = findViewById(R.id.btnPlay)
        btnPlay.setOnClickListener {
            val intent = Intent(this@Menu, Game::class.java)
            startActivity(intent)
        }

        val btnConfig: Button = findViewById(R.id.btnConfig)
        btnConfig.setOnClickListener {
            val intent = Intent(this@Menu, Config::class.java)
            startActivity(intent)
        }
    }
}