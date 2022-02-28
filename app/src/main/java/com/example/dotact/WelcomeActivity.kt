package com.example.dotact


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager


import kotlinx.android.synthetic.main.activity_welcome.*



class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)



        val fragmentScreens= arrayListOf<Fragment>(
            FirstScreenFragment(),
            SecondScreenFragment(),
            ThirdScreenFragment()
        )

        val adapter = WelcomeSliderViewPagerAdapter( fragmentScreens, supportFragmentManager, lifecycle )
        viewPager2.adapter=adapter

    }
}