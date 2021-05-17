package com.clovertech.autolibdz

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.bottom_sheet_presistant_park.*
import kotlinx.android.synthetic.main.bottom_sheet_presistant_park.view.*
import kotlinx.android.synthetic.main.custom_search_dialog_black.*
import kotlinx.android.synthetic.main.custom_search_dialog_black.view.*
import kotlinx.android.synthetic.main.custom_search_dialog_yello.*
import kotlinx.android.synthetic.main.custom_search_dialog_yello.view.*
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() , OnMapReadyCallback , GoogleMap.OnMarkerClickListener , View.OnClickListener {

    private lateinit var googleMap: GoogleMap
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<*>
    lateinit var searchDialogPosition : Dialog
    lateinit var searchDialogPark : Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        searchDialogPosition = Dialog(requireContext())
        searchDialogPark = Dialog(requireContext())

    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        view.checked_position.setOnClickListener(this)
        view.checked_park.setOnClickListener(this)
        view.search_position.setOnClickListener(this)
        view.search_park.setOnClickListener(this)

        val mapFragment = childFragmentManager.findFragmentById(R.id.google_map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        bottomSheetBehavior = BottomSheetBehavior.from(view.bottom_sheet_layout)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN

        return view
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.checked_position -> {
                moveSearchPositionDialog()
            }
            R.id.checked_park -> {
                moveSearchParkDialog()
                bottomSheetBehavior.addBottomSheetCallback(object : BottomSheetCallback() {
                    override fun onStateChanged(bottomSheet: View, newState: Int) {
                        if (newState == STATE_EXPANDED) {
                            activity?.bottom_bar?.visibility = View.GONE
                            images_container.visibility = View.VISIBLE
                            img_container.visibility = View.GONE
                            dots.visibility = View.VISIBLE
                        }
                        else if (newState == STATE_HIDDEN || newState == STATE_COLLAPSED) {
                            activity?.bottom_bar?.visibility = android.view.View.VISIBLE
                            images_container.visibility = View.GONE
                            img_container.visibility = View.VISIBLE
                            dots.visibility = View.GONE
                        }
                    }
                    override fun onSlide(bottomSheet: View, slideOffset: Float) {}
                })
            }
            R.id.search_position -> {
                searchDialogPosition.setContentView(R.layout.custom_search_dialog_position_expanded)
                searchDialogPosition.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
                searchDialogPosition.show()
            }
            R.id.search_park -> {
                searchDialogPark.setContentView(R.layout.custom_search_dialog_park_expanded)
                searchDialogPark.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
                searchDialogPark.show()
            }
        }
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        // Add a marker in Sydney and move the camera
        val algeria = LatLng(28.0268755, 1.6528399999999976)
        googleMap.addMarker(
                MarkerOptions()
                        .position(algeria)
                        .title("Algeria")
        )
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(algeria, 5f))
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        if (marker.isInfoWindowShown) {
            marker.hideInfoWindow()
        } else {
            marker.showInfoWindow()
        }
        return false
    }

    fun Float.toDips() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, resources.displayMetrics)

    private fun moveSearchPositionDialog(){
        var handler= Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                search_position_dialog.x = search_position_dialog.x + (search_position_dialog.width * 0.9).toFloat()
                search_position_dialog.y = (0).toFloat()
                checked_position.visibility = View.GONE
                search_position.visibility = View.GONE
            }
        }, 500)
    }

    private fun moveSearchParkDialog(){
        var handler= Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                search_park_dialog.x = search_park_dialog.x + (search_park_dialog.width * 0.88).toFloat()
                search_park_dialog.y = (400).toFloat()
                checked_park.visibility = View.GONE
                search_park.visibility = View.GONE
            }
        }, 500)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

}