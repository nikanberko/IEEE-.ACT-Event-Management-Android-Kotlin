package com.example.dotact

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.format.DateFormat
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_admin.*
import kotlinx.android.synthetic.main.fragment_choose_location.*
import kotlinx.android.synthetic.main.fragment_choose_location_screen.*
import kotlinx.android.synthetic.main.fragment_event_info.*
import kotlinx.android.synthetic.main.fragment_image_upload.*
import java.util.*

class AdminActivity : AppCompatActivity() {

    var db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        val fragmentScreens= arrayListOf<Fragment>(
            EventTitleScreenFragment(),
            FullDescriptionScreenFragment(),
            ShortDescriptionScreenFragment(),
            OnlyForMembersScreenFragment(),
            ImageUploadFragment(),
            ChooseLocationFragment(),
            DateTImeUploadFragment()

        )

        val adapter = UploadEventSliderViewPagerAdapter( fragmentScreens, supportFragmentManager, lifecycle )
        viewPager2UploadEvent.adapter=adapter
        viewPager2UploadEvent.offscreenPageLimit=8





        viewPager2UploadEvent.isUserInputEnabled = false;

    }


     override fun onBackPressed() {
     val intent= Intent(this, MainActivity::class.java)
     startActivity(intent)
     finish()
 }

}

