package com.example.dotact
import android.R.attr.author
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.event_item.view.*
import kotlinx.android.synthetic.main.fragment_event_info.*
import kotlinx.android.synthetic.main.fragment_event_info.view.*


class EventRecyclerViewAdapter(private val events: ArrayList<Event>) : RecyclerView.Adapter<EventRecyclerViewAdapter.EventViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.event_item,
                parent, false)
        return EventViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {

        val currItem = events[position]
        var storageReference=FirebaseStorage.getInstance().reference


        holder.title.text=currItem.title
        holder.briefDescription.text = currItem.briefDescription
        holder.date.text = currItem.date
        GlideApp.with(holder.itemView).load(storageReference?.child("eventTitleImages/${currItem.titleImage}.jpg"))
            .placeholder(R.mipmap.no_title_image).override(holder.eventPicture.ivCardEventPicture.width).into(holder.eventPicture.ivCardEventPicture)
        holder.itemView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val activity = v!!.context as AppCompatActivity
                val eventInfoFragment = EventInfoFragment()

                val bundle = Bundle()

                bundle.putString("title", currItem.title)
                bundle.putString("time", currItem.time)
                bundle.putString("titleImage", currItem.titleImage)
                bundle.putString("fullDescription", currItem.fullDescription)
                bundle.putString("location", currItem.location)
                bundle.putString("date", currItem.date)
                bundle.putString("form", currItem.form)
                bundle.putString("latitude", currItem.latitude)
                bundle.putString("longitude", currItem.longitude)
                eventInfoFragment.arguments=bundle
                activity.tvRefreshIcon.visibility=GONE
                activity.tvRefreshIcon.isClickable=false
                activity.tvUpperMenu.visibility=GONE
                activity.tvLogoUpper.visibility=GONE
                activity.tvSignOut.visibility=GONE
                activity.tvSignOut.isClickable=false

                activity.supportFragmentManager.beginTransaction()
                        .replace(R.id.mainScreen, eventInfoFragment).addToBackStack(null).commit()


            }
        })
    }

    override fun getItemCount(): Int {

        return events.size
    }


    class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val title : TextView = itemView.findViewById(R.id.tvEventCardTitle)
        val briefDescription : TextView = itemView.findViewById(R.id.tvBriefDescription)
        val date: TextView = itemView.findViewById(R.id.tvEventDate)
        val eventPicture:ImageView=itemView.findViewById(R.id.ivCardEventPicture)

    }

}