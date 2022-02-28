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
import kotlinx.android.synthetic.main.fragment_first_screen.*
import kotlinx.android.synthetic.main.fragment_full_description_screen.*
import kotlinx.android.synthetic.main.fragment_only_for_members_screen.*
import kotlinx.android.synthetic.main.fragment_third_screen.*

class OnlyForMembersScreenFragment: Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_only_for_members_screen, container, false)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnNextUpload3.setOnClickListener {
            requireActivity().viewPager2UploadEvent.currentItem = requireActivity().viewPager2UploadEvent.currentItem+1
            if(cbMembersOnlyUpload.isChecked)requireActivity().cbMembersOnlyCreate.isChecked=true
            else requireActivity().cbMembersOnlyCreate.isChecked=false
        }
        btnBackUpload2.setOnClickListener {
            requireActivity().viewPager2UploadEvent.currentItem = requireActivity().viewPager2UploadEvent.currentItem-1
        }




    }

}