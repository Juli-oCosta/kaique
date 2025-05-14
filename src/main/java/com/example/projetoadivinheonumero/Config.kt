package com.example.projetoadivinheonumero

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class Config : AppCompatActivity() {

    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config)

        prefs = getSharedPreferences("user_config", MODE_PRIVATE)

        val rgTheme = findViewById<RadioGroup>(R.id.rgConfigTheme)
        val rgDigits = findViewById<RadioGroup>(R.id.rgConfigDigits)
        val rgAttempts = findViewById<RadioGroup>(R.id.rgConfigAttempts)

        rgTheme.check(prefs.getInt("theme", R.id.rbThemeLigth))
        rgDigits.check(prefs.getInt("digitsID", R.id.rbOneDigits))
        rgAttempts.check(prefs.getInt("attemptsID", R.id.rbFiveAttempts))

        rgTheme.setOnCheckedChangeListener { _, checkedId ->
            prefs.edit().putInt("theme", checkedId).apply()
            applyTheme(checkedId)
        }

        rgDigits.setOnCheckedChangeListener { _, checkedId ->
            val selectedRadioButton = findViewById<RadioButton>(checkedId)
            val digits = selectedRadioButton.tag.toString().toInt()
            prefs.edit().putInt("digits", digits).apply()
            prefs.edit().putInt("digitsID", checkedId).apply()
        }

        rgAttempts.setOnCheckedChangeListener { _, checkedId ->
            val selectedRadioButton = findViewById<RadioButton>(checkedId)
            val attempts = selectedRadioButton.tag.toString().toInt()
            prefs.edit().putInt("attempts", attempts).apply()
            prefs.edit().putInt("attemptsID", checkedId).apply()
        }

        val btnVoltar: Button = findViewById(R.id.btnBack)
        btnVoltar.setOnClickListener {
            val intent = Intent(this@Config, Menu::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun applyTheme(themeId: Int) {
        when (themeId) {
            R.id.rbThemeLigth -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            R.id.rbThemeDark -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }
}