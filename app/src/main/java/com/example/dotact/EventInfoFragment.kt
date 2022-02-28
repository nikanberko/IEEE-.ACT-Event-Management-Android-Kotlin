package com.example.dotact

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.fragment_event_info.*


class EventInfoFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val bundle = this.arguments
        var storageReference= FirebaseStorage.getInstance().reference


        tvFragFullDescription.text=bundle!!.get("fullDescription").toString()
        tvFragDate.text=bundle!!.get("date").toString()
        tvFragLocation.text=bundle!!.get("location").toString()
        tvFragTitle.text=bundle!!.get("title").toString()
        tvFragTime.text=bundle!!.get("time").toString()
        if(bundle!!.get("attendance").toString().toLowerCase()=="no")
        tvMembersOnly.text="Open For Everyone"
        else tvMembersOnly.text="Members Only"
        GlideApp.with(this).load(storageReference?.child("eventTitleImages/${bundle!!.get("titleImage").toString()}.jpg")).placeholder(R.mipmap.no_title_image).into(ivFragTitleImage)
        wbApply.webViewClient= WebViewClient()

        var isEllipsized=1
        tvFragFullDescription.maxLines = 3
        tvFragFullDescription.ellipsize=TextUtils.TruncateAt.END
        tvMoreDesc.setOnClickListener{
            if(isEllipsized==1){
                tvMoreDesc.text="Read less"
                tvFragFullDescription.ellipsize=TextUtils.TruncateAt.MARQUEE
                tvFragFullDescription.maxLines= Int.MAX_VALUE
                isEllipsized=0
            }
            else{
                tvMoreDesc.text="Read more"
                tvFragFullDescription.ellipsize=TextUtils.TruncateAt.END
                tvFragFullDescription.maxLines= 3
                isEllipsized=1
            }
        }

        wbApply.apply {
            settings.javaScriptEnabled=true
            loadUrl(bundle!!.get("form").toString())

        }
        btnApplyEvent.setOnClickListener{
            eventInfoFragment.fullScroll(View.FOCUS_DOWN);
        }


        //val fadeIn= AnimationUtils.loadAnimation(activity, R.anim.fade_in)
       // tvFragLocation.startAnimation(fadeIn)



        val locationBundle=Bundle()
        locationBundle.putString("latitude", bundle!!.get("latitude").toString())
        locationBundle.putString("longitude", bundle!!.get("longitude").toString())
        val eventLocationFragment=EventLocationFragment()
        eventLocationFragment.arguments=locationBundle

        btnShowMap.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.mainScreen, eventLocationFragment)?.addToBackStack(null)?.commit()


        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        return inflater.inflate(R.layout.fragment_event_info, container, false)
    }






}