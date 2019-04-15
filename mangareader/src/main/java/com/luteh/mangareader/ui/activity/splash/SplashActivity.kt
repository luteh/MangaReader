package com.luteh.mangareader.ui.activity.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.luteh.mangareader.ui.activity.home.HomeActivity
import com.luteh.mangareader.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            startActivity(Intent(this, HomeActivity::class.java))
        }, 2000)
    }
}
