package com.example.dotact

import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_admin.*
import kotlinx.android.synthetic.main.fragment_choose_location.*
import kotlinx.android.synthetic.main.fragment_choose_location_screen.*
import java.util.*


class ChooseLocationFragment : Fragment() {
    var latitude=0.0
    var longitude =0.0
    private val callback = OnMapReadyCallback { googleMap ->


        val osijek = LatLng(45.5550, 18.6955)

        googleMap.addMarker(MarkerOptions().position(osijek).draggable(true))

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(osijek, 15f))

        btnBackFromMapUpload.setOnClickListener {
            requireActivity().viewPager2UploadEvent.currentItem = requireActivity().viewPager2UploadEvent.currentItem-1
        }

        fabLocationChosenUpload.setOnClickListener {

        }

        googleMap.setOnMarkerDragListener(object : GoogleMap.OnMarkerDragListener {
            override fun onMarkerDragStart(marker: Marker) {
            }

            override fun onMarkerDragEnd(marker: Marker) {
                // etLatitudeCreate.text = marker.position.latitude.toString()
                //etLongitudeCreate.text= marker.position.longitude.toString()
                latitude = marker.position.latitude
                longitude = marker.position.longitude




                val geocoder = Geocoder(context, Locale.getDefault())
                val addresses: List<Address> = geocoder.getFromLocation(latitude, longitude, 1)
                if(addresses.isNotEmpty() && addresses[0].locality!=null && addresses[0].postalCode!=null) {
                    val address: String = addresses[0].getAddressLine(0)
                    val city: String = addresses[0].getLocality()

                    Log.d("adresa", address)

                    tvChosenMapLocationUpload.text = address
                    if(addresses[0].subThoroughfare!=null && addresses[0].thoroughfare!=null)
                    requireActivity().etLocationNameCreate.text = addresses[0].thoroughfare + " " + addresses[0].subThoroughfare
                    else if(addresses[0].featureName!=null) requireActivity().etLocationNameCreate.text = addresses[0].featureName
                    else requireActivity().etLocationNameCreate.text = city
                }
                requireActivity().fabLocationChosenUpload.setOnClickListener {
                    Log.d("lat", latitude.toString())
                    Log.d("lng", longitude.toString())
                    val tvLat = requireActivity().findViewById<View>(R.id.etLatitudeCreate) as TextView
                    tvLat.text = latitude.toString()
                    val tvLng = requireActivity().findViewById<View>(R.id.etLongitudeCreate) as TextView
                    tvLng.text = longitude.toString()
                    requireActivity().viewPager2UploadEvent.currentItem = requireActivity().viewPager2UploadEvent.currentItem+1
                }
            }

            override fun onMarkerDrag(marker: Marker) {
            }
        })
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_choose_location, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)


    }
}