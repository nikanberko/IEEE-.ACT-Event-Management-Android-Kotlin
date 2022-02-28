package com.example.dotact

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_admin.*
import kotlinx.android.synthetic.main.fragment_event_info.*
import kotlinx.android.synthetic.main.fragment_full_description_screen.*
import kotlinx.android.synthetic.main.fragment_image_upload.*


class ImageUploadFragment : Fragment() {
    private var firebaseStore: FirebaseStorage? = null
    private var storageReference: StorageReference? = null
    private lateinit var randomTitleImage:String
    private val pickImage=71
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firebaseStore = FirebaseStorage.getInstance()
        storageReference = FirebaseStorage.getInstance().reference
        ivTitleImageUpload.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            this.startActivityForResult(gallery,71 )
        }
        btnNextUpload5.setOnClickListener {
            requireActivity().viewPager2UploadEvent.currentItem = requireActivity().viewPager2UploadEvent.currentItem+1
        }
        btnBackUpload4.setOnClickListener {
            requireActivity().viewPager2UploadEvent.currentItem = requireActivity().viewPager2UploadEvent.currentItem-1
        }




    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        return inflater.inflate(R.layout.fragment_image_upload, container, false)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == pickImage && resultCode == AppCompatActivity.RESULT_OK && data!!.data != null) {
            val imageUri= data.data
            ivTitleImageUpload.setImageURI(null)
            ivTitleImageUpload.setImageURI(imageUri)
            Log.e("Uploading:", "Almost Uploading!!!!!!")
            if (imageUri != null) {
                savePhoto(imageUri)
                Log.e("Uploading:", "Uploading!!!!!!")
            }
        }
    }
    private fun savePhoto(uri: Uri) {
        randomTitleImage=getRandomString(10)
        requireActivity().randomStringImage.text=randomTitleImage
        storageReference?.child("eventTitleImages/${randomTitleImage}.jpg")
            ?.putFile(uri)
            ?.addOnCompleteListener { task ->
                if (task.isComplete && task.isSuccessful) {
                    Log.d("Upload successful:", "Upload successful")


                } else {
                    Log.d("Upload unsuccessful:", "Upload unsuccessful")
                }
            }
            ?.addOnFailureListener {
                Log.d("Upload unsuccessful:", it.message.toString())
            }
    }
    private fun getRandomString(length: Int) : String {
        val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }






}