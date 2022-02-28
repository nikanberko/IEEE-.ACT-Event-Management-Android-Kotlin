package com.example.dotact

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_admin.*
import kotlinx.android.synthetic.main.activity_registration.*
import kotlinx.android.synthetic.main.activity_welcome.*
import kotlinx.android.synthetic.main.fragment_event_title_screen.*
import kotlinx.android.synthetic.main.fragment_first_screen.*
import kotlinx.android.synthetic.main.fragment_full_description_screen.*
import kotlinx.android.synthetic.main.fragment_third_screen.*

class FullDescriptionScreenFragment: Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_full_description_screen, container, false)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnNextUpload2.setOnClickListener {
            requireActivity().viewPager2UploadEvent.currentItem = requireActivity().viewPager2UploadEvent.currentItem+1
            requireActivity().etFullDescCreate.text=etFullDescUpload.text.toString()
        }
        btnBackUpload.setOnClickListener {
            requireActivity().viewPager2UploadEvent.currentItem = requireActivity().viewPager2UploadEvent.currentItem-1
        }




    }

}