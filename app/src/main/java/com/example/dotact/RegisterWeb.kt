package com.example.dotact

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_register_web.*
import kotlinx.android.synthetic.main.activity_welcome.*

class RegisterWeb : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_web)
        wbRegisterOnline.webViewClient= WebViewClient()

        wbRegisterOnline.apply {
            settings.javaScriptEnabled=true
            loadUrl("https://services10.ieee.org/as/authorization.oauth2?response_type=code&client_id=PF_AS_FOR_PA&redirect_uri=https%3A%2F%2Fwww.ieee.org%2Fpa%2Foidc%2Fcb&state=eyJ6aXAiOiJERUYiLCJhbGciOiJkaXIiLCJlbmMiOiJBMTI4Q0JDLUhTMjU2Iiwia2lkIjoiMWEiLCJzdWZmaXgiOiIwRWkxQTMuMTYyNDY0NTM5NSJ9..mJhCLf4nnGMrTnG7LDyk0g.NKLzxRwv4FMNiaPWRRCtuIcijPGWPdWv0Z5JonwfXoT_zdCdAQyYTf4TOTTJ4I0zbqwvI3KoOoe9sWUiPPPiPugJXLOqdtus9ym9XaATD3KVHSuKeCg2xvZp2PWTxS6w.-XwYRuI2HRY9ztMDyXSldw&nonce=GtmZTzYkQoLwSaylOL4S0CtoTWjpRgfo6fYezHGzk2U&acr_values=msp%20sts%20stm%20prf%20crt%20col%20otk%20cmc%20cmp%20spe&scope=openid%20profile%20address%20email%20phone&vnd_pi_requested_resource=https%3A%2F%2Fwww.ieee.org%2Fmembership-application%2Fjoin.html&vnd_pi_application_name=MembershipApplication")

        }


        /*val fragmentScreens= arrayListOf<Fragment>(
            FirstTutorialScreenFragment(),

        )

        val adapter = RegistrationTutorialViewPagerAdapter( fragmentScreens, supportFragmentManager, lifecycle )
        viewPager2.adapter=adapter*/
    }

    override fun onBackPressed() {
        if(wbRegisterOnline.canGoBack())wbRegisterOnline.goBack()
        else {
            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}