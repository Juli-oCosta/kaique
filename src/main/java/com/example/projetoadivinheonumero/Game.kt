package com.example.projetoadivinheonumero

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.pow

class Game : AppCompatActivity() {
    var numberRandom: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val txtNumberRandom = findViewById<TextView>(R.id.txtNumberRandom)
        val txtHint = findViewById<TextView>(R.id.txtHint)
        val txtAttempts = findViewById<TextView>(R.id.txtAttempts)

        val edtNumber = findViewById<EditText>(R.id.edtNumber)

        val btnGenerateNumber = findViewById<Button>(R.id.btnGenerateNumber)
        val btnPlay = findViewById<Button>(R.id.btnPlay)
        val btnBack = findViewById<Button>(R.id.btnBack)
        val btnRestart = findViewById<Button>(R.id.btnRestart)

        edtNumber.isEnabled = false
        btnPlay.isEnabled = false
        btnRestart.isEnabled = false

        val prefs = getSharedPreferences("user_config", MODE_PRIVATE)

        val numberDigits = prefs.getInt("digits", 2)
        val numberAttempts = prefs.getInt("attempts", 10)

        txtNumberRandom.text = "?".repeat(numberDigits)
        edtNumber.hint = "9".repeat(numberDigits)

        var quantTried: Int = 0

        btnGenerateNumber.setOnClickListener {
            numberRandom = generateRandomNumber(numberDigits)

            txtHint.text = "Digite um Número"
            txtAttempts.text = "$quantTried/$numberAttempts Tentativas"

            edtNumber.isEnabled = true
            btnPlay.isEnabled = true
            btnRestart.isEnabled = true

            btnGenerateNumber.isEnabled = false
        }

        btnPlay.setOnClickListener {
            if(edtNumber.text.toString() != "") {
                val numberTried = edtNumber.text.toString().toInt()

                var strHint = checkNumber(numberTried)

                quantTried++

                if (strHint != "Você Acertou!" && quantTried >= numberAttempts) {
                    strHint = "Você Perdeu!"
                }

                txtHint.text = strHint
                txtAttempts.text = "$quantTried/$numberAttempts Tentativas"

                edtNumber.text.clear()

                if (strHint == "Você Acertou!" || strHint == "Você Perdeu!") {
                    txtNumberRandom.text = numberRandom.toString()

                    edtNumber.isEnabled = false
                    btnPlay.isEnabled = false

                    if (strHint == "Você Acertou!"){
                        txtHint.setTextColor(Color.BLACK)
                        txtHint.setBackgroundColor(Color.parseColor("#C8E6C9"))
                    }else{
                        txtHint.setTextColor(Color.BLACK)
                        txtHint.setBackgroundColor(Color.parseColor("#FFCDD2"))
                    }
                }
            }else{
                txtHint.text = "Digite um Número"
            }
        }

        btnBack.setOnClickListener {
            val intent = Intent(this@Game, Menu::class.java)
            startActivity(intent)
            finish()
        }

        btnRestart.setOnClickListener {
            val intent = intent
            finish()
            startActivity(intent)
        }
    }

    private fun generateRandomNumber(digits: Int): Int{
        val minNumber= 10.0.pow((digits - 1).toDouble()).toInt()
        val maxNumber = 10.0.pow(digits.toDouble()).toInt() - 1

        return (minNumber..maxNumber).random()
    }

    private fun checkNumber(number: Int): String{
        return if(number == numberRandom){
            "Você Acertou!"
        }else if(numberRandom < number){
            "O Número é Menor"
        }else{
            "O Número é Maior"
        }
    }
}