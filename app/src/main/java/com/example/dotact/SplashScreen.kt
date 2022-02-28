package com.example.dotact

import android.animation.AnimatorSet
import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.TransitionDrawable
import android.os.Bundle
import android.view.View
import android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
import android.view.View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
import android.view.Window
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_splash_screen.*


class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        @Suppress("DEPRECATED_IDENTITY_EQUALS", "DEPRECATION")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val transitionBgColor = splashBg.background as TransitionDrawable
        transitionBgColor.startTransition(2000)
        transitionTextColor(tvSplashLogo)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION



        val ease= AnimationUtils.loadAnimation(this, R.anim.fade_in_3times)
        //ease.startOffset=3000
        tvSplashLogo.startAnimation(ease)
        val intent = Intent (this, RegistrationActivity::class.java)
        ease.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(arg0: Animation) {}
            override fun onAnimationRepeat(arg0: Animation) {}
            override fun onAnimationEnd(arg0: Animation) {
                startActivity(intent)
                finish()
            }
        })


    }
    fun transitionTextColor(tv: TextView) {
        val a = ObjectAnimator.ofInt(tv, "textColor", Color.WHITE, Color.parseColor("#FF6E40"))
        a.interpolator = LinearInterpolator()
        a.startDelay=1000
        a.duration = 1000
        a.setEvaluator(ArgbEvaluator())
        val t = AnimatorSet()
        t.play(a)
        t.start()
    }
}