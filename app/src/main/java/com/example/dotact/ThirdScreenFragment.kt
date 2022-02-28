package com.example.dotact

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_welcome.*
import kotlinx.android.synthetic.main.fragment_first_screen.*
import kotlinx.android.synthetic.main.fragment_second_screen.*
import kotlinx.android.synthetic.main.fragment_third_screen.*

class ThirdScreenFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.fragment_third_screen, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val ease= AnimationUtils.loadAnimation(activity, R.anim.ease_in)
        tvQuestionMark.startAnimation(ease)


        btnLetsGo.setOnClickListener {
            val intent = Intent (activity, MainActivity::class.java)
            activity?.startActivity(intent)
            activity?.finish()
        }

    }

}