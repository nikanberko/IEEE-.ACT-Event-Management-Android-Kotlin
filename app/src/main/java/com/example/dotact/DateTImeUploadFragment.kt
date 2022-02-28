package com.example.dotact

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_admin.*
import kotlinx.android.synthetic.main.fragment_date_t_ime_upload.*
import kotlinx.android.synthetic.main.fragment_full_description_screen.*
import java.util.*


class DateTImeUploadFragment : Fragment(), DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener {
    var day = 0
    var month: Int = 0
    var year: Int = 0
    var hour: Int = 0
    var minute: Int = 0
    var myDay = 0
    var myMonth: Int = 0
    var myYear: Int = 0
    var myHour: Int = 0
    var myMinute: Int = 0

    lateinit var auth: FirebaseAuth

    private var firebaseStore: FirebaseStorage? = null
    private var storageReference: StorageReference? = null
    var db = FirebaseFirestore.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_date_t_ime_upload, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        firebaseStore = FirebaseStorage.getInstance()
        storageReference = FirebaseStorage.getInstance().reference

        btnBackFinalUpload.setOnClickListener {
            requireActivity().viewPager2UploadEvent.currentItem = requireActivity().viewPager2UploadEvent.currentItem-1
        }
        btnDateUpload.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()
            day = calendar.get(Calendar.DAY_OF_MONTH)
            month = calendar.get(Calendar.MONTH)
            year = calendar.get(Calendar.YEAR)
            val datePickerDialog =
                DatePickerDialog(requireActivity(), this, year, month,day)
            datePickerDialog.show()

        }
        btnTimeUpload.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()
            hour = calendar.get(Calendar.HOUR)
            minute = calendar.get(Calendar.MINUTE)
            val timePickerDialog = TimePickerDialog(requireContext(), this, hour, minute,
                true)
            timePickerDialog.show()

        }
        btnFinishUpload.setOnClickListener{
            requireActivity().etApplicationFormLinkCreate.text=etApplicationFormLinkUpload.text.toString()
            if(requireActivity().etEventTitleCreate.text.toString()!="" && requireActivity().etTitleDescCreate.text.toString()!="" && requireActivity().etFullDescCreate.text.toString()!="" &&
                requireActivity().btnDateCreate.text.toString()!="" && requireActivity().etLocationNameCreate.text.toString()!="" && requireActivity().etApplicationFormLinkCreate.text.toString()!="" &&
                requireActivity().etTimeCreate.text.toString()!="" && requireActivity().etLatitudeCreate.text.toString()!="" && requireActivity().etLongitudeCreate.text.toString()!=""
                && myYear!=0 && myMonth!=0 && myDay!=0 && myHour!=0 && myMinute!=0){
                saveEvent()


                val intent= Intent(context, MainActivity::class.java)
                startActivity(intent)
            }
            else Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()

        }



    }
    private fun saveEvent() {

        var title = requireActivity().etEventTitleCreate.text.toString()
        var shortDesc = requireActivity().etTitleDescCreate.text.toString()
        var fullDesc = requireActivity().etFullDescCreate.text.toString()
        var date = btnDateUpload.text.toString()
        var location = requireActivity().etLocationNameCreate.text.toString()
        var time = btnTimeUpload.text.toString()
        var applicationFormLink = requireActivity().etApplicationFormLinkCreate.text.toString()
        var latitude = requireActivity().etLatitudeCreate.text.toString()
        var longitude = requireActivity().etLongitudeCreate.text.toString()
        var titleImage="noImage"
        if(requireActivity().randomStringImage.text!=null)
        titleImage=requireActivity().randomStringImage.text.toString()


        var event: Event

        if (requireActivity().cbMembersOnlyCreate.isChecked) {
            event = Event("no", shortDesc, date, applicationFormLink, fullDesc, latitude, location, longitude, time, title, titleImage)
        }
        else event = Event("yes", shortDesc, date, applicationFormLink, fullDesc, latitude, location, longitude, time, title, titleImage)

        db.collection("events")
            .add(event)
            .addOnSuccessListener(OnSuccessListener<DocumentReference> { documentReference -> Log.d("Event upload", "DocumentSnapshot added with ID: " + documentReference.id) })
            .addOnFailureListener(OnFailureListener { e -> Log.w("Event upload", "Error adding document", e) })
    }
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        myDay = day
        myYear = year
        myMonth = month
        btnDateUpload.text=  "$myDay.$myMonth.$myYear."
        requireActivity().btnDateCreate.text=  "$myDay.$myMonth.$myYear."
    }
    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        myHour = hourOfDay
        myMinute = minute
        requireActivity().etTimeCreate.text = "$myHour:$myMinute h"
        requireActivity().btnTimeUpload.text = "$myHour:$myMinute h"

    }


}