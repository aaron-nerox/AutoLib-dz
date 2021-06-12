package com.clovertech.autolibdz

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
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
import kotlinx.android.synthetic.main.fragment_home.view.*
import model.Borne
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import utils.RetrofitInstance


class HomeFragment : Fragment() , OnMapReadyCallback , GoogleMap.OnMarkerClickListener , View.OnClickListener {

    private lateinit var googleMap: GoogleMap
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<*>
    lateinit var searchDialogPosition : Dialog
    lateinit var searchDialogPark : Dialog
    var xPosition : Float = 0.0f
    var yPosition : Float = 0.0f

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
        view.search_position_dialog.setOnClickListener(this)
        view.see_cars_btn.setOnClickListener(this)

        val mapFragment = childFragmentManager.findFragmentById(R.id.google_map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        bottomSheetBehavior = from(view.bottom_sheet_layout)
        bottomSheetBehavior.state = STATE_HIDDEN
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

        return view
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.search_position_dialog -> {
                if (search_position.visibility != View.VISIBLE){
                    bottomSheetBehavior.state = STATE_HIDDEN
                    search_position_dialog.x = xPosition
                    search_position_dialog.y = yPosition
                    search_position.visibility = View.VISIBLE
                    checked_position.visibility = View.VISIBLE
                    search_park_dialog.visibility = View.VISIBLE
                }
            }
            R.id.checked_position -> {
                moveSearchPositionDialog()
            }
            R.id.checked_park -> {
                search_park_dialog.visibility = View.GONE
                bottomSheetBehavior.state = STATE_COLLAPSED
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
            R.id.see_cars_btn -> {
                startActivity(Intent(context,FindYourCarActivity::class.java))
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

        val call = RetrofitInstance.borneApi.getBornes()
        call.enqueue(object: Callback<List<Borne>> {
            override fun onFailure(call: Call<List<Borne>>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(activity, t.toString(), Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<List<Borne>>, response: Response<List<Borne>>) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        val bornes = response.body()
                        bornes?.forEach { borne: Borne ->
                            val borneCoordinates = LatLng(borne.latitude.toString().toDouble(), borne.longitude.toString().toDouble())
                            googleMap.addMarker(
                                    MarkerOptions()
                                            .position(borneCoordinates)
                                            .title("Wilaya: ${borne.city}")
                            )
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(borneCoordinates, 5f))
                        }
                    }
                } else {
                    Toast.makeText(activity, response.code().toString(), Toast.LENGTH_LONG).show()
                }
            }
        })
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
        xPosition = search_position_dialog.x
        yPosition = search_position_dialog.y
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

}