package com.abhirajsharma.urbanspeed

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.activity_login_actvity.*

class LoginActivity : AppCompatActivity() {

    lateinit var mCallbacks:PhoneAuthProvider.OnVerificationStateChangedCallbacks

    private val auth by lazy { FirebaseAuth.getInstance() }
    var verificationId:String = ""
    private val RC_SIGN_IN = 9001
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_actvity)

        phoneVerif.setOnClickListener { startActivity(Intent(this, PhoneLogin::class.java)) }

//        otpBtn.setOnClickListener {
//            progressBar.visibility = View.VISIBLE
//            verify()
//        }

//        verifyBtn.setOnClickListener {
//            Log.d("checkMe","otp: "+otp_et.text.toString())
//            progressBar.visibility = View.VISIBLE
//            authenticate()
//        }

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)
        google_Login.setOnClickListener {
            signIn()
        }

        facebook_Login.setOnClickListener { rootLayout.showSnackBar("Coming Soon.") }
    }

//    private fun verificationCallbacks(){
//        mCallbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
//            override fun onVerificationCompleted(p0: PhoneAuthCredential) {
//                signInWithPhoneAuthCredential(p0)
//            }
//
//            override fun onVerificationFailed(e: FirebaseException) {
//                progressBar.visibility = View.GONE
//                    if (e is FirebaseAuthInvalidCredentialsException) {
//                        if (phone_et.text?.isEmpty()!!){
//                            rootLayout.showSnackBar("The field cannot be empty.")
//                        }else {
//                            rootLayout.showSnackBar("The format of the phone number provided is incorrect.")
//                        }
//                    } else if (e is FirebaseTooManyRequestsException) {
//                        rootLayout.showSnackBar(e.localizedMessage!!.toString())
//                    }
//            }
//
//            override fun onCodeSent(p0: String, p1: ForceResendingToken) {
//                super.onCodeSent(p0, p1)
//                verificationId = p0
//            }
//        }
//    }
//
//    private fun authenticate() {
//        val verificationNum = otp_et.text.toString()
//        val credential = PhoneAuthProvider.getCredential(verificationId, verificationNum)
//        Log.d("checkMe",credential.toString())
//        signInWithPhoneAuthCredential(credential)
//    }
//
//    private fun verify() {
//        verificationCallbacks()
//        PhoneAuthProvider.getInstance().verifyPhoneNumber(
//                "+91"+phone_et.text.toString(),
//                60,
//                TimeUnit.SECONDS,
//                this,
//                mCallbacks)
//
//    }
//
//    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
//        auth.signInWithCredential(credential).addOnCompleteListener(this) {
//            if (it.isSuccessful){
//                rootLayout.showSnackBar("Account Created Successfully.")
//                progressBar.visibility = View.GONE
//                startActivity(Intent(this, MainActivity::class.java))
//            }
//        }.addOnFailureListener {
//            rootLayout.showSnackBar("Error : ${it.localizedMessage}")
//        }
//    }

    private fun signIn () {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            try {
                val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                handleResult (task)
            }catch (e:Exception){ }
        }else {
            Toast.makeText(this, "Problem in execution order :(", Toast.LENGTH_LONG).show()
        }
    }
    private fun handleResult (completedTask: Task<GoogleSignInAccount>) {
        val account= completedTask.getResult(ApiException::class.java)!!
        firebaseAuthWithGoogle(account.idToken!!)
//            updateUI (account)

    }
    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser!!
                        updateUI(user)
                    } else {
                        rootLayout.showSnackBar("Authentication Failed.")
                    }
                }
    }
    private fun updateUI (account: FirebaseUser) {
        progressBar.visibility = View.GONE
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
    private fun View.showSnackBar(msg: String) {
        val snack = Snackbar.make(this, msg, Snackbar.LENGTH_SHORT)
        snack.animationMode = Snackbar.ANIMATION_MODE_SLIDE
        snack.show()
    }
}