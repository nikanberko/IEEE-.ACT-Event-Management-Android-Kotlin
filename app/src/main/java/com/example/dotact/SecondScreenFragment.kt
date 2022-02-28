package com.example.dotact

import android.animation.AnimatorSet
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_registration.*
import kotlinx.android.synthetic.main.activity_welcome.*
import kotlinx.android.synthetic.main.fragment_first_screen.*
import kotlinx.android.synthetic.main.fragment_second_screen.*


class SecondScreenFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_second_screen, container, false)
    }

       override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

           val ease=AnimationUtils.loadAnimation(activity, R.anim.ease_in)
           ivCalendar.startAnimation(ease)
           tvDotActMainGoal.startAnimation(ease)
           val fade= AnimationUtils.loadAnimation(activity, R.anim.fade_in)
           tvSwipeRightSecondScreen.startAnimation(fade)
           ivArrowSwipeRightSecondScreen.startAnimation(fade)




       }


}