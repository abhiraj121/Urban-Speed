package com.abhirajsharma.urbanspeed

import android.content.Intent
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class SplashScreen: AppCompatActivity() {

    private val mAuth by lazy {
        FirebaseAuth.getInstance()
    }

    override fun onStart() {
        super.onStart()
        if (mAuth.currentUser==null){
            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(Intent(this, LoginActivity::class.java))
            }, 1500)
        }else{
            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(Intent(this, MainActivity::class.java))
            }, 1000)
        }
    }

}