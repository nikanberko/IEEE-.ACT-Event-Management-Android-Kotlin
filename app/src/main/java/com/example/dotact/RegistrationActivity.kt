package com.example.dotact

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_registration.*
import kotlinx.android.synthetic.main.fragment_first_screen.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext


class RegistrationActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    private var firebaseStore: FirebaseStorage? = null
    private var storageReference: StorageReference? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION

            val animation = AnimationUtils.loadAnimation(this, R.anim.fade_in)
             tvWelcomeAnimated.startAnimation(animation)


        auth = FirebaseAuth.getInstance()
        firebaseStore = FirebaseStorage.getInstance()
        storageReference = FirebaseStorage.getInstance().reference
        auth.signOut()

        btnCreateAccount.setOnClickListener {
            registerUser()
        }
        btnLogin.setOnClickListener {
            logInUser()
        }

    }

    override fun onStart() {
        super.onStart()
        checkLoggedInState()
    }

    private fun registerUser(){
        val email=etEmail.text.toString()
        val password=etPassword.text.toString()
        if(email.isNotEmpty()&&password.isNotEmpty()){
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    auth.createUserWithEmailAndPassword(email, password).await()
                    withContext(Dispatchers.Main){
                        checkLoggedInState()
                    }
                }catch (e: Exception){
                    withContext(Dispatchers.Main){
                        Toast.makeText(this@RegistrationActivity, e.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
    private fun logInUser(){
        val email=etEmail.text.toString()
        val password=etPassword.text.toString()
        if(email.isNotEmpty()&&password.isNotEmpty()){
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    auth.signInWithEmailAndPassword(email, password).await()
                    withContext(Dispatchers.Main){
                        checkLoggedInState()
                    }
                }catch (e: Exception){
                    withContext(Dispatchers.Main){
                        Toast.makeText(this@RegistrationActivity, e.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
    private fun checkLoggedInState(){
        if(auth.currentUser!=null){
            Toast.makeText(this@RegistrationActivity, "Success. Now act! ", Toast.LENGTH_SHORT).show()
            val metadata = auth.currentUser!!.metadata
            if (metadata!!.creationTimestamp == metadata!!.lastSignInTimestamp) {
                val intent= Intent(this, WelcomeActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                val intent= Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()

            }

        }

    }

}