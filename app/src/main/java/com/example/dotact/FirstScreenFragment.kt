package com.example.dotact

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_registration.*
import kotlinx.android.synthetic.main.activity_welcome.*
import kotlinx.android.synthetic.main.fragment_first_screen.*
import kotlinx.android.synthetic.main.fragment_third_screen.*

class FirstScreenFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_first_screen, container, false)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fade= AnimationUtils.loadAnimation(activity, R.anim.fade_in)
        tvSwipeRight.startAnimation(fade)
        ivArrowSwipeRight.startAnimation(fade)

        btnSkipTutorial.setOnClickListener {
            val intent = Intent (activity, MainActivity::class.java)
            activity?.startActivity(intent)
            activity?.finish()
        }


    }

}