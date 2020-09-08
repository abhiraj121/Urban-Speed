package com.abhirajsharma.urbanspeed

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken
import kotlinx.android.synthetic.main.activity_login_actvity.*
import java.util.concurrent.TimeUnit


class LoginActivity : AppCompatActivity() {

    val TAG = "creationOfObject"
    private val auth by lazy { FirebaseAuth.getInstance() }
    lateinit var mVerificationId:String
//    lateinit var mResendToken

//    private val TAG = "GoogleActivity"
    private val RC_SIGN_IN = 9001
//    private lateinit var auth: FirebaseAuth
    private lateinit var signOut: Button
    private lateinit var GoogleSignInClient: GoogleSignInClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_actvity)

        otpBtn.setOnClickListener {
            progressBar.visibility = View.VISIBLE

            val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                    Log.d(TAG, "onVerificationCompleted:$credential")
                    signInWithPhoneAuthCredential(credential)
                }

                override fun onVerificationFailed(e: FirebaseException) {
                    progressBar.visibility = View.GONE
                    Log.w(TAG, "onVerificationFailed", e)
                    if (e is FirebaseAuthInvalidCredentialsException) {
                        if (phone_et.text?.isEmpty()!!){
                            rootLayout.showSnackBar("The field cannot be empty.")
                        }else {
                            rootLayout.showSnackBar("The format of the phone number provided is incorrect.")
                        }
                    } else if (e is FirebaseTooManyRequestsException) {
                        rootLayout.showSnackBar(e.localizedMessage!!.toString())
                    }

                }

                override fun onCodeSent(verificationId: String, token: ForceResendingToken) {
                    Log.d(TAG, "onCodeSent:$verificationId")
                    mVerificationId = verificationId
//                    mResendToken = token
                }

            }
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    "+91" + phone_et.text.toString(),
                    60,
                    TimeUnit.SECONDS,
                    this,
                    callbacks
            )
        }


        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
//        signOut = findViewById<View>(R.id.signOutBtn) as Button
        GoogleSignInClient = GoogleSignIn.getClient(this, gso)
        google_Login.setOnClickListener {
            signIn()
        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential).addOnCompleteListener(this) {
            Log.d("TAG", "signInWithCredential:success")
            rootLayout.showSnackBar("Account Created Successfully")
            progressBar.visibility = View.GONE
            startActivity(Intent(this, MainActivity::class.java))
        }.addOnFailureListener {
            rootLayout.showSnackBar("Error : ${it.localizedMessage}")
        }
    }

    private fun signIn () {
        val signInIntent = GoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleResult (task)
        }else {
            Toast.makeText(this, "Problem in execution order :(", Toast.LENGTH_LONG).show()
        }
    }
    private fun handleResult (completedTask: Task<GoogleSignInAccount>) {
        try {
            val account= completedTask.getResult(ApiException::class.java)!!
            firebaseAuthWithGoogle(account.idToken!!)
//            updateUI (account)
        } catch (e: ApiException) {
//            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show()
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithCredential:success")
                        val user = auth.currentUser!!
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithCredential:failure", task.exception)
                        rootLayout.showSnackBar("Authentication Failed.")
//                        updateUI(null)
                    }
                }
    }

    private fun updateUI (account: FirebaseUser) {
        progressBar.visibility = View.GONE
        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun View.showSnackBar(msg: String) {
        val snack = Snackbar.make(this, msg, Snackbar.LENGTH_SHORT)
        snack.animationMode = Snackbar.ANIMATION_MODE_SLIDE
        snack.show()
    }
}