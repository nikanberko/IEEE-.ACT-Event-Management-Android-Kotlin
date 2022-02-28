package com.example.dotact

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_third_screen.*

class MainActivity : AppCompatActivity() {
    private lateinit var db:FirebaseFirestore

    private lateinit var eventAdapter:EventRecyclerViewAdapter
    private lateinit var events : ArrayList<Event>
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db= FirebaseFirestore.getInstance()

        var fabOpen=false

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or View.SYSTEM_UI_FLAG_FULLSCREEN //or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION


        fabBecomeMember.isVisible=false
        fabUploadEvent.isVisible=false
        fabSignOut.isVisible=false
      //  tvMembership.isVisible=false
      //  tvSignOut.isVisible=false
      //  tvUploadEvent.isVisible=false


        val fadeIn= AnimationUtils.loadAnimation(this, R.anim.fab_fade_in)

        val fadeOut= AnimationUtils.loadAnimation(this, R.anim.fab_fade_out)

        tvSignOut.setOnClickListener {
            val intent= Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
            auth.signOut()
            finish()

        }
        tvRefreshIcon.setOnClickListener {
            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        fabMore.setOnClickListener {
            if (fabOpen) {

                fabBecomeMember.startAnimation(fadeOut)
                fabUploadEvent.startAnimation(fadeOut)
                fabSignOut.startAnimation(fadeOut)
                fabBecomeMember.isClickable=false
                fabSignOut.isClickable=false
                fabUploadEvent.isClickable=false
              //  tvMembership.startAnimation(fadeOut)
              //  tvSignOut.startAnimation(fadeOut)
              //  tvUploadEvent.startAnimation(fadeOut)
                fabOpen=false
            }
            else{
                fabBecomeMember.isVisible=true
                fabSignOut.isVisible=true
                fabUploadEvent.isVisible=true
            //    tvUploadEvent.isVisible=true
            //    tvMembership.isVisible=true
            //    tvSignOut.isVisible=true
                fabBecomeMember.isClickable=true
                fabSignOut.isClickable=true
                fabUploadEvent.isClickable=true
                fabBecomeMember.startAnimation(fadeIn)
                fabSignOut.startAnimation(fadeIn)
                fabUploadEvent.startAnimation(fadeIn)
               // tvMembership.startAnimation(fadeIn)
              //  tvSignOut.startAnimation(fadeIn)
             //   tvUploadEvent.startAnimation(fadeIn)

                fabOpen=true
            }
        }

        fabSignOut.setOnClickListener {
                val intent= Intent(this, RegistrationActivity::class.java)
                startActivity(intent)
                auth.signOut()
                finish()
                }
        fabBecomeMember.setOnClickListener{
            val intent= Intent(this, RegisterWeb::class.java)
            startActivity(intent)
            finish()
        }

        fabUploadEvent.setOnClickListener {
            val intent= Intent(this, AdminActivity::class.java)
            startActivity(intent)
            finish()
        }



        rvEvents.layoutManager = LinearLayoutManager(this)
        rvEvents.setHasFixedSize(true)
        events = arrayListOf<Event>()
        getEvents();
    }
    private fun getEvents(){
        db.collection("events")
                .get()
                .addOnSuccessListener {
                    for(document in it){
                        events.add(document.toObject(Event::class.java))

                    }
                    Log.d("Broj elemenata: ",events.size.toString())

                    rvEvents.adapter=EventRecyclerViewAdapter(events)
                    Log.d("adapter velicina: ", rvEvents.adapter?.itemCount.toString())

                   rvEvents.adapter?.notifyDataSetChanged()
                    val fadeIn= AnimationUtils.loadAnimation(this, R.anim.ease_in)
                    rvEvents.startAnimation(fadeIn)

                }
    }

    override fun onBackPressed() {
        val intent= Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }


}