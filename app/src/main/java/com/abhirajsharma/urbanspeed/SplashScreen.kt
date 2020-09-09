package com.abhirajsharma.urbanspeed

import android.content.Intent
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


class SplashScreen: AppCompatActivity() {

    private val mAuth by lazy { FirebaseAuth.getInstance() }

    override fun onStart() {
        super.onStart()
        Log.d("checkMe", mAuth.currentUser.toString())
        if (mAuth.currentUser==null){
            startActivity(Intent(this, LoginActivity::class.java))
        }else{
            startActivity(Intent(this, MainActivity::class.java))
        }
    }


}