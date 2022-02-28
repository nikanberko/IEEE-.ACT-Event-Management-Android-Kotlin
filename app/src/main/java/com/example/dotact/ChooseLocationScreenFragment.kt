package com.example.dotact

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_admin.*
import kotlinx.android.synthetic.main.activity_registration.*
import kotlinx.android.synthetic.main.activity_welcome.*
import kotlinx.android.synthetic.main.fragment_choose_location_screen.*
import kotlinx.android.synthetic.main.fragment_first_screen.*
import kotlinx.android.synthetic.main.fragment_third_screen.*

class ChooseLocationScreenFragment: Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_choose_location_screen, container, false)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val chooseLocationFragment = ChooseLocationFragment()
        btnMapCreate.isClickable=true
        btnMapCreate.setOnClickListener{

            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.AdminConstraintViewCreate, chooseLocationFragment).addToBackStack(null).commit()
           // requireActivity().fabLocationChosen.isVisible=true
           // requireActivity().fabLocationChosen.isClickable=true
            requireActivity().btnUploadEvent.visibility= View.GONE
            requireActivity().btnUploadEvent.isClickable=false
            requireActivity().etTimeCreate.visibility= View.GONE
            requireActivity().etTimeCreate.isClickable=false
            requireActivity().btnDateCreate.visibility= View.GONE
            requireActivity().btnDateCreate.isClickable=false
            requireActivity().btnMapCreate.visibility= View.GONE
            requireActivity().btnMapCreate.isClickable=false
           // requireActivity().tvChosenMapLocation.visibility= View.VISIBLE


        }



    }

}