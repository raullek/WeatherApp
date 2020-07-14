package ru.sberbank.weatherapp.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.sberbank.weatherapp.R
import ru.sberbank.weatherapp.ui.main.MainScreenActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startActivity(Intent(this, MainScreenActivity::class.java))
        finish()

    }
}